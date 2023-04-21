package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.payMyBuddy.repository.FriendRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {
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

    public Friend addFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    public Friend findFriendByEmail(String adresseEmail) {
        for (User user : userRepository.findAll()) {
            if (user.getAdresseEmail().equals(adresseEmail)) {
            }
        }
        return null;
    }

    public List<Friend> getFriendsByUser(Integer idUser) {
        return friendRepository.findAllByUser_Id(idUser);
    }
}