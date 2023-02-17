package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.payMyBuddy.repository.TransactionRepository;

import java.util.Optional;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;



    public Iterable<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer id) {
            return transactionRepository.findById(id);
    }
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // idEmeteur iDdestinataire + montant
}// object user avec le findbyid et le destinataure