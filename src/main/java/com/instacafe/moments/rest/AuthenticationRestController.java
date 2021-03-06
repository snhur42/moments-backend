package com.instacafe.moments.rest;


import com.instacafe.moments.dto.request.AuthenticationRequestDTO;
import com.instacafe.moments.dto.request.ClientRequestDTO;
import com.instacafe.moments.dto.request.LogoutRequestDTO;
import com.instacafe.moments.dto.request.ResetPasswordRequestDTO;
import com.instacafe.moments.dto.response.AuthAccessJwtResponseDTO;
import com.instacafe.moments.dto.response.AuthRefreshJwtResponseDTO;
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


    @GetMapping("get_user/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable String userId) {
        return new ResponseEntity<>(appUserService.findById(userId), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthAccessJwtResponseDTO> authenticate(HttpServletResponse response, @RequestBody AuthenticationRequestDTO request) {
        log.info("Login: " + request.getEmail());
        return authenticationService.authenticate(response, request);
    }

    @PostMapping("refresh_token")
    public ResponseEntity<AuthRefreshJwtResponseDTO> updateRefreshToken(@CookieValue(name = "refreshToken", defaultValue = "No cookies") String refreshToken, HttpServletResponse response) {
        return authenticationService.refreshToken(refreshToken, response);
    }

    @PostMapping("logout")
    public void logout(HttpServletResponse response, HttpServletRequest request, @RequestBody LogoutRequestDTO logoutRequestDTO) {
        log.info("Logout : " + logoutRequestDTO.getUserId() + " " + logoutRequestDTO.getFingerPrint());
        log.debug("Logout : " + logoutRequestDTO.getUserId() + " " + logoutRequestDTO.getFingerPrint());
        authenticationService.logout(response, request, logoutRequestDTO);
    }


    @PostMapping("create_client")
    public ResponseEntity<Boolean> createClient(@RequestBody ClientRequestDTO clientDTO) {
        return new ResponseEntity<>(appUserService.save(clientDTO), HttpStatus.CREATED);
    }


    @PostMapping("reset_password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        log.info("Reset password for " + resetPasswordRequestDTO.getEmail());
        return new ResponseEntity<>(appUserService.resetUserPassword(resetPasswordRequestDTO.getEmail()), HttpStatus.CREATED);
    }
}
