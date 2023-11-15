package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import org.java.lessons.springLaMiaPizzeriaCrud.model.Offer;
import org.java.lessons.springLaMiaPizzeriaCrud.model.Pizza;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.OfferRepository;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/offers")
public class OfferController {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    OfferRepository offerRepository;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(()->new RuntimeException("La pizza con id " + pizzaId + " non trovato!"));
        Offer offer = new Offer();
        offer.setStartDate(LocalDate.now());
        offer.setEndDate(LocalDate.now().plusMonths(1));
        offer.setPizza(pizza);
        model.addAttribute("offer", offer);
        return "offers/form";
    }
}
