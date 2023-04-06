package com.openclassrooms.payMyBuddy.controller;



import com.openclassrooms.payMyBuddy.model.Friend;
import com.openclassrooms.payMyBuddy.model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @GetMapping()
    public String showTransfer(Model model) {



        return "transfer";
    }
// modifier m'a bdd pour mieux corresponde au maquette?
    // ou dto.
    // modifier Transaction pour rajouter une description objet et bdd
    // rajouter un jeu de donnée dans mes utilisateur


}