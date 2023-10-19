package com.example.urbancart.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.urbancart.dto.category.CategoryInputDto;
import com.example.urbancart.model.Category;
import com.example.urbancart.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  @Mock private CategoryRepository categoryRepository;

  @InjectMocks private CategoryService categoryService;

  @Mock private ModelMapper modelMapper;

  @Test
  void testFindAll() {
    List<Category> categoryList = new ArrayList<>();
    when(categoryRepository.findAll()).thenReturn(categoryList);

    var result = categoryService.findAll();

    assertNotNull(result);
    verify(categoryRepository, times(1)).findAll();
  }

  @Test
  void testFindById() {
    UUID categoryId = UUID.randomUUID();
    Category category = new Category();
    when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

    Category result = categoryService.findById(categoryId);

    assertNotNull(result);
    verify(categoryRepository, times(1)).findById(categoryId);
  }

  @Test
  void testSave() {
    Category category = new Category();
    when(modelMapper.map(any(), any())).thenReturn(category);
    when(categoryRepository.save(any(Category.class))).thenReturn(category);

    Category result = categoryService.save(new CategoryInputDto());

    assertNotNull(result);
    verify(categoryRepository, times(1)).save(any(Category.class));
  }

  @Test
  void testDeleteById() {
    UUID categoryId = UUID.randomUUID();
    categoryService.deleteById(categoryId);

    verify(categoryRepository, times(1)).deleteById(categoryId);
  }
}
