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
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterUserRequest request) {
        //1.建立 user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("ROLE_USER");
        userRepository.save(user);
        //2.產出token
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse auth(LoginRequest request) {
        //1.找到對應的 user
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if (request.getPassword().equals(user.getPassword())){
                String jwtToken = jwtService.generateToken(user);
                return new AuthResponse(jwtToken);
            }
        }
        throw new RuntimeException("Invalid credentials!!");
    }
}
