package com.instacafe.moments.model.user;

import com.instacafe.moments.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AppUser")
@Table(name = "app_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "user_phone_unique", columnNames = "phone")
        }
)
public class AppUser extends AppUserDetails {
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "phone", nullable = false, unique = true, columnDefinition = "TEXT")
    private String phone;
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "city", nullable = false)
    private City city;


    @Override
    public String getUsername() {
        return getEmail();
    }
}
