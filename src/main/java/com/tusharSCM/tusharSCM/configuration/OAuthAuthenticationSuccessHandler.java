package com.tusharSCM.tusharSCM.configuration;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.tusharSCM.tusharSCM.entities.Providers;
import com.tusharSCM.tusharSCM.entities.User;
import com.tusharSCM.tusharSCM.helpers.AppConstants;
import com.tusharSCM.tusharSCM.repositries.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        System.out.println("Authentication Success Handler OAuth");

        // Save Data before redirecting in the data base
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        // System.out.println(user.getName());
        // user.getAttributes().forEach((key , value)-> {
        // System.out.println(key.toString()+" : "+value.toString());
        // });

        // System.out.println(user.getAuthorities().toString());

        // Extract the data
        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();

        // Create user and save to data base
        User userInfo = new User();
        userInfo.setName(name);
        userInfo.setEmail(email);
        userInfo.setProfilePic(picture);
        userInfo.setPassword("password"+name+user.getName());
        userInfo.setUserId(UUID.randomUUID().toString());
        userInfo.setProvider(Providers.GOOGLE);
        userInfo.setEnabled(true);
        userInfo.setEmailVerified(true);
        userInfo.setProviderUserId(user.getName());
        userInfo.setRoleList(List.of(AppConstants.ROLE_USER));
        userInfo.setAbout("Hii , I am " + name + " started with storing Contacts on cloud.");

        User findUserIfPresent = userRepo.findByEmail(email).orElse(null);

        if(findUserIfPresent == null){
            userRepo.save(userInfo);
            System.out.println("New User Saved : " + email);
        }

        

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
