package com.instacafe.moments.rest;


import com.instacafe.moments.dto.ClientDTO;
import com.instacafe.moments.dto.request.AuthenticationRequest;
import com.instacafe.moments.dto.request.LogoutRequest;
import com.instacafe.moments.dto.response.AuthenticationResponse;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.service.auth.AuthenticationService;
import com.instacafe.moments.service.user.impl.AppUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {
    private final AppUserServiceImpl appUserService;

    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService, AppUserServiceImpl appUserService) {
        this.authenticationService = authenticationService;
        this.appUserService = appUserService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authenticate(HttpServletResponse response, @RequestBody AuthenticationRequest request) {
        log.info("Login: " + request.getEmail());
        return authenticationService.authenticate(response, request);
    }

    @PostMapping("refresh_token")
    public ResponseEntity<AuthenticationResponse> updateRefreshToken(@CookieValue(name = "refreshToken", defaultValue = "No cookies") String refreshToken, HttpServletResponse response) {
        return authenticationService.refreshToken(refreshToken, response);
    }

    @PostMapping("logout")
    public void logout(HttpServletResponse response, HttpServletRequest request, @RequestBody LogoutRequest logoutRequest) {
        log.info("Logout : " + logoutRequest.getUserId() + " " + logoutRequest.getFingerPrint());
        log.debug("Logout : " + logoutRequest.getUserId() + " " + logoutRequest.getFingerPrint());
        authenticationService.logout(response, request, logoutRequest);
    }


    @PostMapping("create_client")
    public ResponseEntity<AppUser> createClient(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(appUserService.save(clientDTO), HttpStatus.CREATED);
    }
}
