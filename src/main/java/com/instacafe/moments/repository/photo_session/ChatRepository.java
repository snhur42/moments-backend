package com.instacafe.moments.repository.photo_session;

import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.photo_session.chat.Chat;
import com.instacafe.moments.repository.EntityRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRepository extends EntityRepository<Chat> {

}
