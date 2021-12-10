package com.instacafe.moments.service.user;

import com.instacafe.moments.exception.model.UserNotFoundException;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.repository.user.UserRepository;
import com.instacafe.moments.service.EntityServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public abstract class UserServiceImpl<T extends AppUser, R extends UserRepository<T>> extends EntityServiceImpl<T, R> {
    @Value("${auth.jwt.noJwtTokenName}")
    private String noJwtTokenName;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(R repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public T save(T t) {
        t.setAccountNonExpired(true);
        t.setCredentialsNonExpired(true);
        t.setAccountNonLocked(true);
        t.setEnabled(true);
        t.setPassword(passwordEncoder.encode(t.getPassword()));
        return repository.save(t);
    }

    @Override
    public T findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User was not found by this id: " + id));
    }

}
