package com.instacafe.moments.dto;

import com.instacafe.moments.model.enums.City;
import com.instacafe.moments.model.enums.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
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
}
