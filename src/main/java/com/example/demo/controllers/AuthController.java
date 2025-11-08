package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.components.JwtUtil;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody LoginDTO loginDto, BindingResult result){
       
        if (result.hasErrors()) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "status", "error",
                        "message", "Validation failed",
                        "errors", errors
                ));
    
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(loginDto.getEmail());


        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "User login successfully !",
            "data", Map.of("token", token)
    )); 
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> registerUser(@Valid @RequestBody RegisterDTO signUpDto, BindingResult result){

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "status", "error",
                            "message", "Validation failed",
                            "errors", errors
                    ));
        
            }

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return ResponseEntity
            .badRequest()
            .body(Map.of(
                    "status", "error",
                    "message", "Validation failed",
                    "errors", Map.of("email", "Email is already in use !")
            ));
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "User registered successfully !",
            "data", user
    )); 
    }

}
