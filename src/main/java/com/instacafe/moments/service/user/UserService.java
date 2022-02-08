package com.instacafe.moments.service.user;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.user.AppUser;

import java.util.List;
import java.util.UUID;

public interface UserService<T extends AppUser> {
    T save(UserDTO userDTO);

    T update(String userId, UserDTO userDTO);

    List<T> saveAll(Iterable<T> ts);

    T findById(UUID id);

    List<T> findAll();

    void deleteById(UUID id);
}
