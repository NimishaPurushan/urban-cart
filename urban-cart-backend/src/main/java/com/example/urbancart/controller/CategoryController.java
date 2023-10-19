package com.example.urbancart.controller;

import com.example.urbancart.dto.category.CategoryInputDto;
import com.example.urbancart.model.Category;
import com.example.urbancart.service.CategoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  public Category create(@RequestBody CategoryInputDto category) {
    return this.categoryService.save(category);
  }

  @GetMapping
  public List<Category> findAll() {
    return this.categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable UUID id) {
    return this.categoryService.findById(id);
  }

  @PostMapping("/{id}")
  public Category update(@PathVariable UUID id, @RequestBody CategoryInputDto category) {
    return this.categoryService.update(id, category);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    this.categoryService.deleteById(id);
  }
}
