package com.example.urbancart.service;

import com.example.urbancart.model.Product;
import com.example.urbancart.repository.ProductRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  public final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Page<Product> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());
    return productRepository.findAll(pageable);
  }

  public Optional<Product> findById(UUID id) {
    return this.productRepository.findById(id);
  }

  public Product save(Product product) {
    return this.productRepository.save(product);
  }

  public Product update(UUID id, Product product) {
    var productToUpdate = this.productRepository.findById(id).get();
    productToUpdate.setName(product.getName());
    productToUpdate.setPrice(product.getPrice());
    productToUpdate.setQuantity(product.getQuantity());
    productToUpdate.setDescription(product.getDescription());
    return this.productRepository.save(product);
  }

  public void remove(UUID id) {
    this.productRepository.deleteById(id);
  }
}
