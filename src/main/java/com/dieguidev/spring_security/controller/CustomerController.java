package com.dieguidev.spring_security.controller;

import com.dieguidev.spring_security.dto.RegisteredUser;
import com.dieguidev.spring_security.dto.SaveUser;
import com.dieguidev.spring_security.persistence.entity.User;
import com.dieguidev.spring_security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationSevice;


    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne(@RequestBody @Valid SaveUser newUser) {
        RegisteredUser registeredUser = authenticationSevice.registerOneCustomer(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }


    @PreAuthorize("denyAll")
    @GetMapping ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(Arrays.asList());
    }
}
