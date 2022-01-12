package com.instacafe.moments.security.jwt.provider;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface JwtTokenProvider {
    String createToken(String userId, String role);
    boolean validateToken(String token);
    Authentication getAuthentication(String token);
    String getSubject(String token) ;
    String resolveToken(HttpServletRequest request) ;
    Date getExpiredDate(String token);
    boolean IsExpired(String token);
}
