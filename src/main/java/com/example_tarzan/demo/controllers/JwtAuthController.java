package com.example_tarzan.demo.controllers;

import com.example_tarzan.demo.requests.LoginRequest;
import com.example_tarzan.demo.requests.RegisterUserRequest;
import com.example_tarzan.demo.responses.AuthResponse;
import com.example_tarzan.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@CrossOrigin("*")
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.auth(request));
    }
}
