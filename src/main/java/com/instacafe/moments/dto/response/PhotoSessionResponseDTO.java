package com.instacafe.moments.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PhotoSessionResponseDTO extends AbstractEntityDTO {
    private final String managerId;
    private final String clientId;
    private final String photographerId;
    private final List<String> allPhotos;
    private final List<String> finalPhotos;
    private final String chatId;
    private final String photoSessionType;
    private final String status;
    private final String duration;
    private final String location;
    private final String price;
    private final String certificateId;
    private final String brief;
    private final String willHappenAt;

    public PhotoSessionResponseDTO(String id,
                                   LocalDateTime created,
                                   LocalDateTime modified,
                                   String managerId,
                                   String clientId,
                                   String photographerId,
                                   List<String> allPhotos,
                                   List<String> finalPhotos,
                                   String chatId,
                                   String photoSessionType,
                                   String status,
                                   String duration,
                                   String location,
                                   String price,
                                   String certificateId,
                                   String brief,
                                   String willHappenAt) {
        super(id, created, modified);
        this.managerId = managerId;
        this.clientId = clientId;
        this.photographerId = photographerId;
        this.allPhotos = allPhotos;
        this.finalPhotos = finalPhotos;
        this.chatId = chatId;
        this.photoSessionType = photoSessionType;
        this.status = status;
        this.duration = duration;
        this.location = location;
        this.price = price;
        this.certificateId = certificateId;
        this.brief = brief;
        this.willHappenAt = willHappenAt;
    }
}
