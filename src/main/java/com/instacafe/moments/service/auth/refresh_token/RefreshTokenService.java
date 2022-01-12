package com.instacafe.moments.service.auth.refresh_token;

import com.instacafe.moments.model.refresh_token.RefreshToken;

import java.util.List;
import java.util.UUID;


public interface RefreshTokenService {
    RefreshToken save(RefreshToken refreshToken);
    RefreshToken findById(UUID id);
    List<RefreshToken> findAll();
    void deleteAllByUserId(String userId);
    void delete(RefreshToken refreshToken);
    void deleteAll();
}




