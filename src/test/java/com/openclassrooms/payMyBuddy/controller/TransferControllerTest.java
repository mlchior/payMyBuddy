package com.openclassrooms.payMyBuddy.controller;

import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.service.CompteService;
import com.openclassrooms.payMyBuddy.service.FriendService;
import com.openclassrooms.payMyBuddy.service.TransactionService;
import com.openclassrooms.payMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransferControllerTest {

    @Mock
    private CompteService compteService;
    @Mock
    private FriendService friendService;
    @Mock
    private TransactionService transactionService;
    @Mock
    private UserService userService;
    @Mock
    private Authentication authentication;
    @Mock
    private Model model;

    private TransferController transferController;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transferController = new TransferController(compteService, friendService, transactionService, userService);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void showTransfer_shouldReturnTransferView() {
        User user = new User("test@test.com", "password", "testUser");
        List<Friend> friends = Arrays.asList(new Friend(), new Friend(), new Friend());
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
        when(authentication.getName()).thenReturn(user.getEmail());
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(friendService.getFriends(user.getId())).thenReturn(friends);
        when(transactionService.getTransactionsByUserId(user.getId(), 1, 5)).thenReturn(transactions);
        when(transactionService.getTransactionPageCountByUserId(user.getId(), 5)).thenReturn(2);

        String viewName = transferController.showTransfer(model, authentication, 1);

        assertEquals("transfer", viewName);
        verify(model, times(4)).addAttribute(anyString(), any());
    }
}
