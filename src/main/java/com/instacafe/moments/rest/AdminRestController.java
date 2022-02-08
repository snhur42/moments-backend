package com.instacafe.moments.rest;

import com.instacafe.moments.dto.CurrentBriefQuestionsDTO;
import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.photo_session.brief.CurrentBriefQuestions;
import com.instacafe.moments.model.user.roles.Admin;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.service.user.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {
    private final AdminService adminService;

    @Autowired
    public AdminRestController(@Qualifier("adminService") AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("create_admin")
    public ResponseEntity<Admin> createAdmin(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(adminService.save(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
    }

    @GetMapping("admins/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable String adminId) {
        return new ResponseEntity<>(adminService.findById(UUID.fromString(adminId)), HttpStatus.OK);
    }

    @PutMapping("update_admin/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable String adminId, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(adminService.update(adminId, userDTO), HttpStatus.OK);
    }

    @PostMapping("create_manager")
    public ResponseEntity<Manager> createManager(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(adminService.saveManager(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("managers")
    public ResponseEntity<List<Manager>> getAllManagers() {
        return new ResponseEntity<>(adminService.findAllManagers(), HttpStatus.OK);
    }

    @PutMapping("update_manager/{managerId}")
    public ResponseEntity<Manager> updateManager(@PathVariable String managerId, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(adminService.updateManager(managerId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_manager/{managerId}")
    public void changeManagerEnableStatus(@PathVariable String managerId) {
        adminService.changeManagerEnableStatus(managerId);
    }

    @PostMapping("create_photographer")
    public ResponseEntity<Photographer> createPhotographer(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(adminService.savePhotographer(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("photographers")
    public ResponseEntity<List<Photographer>> getAllPhotographers() {
        return new ResponseEntity<>(adminService.findAllPhotographers(), HttpStatus.OK);
    }

    @PutMapping("update_photographer/{photographerId}")
    public ResponseEntity<Photographer> updatePhotographer(@PathVariable String photographerId, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(adminService.updatePhotographer(photographerId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_photographer/{photographerId}")
    public void changePhotographerEnableStatus(@PathVariable String photographerId) {
        adminService.changePhotographerEnableStatus(photographerId);
    }

    @GetMapping("clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(adminService.findAllClients(), HttpStatus.OK);
    }

    @PutMapping("admins/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable String clientId, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(adminService.updateClient(clientId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_client/{clientId}")
    public void changeClientEnableStatus(@PathVariable String clientId) {
        adminService.changeClientEnableStatus(clientId);
    }

    @GetMapping("get_brief_questions")
    public ResponseEntity<CurrentBriefQuestions> getBriefQuestion() {
        return new ResponseEntity<>(adminService.getBriefQuestion(), HttpStatus.OK);
    }

    @PostMapping("create_brief_questions")
    public ResponseEntity<CurrentBriefQuestions> createBriefQuestion() {
        return new ResponseEntity<>(adminService.createBriefQuestion(), HttpStatus.OK);
    }

    @PutMapping(value = "update_brief_questions",
                consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<CurrentBriefQuestions> updateBriefQuestion(@RequestBody CurrentBriefQuestionsDTO currentBriefQuestionsDTO) {
        return new ResponseEntity<>(adminService.updateBriefQuestion(currentBriefQuestionsDTO), HttpStatus.OK);
    }

//    @GetMapping("photo_sessions")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessions() {
//        return new ResponseEntity<>(adminService.findAllPhotoSessions(), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{managerId}")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByManagerId(@PathVariable UUID managerId) {
//        return new ResponseEntity<>(adminService.findAllPhotoSessionsByManagerId(managerId), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{photographerId}")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByPhotographerId(@PathVariable UUID photographerId) {
//        return new ResponseEntity<>(adminService.findAllPhotoSessionsByPhotographerId(photographerId), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{clientId}")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByClientId(@PathVariable UUID clientId) {
//        return new ResponseEntity<>(adminService.findAllPhotoSessionsByClientId(clientId), HttpStatus.OK);
//    }
//
//    @GetMapping("photos")
//    public ResponseEntity<List<Photo>> getAllPhoto() {
//        return new ResponseEntity<>(adminService.findAllPhotos(), HttpStatus.OK);
//    }
//
//    @GetMapping("photos/{photoSessionId}")
//    public ResponseEntity<List<Photo>> getAllPhotoByPhotoSessionId(@PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(adminService.findAllPhotosByPhotoSessionsId(photoSessionId), HttpStatus.OK);
//    }
//
//    @GetMapping("certificates")
//    public ResponseEntity<List<Certificate>> getAllCertificate() {
//        return new ResponseEntity<>(adminService.getAllCertificate(), HttpStatus.OK);
//    }


}
