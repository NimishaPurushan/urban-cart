package com.example.urbancart.controller;

import com.example.urbancart.model.Product;
import com.example.urbancart.service.ProductService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/products")
public class ProductController {

  public final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product create(@RequestBody Product product) {
    return this.productService.save(product);
  }

  @GetMapping
  public Page<Product> findAll(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "10") Integer size) {
    return this.productService.findAll(page, size);
  }

  @GetMapping("/{id}")
  public Optional<Product> findById(@PathVariable UUID id) {
    return this.productService.findById(id);
  }

  @PutMapping("/{id}")
  public Product update(@PathVariable UUID id, @RequestBody Product product) {
    return this.productService.update(id, product);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    this.productService.remove(id);
  }
}
