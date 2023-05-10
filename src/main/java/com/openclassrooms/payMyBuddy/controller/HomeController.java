package com.openclassrooms.payMyBuddy.controller;

import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.model.User;

import com.openclassrooms.payMyBuddy.service.CompteService;
import com.openclassrooms.payMyBuddy.service.FriendService;
import com.openclassrooms.payMyBuddy.service.TransactionService;
import com.openclassrooms.payMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompteService compteService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/home")
    public String showHome(Model model, Authentication authentication,
                           @RequestParam(value = "page", defaultValue = "1") int page) {
        User currentUser = userService.getUserByEmail(authentication.getName());
        int currentUserId = currentUser.getId();

        // Récupérer le solde du compte et l'ajouter au modèle
        float accountBalance = compteService.getAccountBalance(currentUserId);
        model.addAttribute("accountBalance", accountBalance);

        // Récupérer la liste des transactions et l'ajouter au modèle
        List<Transaction> transactions = transactionService.getTransactionsByUserId(currentUserId, page, 5);
        model.addAttribute("transactions", transactions);
        int totalPages = transactionService.getTransactionPageCountByUserId(currentUserId, 5);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        // Ajouter les informations de l'utilisateur au modèle
        model.addAttribute("user", currentUser);

        return "home";
    }
    @PostMapping("/account/update")
    public String updateAccount(Authentication authentication,
                                @RequestParam("action") String action,
                                @RequestParam("amount") float amount) {
        User currentUser = userService.getUserByEmail(authentication.getName());
        int currentUserId = currentUser.getId();

        if (action.equals("add")) {
            // Ajouter de l'argent
            compteService.addAmount(currentUserId, amount);
        } else if (action.equals("withdraw")) {
            // Retirer de l'argent
            boolean success = compteService.withdrawAmount(currentUserId, amount);

            if (!success) {
                // Gérer l'échec du retrait (par exemple, si le solde est insuffisant)
            }
        }

        // Rediriger vers la page d'accueil après la mise à jour du compte
        return "redirect:/home";
    }
}
