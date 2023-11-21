package org.java.lessons.springLaMiaPizzeriaCrud.api;

import org.java.lessons.springLaMiaPizzeriaCrud.model.Pizza;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pizzas")
@CrossOrigin
public class PizzaRestController {
    @Autowired
    PizzaRepository pizzaRepository;

    // endpoint per la lista di tutte le pizze
    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> search) {
        if (search.isPresent()) {
            return pizzaRepository.findByNameContainingIgnoreCase(search.get());
            // altrimenti prendo tutti le pizze non filtrate
        } else {
           return pizzaRepository.findAll();
        }
    }
}
