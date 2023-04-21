package com.openclassrooms.payMyBuddy.controller;



import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.service.CompteService;
import com.openclassrooms.payMyBuddy.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TransferController {
    @Autowired
    private CompteService compteService;

    @Autowired
    private FriendService friendService;


    @GetMapping("/transfer")
    public String showTransfer(Model model) {
        model.addAttribute("solde", compteService.showSolde(3));

        return "transfer";
    }


    }

        //confussion id et id-user dans la base de donnée



    // La personne est connecte du coup j'ai son id en session
    // Definir un id d'une personne que j'ai dans ma base de donnée et afficher ses amis
// modifier m'a bdd pour mieux corresponde au maquette?
    // ou dto.
    // modifier Transaction pour rajouter une description objet et bdd
    // rajouter un jeu de donnée dans mes utilisateur

