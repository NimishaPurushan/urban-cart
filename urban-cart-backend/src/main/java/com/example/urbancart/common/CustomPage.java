package com.example.urbancart.common;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class CustomPage<T> {
  private List<T> data;
  private int page;
  private int size;
  private long count;
  private long totalPages;

  public CustomPage(Page<T> page) {
    this.data = page.getContent();
    this.count = page.getTotalElements();
    this.page = page.getNumber();
    this.size = page.getSize();
    this.totalPages = page.getTotalPages();
  }
}
