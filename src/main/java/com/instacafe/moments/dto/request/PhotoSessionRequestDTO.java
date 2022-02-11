package com.instacafe.moments.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PhotoSessionRequestDTO {
    private String managerId;
    private String clientId;
    private String photographerId;

    private String type;
    private String duration;
    private String location;
    private String city;

    private String willHappened;
    private String certificateId;
}
