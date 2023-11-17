package org.java.lessons.springLaMiaPizzeriaCrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {
    // Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "Il titolo non può essere un campo vuoto!")
    @Size(max = 255, message = "Il titolo deve essere < di 255 caratteri!")
    private String name;
    @Lob
    private String description;
    @Lob
    @URL(message = "Il link deve essere un URL valido!")
    private String imageURL;
    @Column(nullable = false)
    @NotNull(message = "Il numero non può essere nullo!")
    @Min(value = 1, message = "Il prezzo non può essere inferiore a 1 €!")
    private double price;
    private LocalDateTime createdAt;

    // RELAZIONE ONE TO MANI CON LE OFFERTE
    @OneToMany(mappedBy = "pizza", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Offer> offers = new ArrayList<>();

    // RELAZIONE MANY TO MANY CON GLI INGREDIENTI
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;

    // Getter e setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Offer> getOffers() {
        return offers;
    }
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean hasOffers() {
        return !offers.isEmpty();
    }
}
