package org.java.lessons.springLaMiaPizzeriaCrud.controller;

import org.java.lessons.springLaMiaPizzeriaCrud.model.User;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.UserRepository;
import org.java.lessons.springLaMiaPizzeriaCrud.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public String index(Authentication authentication, Model model) {
        DatabaseUserDetails principal = (DatabaseUserDetails) authentication.getPrincipal();
        User loggedUser = userRepository.findById(principal.getId()).get();
        model.addAttribute("firstName", loggedUser.getFirstName());
        model.addAttribute("lastName", loggedUser.getLastName());
        // recupero la lista di users e la passo al model
        return "users/index";
    }
}
