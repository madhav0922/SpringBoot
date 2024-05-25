package com.example.demo.Product.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Used to tell Java to map this entity(object) with the database.
@Data // This automatically creates our getters and setters using LOMBOK.
@Table(name="product") // This tells it exactly which table to use exactly.
// (used Jakarta Persistence (import) from dropdown if asked)
public class Product {
    @Id // Tells spring boot that this is unique identifier in sql table.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Automatically generates (auto increment) the ID in the db table.
    @Column(name = "id") // which column to map it with in the table.
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private Double price;
    @Column(name="quantity")
    private Integer quantity;
}
