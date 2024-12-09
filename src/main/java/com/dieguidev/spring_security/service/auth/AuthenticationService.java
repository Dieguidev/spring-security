package com.dieguidev.spring_security.service.auth;

import com.dieguidev.spring_security.dto.RegisteredUser;
import com.dieguidev.spring_security.dto.SaveUser;
import com.dieguidev.spring_security.persistence.entity.User;
import com.dieguidev.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    public RegisteredUser registerOneCustomer( SaveUser newUser) {
        User user = userService.registerOneCustomer(newUser);

        RegisteredUser userDto = new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user);
        userDto.setJwt(jwt);

        return userDto;
    }
}
