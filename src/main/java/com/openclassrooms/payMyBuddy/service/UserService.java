package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
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

    public Integer save(User user) {
        if (user != null) {
        /**if (adresseEmailUnique(user.getAdresseEmail())){
            return null;
        }*/
            User savedUser = new User();
            savedUser.setAdresseEmail(user.getAdresseEmail());
            savedUser.setMotDePasse(user.getMotDePasse());
            savedUser.setUsername(user.getUsername());
            userRepository.save(savedUser);
            return userRepository.save(savedUser).getId();
    }
        return null;
    }
    // verify if the AdresseEmail is already used by another user
    /*public boolean adresseEmailUnique(String adresseEmail) {
        return userRepository.existsByAdresseEmail(adresseEmail);
    }**/

}
