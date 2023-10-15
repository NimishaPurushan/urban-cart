package com.example.urbancart.model;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    private String name;

    private String description;
    
    private int price;

    private int quantity;
}