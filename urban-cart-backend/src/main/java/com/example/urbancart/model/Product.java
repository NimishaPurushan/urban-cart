package com.example.urbancart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

  @Id @GeneratedValue private UUID id;

  @NotBlank @NotNull private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Min(0)
  @Column(columnDefinition = "BIGINT")
  private BigInteger price; // in cents

  @Min(0)
  private Integer quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
}
