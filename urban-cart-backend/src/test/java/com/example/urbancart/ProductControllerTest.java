package com.example.urbancart;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.urbancart.controller.ProductController;
import com.example.urbancart.model.Product;
import com.example.urbancart.service.ProductService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ProductService productService;

  @InjectMocks private ProductController productController;

  @Test
  public void findAllProducts_ReturnsListOfProducts() throws Exception {
    // Arrange
    List<Product> productList = Arrays.asList(new Product(), new Product());
    when(productService.findAll()).thenReturn(productList);

    // Act and Assert
    mockMvc
        .perform(get("/products"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void findProductById_ReturnsProduct() throws Exception {
    // Arrange
    UUID productId = UUID.randomUUID();
    Product product = new Product();
    when(productService.findById(productId)).thenReturn(Optional.of(product));

    // Act and Assert
    mockMvc
        .perform(get("/products/{id}", productId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void createProduct_ReturnsCreatedProduct() throws Exception {
    // Arrange
    Product product = new Product();
    when(productService.save(Mockito.any(Product.class))).thenReturn(product);

    // Act and Assert
    mockMvc
        .perform(post("/products").contentType(MediaType.APPLICATION_JSON).content("{}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void deleteProduct_ReturnsNoContent() throws Exception {
    UUID productId = UUID.randomUUID();
    mockMvc.perform(delete("/products/{id}", productId)).andExpect(status().isNoContent());

    verify(productService, times(1)).remove(productId);
  }
}
