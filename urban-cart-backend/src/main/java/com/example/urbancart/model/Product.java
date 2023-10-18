package com.example.urbancart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

  @JsonIgnore
  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  @ManyToMany(mappedBy = "products")
  private Set<Category> categories;

  @JsonIgnore
  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
