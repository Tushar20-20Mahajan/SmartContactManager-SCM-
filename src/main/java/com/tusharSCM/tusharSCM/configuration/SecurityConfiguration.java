package com.tusharSCM.tusharSCM.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.tusharSCM.tusharSCM.services.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfiguration {

    // User create and login using the java code with in-memory service
    // using bean configuration @Bean
    // Spring Boot security uses UserDetailsService for authentication, having a
    // method named
    // loadUserByUsername, and uses that method to fetch the user details.
    // It matches the loaded user details with the entered details; if matches, then
    // login.

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    // Configuration of authentication provider for Spring Security
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // UserDetailsService object
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // Password encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    // Giving access to only the limited users and protecting the URLs starting with
    // /user
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Configuration
        // Configured the URLs which will be public and which ones will be
        // restricted/authenticated
        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> {
            // The below will permit all the below URLs
            // authorize.requestMatchers("/home", "/signUp").permitAll();
            // The below will restrict (authenticate) all the URLs starting with /user
            authorize.requestMatchers("/user/**").authenticated();
            // The below will permit all other URLs
            authorize.anyRequest().permitAll();
        });

        // Form Default Login
        // If we want to change anything in the form login we will do here
        httpSecurity.formLogin(formLogin -> {
            // formLogin.loginPage("/login");
            // formLogin.loginProcessingUrl("/authenticate");
            // formLogin.successForwardUrl("/user/dashboard");
            // formLogin.failureForwardUrl("/login?error=true");
            // formLogin.usernameParameter("email");
            // formLogin.passwordParameter("password");
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/dashboard", true);
            formLogin.failureUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");


            // Custom Failure Handler to show error messages
             // Custom Authentication Failure Handler Bean
            formLogin.failureHandler(new AuthenticationFailureHandler() {

            @Override
            public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
                request.setAttribute("error", "User not found");
                response.sendRedirect("/login?error=true");
            }

            });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            // @Override
            // public void onAuthenticationSuccess(HttpServletRequest request,
            // HttpServletResponse response,
            // Authentication authentication) throws IOException, ServletException {
            // // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationSuccess'");
            // }

            // });

        });

        return httpSecurity.build();
    }

   
   

    // Password encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
