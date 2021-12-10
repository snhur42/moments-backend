package com.instacafe.moments.service.photo_session;

import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.service.EntityServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Qualifier("photoSessionService")
public class PhotoSessionService extends EntityServiceImpl<PhotoSession, PhotoSessionRepository> {
    public PhotoSessionService(PhotoSessionRepository photoSessionRepository) {
        super(photoSessionRepository);
    }
}
