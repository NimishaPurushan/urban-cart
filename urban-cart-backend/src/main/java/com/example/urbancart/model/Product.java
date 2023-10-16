package com.example.urbancart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigInteger;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

  @Id @GeneratedValue private UUID id;

  @NotBlank private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @PositiveOrZero
  @Column(columnDefinition = "BIGINT")
  private BigInteger price; // in cents

  @PositiveOrZero private int quantity;
}
