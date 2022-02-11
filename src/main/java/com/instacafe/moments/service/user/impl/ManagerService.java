package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.request.PhotoSessionRequestDTO;
import com.instacafe.moments.dto.response.CertificateResponseDTO;
import com.instacafe.moments.dto.response.PhotoSessionResponseDTO;
import com.instacafe.moments.model.enums.*;
import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.photo_session.brief.Brief;
import com.instacafe.moments.model.photo_session.certificate.Certificate;
import com.instacafe.moments.model.photo_session.chat.Chat;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.repository.photo_session.BriefRepository;
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
    private final BriefRepository briefRepository;


    @Autowired
    public ManagerService(CertificateRepository certificateRepository,
                          @Qualifier("appUserServiceImpl") AppUserService appUserService,
                          ChatRepository chatRepository,
                          BriefRepository briefRepository,
                          PhotoSessionRepository photoSessionRepository) {
        this.certificateRepository = certificateRepository;
        this.appUserService = appUserService;
        this.chatRepository = chatRepository;
        this.briefRepository = briefRepository;
        this.photoSessionRepository = photoSessionRepository;
    }


    public List<CertificateResponseDTO> findAllCertificate() {
        return this.certificateRepository.findAll().stream().map(c -> new CertificateResponseDTO(
                c.getId().toString(),
                c.getCreated(),
                c.getModified(),
                c.getCertificateNumber()
        )).toList();
    }

    public List<CertificateResponseDTO> addNewCertificate() {
        String newCertificateNumber = LocalDate.now().toString().concat(" - ").concat(RandomStringUtils.random(6, true, true));

        Certificate certificate = new Certificate(newCertificateNumber);
        this.certificateRepository.save(certificate);


        return this.certificateRepository.findAll().stream().map(c ->
                new CertificateResponseDTO(
                        c.getId().toString(),
                        c.getCreated(),
                        c.getModified(),
                        c.getCertificateNumber()
                )).toList();
    }

    public void deleteCertificate(String certificateId) {
        this.certificateRepository.deleteById(UUID.fromString(certificateId));
    }


    public boolean savePhotoSession(PhotoSessionRequestDTO photoSessionRequestDTO) {
        AppUser client = appUserService.findByIdAndRole(photoSessionRequestDTO.getClientId(), Role.CLIENT);
        AppUser photographer = appUserService.findByIdAndRole(photoSessionRequestDTO.getPhotographerId(), Role.PHOTOGRAPHER);
        AppUser manager = appUserService.findByIdAndRole(photoSessionRequestDTO.getManagerId(), Role.MANAGER);

        PhotoSession photoSession = new PhotoSession();

        photoSession.setManager(manager);
        photoSession.setPhotographer(photographer);
        photoSession.setClient(client);

        photoSession.setPhotoSessionStatus(PhotoSessionStatus.NEW_PHOTO_SESSION);
        photoSession.setPhotoSessionType(PhotoSessionType.valueOf(photoSessionRequestDTO.getType()));
        photoSession.setDuration(Duration.valueOf(photoSessionRequestDTO.getDuration()));
        photoSession.setBrief(this.briefRepository.save(new Brief()));

        photoSession.setLocation(photoSessionRequestDTO.getLocation());
        photoSession.setCity(City.valueOf(photoSessionRequestDTO.getCity()));

        photoSession.setPrice(200);
        photoSession.setChat(this.chatRepository.save(new Chat()));

        photoSession.setWillHappenAt(LocalDateTime.now().plusWeeks(2));

        if (photoSessionRequestDTO.getCertificateId() != null) {
            Certificate certificate = certificateRepository.findById(UUID.fromString(photoSessionRequestDTO.getCertificateId())).get();
            photoSession.setCertificate(certificate);
        }

        PhotoSession newPhotoSession = photoSessionRepository.save(photoSession);

        return true;
    }

    public List<PhotoSessionResponseDTO> findAllPhotoSessions() {
        return photoSessionRepository.findAll().stream().map(photoSession ->
                new PhotoSessionResponseDTO(
                        photoSession.getId().toString(),
                        photoSession.getCreated(),
                        photoSession.getModified(),
                        photoSession.getManager().getId().toString(),
                        photoSession.getClient().getId().toString(),
                        photoSession.getPhotographer().getId().toString(),
                        photoSession.getAllPhoto().stream().map(Photo::getPath).toList(),
                        photoSession.getFinalPhoto().stream().map(Photo::getPath).toList(),
                        photoSession.getChat().getId().toString(),
                        photoSession.getPhotoSessionType().toString(),
                        photoSession.getPhotoSessionStatus().toString(),
                        photoSession.getDuration().toString(),
                        photoSession.getLocation(),
                        Integer.toString(photoSession.getPrice()),
                        photoSession.getCertificate().getId().toString(),
                        photoSession.getBrief().toString(),
                        photoSession.getWillHappenAt().toString()
                )).toList();
    }
}

