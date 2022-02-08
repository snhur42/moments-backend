package com.instacafe.moments.rest;

import com.instacafe.moments.service.user.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client/{clientId}")
public class ClientRestController {
    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }


//
//    @GetMapping("photo_sessions")
//    public ResponseEntity<List<PhotoSession>> getAllPhotoSessions(@PathVariable UUID clientId) {
//        return new ResponseEntity<>(clientService.findAllPhotoSessionsByClientId(clientId), HttpStatus.OK);
//    }
//
//    @GetMapping("photo_sessions/{photoSessionId}")
//    public ResponseEntity<PhotoSession> getPhotoSessionById(@PathVariable UUID clientId, @PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(clientService.findPhotoSessionById(clientId, photoSessionId), HttpStatus.OK);
//    }
//
//    @PutMapping("photo_sessions")
//    public ResponseEntity<PhotoSession> updatePhotoSession(@RequestBody PhotoSession photoSession) {
//        return new ResponseEntity<>(clientService.updatePhotoSession(photoSession), HttpStatus.OK);
//    }
//
//    @GetMapping("photos")
//    public ResponseEntity<List<Photo>> getAllPhoto(@PathVariable UUID clientId) {
//        return new ResponseEntity<>(clientService.findAllPhotosByClientId(clientId), HttpStatus.OK);
//    }
//
//    @GetMapping("photos/{photoSessionId}")
//    public ResponseEntity<List<Photo>> getAllPhotoByPhotoSessionId(@PathVariable UUID photoSessionId) {
//        return new ResponseEntity<>(clientService.findAllPhotosByPhotoSessionsId(photoSessionId), HttpStatus.OK);
//    }

}
