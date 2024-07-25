package com.tusharSCM.tusharSCM.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class PageController {

    // Route For Home 
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page Handler");

        // Sending Data To View
        model.addAttribute("name", "Tushar Mahajan");
        model.addAttribute("YouTube" , "SportsAround");
        model.addAttribute("GitHub" , "https://github.com/Tushar20-20Mahajan");
        return "Home";
    }

    // Route For About
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About Page Handler");
        return "About";
    }


    // Route For Services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services Page Handler");
        return "Services";
    }

    // Route For Contact
    @RequestMapping("/contact")
    public String contactPage(){
        System.out.println("Contact Page Handler");
        return "Contact";
    }

    // Route For Login
    @RequestMapping("/login")
    public String loginPage(){
        System.out.println("Login Page Handler");
        return "Login";
    }

    // Route For SignUp
    @RequestMapping("/signUp")
    public String signUpPage(){
        System.out.println("SignUp Page Handler");
        return "SignUp";
    }

    
    
}
