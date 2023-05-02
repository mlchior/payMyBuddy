package com.openclassrooms.payMyBuddy.service;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void updateExistingPasswords() {
        // Arrange
        User user1 = new User();
        user1.setMotDePasse("password1");
        User user2 = new User();
        user2.setMotDePasse("password2");
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);
        when(passwordEncoder.encode("password1")).thenReturn("hashedPassword1");
        when(passwordEncoder.encode("password2")).thenReturn("hashedPassword2");

        // Act
        userServiceImpl.updateExistingPasswords();

        // Assert
        assertEquals("hashedPassword1", user1.getMotDePasse());
        assertEquals("hashedPassword2", user2.getMotDePasse());
        verify(userRepository, times(1)).findAll();
        verify(userRepository, times(1)).save(user1);
        verify(userRepository, times(1)).save(user2);
        verify(passwordEncoder, times(1)).encode("password1");
        verify(passwordEncoder, times(1)).encode("password2");

    }
}

