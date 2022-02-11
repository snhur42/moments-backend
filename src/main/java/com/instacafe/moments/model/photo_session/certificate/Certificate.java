package com.instacafe.moments.model.photo_session.certificate;

import com.instacafe.moments.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Certificate")
@Table(name = "certificate")
public class Certificate extends AbstractEntity {
    @Column(name = "certificate_number", columnDefinition = "TEXT", nullable = false)
    private String certificateNumber;
}
