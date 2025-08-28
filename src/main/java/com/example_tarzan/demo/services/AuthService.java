package com.example_tarzan.demo.services;

import com.example_tarzan.demo.models.User;
import com.example_tarzan.demo.repositories.UserRepository;
import com.example_tarzan.demo.requests.LoginRequest;
import com.example_tarzan.demo.requests.RegisterUserRequest;
import com.example_tarzan.demo.responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse auth(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if (request.getPassword().equals(user.getPassword())){
                String jwtToken = jwtService.generateToken(user);
                return new AuthResponse(jwtToken);
            }
        }
        throw new RuntimeException("無效的憑證!!");
    }
}
