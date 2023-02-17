package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.payMyBuddy.repository.FriendRepository;

import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<Friend> getFriends() {
        return friendRepository.findAll();
    }

    public Optional<Friend> getFriendById(Integer id) {
        return friendRepository.findById(id);
    }

    //TODO: fonction pour rajouter des amis avec a sa liste d'amis avec une adresse email
   // search by email and add to friend list
    public Friend addFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    public Friend findFriendByEmail(String adresseEmail) {
        for (User user : userRepository.findAll()){
            if (user.getAdresseEmail().equals(adresseEmail)){
                Friend friend = new Friend();
                //setUser = id User de la personne qui est connecté
               // friend.setUser();
                friend.setFriend(user);
            }

        }
        return null;


}

    }