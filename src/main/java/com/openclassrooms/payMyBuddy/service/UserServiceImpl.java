package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateExistingPasswords() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            String plainPassword = user.getMotDePasse();
            String hashedPassword = passwordEncoder.encode(plainPassword);
            user.setMotDePasse(hashedPassword);
            userRepository.save(user);
        }
    }
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int idUser) {
        Optional<User> userOptional = userRepository.findById(idUser);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found with id: " + idUser);
        }
    }


    //delete user by id
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public Integer save(User user) {
        if (user != null) {
            /**if (adresseEmailUnique(user.getEmail())){
             return null;
             }*/
            User savedUser = new User();
            savedUser.setEmail(user.getEmail());
            savedUser.setMotDePasse(user.getMotDePasse());
            savedUser.setUsername(user.getUsername());
            userRepository.save(savedUser);
            return userRepository.save(savedUser).getId();
        }
        return null;
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
    // verify if the AdresseEmail is already used by another user
    /*public boolean adresseEmailUnique(String adresseEmail) {
        return userRepository.existsByAdresseEmail(adresseEmail);
    }**/
}
