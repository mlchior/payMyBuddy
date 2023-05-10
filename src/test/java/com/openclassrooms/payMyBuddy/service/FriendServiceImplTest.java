package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.FriendRepository;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import com.openclassrooms.payMyBuddy.service.FriendServiceImpl;
import com.openclassrooms.payMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FriendServiceImplTest {
    @Mock
    private FriendRepository friendRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private FriendServiceImpl friendService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createFriend() {
        // Given
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(2);

        when(userService.getUserById(user1.getId())).thenReturn(user1);
        when(userService.getUserById(user2.getId())).thenReturn(user2);

        // When
        friendService.createFriend(user1.getId(), user2.getId());

        // Then
        verify(friendRepository).save(any(Friend.class));
    }

    @Test
    void getFriendsByUser() {
        // Given
        User user = new User();
        user.setId(1);

        when(userService.getUserById(user.getId())).thenReturn(user);

        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend(user, new User()));
        friends.add(new Friend(user, new User()));

        when(friendRepository.findByUser(user)).thenReturn(friends);

        // When
        List<Friend> result = friendService.getFriendsByUser(user.getId());

        // Then
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(friend -> friend.getUser().equals(user)));
    }

    @Test
    void getFriendById() {
        // Given
        Friend friend = new Friend();

        when(friendRepository.findById(friend.getId())).thenReturn(Optional.of(friend));

        // When
        Optional<Friend> result = friendService.getFriendById(friend.getId());

        // Then
        assertEquals(friend, result.orElse(null));
    }

    @Test
    void addFriend() {
        // Given
        Friend friend = new Friend();

        when(friendRepository.save(friend)).thenReturn(friend);

        // When
        Friend result = friendService.addFriend(friend);

        // Then
        assertEquals(friend, result);
    }

    @Test
    void saveFriend() {
        // Given
        Friend friend = new Friend();

        // When
        friendService.saveFriend(friend);

        // Then
        verify(friendRepository).save(friend);
    }
    /*@Test
    void findFriendByEmail_shouldReturnCorrectFriend() {
        // Given
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

        Friend friend1 = new Friend();
        friend1.setUser(user1);
        friend1.setFriend(user2);
        friendRepository.save(friend1);

        // When
        Friend foundFriend = friendService.findFriendByEmail(user2.getEmail());

        // Then
        assertNotNull(foundFriend);
        assertEquals(friend1, foundFriend);
    }*/
}
