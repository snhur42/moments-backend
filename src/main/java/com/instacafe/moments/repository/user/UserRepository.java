package com.instacafe.moments.repository.user;

import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.repository.EntityRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T extends AppUser> extends EntityRepository<T> {
    Optional<T> findByEmail(String email);

}
