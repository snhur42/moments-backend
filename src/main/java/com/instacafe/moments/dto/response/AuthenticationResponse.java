package com.instacafe.moments.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private boolean success;
    private String accessToken;
}