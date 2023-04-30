package com.openclassrooms.payMyBuddy.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'adresse e-mail : " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getMotDePasse(),
                new ArrayList<>());
    }
    public User save(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setMotDePasse(new BCryptPasswordEncoder().encode(user.getMotDePasse()));
        newUser.setUsername(user.getUsername());
        return userRepository.save(newUser);
    }

}
