package com.instacafe.moments.model.photo_session;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.instacafe.moments.model.AbstractEntity;
import com.instacafe.moments.model.enums.City;
import com.instacafe.moments.model.enums.Duration;
import com.instacafe.moments.model.enums.PhotoSessionStatus;
import com.instacafe.moments.model.enums.PhotoSessionType;
import com.instacafe.moments.model.photo_session.brief.Brief;
import com.instacafe.moments.model.photo_session.certificate.Certificate;
import com.instacafe.moments.model.photo_session.chat.Chat;
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
            name = "all_photos_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "all_photos_fk"
            )
    )
    private List<Photo> allPhoto;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "final_photos_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "final_photos_fk"
            )
    )
    private List<Photo> finalPhoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "photo_session_status", columnDefinition = "TEXT")
    private PhotoSessionStatus photoSessionStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "chat_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "chat_id_fk"
            )
    )
    private Chat chat;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "photo_session_type")
    private PhotoSessionType photoSessionType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "duration")
    private Duration duration;

    @Positive(message = "price must be positive")
    @Column(name = "price", updatable = true, nullable = false, columnDefinition = "SMALLINT")
    private int price;

    @Column(name = "location", updatable = true, nullable = true, columnDefinition = "TEXT")
    private String location;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime willHappenAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "city")
    private City city;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "certificate_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "certificate_id_fk"
            )
    )
    private Certificate certificate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "brief_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "brief_id_fk"
            )
    )
    private Brief brief;
}
