package com.example.urbancart.repository;

import com.example.urbancart.model.Product;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAll(Pageable pageable);
}


