package com.instacafe.moments.service.user.impl;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.photo_session.Photo;
import com.instacafe.moments.model.user.roles.Photographer;
import com.instacafe.moments.repository.photo_session.PhotoRepository;
import com.instacafe.moments.repository.photo_session.PhotoSessionRepository;
import com.instacafe.moments.repository.user.PhotographerRepository;
import com.instacafe.moments.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier("photographerService")
public class PhotographerService extends UserServiceImpl<Photographer, PhotographerRepository> {
    private final PhotoSessionRepository photoSessionRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotographerService(PhotographerRepository photographerRepository,
                               PasswordEncoder passwordEncoder,
                               PhotoRepository photoRepository,
                               PhotoSessionRepository photoSessionRepository) {
        super(passwordEncoder, photographerRepository);
        this.photoSessionRepository = photoSessionRepository;
        this.photoRepository = photoRepository;

    }

    @Override
    public Photographer save(UserDTO userDTO) {
        Photographer photographer = new Photographer();
        return repository.save(this.parseUserFromUserDTO(photographer, userDTO));
    }

    @Override
    public Photographer update(String photographerId, UserDTO userDTO) {
        return this.updateUserFromUserDTO(photographerId, userDTO);
    }

    public void savePhotos(MultipartFile... files) {
        try {
            String OUT_PATH = "/Users/vitaliiprovotar/IdeaProjects/moments-backend/src/main/webapp/WEB-INF/images/";

            // read and write the file to the local folder
            Arrays.asList(files).stream().forEach(file -> {
                byte[] bytes = new byte[0];
                try {
                    bytes = file.getBytes();
                    Path path = Paths.get(OUT_PATH + file.getOriginalFilename());
                    Files.write(path, bytes);
                    this.photoRepository.save(new Photo("http://localhost:8800/images/" + file.getOriginalFilename()));
                } catch (IOException e) {

                }
            });


        } catch (Exception e) {

        }
    }

    public List<String> getPhotos() {
        return this.photoRepository.findAll().stream().map(Photo::getPath).collect(Collectors.toList());
    }

}