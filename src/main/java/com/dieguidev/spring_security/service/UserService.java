package com.dieguidev.spring_security.service;

import com.dieguidev.spring_security.dto.SaveUser;
import com.dieguidev.spring_security.persistence.entity.User;

public interface UserService {
    User registerOneCustomer(SaveUser newUser);
}
