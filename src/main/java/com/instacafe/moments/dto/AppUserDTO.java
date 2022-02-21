package com.instacafe.moments.dto;

import com.instacafe.moments.model.enums.City;
import com.instacafe.moments.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AppUserDTO {
    private String id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Role role;
    private City city;
}
