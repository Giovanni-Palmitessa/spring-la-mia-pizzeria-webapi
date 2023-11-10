package org.java.lessons.springLaMiaPizzeriaCrud.repository;

import org.java.lessons.springLaMiaPizzeriaCrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository  extends JpaRepository<Pizza, Integer> {
    // query custom per ricerca su title e authors
    List<Pizza> findByNameContainingIgnoreCase(String nameKeyword);
}
