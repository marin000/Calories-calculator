package com.app.caloriescalculator.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.caloriescalculator.Model.User;
import com.app.caloriescalculator.Validator.UserRegistrationDto;

import java.util.Collection;


public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

    Collection <User> findAllUsers();

    void deleteUser(User user);

    User updateCalorie(String email,Float result);
}