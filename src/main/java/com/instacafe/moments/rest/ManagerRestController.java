package com.instacafe.moments.rest;

import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Manager;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.service.user.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/manager")
public class ManagerRestController {
    private final ManagerService managerService;

    @Autowired
    public ManagerRestController(ManagerService managerService) {
        this.managerService = managerService;
    }


    @GetMapping("managers/{managerId}")
    public ResponseEntity<Manager> getManagerById(@PathVariable UUID managerId) {
        return new ResponseEntity<>(managerService.findById(managerId), HttpStatus.OK);
    }

    @PutMapping("managers")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager) {
        return new ResponseEntity<>(managerService.update(manager), HttpStatus.OK);
    }


    @GetMapping("photographers")
    public ResponseEntity<List<Photographer>> getAllPhotographers() {
        return new ResponseEntity<>(managerService.findAllPhotographers(), HttpStatus.OK);
    }

    @GetMapping("photographers/{photographerId}")
    public ResponseEntity<Photographer> getPhotographerById(@PathVariable UUID photographerId) {
        return new ResponseEntity<>(managerService.findPhotographerById(photographerId), HttpStatus.OK);
    }

    @PutMapping("photographers")
    public ResponseEntity<Photographer> updatePhotographer(@RequestBody Photographer photographer) {
        return new ResponseEntity<>(managerService.updatePhotographer(photographer), HttpStatus.OK);
    }


    @PostMapping("create_client")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(managerService.saveClient(client), HttpStatus.CREATED);
    }

    @GetMapping("clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(managerService.findAllClients(), HttpStatus.OK);
    }

    @GetMapping("clients/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID clientId) {
        return new ResponseEntity<>(managerService.findClientById(clientId), HttpStatus.OK);
    }

    @PutMapping("clients")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return new ResponseEntity<>(managerService.updateClient(client), HttpStatus.OK);
    }

    @DeleteMapping("clients/{clientId}")
    public ResponseEntity<?> deleteClientById(@PathVariable UUID clientId) {
        managerService.deleteClientById(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("create_photo_session")
    public ResponseEntity<PhotoSession> createPhotoSession(@Param("managerId") UUID managerId,
                                                           @RequestParam("clientId") UUID clientId,
                                                           @RequestParam("photographerId") UUID photographerId,
                                                           @RequestBody PhotoSession photoSession
    ) {
        return new ResponseEntity<>(managerService.savePhotoSession(managerId, clientId, photographerId, photoSession),
                HttpStatus.CREATED);
    }

    @GetMapping("photo_sessions")
    public ResponseEntity<List<PhotoSession>> getAllPhotoSessions() {
        return new ResponseEntity<>(managerService.findAllPhotoSessions(), HttpStatus.OK);
    }

    @GetMapping("photo_sessions/{managerId}")
    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByManagerId(@PathVariable UUID managerId) {
        return new ResponseEntity<>(managerService.findAllPhotoSessionsByManagerId(managerId), HttpStatus.OK);
    }

    @GetMapping("photo_sessions/{photographerId}")
    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByPhotographerId(@PathVariable UUID photographerId) {
        return new ResponseEntity<>(managerService.findAllPhotoSessionsByPhotographerId(photographerId), HttpStatus.OK);
    }

    @GetMapping("photo_sessions/{clientId}")
    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByClientId(@PathVariable UUID clientId) {
        return new ResponseEntity<>(managerService.findAllPhotoSessionsByClientId(clientId), HttpStatus.OK);
    }

    @GetMapping("photo_sessions/{photoSessionId}")
    public ResponseEntity<PhotoSession> getPhotoSessionById(@PathVariable UUID photoSessionId) {
        return new ResponseEntity<>(managerService.findPhotoSessionById(photoSessionId), HttpStatus.OK);
    }


    @GetMapping("photos")
    public ResponseEntity<List<Photo>> getAllPhoto() {
        return new ResponseEntity<>(managerService.findAllPhotos(), HttpStatus.OK);
    }

    @GetMapping("photos/{photoId}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable UUID photoId) {
        return new ResponseEntity<>(managerService.findPhotoById(photoId), HttpStatus.OK);
    }

    @GetMapping("photos/{photoSessionId}")
    public ResponseEntity<List<Photo>> getAllPhotoByPhotoSessionId(@PathVariable UUID photoSessionId) {
        return new ResponseEntity<>(managerService.findAllPhotosByPhotoSessionsId(photoSessionId), HttpStatus.OK);
    }


}
