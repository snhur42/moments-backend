package com.instacafe.moments.rest;

import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.photo_session.PhotoSession;
import com.instacafe.moments.service.user.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/client")
public class ClientRestController {
    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }
//
//    @GetMapping("photo_sessions")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessions() {
//        return new ResponseEntity<>(clientService.findAllPhotoSessions(), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{photoSessionId}")
//    public ResponseEntity<PhotoSession> getPhotoSessionById(@PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(clientService.findPhotoSessionById(photoSessionId), HttpStatus.OK);
//    }
//
//    @PutMapping("photo_sessions")
//    public ResponseEntity<PhotoSession> updatePhotoSession(@RequestBody PhotoSession photoSession) {
//        return new ResponseEntity<>(clientService.updatePhotoSession(photoSession), HttpStatus.OK);
//    }
//
//    @GetMapping("photos")
//    public ResponseEntity<List<Photo>> getAllPhoto() {
//        return new ResponseEntity<>(clientService.findAllPhotos(), HttpStatus.OK);
//    }
//
//    @GetMapping("photos/{photoId}")
//    public ResponseEntity<Photo> getPhotoById(@PathVariable UUID photoId) {
//        return new ResponseEntity<>(clientService.findPhotoById(photoId), HttpStatus.OK);
//    }
//
//    @GetMapping("photos/{photoSessionId}")
//    public ResponseEntity<List<Photo>> getAllPhotoByPhotoSessionId(@PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(clientService.findAllPhotosByPhotoSessionsId(photoSessionId), HttpStatus.OK);
//    }

}
