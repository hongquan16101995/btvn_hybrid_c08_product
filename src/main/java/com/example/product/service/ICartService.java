package com.example.product.service;

import com.example.product.model.Cart;
import com.example.product.model.CartDetail;
import com.example.product.model.Product;

public interface ICartService {

    Iterable<CartDetail> findAllByStatus();
    Cart findByStatus();

    void addToCart(Product product);

    void up(Long id);

    void down(Long id);

    void delete(Long id);

    void deleteAll();

    void payment(Long cId);
}
