package com.example.hadmediatorservice.repository;

import com.example.hadmediatorservice.bean.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByUsername(String username);
    Login findByUsernameAndPassword(String username, String password);
    Login findByUsernameAndRole(String username, String role);
}
