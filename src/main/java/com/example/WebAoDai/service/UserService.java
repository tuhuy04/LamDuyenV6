package com.example.WebAoDai.service;


import com.example.WebAoDai.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {

    Optional<User> findByEmail(String email);

    boolean validateCredentials(String username, String password);

    User saveUser(User user);
}

