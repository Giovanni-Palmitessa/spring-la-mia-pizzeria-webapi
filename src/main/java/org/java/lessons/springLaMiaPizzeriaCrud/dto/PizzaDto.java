package org.java.lessons.springLaMiaPizzeriaCrud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.java.lessons.springLaMiaPizzeriaCrud.model.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PizzaDto {
    // Attributi
    private Integer id;
    @NotBlank(message = "Il titolo non può essere un campo vuoto!")
    @Size(max = 255, message = "Il titolo deve essere < di 255 caratteri!")
    private String name;
    private String description;
    @URL(message = "Il link deve essere un URL valido!")
    private String imageURL;
    private LocalDateTime createdAt;
    @NotNull(message = "Il numero non può essere nullo!")
    @Min(value = 1, message = "Il prezzo non può essere inferiore a 1 €!")
    private double price;
    private MultipartFile coverFile;
    private List<Ingredient> ingredients = new ArrayList<>();

    // getter e setter

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

    public MultipartFile getCoverFile() {
        return coverFile;
    }

    public void setCoverFile(MultipartFile coverFile) {
        this.coverFile = coverFile;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
