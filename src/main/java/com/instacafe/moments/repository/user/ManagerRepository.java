package com.instacafe.moments.repository.user;

import com.instacafe.moments.model.user.roles.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends UserRepository<Manager> {
}