package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.payMyBuddy.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;


@Service
public interface TransactionService {

    public void createTransaction(User sender, User receiver, float amount);

    public Iterable<Transaction> getTransactions();
    public Optional<Transaction> getTransactionById(Integer id);
    public Transaction addTransaction(Transaction transaction);

    List<Transaction> getTransactionsByUserId(Integer userId, int page, int size);
    public int getTransactionPageCountByUserId(int userId, int size);
}

    // idEmeteur iDdestinataire + montant
// object user avec le findbyid et le destinataure