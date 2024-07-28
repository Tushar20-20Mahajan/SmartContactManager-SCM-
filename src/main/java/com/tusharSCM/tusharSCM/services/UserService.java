package com.tusharSCM.tusharSCM.services;

import java.util.Optional;
import java.util.List;

import com.tusharSCM.tusharSCM.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String userId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
     // Add more methods here to add services[logic]
}
