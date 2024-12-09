package com.dieguidev.spring_security.persistence.repository;

import com.dieguidev.spring_security.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
