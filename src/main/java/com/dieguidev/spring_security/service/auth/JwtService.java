package com.dieguidev.spring_security.service.auth;

import com.dieguidev.spring_security.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String generateToken(UserDetails user) {
        return null;
    }
}
