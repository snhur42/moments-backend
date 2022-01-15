package com.instacafe.moments.rest;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.user.roles.Client;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.service.user.impl.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @GetMapping("photographers")
    public ResponseEntity<List<Photographer>> getAllPhotographers() {
        return new ResponseEntity<>(managerService.findAllPhotographers(), HttpStatus.OK);
    }

    @GetMapping("photographers/{photographerId}")
    public ResponseEntity<Photographer> getPhotographerById(@PathVariable UUID photographerId) {
        return new ResponseEntity<>(managerService.findPhotographerById(photographerId), HttpStatus.OK);
    }

    @PutMapping("photographers/{photographerId}")
    public ResponseEntity<Photographer> updatePhotographer(@PathVariable String photographerId, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(managerService.updatePhotographer(photographerId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_photographer/{photographerId}")
    public void changePhotographerEnableStatus(@PathVariable String photographerId) {
        managerService.changePhotographerEnableStatus(photographerId);
    }




    @PostMapping("create_client")
    public ResponseEntity<Client> createClient(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(managerService.saveClient(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(managerService.findAllClients(), HttpStatus.OK);
    }

    @GetMapping("clients/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID clientId) {
        return new ResponseEntity<>(managerService.findClientById(clientId), HttpStatus.OK);
    }

    @PutMapping("clients/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable String clientId, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(managerService.updateClient(clientId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_client/{clientId}")
    public void changeClientEnableStatus(@PathVariable String clientId) {
        managerService.changeClientEnableStatus(clientId);
    }


//
//
//    @PostMapping("create_photo_session")
//    public ResponseEntity<PhotoSession> createPhotoSession(@Param("managerId") UUID managerId,
//                                                           @RequestParam("clientId") UUID clientId,
//                                                           @RequestParam("photographerId") UUID photographerId,
//                                                           @RequestBody PhotoSession photoSession
//    ) {
//        return new ResponseEntity<>(managerService.savePhotoSession(managerId, clientId, photographerId, photoSession),
//                HttpStatus.CREATED);
//    }
//
//    @GetMapping("photo_sessions")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessions() {
//        return new ResponseEntity<>(managerService.findAllPhotoSessions(), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{managerId}")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByManagerId(@PathVariable UUID managerId) {
//        return new ResponseEntity<>(managerService.findAllPhotoSessionsByManagerId(managerId), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{photographerId}")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByPhotographerId(@PathVariable UUID photographerId) {
//        return new ResponseEntity<>(managerService.findAllPhotoSessionsByPhotographerId(photographerId), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{clientId}")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessionsByClientId(@PathVariable UUID clientId) {
//        return new ResponseEntity<>(managerService.findAllPhotoSessionsByClientId(clientId), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{photoSessionId}")
//    public ResponseEntity<PhotoSession> getPhotoSessionById(@PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(managerService.findPhotoSessionById(photoSessionId), HttpStatus.OK);
//    }
//
//
//    @GetMapping("photos")
//    public ResponseEntity<List<Photo>> getAllPhoto() {
//        return new ResponseEntity<>(managerService.findAllPhotos(), HttpStatus.OK);
//    }
//
//    @GetMapping("photos/{photoId}")
//    public ResponseEntity<Photo> getPhotoById(@PathVariable UUID photoId) {
//        return new ResponseEntity<>(managerService.findPhotoById(photoId), HttpStatus.OK);
//    }
//
//    @GetMapping("photos/{photoSessionId}")
//    public ResponseEntity<List<Photo>> getAllPhotoByPhotoSessionId(@PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(managerService.findAllPhotosByPhotoSessionsId(photoSessionId), HttpStatus.OK);
//    }


}
