package org.java.lessons.springLaMiaPizzeriaCrud.repository;

import org.java.lessons.springLaMiaPizzeriaCrud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
