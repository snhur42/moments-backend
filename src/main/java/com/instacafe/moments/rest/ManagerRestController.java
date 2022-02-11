package com.instacafe.moments.rest;

import com.instacafe.moments.dto.request.AppUserRequestDTO;
import com.instacafe.moments.dto.request.PhotoSessionRequestDTO;
import com.instacafe.moments.dto.response.CertificateResponseDTO;
import com.instacafe.moments.dto.response.PhotoSessionResponseDTO;
import com.instacafe.moments.model.enums.Role;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.service.user.impl.AppUserServiceImpl;
import com.instacafe.moments.service.user.impl.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/manager")
public class ManagerRestController {
    private final ManagerService managerService;
    private final AppUserServiceImpl appUserService;

    @Autowired
    public ManagerRestController(@Qualifier("managerService") ManagerService managerService,
                                 @Qualifier("appUserServiceImpl") AppUserServiceImpl appUserService) {
        this.managerService = managerService;
        this.appUserService = appUserService;
    }

    @GetMapping("managers/{managerId}")
    public ResponseEntity<AppUser> getAdminById(@PathVariable String managerId) {
        return new ResponseEntity<>(appUserService.findById(managerId), HttpStatus.OK);
    }

    @GetMapping("get_all_certificates")
    public ResponseEntity<List<CertificateResponseDTO>> getAllCertificate() {
        return new ResponseEntity<>(managerService.findAllCertificate(), HttpStatus.OK);
    }

    @PostMapping("add_certificate")
    public ResponseEntity<List<CertificateResponseDTO>> AddNewCertificate() {
        return new ResponseEntity<>(managerService.addNewCertificate(), HttpStatus.OK);
    }

    @DeleteMapping("delete_certificate/{certificateId}")
    public void deleteCertificate(@PathVariable String certificateId) {
        managerService.deleteCertificate(certificateId);
    }


    @GetMapping("photographers")
    public ResponseEntity<List<AppUser>> getAllPhotographers() {
        return new ResponseEntity<>(appUserService.findAllByRole(Role.PHOTOGRAPHER), HttpStatus.OK);
    }

    @GetMapping("photographers/{photographerId}")
    public ResponseEntity<AppUser> getPhotographerById(@PathVariable String photographerId) {
        return new ResponseEntity<>(appUserService.findByIdAndRole(photographerId, Role.PHOTOGRAPHER), HttpStatus.OK);
    }

    @PutMapping("photographers/{photographerId}")
    public ResponseEntity<AppUser> updatePhotographer(@PathVariable String photographerId, @RequestBody AppUserRequestDTO userDTO) {
        return new ResponseEntity<>(appUserService.update(photographerId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_photographer/{photographerId}")
    public void changePhotographerEnableStatus(@PathVariable String photographerId) {
        appUserService.changeUserEnableStatus(photographerId);
    }

    @GetMapping("clients")
    public ResponseEntity<List<AppUser>> getAllClients() {
        return new ResponseEntity<>(appUserService.findAllByRole(Role.CLIENT), HttpStatus.OK);
    }

    @GetMapping("clients/{clientId}")
    public ResponseEntity<AppUser> getClientById(@PathVariable String clientId) {
        return new ResponseEntity<>(appUserService.findByIdAndRole(clientId, Role.CLIENT), HttpStatus.OK);
    }

    @PutMapping("clients/{clientId}")
    public ResponseEntity<AppUser> updateClient(@PathVariable String clientId, @RequestBody AppUserRequestDTO userDTO) {
        return new ResponseEntity<>(appUserService.update(clientId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_client/{clientId}")
    public void changeClientEnableStatus(@PathVariable String clientId) {
        appUserService.changeUserEnableStatus(clientId);
    }

    @GetMapping("photo_sessions")
    public ResponseEntity<List<PhotoSessionResponseDTO>> getAllPhotoSessions() {
        return new ResponseEntity<>(managerService.findAllPhotoSessions(), HttpStatus.OK);
    }

    @PostMapping("create_photo_session")
    public ResponseEntity<Boolean> createPhotoSession(@RequestBody PhotoSessionRequestDTO photoSessionRequestDTO
    ) {
        return new ResponseEntity<>(managerService.savePhotoSession(photoSessionRequestDTO),
                HttpStatus.CREATED);
    }

}
