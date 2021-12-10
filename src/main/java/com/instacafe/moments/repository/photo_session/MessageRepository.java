package com.instacafe.moments.repository.photo_session;

import com.instacafe.moments.model.photo_session.Message;
import com.instacafe.moments.repository.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends EntityRepository<Message> {
}
