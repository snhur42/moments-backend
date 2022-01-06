package com.instacafe.moments.rest;


import com.instacafe.moments.dto.request.AuthenticationRequest;
import com.instacafe.moments.dto.request.LogoutRequest;
import com.instacafe.moments.dto.request.RefreshTokenRequest;
import com.instacafe.moments.dto.response.AuthenticationResponse;
import com.instacafe.moments.service.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authenticate(HttpServletResponse response, @RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.authenticate(response, request), HttpStatus.OK);
    }

    @PostMapping("refresh_token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletResponse response, HttpServletRequest request, @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ResponseEntity<>(authenticationService.refreshToken(response, refreshTokenRequest), HttpStatus.OK);
    }

    @PostMapping("logout")
    public void logout(@RequestBody LogoutRequest logoutRequest, HttpServletResponse response, HttpServletRequest request) {
        authenticationService.logout(logoutRequest, response, request);
    }

}
