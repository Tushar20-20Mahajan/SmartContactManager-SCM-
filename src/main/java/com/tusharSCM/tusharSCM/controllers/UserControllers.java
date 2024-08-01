package com.tusharSCM.tusharSCM.controllers;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserControllers {

    // User Dashboard Page 

    @RequestMapping(value = "/dashboard" , method = RequestMethod.GET)
    public String userDashboard(Principal principal) {
        System.out.println("User Dashboard Handler");
        String name = principal.getName();
        System.out.println("User name :" +name);
        return "/user/dashboard";
    }

    // User Profile Page
    @RequestMapping(value = "/profile" , method = RequestMethod.GET)
    public String userProfile() {
        System.out.println("User Profile Handler");
        return "/user/profile";
    }

    // User Add Contact Page 

    // User view contact page 


    // User edit contact page 


    // user delete contact page

}
