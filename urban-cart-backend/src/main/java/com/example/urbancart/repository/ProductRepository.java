package com.example.urbancart.repository;

import com.example.urbancart.model.Product;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  Page<Product> findAllByIsDeletedAndNameContainingIgnoreCase(
      Pageable pageable, Boolean isDeleted, String search);

  @Query("UPDATE Product p SET p.isDeleted = true WHERE p.id = :id")
  @Modifying
  public void softDeleteById(UUID id);
}
