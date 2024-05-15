package com.bearerauth.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bearerauth.authentication.entities.User;
import com.bearerauth.authentication.repositories.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // load user from database
        User user = accountRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        return user;

    }

}
