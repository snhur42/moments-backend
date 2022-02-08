package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.ClientDTO;
import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.repository.user.ClientRepository;
import com.instacafe.moments.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@Qualifier("clientService")
public class ClientService extends UserServiceImpl<Client, ClientRepository> {
    private final PhotoSessionRepository photoSessionRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         PasswordEncoder passwordEncoder,
                         PhotoSessionRepository photoSessionRepository) {
        super(passwordEncoder, clientRepository);
        this.photoSessionRepository = photoSessionRepository;

    }

    private Client saveClient(ClientDTO clientDTO) {
        Client client = new Client();

        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        client.setCity(clientDTO.getCity());
        client.setRole(clientDTO.getRole());
        client.setAccountNonExpired(true);
        client.setCredentialsNonExpired(true);
        client.setAccountNonLocked(true);
        client.setEnabled(true);

        return repository.save(client);
    }

    @Override
    public Client save(UserDTO userDTO) {
        return this.saveClient((ClientDTO) userDTO);
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