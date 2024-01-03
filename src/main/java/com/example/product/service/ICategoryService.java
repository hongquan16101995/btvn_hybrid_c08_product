package com.example.product.service;

import com.example.product.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {

    Iterable<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    void save(Category category);

    void deleteById(Long id);

    Category findOne(Long id);
}
