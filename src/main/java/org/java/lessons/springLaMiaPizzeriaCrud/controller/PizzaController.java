package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import jakarta.validation.Valid;
import org.java.lessons.springLaMiaPizzeriaCrud.model.Pizza;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.IngredientRepository;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PizzaController {
    // Attributi
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    IngredientRepository ingredientRepository;

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
    public String index(@RequestParam Optional<String> search , Model model) {
        List<Pizza> pizzaList;
        // se il parametro di ricerca è presente filtro la lista delle pizze
        if (search.isPresent()) {
            pizzaList = pizzaRepository.findByNameContainingIgnoreCase(search.get());
            // altrimenti prendo tutte le pizze non filtrati
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
        model.addAttribute("ingredientList", ingredientRepository.findAll());
        return "pizzas/form";
    }

    //metodo post per il create che salva le informazioni del form
    @PostMapping("/pizzas/store")
    public String storePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Model model){
        // validare che i dati siano corretti
        if (bindingResult.hasErrors()){
            // ci sono errori devo ricaricare il form
            model.addAttribute("ingredientList", ingredientRepository.findAll());
            return "pizzas/form";
        }
        // setto il timestamp di creazione
        formPizza.setCreatedAt(LocalDateTime.now());
        // se i dati sono corretti salvo il libro su database
        Pizza savedPizza = pizzaRepository.save(formPizza);
        redirectAttributes.addFlashAttribute("message", "La " + savedPizza.getName() + " è stata creata con " + "successo!!");
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }

    // metodo che mostra la pagina di modifica di un libro
    @GetMapping("/pizzas/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        // a partire dall'id recupero i dettagli della Pizza
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()){
            // aggiungo la pizza come attributo del Model
            model.addAttribute("pizza", result.get());
            model.addAttribute("ingredientList", ingredientRepository.findAll());
            //proseguo a restituire la pagina di modifica
            return "/pizzas/form";
        } else {
            // sollevo un'eccezione cin HttpStatus 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza con id " + id + " non trovato!");
        }
    }

    // metodo che riceve il submit del form di edit e salva il libro
    @PostMapping("/pizzas/edit/{id}")
    public String update(@PathVariable Integer id,@Valid @ModelAttribute Pizza formPizza, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, Model model){
        // se valido la pizza
        if (bindingResult.hasErrors()) {
            //se ci sono errori ricarico la pagina con il form
            model.addAttribute("ingredientList", ingredientRepository.findAll());
            return "/pizzas/form";
        } else {
            // recupero la pizza che voglio modificare da db
            Pizza pizzaToEdit =
                    pizzaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
            // se lo trovo modifico solo gli attributi che erano campi del form
            pizzaToEdit.setName(formPizza.getName());
            pizzaToEdit.setDescription(formPizza.getDescription());
            pizzaToEdit.setImageURL(formPizza.getImageURL());
            pizzaToEdit.setPrice(formPizza.getPrice());
            pizzaToEdit.setIngredients(formPizza.getIngredients());
            // se non ci sono errori salvo la pizza
            Pizza savedPizza = pizzaRepository.save(pizzaToEdit);
            redirectAttributes.addFlashAttribute("message", "La " + savedPizza.getName() + " è stata modificata con successo!");
            return "redirect:/pizzas/show/" + savedPizza.getId();
        }
    }

    // metodo per eliminare la pizza da db
    @PostMapping("/pizzas/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        // recuperiamo la pizza con quell'id
        Pizza pizzaToDelete =
                pizzaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // se esiste lo elimino
        pizzaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "La " + pizzaToDelete.getName() + " è stata eliminata!");
        return "redirect:/pizzas";
    }
}
