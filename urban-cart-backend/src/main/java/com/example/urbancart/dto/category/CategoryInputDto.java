package com.example.urbancart.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInputDto {
  @NotBlank @NotNull private String name;

  private String description;
}
