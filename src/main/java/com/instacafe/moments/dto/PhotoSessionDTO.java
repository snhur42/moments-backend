package com.instacafe.moments.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PhotoSessionDTO {
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
