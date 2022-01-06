package com.instacafe.moments.dto.request;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
    private String fingerPrint;
}
