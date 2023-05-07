package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class FriendServiceImplTest {
    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Test
    void createFriend() {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setMotDePasse("password1");
        user1.setEmail("user1@example.com");
        userService.saveUser(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setMotDePasse("password2");
        user2.setEmail("user2@example.com");
        userService.saveUser(user2);

        friendService.createFriend(user1.getId(), user2.getId());

        List<Friend> friends = friendService.getFriendsByUser(user1.getId());
        assertEquals(1, friends.size());
        Friend friend = friends.get(0);
        assertEquals(user1, friend.getUser());
        assertEquals(user2, friend.getFriend());
    }

}