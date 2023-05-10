package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Compte;
import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.CompteRepository;
import com.openclassrooms.payMyBuddy.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CompteService compteService;

    @Mock
    private CompteRepository compteRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private User sender;
    private User receiver;
    private Compte compteEmetteur;
    private Compte compteReceveur;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        sender = new User();
        sender.setId(1);
        sender.setUsername("John");
        sender.setEmail("john.doe@test.com");
        sender.setMotDePasse("password");

        receiver = new User();
        receiver.setId(2);
        receiver.setUsername("Jane");
        receiver.setEmail("jane.doe@test.com");
        receiver.setMotDePasse("password");

        compteEmetteur = new Compte();
        compteEmetteur.setId(1);
        compteEmetteur.setSolde(100f);
        compteEmetteur.setUser(sender);

        compteReceveur = new Compte();
        compteReceveur.setId(2);
        compteReceveur.setSolde(50f);
        compteReceveur.setUser(receiver);

        transaction = new Transaction();
        transaction.setAmount(10f);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setFrais(transaction.getAmount() * 0.005f);
    }

    @Test
    void testCreateTransaction() {
        when(compteService.getCompteByUserId(sender.getId())).thenReturn(compteEmetteur);
        when(compteService.getCompteByUserId(receiver.getId())).thenReturn(compteReceveur);
        when(compteRepository.save(compteEmetteur)).thenReturn(compteEmetteur);
        when(compteRepository.save(compteReceveur)).thenReturn(compteReceveur);

        transactionService.createTransaction(sender, receiver, transaction.getAmount());

        assertEquals(90f, compteEmetteur.getSolde());
        assertEquals(60f, compteReceveur.getSolde());
        assertNotNull(transaction.getDateTime());
        assertNotNull(transaction.getSender());
        assertNotNull(transaction.getReceiver());
        assertNotNull(transaction.getAmount());
        assertNotNull(transaction.getFrais());
        assertEquals(sender, transaction.getSender());
        assertEquals(receiver, transaction.getReceiver());
    }

    @Test
    void testCreateTransactionWithInsufficientBalance() {
        compteEmetteur.setSolde(0f);

        when(compteService.getCompteByUserId(sender.getId())).thenReturn(compteEmetteur);
        when(compteService.getCompteByUserId(receiver.getId())).thenReturn(compteReceveur);

        transactionService.createTransaction(sender, receiver, transaction.getAmount());

        assertEquals(0f, compteEmetteur.getSolde());
        assertEquals(50f, compteReceveur.getSolde());
    }


    @Test
    void testGetTransactions() {
        List<Transaction> transactionList = List.of(transaction);
        when(transactionRepository.findAll()).thenReturn(transactionList);

        Iterable<Transaction> transactions = transactionService.getTransactions();

        assertEquals(transactionList, transactions);
    }

    @Test
    void testGetTransactionById() {
        when(transactionRepository.findById(transaction.getId())).thenReturn(Optional.of(transaction));

        Optional<Transaction> optionalTransaction = transactionService.getTransactionById(transaction.getId());

        assertTrue(optionalTransaction.isPresent());
        assertEquals(transaction, optionalTransaction.get());
    }

    @Test
    void testAddTransaction() {
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction addedTransaction = transactionService.addTransaction(transaction);

        assertEquals(transaction, addedTransaction);
    }
}
