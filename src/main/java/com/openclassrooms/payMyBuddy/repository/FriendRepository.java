package com.openclassrooms.payMyBuddy.repository;

import com.openclassrooms.payMyBuddy.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
    List<Friend> findByUserId(int userId);
    List<Friend> findAllByUser_Id(Integer idUser);
}
