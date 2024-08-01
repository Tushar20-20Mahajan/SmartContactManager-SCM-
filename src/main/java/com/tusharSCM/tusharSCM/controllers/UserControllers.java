package com.tusharSCM.tusharSCM.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tusharSCM.tusharSCM.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserControllers {

    // User Dashboard Page 
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String userDashboard(Authentication authentication) {
        System.out.println("User Dashboard Handler");
        String userName = Helper.getEmailOfLogingInUser(authentication);
        System.out.println("User name: " + userName);
        // Fetch data from Data Base
        return "/user/dashboard";
    }

    // User Profile Page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile(Authentication authentication) {
        System.out.println("User Profile Handler");
        String userName = Helper.getEmailOfLogingInUser(authentication);
        System.out.println("User name: " + userName);

        // Fetch data from Data Base 
        return "/user/profile";
    }

    // User Add Contact Page 

    // User view contact page 

    // User edit contact page 

    // User delete contact page
}
