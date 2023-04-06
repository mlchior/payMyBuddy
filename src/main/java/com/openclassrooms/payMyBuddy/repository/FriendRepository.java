package com.openclassrooms.payMyBuddy.repository;

import com.openclassrooms.payMyBuddy.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
//requete pour recuperer les amis d'un utilisateur
    @Query("SELECT f FROM Friend f WHERE f.id_user =  :user")
    List<Friend> findByUserId(@Param("id_user") Integer id_user);
}
