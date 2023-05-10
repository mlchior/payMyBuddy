package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Compte;
import com.openclassrooms.payMyBuddy.repository.CompteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompteServiceImplTest {

    @Mock
    private CompteRepository compteRepository;

    @InjectMocks
    private CompteServiceImpl compteService;

    @Test
    void testGetAccountBalance() {
        int currentUserId = 1;
        float expectedAccountBalance = 100f;
        when(compteRepository.findSoldeByUserId(currentUserId)).thenReturn(expectedAccountBalance);

        float accountBalance = compteService.getAccountBalance(currentUserId);

        assertEquals(expectedAccountBalance, accountBalance);
    }

    @Test
    void testAddAmount() {
        int currentUserId = 1;
        float amount = 10f;
        Compte compte = new Compte();
        compte.setSolde(100f);
        when(compteRepository.findByUserId(currentUserId)).thenReturn(compte);
        when(compteRepository.save(compte)).thenReturn(compte);

        compteService.addAmount(currentUserId, amount);

        assertEquals(110f, compte.getSolde());
    }

    @Test
    void testWithdrawAmountWithEnoughBalance() {
        int currentUserId = 1;
        float amount = 10f;
        Compte compte = new Compte();
        compte.setSolde(100f);
        when(compteRepository.findByUserId(currentUserId)).thenReturn(compte);
        when(compteRepository.save(compte)).thenReturn(compte);

        boolean result = compteService.withdrawAmount(currentUserId, amount);

        assertEquals(true, result);
        assertEquals(90f, compte.getSolde());
    }



    @Test
    void testGetCompteByUserId() {
        int currentUserId = 1;
        Compte expectedCompte = new Compte();
        when(compteRepository.findByUserId(currentUserId)).thenReturn(expectedCompte);

        Compte compte = compteService.getCompteByUserId(currentUserId);

        assertEquals(expectedCompte, compte);
    }
}
