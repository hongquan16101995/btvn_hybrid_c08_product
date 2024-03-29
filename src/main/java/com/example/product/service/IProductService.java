package com.example.product.service;

import com.example.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    Page<Product> findAll(Pageable pageable);

    void save(Product product);

    void deleteById(Long id);

    Product findOne(Long id);


}
