package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.photo_session.Certificate;
import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.user.roles.Admin;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.repository.photo_session.CertificateRepository;
import com.instacafe.moments.repository.photo_session.PhotoRepository;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.repository.user.AdminRepository;
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
@Qualifier("adminService")
public class AdminService extends UserServiceImpl<Admin, AdminRepository> {
    private final ClientService clientService;
    private final ManagerService managerService;
    private final ManagerRepository managerRepository;
    private final ClientRepository clientRepository;
    private final PhotographerService photographerService;
    private final PhotographerRepository photographerRepository;
    private final PhotoSessionRepository photoSessionRepository;
    private final PhotoRepository photoRepository;
    private final CertificateRepository certificateRepository;

    @Autowired
    public AdminService(@Qualifier("adminRepository") AdminRepository adminRepository,
                        @Qualifier("managerService")ManagerService managerService,
                        ManagerRepository managerRepository,
                        PhotographerRepository photographerRepository,
                        ClientRepository clientRepository,
                        PhotoSessionRepository photoSessionRepository,
                        PhotoRepository photoRepository,
                        CertificateRepository certificateRepository,
                        @Qualifier("clientService")ClientService clientService,
                        @Qualifier("photographerService")PhotographerService photographerService,
                        PasswordEncoder passwordEncoder) {
        super(passwordEncoder, adminRepository);
        this.managerService = managerService;
        this.managerRepository = managerRepository;
        this.photographerRepository = photographerRepository;
        this.clientService = clientService;
        this.photographerService = photographerService;
        this.clientRepository = clientRepository;
        this.photoSessionRepository = photoSessionRepository;
        this.photoRepository = photoRepository;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public Admin save(UserDTO userDTO) {
        Admin admin = new Admin();
        admin = (Admin) this.createUserFromUserDTO(admin, userDTO);
        return repository.save(admin);
    }

    @Override
    public Admin update(String adminId, UserDTO userDTO) {
        return this.updateUserFromUserDTO(adminId, userDTO);
    }


    public Manager saveManager(UserDTO userDTO) {
        Manager manager = new Manager();
        manager = (Manager) this.createUserFromUserDTO(manager, userDTO);
        return managerRepository.save(manager);
    }

    public List<Manager> findAllManagers() {
        return managerService.findAll();
    }

    public Manager findManagerById(UUID managerId) {
        return managerService.findById(managerId);
    }

    public Manager updateManager(String managerId, UserDTO userDTO) {
        return managerService.update(managerId, userDTO);
    }

    public void changeManagerEnableStatus(String managerId) {
        Manager manager = managerService.findById(UUID.fromString(managerId));
        manager = this.changeUserEnableStatus(manager);
        managerRepository.save(manager);
    }


    public Photographer savePhotographer(UserDTO userDTO) {
        Photographer photographer = new Photographer();
        photographer = (Photographer) this.createUserFromUserDTO(photographer, userDTO);
        return photographerRepository.save(photographer);
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



    public List<Client> findAllClients() {
        return clientService.findAll();
    }

    public Client findClientById(UUID clientId) {
        return clientService.findById(clientId);
    }

    public Client updateClient(String clientId, UserDTO userDTO) {
        return clientService.update(clientId, userDTO);
    }

    public void changeClientEnableStatus(String clientId) {
        Client client = clientService.findById(UUID.fromString(clientId));
        client = this.changeUserEnableStatus(client);
        clientRepository.save(client);
    }


    public List<PhotoSession> findAllPhotoSessions() {
        return photoSessionRepository.findAll();
    }

    public List<PhotoSession> findAllPhotoSessionsByManagerId(UUID managerId) {
        return this.findManagerById(managerId).getPhotoSessions();
    }

    public List<PhotoSession> findAllPhotoSessionsByPhotographerId(UUID photographerId) {
        return this.findPhotographerById(photographerId).getPhotoSessions();
    }

    public List<PhotoSession> findAllPhotoSessionsByClientId(UUID clientId) {
        return this.findClientById(clientId).getPhotoSessions();
    }


    public List<Photo> findAllPhotos() {
        return photoRepository.findAll();
    }

    public List<Photo> findAllPhotosByPhotoSessionsId(UUID photoSessionId) {
        return photoSessionRepository.findById(photoSessionId).get().getPhotos();
    }



    public List<Certificate> getAllCertificate() {
        return certificateRepository.findAll();
    }
}