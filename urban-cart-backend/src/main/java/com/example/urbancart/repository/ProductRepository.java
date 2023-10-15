package com.example.urbancart.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.urbancart.model.Product;

public interface ProductRepository 
    extends JpaRepository<Product, UUID>{


    
}
