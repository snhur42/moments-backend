package com.instacafe.moments.rest;

import com.instacafe.moments.dto.UserDTO;
import com.instacafe.moments.model.user.roles.Admin;
import com.instacafe.moments.service.user.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("admins")
    public ResponseEntity<Admin> updateAdmin(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(adminService.update(userDTO), HttpStatus.OK);
    }

//    .
}
