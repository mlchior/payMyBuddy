package com.openclassrooms.payMyBuddy.repository;

import com.openclassrooms.payMyBuddy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Page<Transaction> findBySenderIdOrReceiverId(Integer senderId, Integer receiverId, Pageable pageable);
}

