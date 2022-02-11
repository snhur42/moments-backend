package com.instacafe.moments.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CertificateResponseDTO extends AbstractEntityDTO {
    private String certificateNumber;

    public CertificateResponseDTO(String id, LocalDateTime created, LocalDateTime modified, String certificateNumber) {
        super(id, created, modified);
        this.certificateNumber = certificateNumber;
    }
}
