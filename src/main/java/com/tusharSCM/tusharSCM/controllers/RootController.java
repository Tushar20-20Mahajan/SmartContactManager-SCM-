package com.tusharSCM.tusharSCM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tusharSCM.tusharSCM.entities.User;
import com.tusharSCM.tusharSCM.helpers.Helper;
import com.tusharSCM.tusharSCM.services.UserService;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    // Passing data of logged in user to every /user
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }

        System.out.println("User Logged In Information Added ");
        String userName = Helper.getEmailOfLogingInUser(authentication);
        System.out.println("User name: " + userName);

        User user = userService.getUserByEmail(userName);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getAbout());
        model.addAttribute("loggedInUser", user);

    }

}
