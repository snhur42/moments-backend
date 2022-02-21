package com.instacafe.moments.dto.response;

import com.instacafe.moments.dto.AppUserDTO;
import com.instacafe.moments.model.user.AppUser;
import lombok.*;
import org.apache.catalina.User;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuthAccessJwtResponseDTO {
    private boolean success;
    private String accessToken;
    private AppUserDTO user;
}