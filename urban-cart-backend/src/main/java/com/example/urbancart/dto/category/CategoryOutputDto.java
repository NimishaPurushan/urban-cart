package com.example.urbancart.dto.category;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryOutputDto extends CategoryInputDto {
  private UUID id;
}
