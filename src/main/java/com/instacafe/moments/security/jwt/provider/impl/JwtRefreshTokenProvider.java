package com.instacafe.moments.security.jwt.provider.impl;

import com.instacafe.moments.service.auth.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtRefreshTokenProvider extends JwtTokenProviderImpl {

    @Autowired
    public JwtRefreshTokenProvider(UserDetailsServiceImpl userDetailsService,
                                  @Value("${auth.jwt.secret.refresh.token}") String secretKey,
                                  @Value("${auth.jwt.expiration.refresh.token}") long validityInMilliseconds) {
        super(userDetailsService, secretKey, validityInMilliseconds);
    }
}