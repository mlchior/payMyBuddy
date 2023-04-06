package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Friend;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface FriendService {
    Iterable<Friend> getFriends();

    // getAllByUser(User)

    Optional<Friend> getFriendById(Integer id);
    Friend addFriend(Friend friend);
    Friend findFriendByEmail(String adresseEmail);

    //recuperer tous les noms des amis d'un user
    Iterable<Friend> getFriendsByUser(Integer id);

    //recuperer tous les noms des amis d'un user
    Iterable<Friend> getFriendsByUserId(Integer id);


}
