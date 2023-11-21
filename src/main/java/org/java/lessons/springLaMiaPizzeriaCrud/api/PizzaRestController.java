package org.java.lessons.springLaMiaPizzeriaCrud.api;

import jakarta.validation.Valid;
import org.java.lessons.springLaMiaPizzeriaCrud.model.Pizza;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    // endpoint dettagli singola pizza presa per id
    @GetMapping("/{id}")
    public Pizza show(@PathVariable Integer id) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        // verifico se il risultato è presente
        if (result.isPresent()) {
            return result.get();
        } else {
            // se non ho trovato la pizza
            // sollevo eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found!");
        }
    }

    // endpoint creazione libro
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza){
        try {
            return pizzaRepository.save(pizza);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // endpoint modifica libro
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        /*try {
            return pizzaRepository.save(pizza);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found!");
        }*/
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            return pizzaRepository.save(pizza);
        } else {
            // se non ho trovato la pizza
            // sollevo eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found!");
        }
    }

    // endpoint per cancellare libro
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            pizzaRepository.findById(id);
            pizzaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found!");
        }
    }

    @GetMapping("/page/v1")
    public Page<Pizza> pageIndex(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return pizzaRepository.findAll(pageable);
    }
}
