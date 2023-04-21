package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Compte;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompteService {
    Boolean envoyerArgent(Integer idCompteEmetteur, Integer idCompteRecepteur, float montant);
    Boolean addMoney(Integer idCompte, float montant);
    Boolean retirerArgent(Integer idCompte, float montant);
    public Compte addCompte(Compte compte);
    public Optional getCompteById(Integer id);
    public Iterable<Compte> getComptes();

    public float showSolde(Integer idUser);
    }

