package com.openclassrooms.payMyBuddy.repository;

import com.openclassrooms.payMyBuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    void deleteByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id = :idUser")
    Optional<User> findByIdUser(@Param("idUser") int idUser);

    User findByEmail(String email);


    
   

    /*boolean adresseEmailUnique(String adresseEmail);

    boolean existsByAdresseEmail(String adresseEmail);**/

}
