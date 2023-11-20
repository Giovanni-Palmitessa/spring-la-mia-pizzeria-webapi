package org.java.lessons.springLaMiaPizzeriaCrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SecurityConfiguration {
    // configurazione su come avere un userDetailService
    @Bean
    public DatabaseUserDetailsService userDetailsService(){
        return new DatabaseUserDetailsService();
    }
}
