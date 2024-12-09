package com.dieguidev.spring_security.service;

import com.dieguidev.spring_security.dto.SaveCategory;
import com.dieguidev.spring_security.persistence.entity.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Page<Category> fndAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(@Valid SaveCategory saveCategory);

    Category updateOneById(Long categoryId, @Valid SaveCategory saveCategory);

    Category disableOneById(Long categoryId);
}
