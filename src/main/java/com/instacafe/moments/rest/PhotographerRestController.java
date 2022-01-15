package com.instacafe.moments.rest;

import com.instacafe.moments.model.user.roles.Admin;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.service.user.impl.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("api/photographer")
public class PhotographerRestController {
    private final PhotographerService photographerService;

    @Autowired
    public PhotographerRestController(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @GetMapping("photographers/{photographerId}")
    public ResponseEntity<Photographer> getPhotographerById(@PathVariable String photographerId) {
        return new ResponseEntity<>(photographerService.findById(UUID.fromString(photographerId)), HttpStatus.OK);
    }

    @PostMapping("uploadFile")
    public ResponseEntity<Photographer> uploadFile(@PathVariable String photographerId) {
        return new ResponseEntity<>(photographerService.findById(UUID.fromString(photographerId)), HttpStatus.OK);
    }


//
//    @GetMapping("photo_sessions")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessions() {
//        return new ResponseEntity<>(photographerService.findAllPhotoSessions(), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{photoSessionId}")
//    public ResponseEntity<PhotoSession> getPhotoSessionById(@PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(photographerService.findPhotoSessionById(photoSessionId), HttpStatus.OK);
//    }
//
//    @PutMapping("photo_sessions")
//    public ResponseEntity<PhotoSession> updatePhotoSession(@RequestBody PhotoSession photoSession) {
//        return new ResponseEntity<>(photographerService.updatePhotoSession(photoSession), HttpStatus.OK);
//    }
//
//
//    @PostMapping("create_photo")
//    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) {
//        return new ResponseEntity<>(photographerService.createPhoto(photo), HttpStatus.CREATED);
//    }
//
//    @PostMapping("create_photos")
//    public ResponseEntity<List<Photo>> createPhotos(@RequestBody List<Photo> photos) {
//        return new ResponseEntity<>(photographerService.createPhotos(photos), HttpStatus.CREATED);
//    }
//
//    @GetMapping("photos")
//    public ResponseEntity<List<Photo>> getAllPhoto() {
//        return new ResponseEntity<>(photographerService.findAllPhotos(), HttpStatus.OK);
//    }
//
//    @GetMapping("photos/{photoId}")
//    public ResponseEntity<Photo> getPhotoById(@PathVariable UUID photoId) {
//        return new ResponseEntity<>(photographerService.findPhotoById(photoId), HttpStatus.OK);
//    }
//
//    @GetMapping("photos/{photoSessionId}")
//    public ResponseEntity<List<Photo>> getAllPhotoByPhotoSessionId(@PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(photographerService.findAllPhotosByPhotoSessionsId(photoSessionId), HttpStatus.OK);
//    }

}
