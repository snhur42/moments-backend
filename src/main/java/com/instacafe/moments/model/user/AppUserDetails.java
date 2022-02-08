package com.instacafe.moments.model.user;

import com.instacafe.moments.model.AbstractEntity;
import com.instacafe.moments.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AppUserDetails extends AbstractEntity implements UserDetails {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "account_non_expired", columnDefinition = "BOOLEAN default TRUE", nullable = false)
    private boolean accountNonExpired;
    @Column(name = "account_non_locked", columnDefinition = "BOOLEAN default TRUE", nullable = false)
    private boolean accountNonLocked;
    @Column(name = "credentials_non_expired", columnDefinition = "BOOLEAN default TRUE", nullable = false)
    private boolean credentialsNonExpired;
    @Column(name = "enabled", columnDefinition = "BOOLEAN default TRUE", nullable = false)
    private boolean enabled;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole().getAuthorities();
    }
}
