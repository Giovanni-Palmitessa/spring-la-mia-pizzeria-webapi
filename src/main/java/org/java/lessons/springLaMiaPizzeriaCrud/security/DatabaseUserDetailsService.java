package org.java.lessons.springLaMiaPizzeriaCrud.security;

import org.java.lessons.springLaMiaPizzeriaCrud.model.User;
import org.java.lessons.springLaMiaPizzeriaCrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // prendo lo username che viene dal form del login ecerco su db un user con quella email
        Optional<User> loggedUser = userRepository.findByEmail(username);
        // se logged user è presente, c'è utente con quella email
        if (loggedUser.isPresent()){
            return new DatabaseUserDetails(loggedUser.get());
        } else {
            // se non è presente tiro un eccezione
            throw new UsernameNotFoundException(username);
        }
    }
}
