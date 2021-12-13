package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.user.roles.Admin;
import com.instacafe.moments.repository.user.AdminRepository;
import com.instacafe.moments.service.photo_session.impl.PhotoService;
import com.instacafe.moments.service.photo_session.impl.PhotoSessionService;
import com.instacafe.moments.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Qualifier("adminService")
public class AdminService extends UserServiceImpl<Admin, AdminRepository> {
    private final ClientService clientService;
    private final PhotographerService photographerService;
    private final ManagerService managerService;
    private final PhotoSessionService photoSessionService;
    private final PhotoService photoService;

    @Autowired
    public AdminService(@Qualifier("adminRepository") AdminRepository adminRepository,
                        @Qualifier("managerService")ManagerService managerService,
                        @Qualifier("clientService")ClientService clientService,
                        @Qualifier("photographerService")PhotographerService photographerService,
                        @Qualifier("photoService")PhotoService photoService,
                        @Qualifier("photoSessionService")PhotoSessionService photoSessionService,
                        PasswordEncoder passwordEncoder) {
        super(adminRepository, passwordEncoder);
        this.managerService = managerService;
        this.clientService = clientService;
        this.photographerService = photographerService;
        this.photoSessionService = photoSessionService;
        this.photoService = photoService;
    }

    @Override
    public Admin save(UserDTO userDTO) {
        Admin admin = new Admin();
        return repository.save(this.createUserFromUserDTO(admin, userDTO));
    }

    @Override
    public Admin update(UserDTO userDTO) {
        return this.updateUserFromUserDTO(userDTO);
    }


//
//    public Manager saveManager(Manager manager) {
//        return managerService.save(manager);
//    }
//
//    public List<Manager> findAllManagers() {
//        return managerService.findAll();
//    }
//
//    public Manager findManagerById(UUID managerId) {
//        return managerService.findById(managerId);
//    }
//
//    public Manager updateManager(Manager manager) {
//        return managerService.update(manager);
//    }
//
//    public void deleteManagerById(UUID managerId) {
//        managerService.deleteById(managerId);
//    }
//
//
//    public Photographer savePhotographer(Photographer photographer) {
//        return photographerService.save(photographer);
//    }
//
//    public List<Photographer> findAllPhotographers() {
//        return photographerService.findAll();
//    }
//
//    public Photographer findPhotographerById(UUID photographerId) {
//        return photographerService.findById(photographerId);
//    }
//
//    public Photographer updatePhotographer(Photographer photographer) {
//        return photographerService.update(photographer);
//    }
//
//    public void deletePhotographerById(UUID photographerId) {
//        photographerService.deleteById(photographerId);
//    }
//
//
//    public List<Client> findAllClients() {
//        return clientService.findAll();
//    }
//
//    public Client findClientById(UUID clientId) {
//        return clientService.findById(clientId);
//    }
//
//    public Client updateClient(Client client) {
//        return clientService.update(client);
//    }
//
//    public void deleteClientById(UUID clientId) {
//        clientService.deleteById(clientId);
//    }
//
//
//    public List<PhotoSession> findAllPhotoSessions() {
//        return photoSessionService.findAll();
//    }
//
//    public List<PhotoSession> findAllPhotoSessionsByManagerId(UUID managerId) {
//        return this.findManagerById(managerId).getPhotoSessions();
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
//
//

}