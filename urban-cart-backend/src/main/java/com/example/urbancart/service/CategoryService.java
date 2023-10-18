package com.example.urbancart.service;

import com.example.urbancart.common.CustomPage;
import com.example.urbancart.model.Category;
import com.example.urbancart.repository.CategoryRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {

  public final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category save(Category category) {
    return this.categoryRepository.save(category);
  }

  public Long count() {
    return this.categoryRepository.count();
  }

  public CustomPage<Category> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("name").descending());
    var data = this.categoryRepository.findAll(pageable);
    return new CustomPage<Category>(data);
  }

  @Cacheable(key = "#id", unless = "#result == null", value = "category")
  public Category findById(UUID id) {
    return this.categoryRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
  }

  @CachePut(key = "#category.id", unless = "#result == null", value = "category")
  public Category update(Category category) {
    return this.categoryRepository.save(category);
  }

  @CacheEvict(key = "#id", value = "category")
  public void deleteById(UUID id) {
    this.categoryRepository.deleteById(id);
  }
}
