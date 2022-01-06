package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.repository.user.ClientRepository;
import com.instacafe.moments.repository.user.ManagerRepository;
import com.instacafe.moments.repository.user.PhotographerRepository;
import com.instacafe.moments.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Qualifier("managerService")
public class ManagerService extends UserServiceImpl<Manager, ManagerRepository> {
    private final ClientService clientService;
    private final PhotographerService photographerService;
    private final PhotographerRepository photographerRepository;
    private final ClientRepository clientRepository;


    @Autowired
    public ManagerService(ManagerRepository managerRepository,
                          ClientService clientService,
                          PhotographerRepository photographerRepository,
                          ClientRepository clientRepository,
                          PhotographerService photographerService,
                          PasswordEncoder passwordEncoder) {
        super(passwordEncoder, managerRepository);
        this.clientService = clientService;
        this.photographerService = photographerService;
        this.photographerRepository = photographerRepository;
        this.clientRepository = clientRepository;

    }

    @Override
    public Manager save(UserDTO userDTO) {
        Manager manager = new Manager();
        return repository.save(this.parseUserFromUserDTO(manager, userDTO));
    }

    @Override
    public Manager update(String managerId, UserDTO userDTO) {
        return this.updateUserFromUserDTO(managerId, userDTO);
    }

        public List<Photographer> findAllPhotographers() {
        return photographerService.findAll();
    }

    public Photographer findPhotographerById(UUID photographerId) {
        return photographerService.findById(photographerId);
    }

    public Photographer updatePhotographer(String photographerId, UserDTO userDTO) {
        return photographerService.update(photographerId, userDTO);
    }

    public void changePhotographerEnableStatus(String photographerId) {
        Photographer photographer = photographerService.findById(UUID.fromString(photographerId));
        photographer = this.changeUserEnableStatus(photographer);
        photographerRepository.save(photographer);
    }

    public Client saveClient(UserDTO userDTO) {
        return clientService.save(userDTO);
    }

    public List<Client> findAllClients() {
        return clientService.findAll();
    }

    public Client findClientById(UUID clientId) {
        return clientService.findById(clientId);
    }

    public Client updateClient(String clientId, UserDTO userDTO) {
        return clientService.update(clientId, userDTO);
    }

    public void deleteClientById(UUID clientId) {
        clientService.deleteById(clientId);
    }

    public void changeClientEnableStatus(String clientId) {
        Client client = clientService.findById(UUID.fromString(clientId));
        client = this.changeUserEnableStatus(client);
        clientRepository.save(client);
    }


//
//
//    public PhotoSession savePhotoSession(UUID managerId, UUID clientId, UUID photographerId, PhotoSession photoSession) {
//        Client client = clientService.findById(clientId);
//        Photographer photographer = photographerService.findById(photographerId);
//        Manager manager = this.findById(managerId);
//
//        photoSession.setManager(manager);
//        photoSession.setPhotographer(photographer);
//        photoSession.setClient(client);
//
//        manager.getPhotoSessions().add(photoSession);
//        photographer.getPhotoSessions().add(photoSession);
//        client.getPhotoSessions().add(photoSession);
//
//        return photoSessionService.save(photoSession);
//    }
//
//    public List<PhotoSession> findAllPhotoSessions() {
//        return photoSessionService.findAll();
//    }
//
//    public List<PhotoSession> findAllPhotoSessionsByManagerId(UUID managerId) {
//        return this.findById(managerId).getPhotoSessions();
//    }
//
//    public List<PhotoSession> findAllPhotoSessionsByPhotographerId(UUID photographerId) {
//        return this.findPhotographerById(photographerId).getPhotoSessions();
//    }
//
//    public List<PhotoSession> findAllPhotoSessionsByClientId(UUID clientId) {
//        return this.findClientById(clientId).getPhotoSessions();
//    }
//
//    public PhotoSession findPhotoSessionById(UUID photoSessionId) {
//        return photoSessionService.findById(photoSessionId);
//    }
//
//
//    public List<Photo> findAllPhotos() {
//        return photoService.findAll();
//    }
//
//    public List<Photo> findAllPhotosByPhotoSessionsId(UUID photoSessionId) {
//        return photoSessionService.findById(photoSessionId).getPhotos();
//    }
//
//    public Photo findPhotoById(UUID photoId) {
//        return photoService.findById(photoId);
//    }
}