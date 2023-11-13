package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import jakarta.validation.Valid;
import org.java.lessons.springLaMiaPizzeriaCrud.model.Pizza;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PizzaController {
    // Attributi
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String home() {
//        throw new RuntimeException("FORCE ERROR"); serve per forzare errori
        return "redirect:/home";
    }

    // Homepage
    @GetMapping("/home")
    public String homePage(Model model){
        String home = "Sono la Homepage";
        model.addAttribute("homePage", home);
        return "home-page";
    }

    // Mapping che prende e ci mostra lista pizze
    @GetMapping("/pizzas")
    /* senza ricerca
    public String index(Model model) {

        List<Pizza> pizzaList = pizzaRepository.findAll();
        model.addAttribute("pizzaList", pizzaList);
        return "pizzas/index";
    }*/
    public String index(@RequestParam(name = "search") Optional<String> search , Model model) {
        List<Pizza> pizzaList;
        // se il parametro di ricerca è presente filtro la lista delle pizze
        if (search.isPresent()) {
            pizzaList = pizzaRepository.findByNameContainingIgnoreCase(search.get());
            // altrimenti prendo tutti i libri non filtrati
        } else {
            pizzaList = pizzaRepository.findAll();
        }
        // passo al template la lista delle pizze
        model.addAttribute("pizzaList", pizzaList);
        model.addAttribute("searchKeyword", search.orElse(""));
        return "pizzas/index";
    }

    // show che ci mostra i dettagli delle pizze
    @GetMapping("/pizzas/show/{id}")
    public String show(@PathVariable Integer id, Model model){
        Optional<Pizza> result = pizzaRepository.findById(id);
        // verifico se il risultato è presente
        if (result.isPresent()) {
            // passo al template l'oggetto book
            model.addAttribute("pizza", result.get());
            return "pizzas/show";
        } else {
            // se non ho trovato il libro
            // sollevo eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found!");
        }
    }

    // Metodo che mostra il form di creazione della pizza
    @GetMapping("/pizzas/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    //metodo post per il create che salva le informazioni del form
    @PostMapping("/pizzas/store")
    public String storePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult){
        // validare che i dati siano corretti
        if (bindingResult.hasErrors()){
            // ci sono errori devo ricaricare il form
            return "pizzas/create";
        }
        // setto il timestamp di creazione
        formPizza.setCreatedAt(LocalDateTime.now());
        // se i dati sono corretti salvo il libro su database
        Pizza savedPizza = pizzaRepository.save(formPizza);
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }
}
