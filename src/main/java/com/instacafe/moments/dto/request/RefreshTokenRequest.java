package com.instacafe.moments.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    private UUID userId;
    private String fingerPrint;
}
