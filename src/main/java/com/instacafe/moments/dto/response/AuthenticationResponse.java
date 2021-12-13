package com.instacafe.moments.dto.response;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private boolean success;
    private String userId;
    private String accessToken;
}