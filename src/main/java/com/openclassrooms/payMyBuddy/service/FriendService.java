package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Friend;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface FriendService {
    List<Friend> getFriends(int idUser);

    // getAllByUser(User)

    Optional<Friend> getFriendById(Integer id);
    Friend addFriend(Friend friend);
    Friend findFriendByEmail(String adresseEmail);

    //recuperer tous les noms des amis d'un user
    List<Friend> getFriendsByUser(Integer idUser);

    void saveFriend(Friend newFriend);

    //recuperer tous les i



}
