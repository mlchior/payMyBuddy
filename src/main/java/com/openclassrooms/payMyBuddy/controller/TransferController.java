package com.openclassrooms.payMyBuddy.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferController {

    @GetMapping("/transfer")
    public String showExemple(Model model) {
        model.addAttribute("name", "Melchior");
        return "transfer";
    }

}