package com.instacafe.moments.model.user.roles;

import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Client")
@Table(name = "client",
        uniqueConstraints = {
                @UniqueConstraint(name = "client_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "client_phone_unique", columnNames = "phone")
        }
)
public class Client extends AppUser {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "photo_sessions_client_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "photoSessions_client_id_fk"
            )
    )
    private List<PhotoSession> photoSessions;
}
