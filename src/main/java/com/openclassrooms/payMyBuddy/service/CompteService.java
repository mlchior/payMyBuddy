package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Compte;
import org.springframework.stereotype.Service;

@Service
public interface CompteService {
    public Compte addCompte(Compte compte);
    float getAccountBalance(int currentUserId);

    void addAmount(int currentUserId, float amount);

    boolean withdrawAmount(int currentUserId, float amount);


    Compte getCompteByUserId(int id);
}

