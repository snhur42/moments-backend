package com.instacafe.moments.repository.user;

import com.instacafe.moments.model.user.roles.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}