package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/offers")
public class OfferController {
    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        return "offers/form";
    }
}
