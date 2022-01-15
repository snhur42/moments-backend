package com.instacafe.moments.service.auth;

import com.instacafe.moments.dto.request.AuthenticationRequest;
import com.instacafe.moments.dto.response.AuthenticationResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceTest {
    private static AuthenticationRequest emptyAuthenticationRequest;
    private static AuthenticationRequest nullAuthenticationRequest;
    private static AuthenticationRequest wrongAuthenticationRequest;

    private static AuthenticationResponse falseAuthenticationResponse;

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationServiceTest(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @BeforeAll
    static void setAuthenticationRequestsAndResponse(){
        emptyAuthenticationRequest = new AuthenticationRequest("", "");
        wrongAuthenticationRequest = new AuthenticationRequest("test@gmail.com", "123456");
        nullAuthenticationRequest = new AuthenticationRequest(null, null);
        falseAuthenticationResponse = new AuthenticationResponse(false, null);
    }

    @Test
    void testAuthIfEmptyAuthenticationRequest() {
        AuthenticationResponse authenticationResponse = this.authenticationService.authenticate(emptyAuthenticationRequest);
        assertEquals(falseAuthenticationResponse, authenticationResponse);
    }

    @Test
    void testAuthIfNullAuthenticationRequest() {
        AuthenticationResponse authenticationResponse = this.authenticationService.authenticate(nullAuthenticationRequest);
        assertEquals(falseAuthenticationResponse, authenticationResponse);
    }


    @Test
    void testAuthIfWrongAuthenticationRequest() {
        AuthenticationResponse authenticationResponse = this.authenticationService.authenticate(wrongAuthenticationRequest);
        assertEquals(falseAuthenticationResponse, authenticationResponse);
    }
}