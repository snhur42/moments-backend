package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.CertificateNumberDTO;
import com.instacafe.moments.dto.PhotoSessionDTO;
import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.enums.City;
import com.instacafe.moments.model.enums.Duration;
import com.instacafe.moments.model.enums.PhotoSessionStatus;
import com.instacafe.moments.model.enums.PhotoSessionType;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.photo_session.certificate.Certificate;
import com.instacafe.moments.model.photo_session.chat.Chat;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.repository.photo_session.CertificateRepository;
import com.instacafe.moments.repository.photo_session.ChatRepository;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.repository.user.ClientRepository;
import com.instacafe.moments.repository.user.ManagerRepository;
import com.instacafe.moments.repository.user.PhotographerRepository;
import com.instacafe.moments.service.user.UserServiceImpl;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
@Qualifier("managerService")
public class ManagerService extends UserServiceImpl<Manager, ManagerRepository> {
    private final ClientService clientService;
    private final PhotographerService photographerService;
    private final PhotographerRepository photographerRepository;
    private final ClientRepository clientRepository;
    private final CertificateRepository certificateRepository;
    private final PhotoSessionRepository photoSessionRepository;
    private final ChatRepository chatRepository;


    @Autowired
    public ManagerService(ManagerRepository managerRepository,
                          ClientService clientService,
                          PhotographerRepository photographerRepository,
                          PhotoSessionRepository photoSessionRepository,
                          ClientRepository clientRepository,
                          ChatRepository chatRepository,
                          PhotographerService photographerService,
                          CertificateRepository certificateRepository,
                          PasswordEncoder passwordEncoder) {
        super(passwordEncoder, managerRepository);
        this.clientService = clientService;
        this.photographerService = photographerService;
        this.photographerRepository = photographerRepository;
        this.photoSessionRepository = photoSessionRepository;
        this.certificateRepository = certificateRepository;
        this.chatRepository = chatRepository;
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

    public List<Certificate> findAllCertificate() {
        return this.certificateRepository.findAll();
    }

    public List<Certificate> addNewCertificate() {
        String newCertificateNumber = LocalDate.now().toString().concat(" - ").concat(RandomStringUtils.random(6, true, true));

        Certificate certificate = new Certificate(newCertificateNumber, null);
        this.certificateRepository.save(certificate);

        return this.certificateRepository.findAll();
    }

    public void deleteCertificate(String certificateId) {
        this.certificateRepository.deleteById(UUID.fromString(certificateId));
    }


    public PhotoSession savePhotoSession(PhotoSessionDTO photoSessionDTO) {
        Client client = clientService.findById(UUID.fromString(photoSessionDTO.getClientId()));
        Photographer photographer = photographerService.findById(UUID.fromString(photoSessionDTO.getPhotographerId()));
        Manager manager = this.findById(UUID.fromString(photoSessionDTO.getManagerId()));

        PhotoSession photoSession = new PhotoSession();

        photoSession.setManager(manager);
        photoSession.setPhotographer(photographer);
        photoSession.setClient(client);

        photoSession.setPhotoSessionStatus(PhotoSessionStatus.NEW_PHOTO_SESSION);
        photoSession.setPhotoSessionType(PhotoSessionType.valueOf(photoSessionDTO.getType()));
        photoSession.setDuration(Duration.valueOf(photoSessionDTO.getDuration()));


        photoSession.setLocation(photoSessionDTO.getLocation());
        photoSession.setCity(City.valueOf(photoSessionDTO.getCity()));

        photoSession.setPrice(200);
        photoSession.setChat(this.chatRepository.save(new Chat()));

        photoSession.setWillHappenAt(LocalDateTime.now().plusWeeks(2));

        return photoSessionRepository.save(photoSession);
    }

    public List<PhotoSession> findAllPhotoSessions() {
        return photoSessionRepository.findAll();
    }
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