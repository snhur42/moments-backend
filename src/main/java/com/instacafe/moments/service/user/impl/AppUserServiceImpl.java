package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.AppUserDTO;
import com.instacafe.moments.model.enums.Role;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.repository.user.UserRepository;
import com.instacafe.moments.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Qualifier("appUserServiceImpl")
public class AppUserServiceImpl implements AppUserService {
    protected final PasswordEncoder passwordEncoder;
    protected final UserRepository repository;


    public AppUserServiceImpl(PasswordEncoder passwordEncoder,
                              UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public AppUser save(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();

        appUser = this.parseAppUserFromAppUserDTO(appUserDTO, appUser);

        return repository.save(appUser);
    }

    @Override
    public AppUser update(String userId, AppUserDTO appUserDTO) {
        AppUser appUser = repository.getById(UUID.fromString(userId));
        appUser = this.parseAppUserFromAppUserDTO(appUserDTO, appUser);
        return repository.save(appUser);
    }


    @Override
    public AppUser findById(String appUserId) {
        return this.repository.findById(UUID.fromString(appUserId)).get();
    }

    @Override
    public List<AppUser> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void deleteById(String appUserId) {
        this.repository.deleteById(UUID.fromString(appUserId));
    }

    @Override
    public AppUser findByIdAndRole(String appUserId, Role role) {
        return this.repository.findByIdAndRole(UUID.fromString(appUserId), role).get();
    }

    @Override
    public List<AppUser> findAllByRole(Role role) {
        return this.repository.findAllByRole(role);
    }

    public void changeUserEnableStatus(String userId) {
        AppUser appUser = repository.getById(UUID.fromString(userId));
        appUser.setAccountNonExpired(!appUser.isAccountNonExpired());
        appUser.setCredentialsNonExpired(!appUser.isCredentialsNonExpired());
        appUser.setAccountNonLocked(!appUser.isAccountNonLocked());
        appUser.setEnabled(!appUser.isEnabled());
    }


    protected AppUser parseAppUserFromAppUserDTO(AppUserDTO userDTO, AppUser appUser) {
        appUser.setFirstName(userDTO.getFirstName());
        appUser.setLastName(userDTO.getLastName());
        appUser.setEmail(userDTO.getEmail());
        appUser.setPhone(userDTO.getPhone());
        appUser.setCity(userDTO.getCity());
        appUser.setRole(userDTO.getRole());
        appUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        appUser.setAccountNonExpired(true);
        appUser.setCredentialsNonExpired(true);
        appUser.setAccountNonLocked(true);
        appUser.setEnabled(true);

        return appUser;
    }
}
