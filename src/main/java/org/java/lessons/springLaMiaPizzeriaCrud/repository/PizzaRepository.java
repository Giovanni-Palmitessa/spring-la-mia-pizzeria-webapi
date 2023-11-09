package org.java.lessons.springLaMiaPizzeriaCrud.repository;

import org.java.lessons.springLaMiaPizzeriaCrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository  extends JpaRepository<Pizza, Integer> {
}
