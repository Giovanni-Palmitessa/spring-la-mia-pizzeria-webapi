package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import org.java.lessons.springLaMiaPizzeriaCrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    IngredientRepository ingredientRepository;
    @GetMapping
    public String index(Model model){
        model.addAttribute("ingredientList", ingredientRepository.findAll());
        // passa al model una categoria vuota come attributo categoryObj del form
        model.addAttribute("categoryObj", new Category());
        return "categories/index";
    }
}
