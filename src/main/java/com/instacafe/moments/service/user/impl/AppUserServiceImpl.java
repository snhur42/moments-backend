package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.request.AppUserRequestDTO;
import com.instacafe.moments.model.enums.Role;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.repository.user.UserRepository;
import com.instacafe.moments.service.user.AppUserService;
import com.instacafe.moments.service.utils.GmailSender;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@Qualifier("appUserServiceImpl")
public class AppUserServiceImpl implements AppUserService {
    protected final PasswordEncoder passwordEncoder;
    protected final UserRepository repository;
    protected final GmailSender gmailSender;


    public AppUserServiceImpl(PasswordEncoder passwordEncoder,
                              GmailSender gmailSender,
                              UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.gmailSender = gmailSender;
    }

    @Override
    public boolean save(AppUserRequestDTO appUserRequestDTO) {
        AppUser appUser = new AppUser();
        appUser = this.parseAppUserFromAppUserDTO(appUserRequestDTO, appUser);
        repository.save(appUser);
//        this.gmailSender.sendEmailToClientAboutRegistration(appUser);
        return true;
    }

    @Override
    public AppUser update(String userId, AppUserRequestDTO appUserRequestDTO) {
        AppUser appUser = repository.getById(UUID.fromString(userId));
        appUser = this.parseAppUserFromAppUserDTO(appUserRequestDTO, appUser);

        return repository.save(appUser);
    }

    public boolean resetUserPassword(String userEmail){
        Optional<AppUser> appUserOptional = repository.findByEmail(userEmail);

        if(appUserOptional.isEmpty()){
            return false;
        } else {
            String newPassword = UUID.randomUUID().toString().substring(0,6);
            AppUser appUser = appUserOptional.get();
            appUser.setPassword(passwordEncoder.encode(newPassword));
            repository.save(appUser);

            this.gmailSender.sendNewPassword(appUser, newPassword);

            return true;
        }
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


    protected AppUser parseAppUserFromAppUserDTO(AppUserRequestDTO userDTO, AppUser appUser) {
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
