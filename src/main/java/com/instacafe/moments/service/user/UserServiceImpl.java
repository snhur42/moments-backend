package com.instacafe.moments.service.user;

import com.instacafe.moments.dto.ClientDTO;
import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.exception.model.UserNotFoundException;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class UserServiceImpl<T extends AppUser, R extends UserRepository<T>> implements UserService<T> {
    protected final PasswordEncoder passwordEncoder;
    protected final R repository;


    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           R repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public T findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User was not found by this id: " + id));
    }

    @Override
    public List<T> saveAll(Iterable<T> ts) {
        return repository.saveAll(ts);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    protected T parseUserFromUserDTO(AppUser appUser, UserDTO userDTO) {
        appUser.setFirstName(userDTO.getFirstName());
        appUser.setLastName(userDTO.getLastName());
        appUser.setEmail(userDTO.getEmail());
        appUser.setPhone(userDTO.getPhone());
        appUser.setCity(userDTO.getCity());
        appUser.setRole(userDTO.getRole());
        appUser.setAccountNonExpired(true);
        appUser.setCredentialsNonExpired(true);
        appUser.setAccountNonLocked(true);
        appUser.setEnabled(true);
        return (T) appUser;
    }

    protected T updateUserFromUserDTO(String userId, UserDTO userDTO) {
        T t = repository.getById(UUID.fromString(userId));

        return repository.save(
                this.parseUserFromUserDTO(t, userDTO)
        );
    }

    protected AppUser createUserFromUserDTO(AppUser appUser, UserDTO userDTO) {
        String password = UUID.randomUUID().toString().substring(0,6);

        appUser.setPassword(passwordEncoder.encode(password));

        System.out.println("Password " + password);

        return repository.save(
                this.parseUserFromUserDTO(appUser, userDTO)
        );
    }

    protected <U extends AppUser> U changeUserEnableStatus(U u) {
        u.setAccountNonExpired(!u.isAccountNonExpired());
        u.setCredentialsNonExpired(!u.isCredentialsNonExpired());
        u.setAccountNonLocked(!u.isAccountNonLocked());
        u.setEnabled(!u.isEnabled());
        return u;
    }
}
