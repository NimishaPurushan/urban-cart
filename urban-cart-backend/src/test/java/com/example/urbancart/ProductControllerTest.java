package com.example.urbancart;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.urbancart.controller.ProductController;
import com.example.urbancart.model.Product;
import com.example.urbancart.service.ProductService;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ProductService productService;

  @InjectMocks private ProductController productController;

  @Test
  public void findAll_ReturnsListOfProducts() throws Exception {
    Page<Product> productPage = new PageImpl<>(Arrays.asList(new Product(), new Product()));
    when(productService.findAll(anyInt(), anyInt(), anyString(), anyString()))
        .thenReturn(productPage);
    mockMvc
        .perform(get("/products"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content.length()").value(2));
  }

  @Test
  public void findAll_ReturnsListOfProductsWithPagination() throws Exception {
    Page<Product> productPage = new PageImpl<>(Arrays.asList(new Product(), new Product()));
    when(productService.findAll(anyInt(), anyInt(), anyString(), anyString()))
        .thenReturn(productPage);
    mockMvc
        .perform(get("/products?page=1&size=1&sortBy=price&sortDirection=desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content.length()").value(2));
  }

  @Test
  public void findProductById_ReturnsProduct() throws Exception {
    UUID productId = UUID.randomUUID();
    Product product = new Product();
    when(productService.findById(productId)).thenReturn(product);
    mockMvc
        .perform(get("/products/{id}", productId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void create_ReturnsCreatedProduct() throws Exception {
    Product product = new Product();
    when(productService.save(Mockito.any(Product.class))).thenReturn(product);
    mockMvc
        .perform(post("/products").contentType(MediaType.APPLICATION_JSON).content("{}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void update_ReturnsUpdatedProduct() throws Exception {
    UUID productId = UUID.randomUUID();
    Product product = new Product();
    when(productService.update(Mockito.any(UUID.class), Mockito.any(Product.class)))
        .thenReturn(product);
    mockMvc
        .perform(
            put("/products/{id}", productId).contentType(MediaType.APPLICATION_JSON).content("{}"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void delete_ReturnsNoContent() throws Exception {
    UUID productId = UUID.randomUUID();
    mockMvc.perform(delete("/products/{id}", productId)).andExpect(status().isNoContent());
    verify(productService, times(1)).remove(productId);
  }
}
