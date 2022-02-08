package com.instacafe.moments.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
public class ClientDTO extends UserDTO {
    @NotEmpty
    private String password;
}
