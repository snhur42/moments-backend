package com.instacafe.moments.model.user.roles;

import com.instacafe.moments.model.user.AppUser;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Admin")
@Table(name = "admin",
        uniqueConstraints = {
                @UniqueConstraint(name = "admin_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "admin_phone_unique", columnNames = "phone")
        }
)
public class Admin extends AppUser {

}
