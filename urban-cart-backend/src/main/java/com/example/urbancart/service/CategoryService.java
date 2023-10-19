package com.example.urbancart.service;

import com.example.urbancart.dto.category.CategoryInputDto;
import com.example.urbancart.model.Category;
import com.example.urbancart.repository.CategoryRepository;
import java.util.List;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {

  public final CategoryRepository categoryRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
    this.categoryRepository = categoryRepository;
    this.modelMapper = modelMapper;
  }

  public Category save(CategoryInputDto category) {
    Category categoryModel = modelMapper.map(category, Category.class);
    return this.categoryRepository.save(categoryModel);
  }

  public Long count() {
    return this.categoryRepository.count();
  }

  public List<Category> findAll() {
    return this.categoryRepository.findAll();
  }

  @Cacheable(key = "#id", unless = "#result == null", value = "category")
  public Category findById(UUID id) {
    return this.categoryRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
  }

  @CachePut(key = "#category.id", unless = "#result == null", value = "category")
  public Category update(UUID id, CategoryInputDto category) {
    Category categoryModel = modelMapper.map(category, Category.class);
    categoryModel.setId(id);
    return this.categoryRepository.save(categoryModel);
  }

  @CacheEvict(key = "#id", value = "category")
  public void deleteById(UUID id) {
    this.categoryRepository.deleteById(id);
  }
}
