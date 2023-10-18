package com.example.urbancart.service;

import com.example.urbancart.common.CustomPage;
import com.example.urbancart.model.Product;
import com.example.urbancart.repository.ProductRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@CacheConfig
@Service
public class ProductService {

  public final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public CustomPage<Product> findAll(
      int page, int size, String sortBy, String sortDirection, Boolean isDeleted, String search) {
    Sort.Direction direction =
        sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
    var data =
        productRepository.findAllByIsDeletedAndNameContainingIgnoreCase(
            pageable, isDeleted, search);
    return new CustomPage<Product>(data);
  }

  @Cacheable(key = "#id", unless = "#result == null", value = "product")
  public Product findById(UUID id) {
    return this.productRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
  }

  public Product save(Product product) {
    return this.productRepository.save(product);
  }

  @CachePut(key = "#id", unless = "#result == null", value = "product")
  public Product update(UUID id, Product product) {
    var productToUpdate = findById(id);
    productToUpdate.setName(product.getName());
    productToUpdate.setPrice(product.getPrice());
    productToUpdate.setQuantity(product.getQuantity());
    productToUpdate.setDescription(product.getDescription());
    return this.productRepository.save(productToUpdate);
  }

  @CacheEvict(key = "#id", value = "product")
  public void remove(UUID id, Boolean isHardDelete) {
    if (isHardDelete) this.productRepository.deleteById(id);
    else this.productRepository.softDeleteById(id);
  }
}
