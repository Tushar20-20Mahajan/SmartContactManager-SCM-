package com.tusharSCM.tusharSCM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tusharSCM.tusharSCM.entities.User;
import com.tusharSCM.tusharSCM.forms.UserForm;
import com.tusharSCM.tusharSCM.helpers.Message;
import com.tusharSCM.tusharSCM.helpers.MessageType;
import com.tusharSCM.tusharSCM.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    // Inject user services
    @Autowired
    private UserService userService;

    // Route For Home
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page Handler");

        // Sending Data To View
        model.addAttribute("name", "Tushar Mahajan");
        model.addAttribute("YouTube", "SportsAround");
        model.addAttribute("GitHub", "https://github.com/Tushar20-20Mahajan");
        return "Home";
    }

    // Route For About
    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About Page Handler");
        return "About";
    }

    // Route For Services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services Page Handler");
        return "Services";
    }

    // Route For Contact
    @RequestMapping("/contact")
    public String contactPage() {
        System.out.println("Contact Page Handler");
        return "Contact";
    }

    // Route For Login
    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("Login Page Handler");
        return "Login";
    }

    // Route For SignUp
    @RequestMapping("/signUp")
    public String signUpPage(Model model) {
        System.out.println("SignUp Page Handler");
        // pass the parameter model and then make a new object userForm of type UserForm
        // and send a blank userForm
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "SignUp";
    }

    // Handler for SignUp -> /do-register
    // Processing Register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm , HttpSession session) {
        System.out.println("Processing Registration");
        // Fetch the data from the form
        System.out.println(userForm);
        // Validate the data and then save it in the data base
        // Extract the data from the userForm and add it into user
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());
        user.setProfilePic(
                "https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg");
        // save the user by the help of user service
        userService.saveUser(user);
        System.out.println("User Saved :");
        // return message "Registration Successful" and redirect to login page

        // add the success or error message (we can also use request but here we will be using session)
        Message message = Message.builder().content("Registration Successful").type(MessageType.blue).build();
        session.setAttribute("message", message);

        // redirect to signUp page 
        return "redirect:/signUp";
    }
}
