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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
    public AppUser loadUserByUsername(String phone) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByPhone(phone);
        Optional<Manager> managerOptional = managerRepository.findByPhone(phone);
        Optional<Client> clientOptional = clientRepository.findByPhone(phone);
        Optional<Photographer> photographerOptional = photographerRepository.findByPhone(phone);

        if(adminOptional.isPresent()){
            return adminOptional.get();
        }else if(managerOptional.isPresent()){
            return managerOptional.get();
        }else if(clientOptional.isPresent()){
            return clientOptional.get();
        }else if(photographerOptional.isPresent()){
            return photographerOptional.get();
        }else {
            throw new UserNotFoundException("There is not User with this username/phone: " + phone);
        }
    }

    public AppUser loadUserByUserId(UUID id) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        Optional<Manager> managerOptional = managerRepository.findById(id);
        Optional<Client> clientOptional = clientRepository.findById(id);
        Optional<Photographer> photographerOptional = photographerRepository.findById(id);

        if(adminOptional.isPresent()){
            return adminOptional.get();
        }else if(managerOptional.isPresent()){
            return managerOptional.get();
        }else if(clientOptional.isPresent()){
            return clientOptional.get();
        }else if(photographerOptional.isPresent()){
            return photographerOptional.get();
        }else {
            throw new UserNotFoundException("There is not User with this id: " + id);
        }
    }

}
