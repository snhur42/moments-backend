package com.instacafe.moments.service.user;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.exception.model.UserNotFoundException;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class UserServiceImpl<T extends AppUser, R extends UserRepository<T>> implements UserService<T> {
    protected final PasswordEncoder passwordEncoder;
    protected final R repository;

    public UserServiceImpl(R repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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

    protected T createUserFromUserDTO(T t, UserDTO userDTO) {
        t.setFirstName(userDTO.getFirstName());
        t.setLastName(userDTO.getLastName());
        t.setEmail(userDTO.getEmail());
        t.setPhone(userDTO.getPhone());
        t.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        t.setCity(userDTO.getCity());
        t.setAccountNonExpired(true);
        t.setCredentialsNonExpired(true);
        t.setAccountNonLocked(true);
        t.setEnabled(true);
        return t;
    }

    protected T updateUserFromUserDTO(UserDTO userDTO) {
        T t = repository.getById(UUID.fromString(userDTO.getUserId()));

        return repository.save(
                this.createUserFromUserDTO(t, userDTO)
        );
    }

}
