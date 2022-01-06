package com.instacafe.moments.repository.user;

import com.instacafe.moments.repository.EntityRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.instacafe.moments.model.user.AppUser;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T extends AppUser> extends EntityRepository<T> {
    Optional<T> findByUsername(String username);

}
