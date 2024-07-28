package com.tusharSCM.tusharSCM.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tusharSCM.tusharSCM.entities.User;
import com.tusharSCM.tusharSCM.helpers.ResourceNotFoundException;
import com.tusharSCM.tusharSCM.repositries.UserRepository;

@Service
/**
 * This class is an implementation of the UserService interface in Java.
 */
// `UserServiceImplementation` is a class that implements the `UserService` interface in
// Java. It is annotated with `@Service`, indicating that it is a Spring service
// component.

public class UserServiceImplementation implements UserService
{
    @Autowired
    private UserRepository userRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // First we have to generate user id 
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // encode password 
        //user.setPassword(userId);

        // set users profile pic 
        //user.setProfilePic(url);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
       User updatedUser = userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not Found"));
       updatedUser.setName(user.getName());
       updatedUser.setEmail(user.getEmail());
       updatedUser.setAbout(user.getAbout());
       updatedUser.setPassword(user.getPassword());
       updatedUser.setPhoneNumber(user.getPhoneNumber());
       updatedUser.setProfilePic(user.getProfilePic());
       updatedUser.setEnabled(user.isEnabled());
       updatedUser.setEmailVerified(user.isEmailVerified());
       updatedUser.setPhoneVerified(user.isPhoneVerified());
       updatedUser.setProvider(user.getProvider());
       updatedUser.setProviderUserId(user.getProviderUserId());

       // save the user in the data base 
       User theUpdatedUser = userRepo.save(updatedUser);
       return Optional.ofNullable(theUpdatedUser);


    }

    @Override
    public void deleteUser(String id) {
        User updatedUser = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not Found"));
        userRepo.delete(updatedUser);
        
    }

    @Override
    public boolean isUserExist(String userId) {
        User updatedUser = userRepo.findById(userId).orElseThrow(null);
        return updatedUser != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User updatedUser = userRepo.findByEmail(email).orElseThrow(null);
        return updatedUser != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }


}
