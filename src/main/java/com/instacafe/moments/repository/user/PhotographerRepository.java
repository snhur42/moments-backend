package com.instacafe.moments.repository.user;

import com.instacafe.moments.model.user.roles.Photographer;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends UserRepository<Photographer> {

}