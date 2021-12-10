package com.instacafe.moments.service.photo_session;

import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.repository.photo_session.PhotoRepository;
import com.instacafe.moments.service.EntityServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Qualifier("photoService")
public class PhotoService extends EntityServiceImpl<Photo, PhotoRepository> {
    public PhotoService(PhotoRepository photoRepository) {
        super(photoRepository);
    }
}




