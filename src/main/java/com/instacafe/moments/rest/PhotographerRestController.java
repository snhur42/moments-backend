package com.instacafe.moments.rest;

import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.service.user.impl.AppUserServiceImpl;
import com.instacafe.moments.service.user.impl.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/photographer")
public class PhotographerRestController {
    private final PhotographerService photographerService;
    private final AppUserServiceImpl appUserService;

    @Autowired
    public PhotographerRestController(PhotographerService photographerService, AppUserServiceImpl appUserService) {
        this.photographerService = photographerService;
        this.appUserService = appUserService;
    }

    @GetMapping("photographers/{photographerId}")
    public ResponseEntity<AppUser> getPhotographerById(@PathVariable String photographerId) {
        return new ResponseEntity<>(appUserService.findById(photographerId), HttpStatus.OK);
    }

//    @PostMapping("/upload_photos")
//    public void uploadFiles(@RequestParam("data") MultipartFile... files) {
//        this.photographerService.savePhotos(files);
//    }
//
//    @GetMapping("/get_photos")
//    public ResponseEntity<List<String>> getFiles() {
//        return new ResponseEntity<>(this.photographerService.getPhotos(), HttpStatus.OK);
//    }


}
