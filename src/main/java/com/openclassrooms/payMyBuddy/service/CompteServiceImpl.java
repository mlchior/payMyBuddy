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
    public Iterable<Compte> getComptes() {
        return compteRepository.findAll();
    }

    @Override
    public Optional<Compte> getCompteById(Integer id) {
        return compteRepository.findById(id);
    }

    @Override
    public  Compte addCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    // create un service pour envouer de l'argent d'un compte à un autre
    @Override
    public Boolean envoyerArgent(Integer idCompteEmetteur, Integer idCompteRecepteur, float montant) {

        float montantFrais = montant * 0.005f;
        Compte compteEmetteur = compteRepository.findById(idCompteEmetteur).get();
        Compte compteRecepteur = compteRepository.findById(idCompteRecepteur).get();
        // verifier si le compte emeteur a le compte receveur dans sa liste d'amis


        if (compteEmetteur.getSolde() >= montant) {
            compteEmetteur.setSolde(compteEmetteur.getSolde() - montant);
        }else {
            System.out.println("Solde insuffisant");
        }
        //calculer la taxe de 0,5% sur le montant de la transaction

        compteRecepteur.setSolde(compteRecepteur.getSolde() + (montant - montantFrais));
        compteRepository.save(compteEmetteur);

        Transaction transaction = new Transaction();
        transaction.setUser(compteEmetteur.getUser());
        transaction.setFriend(compteRecepteur.getUser());
        transaction.setMontant(montant);
        transaction.setDateTime(new Date());
        transaction.setFrais(montantFrais);
        transaction.setDescription("Envoi d'argent");
        transactionRepository.save(transaction);

        return true;
    }



    @Override
    public Boolean addMoney(Integer idCompte, float montant) {
        Compte compte = compteRepository.findById(idCompte).get();
        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);
        return true;
    }

    @Override
    public Boolean retirerArgent(Integer idCompte, float montant) {
        Compte compte = compteRepository.findById(idCompte).get();
        if (compte.getSolde() >= montant) {
            compte.setSolde(compte.getSolde() - montant);
        }else {
            System.out.println("Solde insuffisant");
        }
        compteRepository.save(compte);
        return true;
    }
    @Override
    public float showSolde(Integer idUser) {
        Float solde = compteRepository.findSoldeByUserId(idUser);
        return solde;
    }
}

    //calculer la taxe de 0,5% sur le montant de la transaction
    //TODO : fonction pour rajouter de l'argent

