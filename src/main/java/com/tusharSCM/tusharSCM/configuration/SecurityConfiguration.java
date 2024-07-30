package com.tusharSCM.tusharSCM.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.tusharSCM.tusharSCM.services.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfiguration {

    // user create and login using the java code with in memory service
    // using bean configuration @Bean
    // spring boot security uses user detail service for auth have a method name loadUserByUserName and uses that method to fetch the user details 
    // matched the loaded user details and the entered details if matches then login

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    // configuration of authentication provider for spring security
    @Bean
    public AuthenticationProvider authenticationProvider(){
        //user detail service object
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    // Giving access to only the limited users and protecting the urls starting with /user
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration 
        // Configured the urls which will be public snd which one will be restricted/authenticated
        httpSecurity.authorizeHttpRequests(autherize -> {
            // The below will permit all the below urls 
            //autherize.requestMatchers("/home","/signUp").permitAll();
            // The below will restrict (authenticate) all the urls starting with /user
            autherize.requestMatchers("/user/**").authenticated();
            // The below will permit all other urls 
            autherize.anyRequest().permitAll();
        });

        // Form Default Login
        // if we want to chnage anything in the form login we will do here
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
