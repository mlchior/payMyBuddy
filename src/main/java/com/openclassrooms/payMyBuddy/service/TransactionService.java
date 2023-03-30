package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.payMyBuddy.repository.TransactionRepository;

import java.util.Optional;


@Service
public interface TransactionService {

    public Iterable<Transaction> getTransactions();
    public Optional<Transaction> getTransactionById(Integer id);
    public Transaction addTransaction(Transaction transaction);
}

    // idEmeteur iDdestinataire + montant
// object user avec le findbyid et le destinataure