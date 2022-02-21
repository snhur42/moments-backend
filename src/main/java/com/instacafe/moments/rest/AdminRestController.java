package com.instacafe.moments.rest;

import com.instacafe.moments.dto.request.AppUserRequestDTO;
import com.instacafe.moments.dto.request.CurrentBriefQuestionsRequestDTO;
import com.instacafe.moments.model.enums.Role;
import com.instacafe.moments.model.photo_session.brief.CurrentBriefQuestions;
import com.instacafe.moments.model.user.AppUser;
import com.instacafe.moments.service.user.impl.AdminService;
import com.instacafe.moments.service.user.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {
    private final AdminService adminService;
    private final AppUserServiceImpl appUserService;

    @Autowired
    public AdminRestController(@Qualifier("adminService") AdminService adminService,
                               @Qualifier("appUserServiceImpl") AppUserServiceImpl appUserService) {
        this.adminService = adminService;
        this.appUserService = appUserService;
    }

    @PostMapping("create_user")
    public ResponseEntity<Boolean> createUser(@RequestBody AppUserRequestDTO userDTO) {
        return new ResponseEntity<>(appUserService.save(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("admins")
    public ResponseEntity<List<AppUser>> getAllAdmins() {
        return new ResponseEntity<>(appUserService.findAll(), HttpStatus.OK);
    }

    @PutMapping("update_admin/{adminId}")
    public ResponseEntity<AppUser> updateAdmin(@PathVariable String adminId, @RequestBody AppUserRequestDTO userDTO) {
        return new ResponseEntity<>(appUserService.update(adminId, userDTO), HttpStatus.OK);
    }

    @GetMapping("managers")
    public ResponseEntity<List<AppUser>> getAllManagers() {
        return new ResponseEntity<>(appUserService.findAllByRole(Role.MANAGER), HttpStatus.OK);
    }

    @PutMapping("update_manager/{managerId}")
    public ResponseEntity<AppUser> updateManager(@PathVariable String managerId, @RequestBody AppUserRequestDTO userDTO) {
        return new ResponseEntity<>(appUserService.update(managerId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_manager/{managerId}")
    public void changeManagerEnableStatus(@PathVariable String managerId) {
        appUserService.changeUserEnableStatus(managerId);
    }

    @GetMapping("photographers")
    public ResponseEntity<List<AppUser>> getAllPhotographers() {
        return new ResponseEntity<>(appUserService.findAllByRole(Role.PHOTOGRAPHER), HttpStatus.OK);
    }

    @PutMapping("update_photographer/{photographerId}")
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

    @PutMapping("admins/{clientId}")
    public ResponseEntity<AppUser> updateClient(@PathVariable String clientId, @RequestBody AppUserRequestDTO userDTO) {
        return new ResponseEntity<>(appUserService.update(clientId, userDTO), HttpStatus.OK);
    }

    @PutMapping("block_client/{clientId}")
    public void changeClientEnableStatus(@PathVariable String clientId) {
        appUserService.changeUserEnableStatus(clientId);
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
    public ResponseEntity<CurrentBriefQuestions> updateBriefQuestion(@RequestBody CurrentBriefQuestionsRequestDTO currentBriefQuestionsRequestDTO) {
        return new ResponseEntity<>(adminService.updateBriefQuestion(currentBriefQuestionsRequestDTO), HttpStatus.OK);
    }
}
