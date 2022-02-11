package com.instacafe.moments.dto.response;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuthenticationResponseDTO {
    private boolean success;
    private String accessToken;
}