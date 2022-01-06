package com.instacafe.moments.repository.refresh_token;

import com.instacafe.moments.model.refresh_token.RefreshToken;
import com.instacafe.moments.repository.EntityRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends EntityRepository<RefreshToken> {

    @Modifying
    @Query(value = "delete from RefreshToken r where (r.userId) = (?1)")
    void deleteAllByUserId(String userId);

    @Query(value = "select r from RefreshToken r where (r.userId) = (?1)")
    List<RefreshToken> findAllByUserId(String userId);

    @Query(value = "select r from RefreshToken r where (r.userId) = (?1) and (r.fingerPrint) = (?2)")
    Optional<RefreshToken> findByUserIdAndFingerPrint(String userId, String fingerPrint);
}
