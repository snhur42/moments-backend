package com.instacafe.moments.rest;

import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.user.roles.Admin;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.service.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.save(admin), HttpStatus.CREATED);
    }

    @GetMapping("admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
    }

    @GetMapping("admins/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable UUID adminId) {
        return new ResponseEntity<>(adminService.findById(adminId), HttpStatus.OK);
    }

    @PutMapping("admins")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.update(admin), HttpStatus.OK);
    }


    @PostMapping("create_manager")
    public ResponseEntity<Manager> createManger(@RequestBody Manager manager) {
        return new ResponseEntity<>(adminService.saveManager(manager), HttpStatus.CREATED);
    }

    @GetMapping("managers")
    public ResponseEntity<List<Manager>> getAllManagers() {
        return new ResponseEntity<>(adminService.findAllManagers(), HttpStatus.OK);
    }

    @GetMapping("managers/{managerId}")
    public ResponseEntity<Manager> getManagerById(@PathVariable UUID managerId) {
        return new ResponseEntity<>(adminService.findManagerById(managerId), HttpStatus.OK);
    }

    @PutMapping("managers")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager) {
        return new ResponseEntity<>(adminService.updateManager(manager), HttpStatus.OK);
    }

    @DeleteMapping("managers/{managerId}")
    public ResponseEntity<?> deleteManagerById(@PathVariable UUID managerId) {
        adminService.deleteManagerById(managerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("create_photographer")
    public ResponseEntity<Photographer> createPhotographer(@RequestBody Photographer photographer) {
        return new ResponseEntity<>(adminService.savePhotographer(photographer), HttpStatus.CREATED);
    }

    @GetMapping("photographers")
    public ResponseEntity<List<Photographer>> getAllPhotographers() {
        return new ResponseEntity<>(adminService.findAllPhotographers(), HttpStatus.OK);
    }

    @GetMapping("photographers/{photographerId}")
    public ResponseEntity<Photographer> getPhotographerById(@PathVariable UUID photographerId) {
        return new ResponseEntity<>(adminService.findPhotographerById(photographerId), HttpStatus.OK);
    }

    @PutMapping("photographers")
    public ResponseEntity<Photographer> updatePhotographer(@RequestBody Photographer photographer) {
        return new ResponseEntity<>(adminService.updatePhotographer(photographer), HttpStatus.OK);
    }

    @DeleteMapping("photographers/{photographerId}")
    public ResponseEntity<?> deletePhotographerById(@PathVariable UUID photographerId) {
        adminService.deletePhotographerById(photographerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(adminService.findAllClients(), HttpStatus.OK);
    }

    @GetMapping("clients/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID clientId) {
        return new ResponseEntity<>(adminService.findClientById(clientId), HttpStatus.OK);
    }

    @PutMapping("clients")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return new ResponseEntity<>(adminService.updateClient(client), HttpStatus.OK);
    }

    @DeleteMapping("clients/{clientId}")
    public ResponseEntity<?> deleteClientById(@PathVariable UUID clientId) {
        adminService.deleteClientById(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("photo_sessions")
    public ResponseEntity<List<PhotoSession>> getAllPhotoSessions() {
        return new ResponseEntity<>(adminService.findAllPhotoSessions(), HttpStatus.OK);
    }

    @GetMapping("photo_sessions/{managerId}")
    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByManagerId(@PathVariable UUID managerId) {
        return new ResponseEntity<>(adminService.findAllPhotoSessionsByManagerId(managerId), HttpStatus.OK);
    }

    @GetMapping("photo_sessions/{photographerId}")
    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByPhotographerId(@PathVariable UUID photographerId) {
        return new ResponseEntity<>(adminService.findAllPhotoSessionsByPhotographerId(photographerId), HttpStatus.OK);
    }

    @GetMapping("photo_sessions/{clientId}")
    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByClientId(@PathVariable UUID clientId) {
        return new ResponseEntity<>(adminService.findAllPhotoSessionsByClientId(clientId), HttpStatus.OK);
    }

    @GetMapping("photo_sessions/{photoSessionId}")
    public ResponseEntity<PhotoSession> getPhotoSessionById(@PathVariable UUID photoSessionId) {
        return new ResponseEntity<>(adminService.findPhotoSessionById(photoSessionId), HttpStatus.OK);
    }


    @GetMapping("photos")
    public ResponseEntity<List<Photo>> getAllPhoto() {
        return new ResponseEntity<>(adminService.findAllPhotos(), HttpStatus.OK);
    }

    @GetMapping("photos/{photoId}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable UUID photoId) {
        return new ResponseEntity<>(adminService.findPhotoById(photoId), HttpStatus.OK);
    }

    @GetMapping("photos/{photoSessionId}")
    public ResponseEntity<List<Photo>> getAllPhotoByPhotoSessionId(@PathVariable UUID photoSessionId) {
        return new ResponseEntity<>(adminService.findAllPhotosByPhotoSessionsId(photoSessionId), HttpStatus.OK);
    }

}
