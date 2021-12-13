package com.instacafe.moments.service.photo_session.impl;

import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.service.photo_session.BusinessServiceImpl;
import com.instacafe.moments.service.user.impl.ClientService;
import com.instacafe.moments.service.user.impl.ManagerService;
import com.instacafe.moments.service.user.impl.PhotographerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Qualifier("photoSessionService")
public class PhotoSessionService extends BusinessServiceImpl<PhotoSession, PhotoSessionRepository> {

    public PhotoSessionService(PhotoSessionRepository photoSessionRepository) {
        super(photoSessionRepository);

    }


//    @Override
//    public PhotoSession save(PhotoSession photoSession, PhotoSessionDTO photoSessionDTO) {
//
//        Manager manager = managerService.findById(photoSessionDTO.getManagerId());
//        Photographer photographer = photographerService.findById(photoSessionDTO.getPhotographerId());
//        Client client = clientService.findById(photoSessionDTO.getClientId());
//
//        photoSession.setManager(manager);
//        photoSession.setPhotographer(photographer);
//        photoSession.setClient(client);
//
//        photoSession.setDuration(photoSessionDTO.getDuration());
//        photoSession.setCity(photoSessionDTO.getCity());
//        photoSession.setPhotoSessionType(photoSessionDTO.getPhotoSessionType());
//        photoSession.setWillHappenAt(photoSessionDTO.getWillHappenAt());
//        photoSession.setPrice(photoSessionDTO.getPrice());
//
//        return photoSession;
//    }
//
//
//    protected T updateUserFromUserDTO(UserDTO userDTO) {
//        T t = repository.getById(userDTO.getId());
//
//        return repository.save(
//                this.createUserFromUserDTO(t, userDTO)
//        );
//    }


}
