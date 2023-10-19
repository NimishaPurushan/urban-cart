package com.example.urbancart.seeder;

import com.example.urbancart.dto.category.CategoryInputDto;
import com.example.urbancart.service.CategoryService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder {
  private static final String[] CATEGORY_NAMES = {
    "Electronics",
    "Clothing",
    "Furniture",
    "Grocery",
    "Books",
    "Toys",
    "Sports",
    "Beauty",
    "Health",
    "Automotive",
    "Jewelry",
    "Movies",
    "Music",
    "Garden",
    "Tools",
    "Pet",
    "Baby",
    "Industrial",
    "Software",
    "Shoes",
  };

  private final CategoryService categoryService;

  @Autowired
  public CategorySeeder(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostConstruct
  public void seed() {
    if (categoryService.count() > 0) {
      return;
    }
    for (String name : CATEGORY_NAMES) {
      var category = new CategoryInputDto();
      category.setName(name);
      var description = String.format("This is a %s category", name);
      category.setDescription(description);
      categoryService.save(category);
    }
  }
}
