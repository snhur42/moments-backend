package com.instacafe.moments.service.auth;

import com.instacafe.moments.model.refresh_token.RefreshToken;
import com.instacafe.moments.repository.refresh_token.RefreshTokenRepository;
import com.instacafe.moments.service.EntityServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


@Transactional
@Service
@Qualifier("refreshToken")
public class RefreshTokenService extends EntityServiceImpl<RefreshToken, RefreshTokenRepository> {
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        super(refreshTokenRepository);
    }

    public boolean isPresent(String refreshToken){
        return repository.findByRefreshToken(refreshToken).isPresent();
    }

    public RefreshToken findByRefreshToken(String refreshToken){
        return repository.findByRefreshToken(refreshToken).get();
    }

    public void deleteAllByUserId(UUID userId) {
        repository.deleteAllByUserId(userId.toString());
    }

    public void  delete(RefreshToken refreshToken){
        repository.delete(refreshToken);
    }
}




