package com.tusharSCM.tusharSCM.configuration;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

        // First Identify the Provider
        // Google or Github
        var oAuth2AuthenticationTocken = (OAuth2AuthenticationToken) authentication;
        String authorizedRegistrationId = oAuth2AuthenticationTocken.getAuthorizedClientRegistrationId();

        System.out.println(authorizedRegistrationId);

        // Save Data before redirecting in the data base
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        // System.out.println(user.getName());
        // user.getAttributes().forEach((key , value)-> {
        // System.out.println(key.toString()+" : "+value.toString());
        // });

        // System.out.println(user.getAuthorities().toString());

        // Create user and save to data base
        User userInfo = new User();
        userInfo.setUserId(UUID.randomUUID().toString());
        userInfo.setEnabled(true);
        userInfo.setEmailVerified(true);
        userInfo.setRoleList(List.of(AppConstants.ROLE_USER));

        // if Google
        if (authorizedRegistrationId.equalsIgnoreCase("google")) {
            // Get the User from Google
            // If Google
            // Extract the data
            String email = user.getAttribute("email").toString();
            String name = user.getAttribute("name").toString();
            String picture = user.getAttribute("picture").toString();

            userInfo.setName(name);
            userInfo.setEmail(email);
            userInfo.setProfilePic(picture);
            userInfo.setPassword("password" + name + user.getName());
            userInfo.setProvider(Providers.GOOGLE);
            userInfo.setProviderUserId(user.getName());
            userInfo.setAbout("Hii , I am " + name + " started with storing Contacts on cloud.");

        } else if (authorizedRegistrationId.equalsIgnoreCase("github")) {
            // Get the User from Github
            // If GitHub
            // Extract the data
            String email = user.getAttribute("email") != null ? user.getAttribute("email").toString()
                    : user.getAttribute("login").toString() + "@github.com";
            String name = user.getAttribute("login") != null ? user.getAttribute("login").toString() : "Unknown";
            String picture = user.getAttribute("avatar_url") != null ? user.getAttribute("avatar_url").toString() : "";

            userInfo.setName(name);
            userInfo.setEmail(email);
            userInfo.setProfilePic(picture);
            userInfo.setPassword("password" + name + user.getName());
            userInfo.setProvider(Providers.GITHUB);
            userInfo.setProviderUserId(user.getName());
            userInfo.setAbout("Hi, I am " + name + " started with storing Contacts on cloud.");
        } else {
            System.out.println("Unknown Provider");
        }

        User findUserIfPresent = userRepo.findByEmail(userInfo.getEmail()).orElse(null);

        if (findUserIfPresent == null) {
            userRepo.save(userInfo);
            System.out.println("New User Saved : " + userInfo.getEmail());
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
