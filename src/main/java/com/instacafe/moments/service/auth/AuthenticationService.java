package com.instacafe.moments.service.auth;

import com.instacafe.moments.dto.request.AuthenticationRequest;
import com.instacafe.moments.dto.response.AuthenticationResponse;
import com.instacafe.moments.exception.controller.AuthenticateException;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.security.jwt.provider.impl.JwtAccessTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@Qualifier("authenticationService")
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtAccessTokenProvider jwtAccessTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtAccessTokenProvider = jwtAccessTokenProvider;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            AppUser user = userDetailsService.loadUserByUsername(request.getEmail());

            String accessTokenString = jwtAccessTokenProvider.createToken(
                    user.getId().toString(),
                    user.getRole().toString()
            );
            log.info("Login success: " + request.getEmail());
            return new AuthenticationResponse(true, accessTokenString);

        } catch (AuthenticationException e) {
            log.error("Invalid email/password combination " + request.getEmail());
            return new AuthenticationResponse(false, null);
        }
    }

    public void logout(HttpServletResponse response, HttpServletRequest request) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);

    }
}
