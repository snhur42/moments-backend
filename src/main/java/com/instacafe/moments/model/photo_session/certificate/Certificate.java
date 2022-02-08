package com.instacafe.moments.model.photo_session.certificate;

import com.instacafe.moments.model.AbstractEntity;
import com.instacafe.moments.model.photo_session.PhotoSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Certificate")
@Table(name = "certificate")
public class Certificate extends AbstractEntity {
    @Column(name = "text", columnDefinition = "TEXT", nullable = false)
    private String certificateNumber;

    @OneToOne(mappedBy = "certificate")
    private PhotoSession photoSession;
}
