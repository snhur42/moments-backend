package com.instacafe.moments.service.user;

import com.instacafe.moments.dto.AppUserDTO;
import com.instacafe.moments.model.enums.Role;
import com.instacafe.moments.model.user.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser save(AppUserDTO appUserDTO);

    AppUser update(String appUserId, AppUserDTO appUserDTO);

    AppUser findById(String appUserId);

    AppUser findByIdAndRole(String appUserId, Role role);

    List<AppUser> findAll();

    List<AppUser> findAllByRole(Role role);

    void deleteById(String appUserId);
}