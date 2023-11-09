package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import org.java.lessons.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    // Attributi
    @Autowired
    private PizzaRepository pizzaRepository;
}
