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
    public String showTransfer(Model model, Authentication authentication,
                               @RequestParam(value = "page", defaultValue = "1") int page) {
        User currentUser = userService.getUserByEmail(authentication.getName());
        int currentUserId = currentUser.getId();
        List<Friend> friends = friendService.getFriends(currentUserId);

        model.addAttribute("friends", friends);
        List<Transaction> transactions = transactionService.getTransactionsByUserId(currentUserId, page, 5);
        model.addAttribute("transactions", transactions);
        int totalPages = transactionService.getTransactionPageCountByUserId(currentUserId, 5);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "transfer";
    }

    @PostMapping("/transfer/submit")
    public String submitTransfer(@RequestParam("selectedFriendId") int friendId,
                                 @RequestParam("amount") float amount,
                                 Model model, Authentication authentication) {
        User currentUser = userService.getUserByEmail(authentication.getName());
        int currentUserId = currentUser.getId();
        User sender = userService.getUserById(currentUserId);
        User receiver = friendService.getFriendById(friendId).get().getFriend();

        transactionService.createTransaction(sender, receiver, amount);

        return "redirect:/transfer";
    }
    @PostMapping("/transfer/addConnection")
    public String addConnection(@RequestParam("email") String email, Model model, Authentication authentication) {
        User currentUser = userService.getUserByEmail(authentication.getName());
        int currentUserId = currentUser.getId();
        User friend = userService.getUserByEmail(email);
        friendService.createFriend(currentUserId, friend.getId());


        return "redirect:/transfer";
    }







}

