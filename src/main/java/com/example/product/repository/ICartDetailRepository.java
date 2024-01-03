package com.example.product.repository;

import com.example.product.model.Cart;
import com.example.product.model.CartDetail;
import com.example.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findByProductAndCart(Product product, Cart cart);

    Iterable<CartDetail> findAllByCartStatus(Boolean status);
}
