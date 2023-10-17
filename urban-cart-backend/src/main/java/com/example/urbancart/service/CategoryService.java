package com.example.urbancart.service;

import com.example.urbancart.model.Category;
import com.example.urbancart.repository.CategoryRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  public final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category saveCategory(Category category) {
    return this.categoryRepository.save(category);
  }

  public Page<Category> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("name").descending());
    return this.categoryRepository.findAll(pageable);
  }

  public Category updateCategory(Category category) {
    return this.categoryRepository.save(category);
  }

  public Optional<Category> findById(UUID id) {
    return this.categoryRepository.findById(id);
  }
}
