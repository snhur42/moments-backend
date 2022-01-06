package com.instacafe.moments.service.auth;

import com.instacafe.moments.dto.request.AuthenticationRequest;
import com.instacafe.moments.dto.request.LogoutRequest;
import com.instacafe.moments.dto.request.RefreshTokenRequest;
import com.instacafe.moments.dto.response.AuthenticationResponse;
import com.instacafe.moments.exception.controller.AuthenticateException;
import com.instacafe.moments.exception.controller.TokenRefreshException;
import com.instacafe.moments.model.refresh_token.RefreshToken;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.security.jwt.provider.impl.JwtAccessTokenProvider;
import com.instacafe.moments.security.jwt.provider.impl.JwtRefreshTokenProvider;
import com.instacafe.moments.service.auth.refresh_token.RefreshTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Service
@Qualifier("authenticationService")
public record AuthenticationService(
        AuthenticationManager authenticationManager,
        UserDetailsServiceImpl userDetailsService,
        RefreshTokenServiceImpl refreshTokenServiceImpl,
        JwtAccessTokenProvider jwtAccessTokenProvider,
        JwtRefreshTokenProvider jwtRefreshTokenProvider) {
    @Autowired
    public AuthenticationService {
    }

    public AuthenticationResponse authenticate(HttpServletResponse response, AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            AppUser user = userDetailsService.loadUserByUsername(request.getUsername());

            String accessTokenString = jwtAccessTokenProvider.createToken(user.getId().toString(), request.getUsername(), user.getRole().name());
            String refreshTokenString = jwtRefreshTokenProvider.createToken(user.getId().toString(), request.getUsername(), user.getRole().name());

            long countRefreshToken = refreshTokenServiceImpl.findAll().stream().filter(token -> token.getUserId().equals(user.getId().toString())).count();

            if (countRefreshToken >= 3) {
                refreshTokenServiceImpl.deleteAllByUserId(user.getId().toString());
            }

            RefreshToken refreshTokenObj = refreshTokenServiceImpl.save(new RefreshToken(
                    user.getId().toString(),
                    refreshTokenString,
                    request.getFingerPrint(),
                    jwtRefreshTokenProvider.getExpiredDate(refreshTokenString)
            ));

            setCookieToResponse(response, refreshTokenObj.getId().toString());

            return new AuthenticationResponse(true,
                    user.getId().toString(),
                    user.getRole().name(),
                    accessTokenString,
                    jwtAccessTokenProvider.getExpiredDate(accessTokenString));

        } catch (AuthenticationException e) {
            throw new AuthenticateException("Invalid email/password combination");
        }
    }

    public AuthenticationResponse refreshToken(HttpServletResponse response, RefreshTokenRequest refreshTokenRequest) {
        try {
            RefreshToken refreshToken = refreshTokenServiceImpl.findById(refreshTokenRequest.getRefreshTokenId());

            boolean isRefreshTokenValid = jwtRefreshTokenProvider.validateToken(refreshToken.getRefreshToken());
            boolean isRefreshTokenHasTheSameUserId = refreshTokenRequest.getUserId().equals(UUID.fromString(refreshToken.getUserId()));
            boolean isExpired = refreshToken.getExpiresIn().after(new Date());
            boolean isFingerprintValid = refreshToken.getFingerPrint().equals(refreshTokenRequest.getFingerPrint());

            if (isRefreshTokenValid && isExpired && isFingerprintValid && isRefreshTokenHasTheSameUserId) {

                AppUser user = userDetailsService.loadUserByUserId(UUID.fromString(refreshToken.getUserId()));

                String accessTokenString = jwtAccessTokenProvider.createToken(user.getId().toString(), user.getUsername(), user.getRole().name());
                String refreshTokenString = jwtRefreshTokenProvider.createToken(user.getId().toString(), user.getUsername(), user.getRole().name());

                long countRefreshToken = refreshTokenServiceImpl.findAll().stream().filter(token -> token.getUserId().equals(refreshToken.getUserId())).count();

                if (countRefreshToken > 5) {
                    refreshTokenServiceImpl.deleteAllByUserId(refreshToken.getUserId());
                } else {
                    refreshTokenServiceImpl.delete(refreshToken);
                }

                RefreshToken refreshTokenObj = refreshTokenServiceImpl.save(new RefreshToken(
                        user.getId().toString(),
                        refreshTokenString,
                        refreshTokenRequest.getFingerPrint(),
                        jwtRefreshTokenProvider.getExpiredDate(refreshTokenString)
                ));

                setCookieToResponse(response, refreshTokenObj.getId().toString());

                return new AuthenticationResponse(true, user.getId().toString(), user.getRole().name(), accessTokenString, jwtAccessTokenProvider.getExpiredDate(accessTokenString));
            } else {
                refreshTokenServiceImpl.delete(refreshToken);
            }
            return new AuthenticationResponse(false, null, null, null, null);
        } catch (AuthenticationException e) {
            throw new TokenRefreshException("Invalid refreshToken");
        }
    }

    public void logout(LogoutRequest logoutRequest, HttpServletResponse response, HttpServletRequest request) {
//        this.deleteCookie(request);

        refreshTokenServiceImpl.delete(
                refreshTokenServiceImpl.findByUserIdAndFingerPrint(
                        logoutRequest.getUserId(),
                        logoutRequest.getFingerPrint()
                )
        );

        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    private void setCookieToResponse(HttpServletResponse response, String userId) {
        Cookie cookie = new Cookie("refreshToken", userId);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    private void deleteCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if(cookies.length > 0 ){
            Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("refreshToken"))
                    .forEach(cookie -> cookie.setMaxAge(0));
        }



    }
}
