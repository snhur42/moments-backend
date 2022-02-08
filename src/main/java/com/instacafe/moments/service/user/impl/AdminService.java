package com.instacafe.moments.service.user.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instacafe.moments.dto.CurrentBriefQuestionsDTO;
import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.photo_session.brief.CurrentBriefQuestions;
import com.instacafe.moments.model.photo_session.certificate.Certificate;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.user.roles.Admin;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.repository.photo_session.CertificateRepository;
import com.instacafe.moments.repository.photo_session.CurrentBriefQuestionsRepository;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.repository.user.AdminRepository;
import com.instacafe.moments.repository.user.ClientRepository;
import com.instacafe.moments.repository.user.ManagerRepository;
import com.instacafe.moments.repository.user.PhotographerRepository;
import com.instacafe.moments.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
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
    private final CertificateRepository certificateRepository;
    private final CurrentBriefQuestionsRepository currentBriefQuestionsRepository;


    @Autowired
    public AdminService(@Qualifier("adminRepository") AdminRepository adminRepository,
                        @Qualifier("managerService") ManagerService managerService,
                        ManagerRepository managerRepository,
                        PhotographerRepository photographerRepository,
                        ClientRepository clientRepository,
                        PhotoSessionRepository photoSessionRepository,
                        CertificateRepository certificateRepository,
                        CurrentBriefQuestionsRepository currentBriefQuestionsRepository,
                        @Qualifier("clientService") ClientService clientService,
                        @Qualifier("photographerService") PhotographerService photographerService,
                        PasswordEncoder passwordEncoder) {
        super(passwordEncoder, adminRepository);
        this.managerService = managerService;
        this.managerRepository = managerRepository;
        this.photographerRepository = photographerRepository;
        this.clientService = clientService;
        this.photographerService = photographerService;
        this.clientRepository = clientRepository;
        this.currentBriefQuestionsRepository = currentBriefQuestionsRepository;
        this.photoSessionRepository = photoSessionRepository;
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

    public List<Certificate> getAllCertificate() {
        return certificateRepository.findAll();
    }

    public CurrentBriefQuestions getBriefQuestion() {
        return this.currentBriefQuestionsRepository.findAll().get(0);
    }

    public CurrentBriefQuestions updateBriefQuestion(CurrentBriefQuestionsDTO currentBriefQuestionsDTO) {
       CurrentBriefQuestions currentBriefQuestions =  this.currentBriefQuestionsRepository.findAll().get(0);

       try {
           final ObjectMapper objectMapper = new ObjectMapper();
           String[] strings = objectMapper.readValue(currentBriefQuestionsDTO.getQuestions(), String[].class);
           List<String> questions = new ArrayList(Arrays.asList(strings));
           currentBriefQuestions.setQuestions(questions);
       } catch (JsonProcessingException err){
            log.error("Cant deserialize questions");
       }

       return this.currentBriefQuestionsRepository.save(currentBriefQuestions);
    }

    public CurrentBriefQuestions createBriefQuestion() {
        return this.currentBriefQuestionsRepository.save(new CurrentBriefQuestions());
    }
}