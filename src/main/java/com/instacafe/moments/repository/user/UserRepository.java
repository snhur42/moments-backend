package com.instacafe.moments.repository.user;

import com.instacafe.moments.model.enums.Role;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.repository.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends EntityRepository<AppUser> {
    Optional<AppUser> findByEmail(String email);

    List<AppUser> findAllByRole(Role role);

    Optional<AppUser> findByIdAndRole(UUID appUserId, Role role);
}
