package com.instacafe.moments.model.photo_session;

import com.instacafe.moments.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Certificate")
@Table(name = "certificate")
public class Certificate extends AbstractEntity {
    @Column(name = "text", columnDefinition = "TEXT")
    private String certificateNumber;

    @OneToOne(mappedBy = "certificate")
    private PhotoSession photoSession;
}
