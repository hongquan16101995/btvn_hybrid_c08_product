package com.example.product.service;

import com.example.product.model.CartDetail;

public interface ICartDetailService {
    Iterable<CartDetail> findAllByStatus();
}
