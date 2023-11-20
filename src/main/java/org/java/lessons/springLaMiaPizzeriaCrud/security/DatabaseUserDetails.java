package org.java.lessons.springLaMiaPizzeriaCrud.security;

import org.java.lessons.springLaMiaPizzeriaCrud.model.Role;
import org.java.lessons.springLaMiaPizzeriaCrud.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DatabaseUserDetails implements UserDetails {
    // Attributi
    private Integer id;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities = new HashSet<>();

    // costruttore che copia da istanza di User i dati che mi servono
    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        // per ogni ruolo creo una granted authority
        for (Role role : user.getRoles()) {
            this.authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }
}
