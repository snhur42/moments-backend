package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.service.photo_session.impl.PhotoService;
import com.instacafe.moments.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.repository.user.ClientRepository;
import com.instacafe.moments.service.photo_session.impl.PhotoSessionService;

import javax.transaction.Transactional;

@Service
@Transactional
@Qualifier("clientService")
public class ClientService extends UserServiceImpl<Client, ClientRepository> {
    private final PhotoSessionService photoSessionService;
    private final PhotoService photoService;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         PhotoService photoService,
                         PhotoSessionService photoSessionService,
                         PasswordEncoder passwordEncoder) {
        super(clientRepository, passwordEncoder);
        this.photoSessionService = photoSessionService;
        this.photoService = photoService;
    }

    @Override
    public Client save(UserDTO userDTO) {
        Client client = new Client();
        return repository.save(this.createUserFromUserDTO(client, userDTO));
    }

    @Override
    public Client update(UserDTO userDTO) {
        return this.updateUserFromUserDTO(userDTO);
    }
//
//    public List<PhotoSession> findAllPhotoSessions() {
//        return photoSessionService.findAll();
//    }
//    public PhotoSession findPhotoSessionById(UUID photoSessionId) {
//        return photoSessionService.findById(photoSessionId);
//    }
//    public PhotoSession updatePhotoSession(PhotoSession photoSession) {
//        return photoSessionService.update(photoSession);
//    }
//
//    public List<Photo> findAllPhotos() {
//        return photoService.findAll();
//    }
//    public List<Photo> findAllPhotosByPhotoSessionsId(UUID photoSessionId) {
//        return photoSessionService.findById(photoSessionId).getPhotos();
//    }
//    public Photo findPhotoById(UUID photoId) {
//        return photoService.findById(photoId);
//    }
//    public Photo updatePhoto(Photo photo) {
//        return photoService.update(photo);
//    }
}