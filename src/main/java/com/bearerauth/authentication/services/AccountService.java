package com.bearerauth.authentication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bearerauth.authentication.entities.JwtRequest;
import com.bearerauth.authentication.entities.Role;
import com.bearerauth.authentication.entities.User;
import com.bearerauth.authentication.entities.UserDao;
import com.bearerauth.authentication.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerAdmin(UserDao userDao) {
        User user = new User();
        user.setName(userDao.getName());
        user.setEmail(userDao.getEmail());
        user.setPassword(passwordEncoder.encode(userDao.getPassword()));
        user.setRole(Role.ADMIN);
        User userData = accountRepository.save(user);
        return userData;
    }

    public List<User> getAllUsers() {
        return accountRepository.findAll();
    }

    public User registerUser(UserDao userDao) {
        User user = new User();
        user.setName(userDao.getName());
        user.setEmail(userDao.getEmail());
        user.setPassword(passwordEncoder.encode(userDao.getPassword()));
        user.setRole(Role.USER);
        User userData = accountRepository.save(user);
        return userData;
    }

    public Optional<User> loginAdmin(JwtRequest request) {
        Optional<User> user = accountRepository.findByEmail(request.getEmail());
        if (user.get().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.ADMIN.toString()))) {
            return user;
        } else {
            return Optional.empty();
        }
    }
}
