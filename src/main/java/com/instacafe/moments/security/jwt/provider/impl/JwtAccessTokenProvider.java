package com.instacafe.moments.security.jwt.provider.impl;


import com.instacafe.moments.service.auth.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtAccessTokenProvider extends JwtTokenProviderImpl {

    @Autowired
    public JwtAccessTokenProvider(UserDetailsServiceImpl userDetailsService,
                                  @Value("${auth.jwt.secret.access.token}") String secretKey,
                                  @Value("${auth.jwt.expiration.access.token}") long validityInMilliseconds) {
        super(userDetailsService, secretKey, validityInMilliseconds);
    }
}
