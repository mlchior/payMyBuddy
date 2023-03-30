package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Compte;
import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.repository.CompteRepository;
import com.openclassrooms.payMyBuddy.repository.FriendRepository;
import com.openclassrooms.payMyBuddy.repository.TransactionRepository;
import org.hibernate.resource.beans.internal.BeansMessageLogger_$logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public interface CompteService {
    Boolean envoyerArgent(Integer idCompteEmetteur, Integer idCompteRecepteur, float montant);
    Boolean addMoney(Integer idCompte, float montant);
    Boolean retirerArgent(Integer idCompte, float montant);
    public Compte addCompte(Compte compte);
    public Optional getCompteById(Integer id);
    public Iterable<Compte> getComptes();
    }

