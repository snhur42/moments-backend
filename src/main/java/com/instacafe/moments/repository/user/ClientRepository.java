package com.instacafe.moments.repository.user;

import com.instacafe.moments.model.user.roles.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends UserRepository<Client> {
}