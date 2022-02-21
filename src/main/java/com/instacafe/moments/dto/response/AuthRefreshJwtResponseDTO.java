package com.instacafe.moments.dto.response;

import lombok.*;
import org.apache.catalina.User;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuthRefreshJwtResponseDTO {
    private boolean success;
    private String accessToken;
}