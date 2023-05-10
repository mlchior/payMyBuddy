package com.openclassrooms.payMyBuddy.controller;

import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.service.CompteService;
import com.openclassrooms.payMyBuddy.service.FriendService;
import com.openclassrooms.payMyBuddy.service.TransactionService;
import com.openclassrooms.payMyBuddy.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private CompteService compteService;

    @Mock
    private FriendService friendService;

    @Mock
    private TransactionService transactionService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private HomeController homeController;

    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void showHome_shouldReturnHomeView() {
        // Given
        User user = new User("test@test.com", "password", "testUser");
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
        when(authentication.getName()).thenReturn(user.getEmail());
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(transactionService.getTransactionsByUserId(user.getId(), 1, 5)).thenReturn(transactions);
        when(transactionService.getTransactionPageCountByUserId(user.getId(), 5)).thenReturn(2);

        // When
        String viewName = homeController.showHome(model, authentication, 1);

        // Then
        assertEquals("home", viewName);
        verify(model, times(3)).addAttribute(anyString(), any());
    }

    @Test
    void updateAccount_shouldRedirectToHome() {
        // Given
        User user = new User("test@test.com", "password", "testUser");
        when(authentication.getName()).thenReturn(user.getEmail());
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);

        // When
        String redirectUrl = homeController.updateAccount(authentication, "add", 100);

        // Then
        assertEquals("redirect:/home", redirectUrl);
        verify(compteService, times(1)).addAmount(anyInt(), anyFloat());
    }
}
