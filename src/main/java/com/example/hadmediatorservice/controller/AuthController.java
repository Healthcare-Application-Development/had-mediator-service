package com.example.hadmediatorservice.controller;

import com.example.hadmediatorservice.bean.AuthenticationResponse;
import com.example.hadmediatorservice.bean.Login;
import com.example.hadmediatorservice.bean.Response;
import com.example.hadmediatorservice.encryption.AESUtils;
import com.example.hadmediatorservice.repository.LoginRepository;
import com.example.hadmediatorservice.security.MyUserDetailsServiceImpl;
import com.example.hadmediatorservice.security.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;

    @Autowired
    TokenManager tokenManager;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    AESUtils aesUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody Login login) throws Exception {
        String password = aesUtils.decrypt(login.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUsername(), password));
        } catch (final BadCredentialsException ex) {
            System.out.println(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(login.getUsername());
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        Login retreivedLogin = loginRepository.findByUsernameAndRole(login.getUsername(), login.getRole());
        if (retreivedLogin == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        authenticationResponse.setAccessToken(tokenManager.generateToken(userDetails));
        authenticationResponse.setId(retreivedLogin.getUsername());
        authenticationResponse.setRole(retreivedLogin.getRole());
        return new ResponseEntity<>(new Response(authenticationResponse, 200), HttpStatus.OK);
    }

}

