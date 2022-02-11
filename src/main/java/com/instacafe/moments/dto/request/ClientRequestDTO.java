package com.instacafe.moments.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
public class ClientRequestDTO extends AppUserRequestDTO {
    @NotEmpty
    private String password;
}
