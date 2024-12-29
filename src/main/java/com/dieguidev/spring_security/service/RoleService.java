package com.dieguidev.spring_security.service;

import com.dieguidev.spring_security.persistence.entity.security.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();
}
