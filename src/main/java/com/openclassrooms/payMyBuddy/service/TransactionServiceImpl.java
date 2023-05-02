package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Transaction;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public void createTransaction(User sender, User receiver, float amount) {
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setFrais(amount * 0.005f);
        transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactionsByUserId(Integer userId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "dateTime"));
        Page<Transaction> transactionPage = transactionRepository.findBySenderIdOrReceiverId(userId, userId, pageable);
        return transactionPage.getContent();
    }



    public int getTransactionPageCountByUserId(int userId, int size) {
        Pageable pageable = PageRequest.of(0, size);
        Page<Transaction> transactionPage = transactionRepository.findBySenderIdOrReceiverId(userId, userId, pageable);
        return transactionPage.getTotalPages();
    }

    public Iterable<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return null;
    }


}
