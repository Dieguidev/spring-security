package com.dieguidev.spring_security.persistence.repository.securirty;

import com.dieguidev.spring_security.persistence.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String defaultRole);
}
