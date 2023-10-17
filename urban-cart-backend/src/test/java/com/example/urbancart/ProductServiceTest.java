package com.example.urbancart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import com.example.urbancart.model.Product;
import com.example.urbancart.repository.ProductRepository;
import com.example.urbancart.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock private ProductRepository productRepository;

  @InjectMocks private ProductService productService;

  @Test
  void testFindAll() {
    // Mocking
    List<Product> productList = new ArrayList<>();
    when(productRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productList));

    // Test
    Page<Product> result = productService.findAll(0, 10, "name", "asc");

    // Verify
    assertNotNull(result);
    verify(productRepository, times(1)).findAll(any(Pageable.class));
  }

  @Test
  void testFindById() {
    // Mocking
    UUID productId = UUID.randomUUID();
    Product product = new Product();
    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

    // Test
    Product result = productService.findById(productId);

    // Verify
    assertNotNull(result);
    verify(productRepository, times(1)).findById(productId);
  }

  @Test
  void testFindByIdNotFound() {
    UUID productId = UUID.randomUUID();
    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    assertThrows(ResponseStatusException.class, () -> productService.findById(productId));
    verify(productRepository, times(1)).findById(productId);
  }

  @Test
  void testSave() {
    Product product = new Product();
    when(productRepository.save(product)).thenReturn(product);

    Product result = productService.save(product);
    assertNotNull(result);
    verify(productRepository, times(1)).save(product);
  }

  @Test
  void testUpdate() {
    UUID productId = UUID.randomUUID();
    Product existingProduct = new Product();
    when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
    when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

    Product updatedProduct = new Product();
    updatedProduct.setName("Updated Name");
    Product result = productService.update(productId, updatedProduct);

    assertNotNull(result);
    assertEquals(updatedProduct.getName(), result.getName());
    verify(productRepository, times(1)).findById(productId);
    verify(productRepository, times(1)).save(existingProduct);
  }

  @Test
  void testRemove() {
    productService.remove(UUID.randomUUID());
    verify(productRepository, times(1)).deleteById(any(UUID.class));
  }

  @Test
  void testFindAllSortingDirection() {
    List<Product> productList = new ArrayList<>();
    when(productRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productList));

    Page<Product> resultAsc = productService.findAll(0, 10, "name", "asc");
    Page<Product> resultDesc = productService.findAll(0, 10, "name", "desc");

    assertNotNull(resultDesc);
    assertNotNull(resultAsc);
    verify(productRepository, times(1))
        .findAll(
            argThat(
                (Pageable pageable) -> {
                  Sort.Order order = pageable.getSort().getOrderFor("name");
                  return order != null && order.getDirection().equals(Sort.Direction.DESC);
                }));
    verify(productRepository, times(1))
        .findAll(
            argThat(
                (Pageable pageable) -> {
                  Sort.Order order = pageable.getSort().getOrderFor("name");
                  return order != null && order.getDirection().equals(Sort.Direction.ASC);
                }));
  }
}
