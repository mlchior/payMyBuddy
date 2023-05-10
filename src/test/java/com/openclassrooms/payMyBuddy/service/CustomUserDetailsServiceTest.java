package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsername_whenUserFound_shouldReturnUserDetails() {
        // Given
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);
        user.setMotDePasse(new BCryptPasswordEncoder().encode("password"));
        when(userRepository.findByEmail(email)).thenReturn(user);

        // When
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        // Then
        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals(user.getMotDePasse(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());

        // Verify
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void loadUserByUsername_whenUserNotFound_shouldThrowException() {
        // Given
        String email = "test@test.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // When, Then and Verify
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(email);
        });
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void save_shouldEncodePasswordAndReturnUser() {
        // Given
        User user = new User();
        user.setEmail("test@test.com");
        user.setMotDePasse("password");
        user.setUsername("testUser");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1);
            return savedUser;
        });

        // When
        User savedUser = customUserDetailsService.save(user);

        // Then
        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertTrue(new BCryptPasswordEncoder().matches(user.getMotDePasse(), savedUser.getMotDePasse()));

        // Verify
        verify(userRepository, times(1)).save(any(User.class));
    }

}
