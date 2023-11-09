package org.java.lessons.springLaMiaPizzeriaCrud.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pizzas")
public class Pizza {
    // Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Lob
    private String description;
    private String imageURL;
    @Column(nullable = false)
    private double price;
    private LocalDateTime createdAt;
}
