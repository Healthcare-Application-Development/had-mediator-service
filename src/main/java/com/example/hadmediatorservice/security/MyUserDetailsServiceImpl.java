package com.example.hadmediatorservice.security;

import com.example.hadmediatorservice.bean.Login;
import com.example.hadmediatorservice.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(username);
        if (login == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(login);
    }
}
