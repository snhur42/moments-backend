package com.instacafe.moments.security.jwt.provider.impl;

import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.security.jwt.provider.JwtTokenProvider;
import com.instacafe.moments.service.auth.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
public abstract class JwtTokenProviderImpl implements JwtTokenProvider {

    private final UserDetailsServiceImpl userDetailsService;
    @Value("${auth.header}")
    private String authorizationHeader;
    private String secretKey;
    private final long validityInMilliseconds;

    public JwtTokenProviderImpl(UserDetailsServiceImpl userDetailsService,
                                String secretKey,
                                long validityInMilliseconds) {
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public String createToken(String userId, String username, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);


        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", userId);
        claims.put("expiredDate", validity);


        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {

       try {
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token);
                return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    @Override
    public Authentication getAuthentication(String token) {

        AppUser appUser = this.userDetailsService.loadUserByUsername(getUsername(token));

        return new UsernamePasswordAuthenticationToken(appUser, appUser.getAuthorities(), appUser.getAuthorities());
    }

    @Override
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public Date getExpiredDate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody().getExpiration();
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }
}
