package com.tusharSCM.tusharSCM.repositries;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.tusharSCM.tusharSCM.entities.User;
// Jpa Repo < which entity you are working with , type of id of that entity>
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // extramethods DB related Operations 
    // custom Query methods 
    // custom Finding methods
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email , String password);
    boolean existsByEmail(String email);
}

