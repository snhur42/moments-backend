package com.instacafe.moments.model.photo_session;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.instacafe.moments.model.AbstractEntity;
import com.instacafe.moments.model.enums.City;
import com.instacafe.moments.model.enums.Duration;
import com.instacafe.moments.model.enums.PhotoSessionStatus;
import com.instacafe.moments.model.enums.PhotoSessionType;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PhotoSession")
@Table(name = "photo_session")
public class PhotoSession extends AbstractEntity {
    @Column(name = "link_to_all_photos", unique = true, columnDefinition = "TEXT")
    private String linkToAllPhotos;

    @Column(name = "link_to_final_photos", unique = true, columnDefinition = "TEXT")
    private String linkToFinalPhotos;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "manager_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "manager_id_fk"
            )
    )
    private Manager manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "photographer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "photographer_id_fk"
            )
    )
    private Photographer photographer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "client_id_fk"
            )
    )
    private Client client;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "message_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "message_id_fk"
            )
    )
    private List<Message> message;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "brief_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "brief_id_fk"
            )
    )
    private Brief brief;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "duration")
    private Duration duration;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "city")
    private City city;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "photo_session_type")
    private PhotoSessionType photoSessionType;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime willHappenAt;

    @Positive(message = "price must be positive")
    @Column(name = "price", updatable = true, nullable = false, columnDefinition = "SMALLINT")
    private int price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "certificate_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "certificate_id_fk"
            )
    )
    private Certificate certificate;


    @Enumerated(EnumType.STRING)
    @Column(name = "photo_session_status", columnDefinition = "TEXT")
    private PhotoSessionStatus photoSessionStatus;
}
