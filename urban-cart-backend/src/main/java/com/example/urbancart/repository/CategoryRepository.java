package com.example.urbancart.repository;

import com.example.urbancart.model.Category;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
  Page<Category> findAll(Pageable pageable);
}
