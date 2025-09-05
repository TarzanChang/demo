package com.example_tarzan.demo.controllers;

import com.example_tarzan.demo.models.User;
import com.example_tarzan.demo.repositories.UserRepository;
import com.example_tarzan.demo.requests.LoginRequest;
import com.example_tarzan.demo.requests.RegisterUserRequest;
import com.example_tarzan.demo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/session")
@CrossOrigin("*")
public class SessionAuthController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public SessionAuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request, HttpSession session){
        Optional<User> user = userService.findByUsernameAndPassword(request.getUsername(),request.getPassword());
        if(user.isPresent()){
            session.setAttribute("userId",user.get().getId());
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<User> user = userRepository.findById(userId);
        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
       session.invalidate();
        return ResponseEntity.ok().build();
    }

    //1. 註冊會員 -> 輸入帳號、密碼 -> 註冊成功 -> 登出 -> 登入頁面 -> 再輸入一次帳號密碼 -> 登入成功
    //2. 註冊會員 -> 輸入帳號、密碼 -> 註冊成功 -> 使用者登入狀態 = true -> 會員頁面(/profile);
    // 加入 session.setAttribute("userId",user.getId());
    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody RegisterUserRequest request, HttpSession session){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        userRepository.save(user);

        session.setAttribute("userId",user.getId());
        return  ResponseEntity.ok(user);
    }
}
