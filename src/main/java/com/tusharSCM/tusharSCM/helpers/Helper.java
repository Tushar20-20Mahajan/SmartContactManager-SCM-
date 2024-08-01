package com.tusharSCM.tusharSCM.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLogingInUser(Authentication authentication) {

        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2AuthenticationToken oauthAuthenticationToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

            // Extract the Registration id
            String registrationId = oauthAuthenticationToken.getAuthorizedClientRegistrationId();

            // Google
            if (registrationId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from google");
                return oauthUser.getAttribute("email").toString();
            }
            // Github
            else if (registrationId.equalsIgnoreCase("github")) {
                System.out.println("Getting email from github");
                String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString()
                    :oauthUser.getAttribute("login").toString() + "@github.com";
                return email;
            }

        } else {
            // Regular authentication
            System.out.println("Getting data from local database");
            return authentication.getName();
        }

        return "";
    }
}
