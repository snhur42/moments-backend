package com.instacafe.moments.dto.request;

import com.instacafe.moments.model.enums.City;
import com.instacafe.moments.model.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
public class AppUserRequestDTO {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private City city;
    @NotEmpty
    private Role role;
    @NotEmpty
    private String password;
}
