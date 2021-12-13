package com.instacafe.moments.service.auth.refresh_token;

import com.instacafe.moments.model.refresh_token.RefreshToken;
import com.instacafe.moments.repository.refresh_token.RefreshTokenRepository;
import com.instacafe.moments.service.photo_session.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


public interface RefreshTokenService {
    RefreshToken save(RefreshToken refreshToken);
    RefreshToken update(RefreshToken refreshToken);
    RefreshToken findById(UUID id);
    List<RefreshToken> findAll();
    void deleteAllByUserId(String userId);
    void delete(RefreshToken refreshToken);
}




