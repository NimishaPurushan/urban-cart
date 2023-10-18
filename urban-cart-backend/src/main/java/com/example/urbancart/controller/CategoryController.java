package com.example.urbancart.controller;

import com.example.urbancart.common.CustomPage;
import com.example.urbancart.model.Category;
import com.example.urbancart.service.CategoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public Category create(@RequestBody Category category) {
    return this.categoryService.save(category);
  }

  @GetMapping
  public CustomPage<Category> findAll(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    var data = this.categoryService.findAll(page, size);
    return data;
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable UUID id) {
    return this.categoryService.findById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    this.categoryService.deleteById(id);
  }

  @PostMapping("/{id}")
  public Category update(@PathVariable UUID id, @RequestBody Category category) {
    category.setId(id);
    return this.categoryService.update(category);
  }
}
