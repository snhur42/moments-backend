package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.repository.photo_session.PhotoRepository;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.repository.user.ClientRepository;

import javax.transaction.Transactional;


@Service
@Transactional
@Qualifier("clientService")
public class ClientService extends UserServiceImpl<Client, ClientRepository> {
    private final PhotoSessionRepository photoSessionRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         PasswordEncoder passwordEncoder,
                         PhotoSessionRepository photoSessionRepository,
                         PhotoRepository photoRepository) {
        super(passwordEncoder, clientRepository);
        this.photoSessionRepository = photoSessionRepository;
        this.photoRepository = photoRepository;

    }

    @Override
    public Client save(UserDTO userDTO) {
        Client client = new Client();
        return repository.save(this.parseUserFromUserDTO(client, userDTO));
    }

    @Override
    public Client update(String clientId, UserDTO userDTO) {
        return this.updateUserFromUserDTO(clientId, userDTO);
    }

//    public List<PhotoSession> findAllPhotoSessionsByClientId(UUID clientId) {
//        return repository.findById(clientId).get().getPhotoSessions();
//    }
//    public PhotoSession findPhotoSessionById(UUID clientId, UUID photoSessionId) {
//        return repository.findById(clientId).get().getPhotoSessions().stream().filter(photoSession -> photoSession.getId().equals(photoSessionId)).findFirst().get();
//    }
//    public PhotoSession updatePhotoSession(PhotoSession photoSession) {
//        return photoSessionRepository.save(photoSession);
//    }
//
//    public List<Photo> findAllPhotosByClientId(UUID clientId) {
//        Client client = repository.findById(clientId).get();
//        return photoSessionRepository.findAllByClient(client);
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