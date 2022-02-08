package com.instacafe.moments.service.auth;

import com.instacafe.moments.exception.model.UserNotFoundException;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.model.user.roles.Admin;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.repository.user.AdminRepository;
import com.instacafe.moments.repository.user.ClientRepository;
import com.instacafe.moments.repository.user.ManagerRepository;
import com.instacafe.moments.repository.user.PhotographerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    AdminRepository adminRepository;
    ManagerRepository managerRepository;
    ClientRepository clientRepository;
    PhotographerRepository photographerRepository;

    @Autowired
    public UserDetailsServiceImpl(AdminRepository adminRepository,
                                  ManagerRepository managerRepository,
                                  ClientRepository clientRepository,
                                  PhotographerRepository photographerRepository) {
        this.adminRepository = adminRepository;
        this.managerRepository = managerRepository;
        this.clientRepository = clientRepository;
        this.photographerRepository = photographerRepository;
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByEmail(username);
        Optional<Manager> managerOptional = managerRepository.findByEmail(username);
        Optional<Client> clientOptional = clientRepository.findByEmail(username);
        Optional<Photographer> photographerOptional = photographerRepository.findByEmail(username);

        if (adminOptional.isPresent()) {
            return adminOptional.get();
        } else if (managerOptional.isPresent()) {
            return managerOptional.get();
        } else if (clientOptional.isPresent()) {
            return clientOptional.get();
        } else if (photographerOptional.isPresent()) {
            return photographerOptional.get();
        } else {
            log.error("There is not User with this username: " + username);
            throw new UserNotFoundException("There is not User with this username: " + username);
        }
    }

    public AppUser loadUserByUserId(UUID id) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        Optional<Manager> managerOptional = managerRepository.findById(id);
        Optional<Client> clientOptional = clientRepository.findById(id);
        Optional<Photographer> photographerOptional = photographerRepository.findById(id);

        if (adminOptional.isPresent()) {
            return adminOptional.get();
        } else if (managerOptional.isPresent()) {
            return managerOptional.get();
        } else if (clientOptional.isPresent()) {
            return clientOptional.get();
        } else if (photographerOptional.isPresent()) {
            return photographerOptional.get();
        } else {
            log.error("There is not User with this id: " + id);
            throw new UserNotFoundException("There is not User with this id: " + id);
        }
    }

}
