package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Compte;
import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.repository.CompteRepository;
import com.openclassrooms.payMyBuddy.repository.FriendRepository;
import com.openclassrooms.payMyBuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class CompteServiceImpl implements CompteService{
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private FriendRepository friendRepository;

    @Override
    public  Compte addCompte(Compte compte) {
        return compteRepository.save(compte);
    }
    @Override
    public float getAccountBalance(int currentUserId) {
        Float accountBalance = compteRepository.findSoldeByUserId(currentUserId);
        return accountBalance;
    }

    @Override
    public void addAmount(int currentUserId, float amount) {
        Compte compte = compteRepository.findByUserId(currentUserId);
        compte.setSolde(compte.getSolde() + amount);
        compteRepository.save(compte);
    }
    @Override
    public boolean withdrawAmount(int currentUserId, float amount) {
        Compte compte = compteRepository.findByUserId(currentUserId);
        if (compte.getSolde() >= amount) {
            compte.setSolde(compte.getSolde() - amount);
        }else {
            System.out.println("Solde insuffisant");
        }
        compteRepository.save(compte);
        return true;
    }

}

    //calculer la taxe de 0,5% sur le montant de la transaction
    //TODO : fonction pour rajouter de l'argent

