package com.example.urbancart.controller;

import com.example.urbancart.model.Category;
import com.example.urbancart.service.CategoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  public final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  public String create(@RequestBody Category category) {
    this.categoryService.saveCategory(category);
    return "Category created";
  }

  @GetMapping
  public Page<Category> findAll(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    return this.categoryService.findAll(page, size);
  }

  @GetMapping("/{id}")
  public Optional<Category> findById(@PathVariable UUID id) {
    return this.categoryService.findById(id);
  }
}
