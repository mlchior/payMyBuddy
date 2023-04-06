package com.openclassrooms.payMyBuddy.repository;

import com.openclassrooms.payMyBuddy.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
CompteRepository extends JpaRepository<Compte, Integer> {

}
