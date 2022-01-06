package com.instacafe.moments.repository.photo_session;

import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.repository.EntityRepository;
import org.springframework.stereotype.Repository;
import com.instacafe.moments.model.photo_session.PhotoSession;

import java.util.List;

@Repository
public interface PhotoSessionRepository extends EntityRepository<PhotoSession> {
    List<Photo> findAllByClient(Client client);
}
