package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import org.java.lessons.springLaMiaPizzeriaCrud.model.Pizza;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PizzaController {
    // Attributi
    @Autowired
    private PizzaRepository pizzaRepository;

    // Homepage
    @GetMapping("home")
    public String homePage(Model model){
        String home = "Sono la Homepage";
        model.addAttribute("homePage", home);
        return "home-page";
    }

    // Mapping che prende e ci mostra lista pizze
    @GetMapping("/pizzas")
    public String index(Model model) {

        List<Pizza> pizzaList = pizzaRepository.findAll();
        model.addAttribute("pizzaList", pizzaList);
        return "pizzas/index";
    }
}
