package com.openclassrooms.payMyBuddy.controller;



import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.service.CompteService;
import com.openclassrooms.payMyBuddy.service.FriendService;
import com.openclassrooms.payMyBuddy.service.TransactionService;
import com.openclassrooms.payMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.*;

@Controller
public class TransferController {
    @Autowired
    private CompteService compteService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/transfer")
    public String showTransfer(Model model, Authentication authentication) {
        User currentUser = userService.getUserByEmail(authentication.getName());
        int currentUserId = currentUser.getId();
        List<Friend> friends = friendService.getFriends(currentUserId);
        System.out.println("currentUserId: " + currentUserId);

        model.addAttribute("friends", friends);
        List<Transaction> transactions = transactionService.getTransactionsByUserId(currentUserId);
        model.addAttribute("transactions", transactions);
        return "transfer";
    }

    @PostMapping("/transfer/submit")
    public String submitTransfer(@RequestParam("selectedFriendId") int friendId,
                                 @RequestParam("amount") float amount,
                                 Model model) {
        User sender = userService.getUserById(2);
        User receiver = friendService.getFriendById(friendId).get().getFriend();

        transactionService.createTransaction(sender, receiver, amount);

        return "redirect:/transfer";
    }





}

