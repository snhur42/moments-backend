package com.instacafe.moments.service.auth;

import com.instacafe.moments.dto.request.AuthenticationRequest;
import com.instacafe.moments.dto.request.LogoutRequest;
import com.instacafe.moments.dto.response.AuthenticationResponse;
import com.instacafe.moments.model.refresh_token.RefreshToken;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.security.jwt.provider.impl.JwtAccessTokenProvider;
import com.instacafe.moments.security.jwt.provider.impl.JwtRefreshTokenProvider;
import com.instacafe.moments.service.auth.refresh_token.RefreshTokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@Qualifier("authenticationService")
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final RefreshTokenServiceImpl refreshTokenServiceImpl;
    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final JwtRefreshTokenProvider jwtRefreshTokenProvider;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserDetailsServiceImpl userDetailsService,
                                 RefreshTokenServiceImpl refreshTokenServiceImpl,
                                 JwtAccessTokenProvider jwtAccessTokenProvider,
                                 JwtRefreshTokenProvider jwtRefreshTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.refreshTokenServiceImpl = refreshTokenServiceImpl;
        this.jwtAccessTokenProvider = jwtAccessTokenProvider;
        this.jwtRefreshTokenProvider = jwtRefreshTokenProvider;
    }

    public ResponseEntity<AuthenticationResponse> authenticate(HttpServletResponse response, AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            AppUser user = userDetailsService.loadUserByUsername(request.getEmail());

            String accessTokenString = jwtAccessTokenProvider.createToken(
                    user.getId().toString(),
                    user.getRole().toString()
            );

            String refreshTokenString = jwtRefreshTokenProvider.createToken(
                    user.getId().toString(),
                    user.getRole().toString()
            );

            this.countRefreshTokens(user.getId().toString());

            RefreshToken refreshTokenObj = this.saveRefreshToken(user.getId().toString(), refreshTokenString, request.getFingerPrint(), jwtRefreshTokenProvider.getExpiredDate(refreshTokenString));


            ResponseCookie resCookie = ResponseCookie.from("refreshToken", refreshTokenObj.getId().toString())
                    .httpOnly(true)
                    .secure(true)
                    .path("/api/auth")
                    .maxAge(30 * 24 * 60 * 60)
                    .domain("localhost")
                    .build();


            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(HttpHeaders.SET_COOKIE, resCookie.toString());

            log.info("Login success: " + request.getEmail());

            return ResponseEntity
                    .ok()
                    .headers(responseHeaders)
                    .body(new AuthenticationResponse(true, accessTokenString));


        } catch (AuthenticationException e) {
            log.error("Invalid email/password combination " + request.getEmail());
            return ResponseEntity
                    .ok()
                    .body(new AuthenticationResponse(false, null));
        }
    }


    public ResponseEntity<AuthenticationResponse> refreshToken(String refreshTokenId, HttpServletResponse response) {
        try {
            RefreshToken refreshToken = refreshTokenServiceImpl.findById(UUID.fromString(refreshTokenId));

            if(jwtRefreshTokenProvider.IsExpired(refreshToken.getRefreshToken())){
                AppUser user = userDetailsService.loadUserByUserId(UUID.fromString(refreshToken.getUserId()));

                String accessTokenString = jwtAccessTokenProvider.createToken(
                        user.getId().toString(),
                        user.getRole().toString()
                );

                String refreshTokenString = jwtRefreshTokenProvider.createToken(
                        user.getId().toString(),
                        user.getRole().toString()
                );

                String fingerPrint = refreshToken.getFingerPrint();
                refreshTokenServiceImpl.delete(refreshToken);

                RefreshToken refreshTokenObj = this.saveRefreshToken(user.getId().toString(), refreshTokenString, fingerPrint, jwtRefreshTokenProvider.getExpiredDate(refreshTokenString));

                ResponseCookie resCookie = ResponseCookie.from("refreshToken", refreshTokenObj.getId().toString())
                        .httpOnly(true)
                        .secure(true)
                        .path("/api/auth")
                        .maxAge(30 * 24 * 60 * 60)
                        .domain("localhost")
                        .build();


                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set(HttpHeaders.SET_COOKIE, resCookie.toString());

                log.info("New refresh token: " + refreshTokenString);

                return ResponseEntity
                        .ok()
                        .headers(responseHeaders)
                        .body(new AuthenticationResponse(true, accessTokenString));
            }else {
                return ResponseEntity
                        .ok()
                        .body(new AuthenticationResponse(false, null));
            }




        } catch (AuthenticationException e) {
            return ResponseEntity
                    .ok()
                    .body(new AuthenticationResponse(false, null));
        }



    }

    public void logout(HttpServletResponse response, HttpServletRequest request, LogoutRequest logoutRequest) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        try {
            refreshTokenServiceImpl.delete(
                    refreshTokenServiceImpl.findByUserIdAndFingerPrint(
                            logoutRequest.getUserId(),
                            logoutRequest.getFingerPrint()
                    )
            );
        }catch (Exception err) {
            log.error("logout error " + err.getMessage());
        }

    }

    private void countRefreshTokens(String userId){
        long countRefreshToken = refreshTokenServiceImpl.findAll().stream().filter(token -> token.getUserId().equals(userId)).count();

        if (countRefreshToken >= 4) {
            refreshTokenServiceImpl.deleteAllByUserId(userId);
        }
    }

    private RefreshToken saveRefreshToken(String userId, String refreshTokenString, String fingerPrint, Date expiredDate){
        return refreshTokenServiceImpl.save(new RefreshToken(
                userId,
                refreshTokenString,
                fingerPrint,
                expiredDate
        ));
    }

}
