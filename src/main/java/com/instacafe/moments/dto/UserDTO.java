package com.instacafe.moments.dto;

import com.instacafe.moments.model.enums.City;
import com.instacafe.moments.model.enums.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String phone;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private City city;
    @NotEmpty
    private Role role;
}
