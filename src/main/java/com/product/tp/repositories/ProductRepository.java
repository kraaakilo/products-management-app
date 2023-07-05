package com.product.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.tp.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}