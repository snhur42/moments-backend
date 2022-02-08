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
@Entity(name = "Manager")
@Table(name = "manager",
        uniqueConstraints = {
                @UniqueConstraint(name = "manager_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "manager_phone_unique", columnNames = "phone")

        }
)
public class Manager extends AppUser {

}
