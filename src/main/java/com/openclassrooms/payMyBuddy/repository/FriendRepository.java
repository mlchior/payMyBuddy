package com.openclassrooms.payMyBuddy.repository;

import com.openclassrooms.payMyBuddy.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

}
