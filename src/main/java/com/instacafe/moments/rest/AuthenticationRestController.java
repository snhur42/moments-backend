package com.instacafe.moments.rest;


import com.instacafe.moments.dto.ClientDTO;
import com.instacafe.moments.dto.request.AuthenticationRequest;
import com.instacafe.moments.dto.response.AuthenticationResponse;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.service.auth.AuthenticationService;
import com.instacafe.moments.service.user.impl.ClientService;
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

    private final AuthenticationService authenticationService;
    private final ClientService clientService;

    public AuthenticationRestController(AuthenticationService authenticationService, ClientService clientService) {
        this.authenticationService = authenticationService;
        this.clientService = clientService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("Login: " + request.getEmail());
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }


    @PostMapping("create_client")
    public ResponseEntity<Client> createManager(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.save(clientDTO), HttpStatus.CREATED);
    }
}
