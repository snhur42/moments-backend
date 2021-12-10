package com.instacafe.moments.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private boolean success;
    private String userId;
    private String accessToken;
}