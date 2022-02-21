package com.instacafe.moments.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequestDTO {
    @NotEmpty
    private String email;
}
