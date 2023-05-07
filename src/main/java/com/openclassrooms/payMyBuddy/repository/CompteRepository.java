package com.openclassrooms.payMyBuddy.repository;

import com.openclassrooms.payMyBuddy.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface
CompteRepository extends JpaRepository<Compte, Integer> {
    @Query("SELECT c.solde FROM Compte c WHERE c.user.id = :userId")
    Float findSoldeByUserId(@Param("userId") Integer userId);


    Compte findByUserId(int currentUserId);



}
