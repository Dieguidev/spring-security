package com.dieguidev.spring_security.service.impl;

import com.dieguidev.spring_security.dto.SaveUser;
import com.dieguidev.spring_security.exception.InvalidPasswordException;
import com.dieguidev.spring_security.persistence.entity.User;
import com.dieguidev.spring_security.persistence.repository.UserRepository;
import com.dieguidev.spring_security.persistence.utils.Role;
import com.dieguidev.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerOneCustomer(SaveUser newUser) {

        validatePassword(newUser);

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePassword(SaveUser newUser) {
        if (!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())) {
            throw new InvalidPasswordException("Password don't match");
        }

        if (!newUser.getPassword().equals(newUser.getRepeatedPassword())) {
            throw new InvalidPasswordException("Password don't match");
        }
    }
}
