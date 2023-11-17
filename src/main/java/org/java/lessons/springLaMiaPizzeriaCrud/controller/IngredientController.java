package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import jakarta.validation.Valid;
import org.java.lessons.springLaMiaPizzeriaCrud.model.Ingredient;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    IngredientRepository ingredientRepository;

    // metodo che mostra la pagina degli ingredienti
    @GetMapping
    public String index(Model model){
        model.addAttribute("ingredientList", ingredientRepository.findAll());
        // passo al model un ingrediente vuoto come attributo ingredientObj del form
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";
    }

    // metodo che salva gli ingredienti
    @PostMapping
    public String save(@Valid @ModelAttribute("ingredientObj") Ingredient ingredient, BindingResult bindingResult,
                       Model model) {
        // testo che la categoria è valida
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", ingredientRepository.findAll());
            return "ingredients/index";
        }
        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    // Metodo che gestisce la richiesta di eliminazione
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }

}
