package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUser_shouldReturnUserId() {
        User user = new User("test@test.com", "password", "testUser");

        when(passwordEncoder.encode(user.getMotDePasse())).thenReturn(user.getMotDePasse());
        when(userRepository.save(any(User.class))).thenReturn(user);

        Integer userId = userService.save(user);

        assertNotNull(userId);
    }

    @Test
    public void getUserByEmail_shouldReturnUser() {
        User user = new User("test@test.com", "password", "testUser");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User foundUser = userService.getUserByEmail(user.getEmail());

        assertNotNull(foundUser);
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    public void getUserById_shouldReturnUser() {
        User user = new User("test@test.com", "password", "testUser");
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.getUserById(user.getId());

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    public void deleteById_shouldDeleteUser() {
        User user = new User("test@test.com", "password", "testUser");

        when(userRepository.save(any(User.class))).thenReturn(user);

        Integer userId = userService.save(user);

        userService.deleteById(userId);

        assertEquals(0, userRepository.count());
    }
}
