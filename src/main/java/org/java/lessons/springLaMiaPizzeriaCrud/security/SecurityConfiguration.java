package org.java.lessons.springLaMiaPizzeriaCrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class SecurityConfiguration {
    // configurazione su come avere un userDetailService
    @Bean
    public DatabaseUserDetailsService userDetailsService(){
        return new DatabaseUserDetailsService();
    }

    // configurazione su come avere PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
