package com.tusharSCM.tusharSCM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tusharSCM.tusharSCM.entities.User;
import com.tusharSCM.tusharSCM.helpers.Helper;
import com.tusharSCM.tusharSCM.services.UserService;

@Controller
@RequestMapping("/user")
public class UserControllers {

    
    // User Dashboard Page
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String userDashboard(Model model, Authentication authentication) {
        System.out.println("User Dashboard Handler");

        return "/user/dashboard";
    }

    // User Profile Page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile(Model model, Authentication authentication) {
        System.out.println("User Profile Handler");

        // Fetch data from Data Base
        return "/user/profile";
    }

    // User Add Contact Page

    // User view contact page

    // User edit contact page

    // User delete contact page
}
