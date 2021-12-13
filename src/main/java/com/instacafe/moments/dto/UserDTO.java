package com.instacafe.moments.dto;

import com.instacafe.moments.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private City city;
}
