package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.PhotoSessionDTO;
import com.instacafe.moments.model.enums.*;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.photo_session.certificate.Certificate;
import com.instacafe.moments.model.photo_session.chat.Chat;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.repository.photo_session.CertificateRepository;
import com.instacafe.moments.repository.photo_session.ChatRepository;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.service.user.AppUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Qualifier("managerService")
public class ManagerService {

    private final CertificateRepository certificateRepository;
    private final AppUserService appUserService;
    private final ChatRepository chatRepository;
    private final PhotoSessionRepository photoSessionRepository;


    @Autowired
    public ManagerService(CertificateRepository certificateRepository,
                          @Qualifier("appUserServiceImpl") AppUserService appUserService,
                          ChatRepository chatRepository,
                          PhotoSessionRepository photoSessionRepository) {
        this.certificateRepository = certificateRepository;
        this.appUserService = appUserService;
        this.chatRepository = chatRepository;
        this.photoSessionRepository = photoSessionRepository;
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
        AppUser client = appUserService.findByIdAndRole(photoSessionDTO.getClientId(), Role.CLIENT);
        AppUser photographer = appUserService.findByIdAndRole(photoSessionDTO.getClientId(), Role.PHOTOGRAPHER);
        AppUser manager = appUserService.findByIdAndRole(photoSessionDTO.getClientId(), Role.MANAGER);

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

}