package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Friend;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface FriendService {
    Iterable<Friend> getFriends();
    Optional<Friend> getFriendById(Integer id);
    Friend addFriend(Friend friend);
    Friend findFriendByEmail(String adresseEmail);

}
