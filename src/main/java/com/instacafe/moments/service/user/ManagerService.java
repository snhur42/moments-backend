package com.instacafe.moments.service.user;

import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.repository.user.ManagerRepository;
import com.instacafe.moments.service.photo_session.PhotoService;
import com.instacafe.moments.service.photo_session.PhotoSessionService;
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
    private final PhotoSessionService photoSessionService;
    private final PhotoService photoService;

    @Autowired
    public ManagerService(ManagerRepository managerRepository,
                          @Qualifier("clientService")ClientService clientService,
                          PhotographerService photographerService,
                          PhotoService photoService,
                          PhotoSessionService photoSessionService,
                          PasswordEncoder passwordEncoder) {
        super(managerRepository, passwordEncoder);
        this.clientService = clientService;
        this.photographerService = photographerService;
        this.photoSessionService = photoSessionService;
        this.photoService = photoService;
    }

    public List<Photographer> findAllPhotographers() {
        return photographerService.findAll();
    }

    public Photographer findPhotographerById(UUID photographerId) {
        return photographerService.findById(photographerId);
    }

    public Photographer updatePhotographer(Photographer photographer) {
        return photographerService.update(photographer);
    }


    public Client saveClient(Client client) {
        return clientService.save(client);
    }

    public List<Client> findAllClients() {
        return clientService.findAll();
    }

    public Client findClientById(UUID clientId) {
        return clientService.findById(clientId);
    }

    public Client updateClient(Client client) {
        return clientService.update(client);
    }

    public void deleteClientById(UUID clientId) {
        clientService.deleteById(clientId);
    }


    public PhotoSession savePhotoSession(UUID managerId, UUID clientId, UUID photographerId, PhotoSession photoSession) {
        Client client = clientService.findById(clientId);
        Photographer photographer = photographerService.findById(photographerId);
        Manager manager = this.findById(managerId);

        photoSession.setManager(manager);
        photoSession.setPhotographer(photographer);
        photoSession.setClient(client);

        manager.getPhotoSessions().add(photoSession);
        photographer.getPhotoSessions().add(photoSession);
        client.getPhotoSessions().add(photoSession);

        return photoSessionService.save(photoSession);
    }

    public List<PhotoSession> findAllPhotoSessions() {
        return photoSessionService.findAll();
    }

    public List<PhotoSession> findAllPhotoSessionsByManagerId(UUID managerId) {
        return this.findById(managerId).getPhotoSessions();
    }

    public List<PhotoSession> findAllPhotoSessionsByPhotographerId(UUID photographerId) {
        return this.findPhotographerById(photographerId).getPhotoSessions();
    }

    public List<PhotoSession> findAllPhotoSessionsByClientId(UUID clientId) {
        return this.findClientById(clientId).getPhotoSessions();
    }

    public PhotoSession findPhotoSessionById(UUID photoSessionId) {
        return photoSessionService.findById(photoSessionId);
    }


    public List<Photo> findAllPhotos() {
        return photoService.findAll();
    }

    public List<Photo> findAllPhotosByPhotoSessionsId(UUID photoSessionId) {
        return photoSessionService.findById(photoSessionId).getPhotos();
    }

    public Photo findPhotoById(UUID photoId) {
        return photoService.findById(photoId);
    }
}