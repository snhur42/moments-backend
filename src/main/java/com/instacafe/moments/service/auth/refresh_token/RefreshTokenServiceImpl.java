package com.instacafe.moments.service.auth.refresh_token;

import com.instacafe.moments.model.refresh_token.RefreshToken;
import com.instacafe.moments.repository.refresh_token.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Transactional
@Service
@Qualifier("refreshToken")
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository repository;

    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return repository.save(refreshToken);
    }

    @Override
    public RefreshToken update(RefreshToken refreshToken) {
        return repository.save(refreshToken);
    }

    @Override
    public RefreshToken findById(UUID id) {
        return repository.findById(id).get();
    }

    public RefreshToken findByUserIdAndFingerPrint(String userId, String fingerPrint) {
        return repository.findByUserIdAndFingerPrint(userId, fingerPrint).get();
    }

    public List<RefreshToken> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<RefreshToken> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAllByUserId(String userId) {
        repository.deleteAllByUserId(userId);
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        repository.delete(refreshToken);
    }
}




