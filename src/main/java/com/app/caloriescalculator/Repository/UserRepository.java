package com.app.caloriescalculator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.caloriescalculator.Model.User;
@Repository
public interface UserRepository extends JpaRepository < User, Long > {
    User findByEmail(String email);
}