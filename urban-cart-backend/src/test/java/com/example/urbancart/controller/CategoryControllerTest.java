package com.example.urbancart.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.urbancart.dto.category.CategoryInputDto;
import com.example.urbancart.model.Category;
import com.example.urbancart.service.CategoryService;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
  @Autowired private MockMvc mockMvc;
  @MockBean private CategoryService categoryService;
  @InjectMocks private CategoryController categoryController;

  @Test
  public void findAll_ReturnsListOfCategories() throws Exception {
    List<Category> categoryList = Arrays.asList(new Category(), new Category());
    when(categoryService.findAll()).thenReturn(categoryList);
    mockMvc
        .perform(get("/categories"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").isArray());
  }

  @Test
  public void findById_ReturnsCategory() throws Exception {
    UUID categoryId = UUID.randomUUID();
    Category category = new Category();
    category.setId(categoryId);
    when(categoryService.findById(categoryId)).thenReturn(category);
    mockMvc
        .perform(get("/categories/" + categoryId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(categoryId.toString()));
  }

  @Test
  public void save_ReturnsCategory() throws Exception {
    Category category = new Category();
    when(categoryService.save(any(CategoryInputDto.class))).thenReturn(category);
    mockMvc
        .perform(post("/categories"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void deleteById_ReturnsCategory() throws Exception {
    UUID categoryId = UUID.randomUUID();
    Category category = new Category();
    when(categoryService.update(any(UUID.class), any(CategoryInputDto.class))).thenReturn(category);
    mockMvc.perform(delete("/categories/" + categoryId)).andExpect(status().isNoContent());
  }
}
