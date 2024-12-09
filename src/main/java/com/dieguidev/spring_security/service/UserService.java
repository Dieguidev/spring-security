package com.dieguidev.spring_security.service;

import com.dieguidev.spring_security.dto.SaveUser;
import com.dieguidev.spring_security.persistence.entity.User;

import java.util.Optional;

public interface UserService {
    User registerOneCustomer(SaveUser newUser);

    Optional<User> findOneByUsername(String username);
}
