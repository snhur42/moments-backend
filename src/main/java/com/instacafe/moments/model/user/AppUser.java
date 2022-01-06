package com.instacafe.moments.model.user;

import com.instacafe.moments.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AppUser extends AppUserDetails{
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "phone", nullable = false, unique = true, columnDefinition = "TEXT")
    private String phone;
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "city")
    private City city;
}
