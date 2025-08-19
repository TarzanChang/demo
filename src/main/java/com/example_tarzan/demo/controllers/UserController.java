package com.example_tarzan.demo.controllers;

import com.example_tarzan.demo.models.User;
import com.example_tarzan.demo.requests.CreateUserRequest;
import com.example_tarzan.demo.requests.UpdateUserRequest;
import com.example_tarzan.demo.responses.CreateUserResponse;
import com.example_tarzan.demo.responses.GetUserResponse;
import com.example_tarzan.demo.responses.UpdateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Integer, User> mockUser = new HashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger();

    //建構子:每當 UserController被讀取時要作的事情...
    public UserController(){
        mockUser.put(1,new User(1,"admin","admin@gmail.com"));
        mockUser.put(2,new User(2,"user1","user1@gmail.com"));
        mockUser.put(3,new User(3,"user2","user2@gmail.com"));
        atomicInteger.set(4);
        }
    // response id,userName 資安隱私考量
    // Map<Key, map<key,val>>
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAllUsers(){
        List<User> userList = new ArrayList<>(mockUser.values());
        List<GetUserResponse> responses = new ArrayList<>();
        for (User user : userList){
            GetUserResponse response = new GetUserResponse(user.getId(),user.getUsername());
//            response.setId(user.getId());
//            response.setUsername(user.getUserName());
            responses.add(response);
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable int id){
        User user = mockUser.get(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUserNameById(@PathVariable int id, @RequestBody User request){
//        User user = mockUser.get(id);
//        if (user == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        //update user w/ request body from postman
//        System.out.println(request.getUserName());
//
//        return ResponseEntity.ok(user);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUserById(@PathVariable int id, @RequestBody UpdateUserRequest request){
        User user = mockUser.get(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        System.out.println(request.getUsername());

        //update user w/ request body from postman
        user.setUsername(request.getUsername());

        UpdateUserResponse response = new UpdateUserResponse(user.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
        int newId = atomicInteger.getAndIncrement();
        User user = new User(newId, request.getUsername(), request.getEmail());
        mockUser.put(newId,user);
        //userName
        CreateUserResponse response = new CreateUserResponse(user.getUsername());
        //user序列化回傳jason
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id){
        User user = mockUser.get(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        mockUser.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetUserResponse>> searchUser(@RequestParam String keyword){
//        List<User> results = mockUser.values()
//                .stream()
//                .filter(user ->
//                user.getUserName().toLowerCase().contains(keyword.toLowerCase()))
//                .toList();
//
//        List<GetUserResponse> responses = new ArrayList<>();
//        for (User user : results){
//            GetUserResponse response = new GetUserResponse(user.getId(),user.getUserName());
//
//            responses.add(response);
//        }
//        return ResponseEntity.ok(responses);

//        List<GetUserResponse> results = mockUser.values()
//                .stream()
//                .filter(user ->
//                user.getUserName().toLowerCase().contains(keyword.toLowerCase()))
//                .map(this::toGetUserResponse)
//                .toList();
//        return ResponseEntity.ok(results);

        List<GetUserResponse> results = mockUser.values()
                .stream()
                .filter(user ->
                        user.getUsername().toLowerCase().contains(keyword.toLowerCase()))
                .map(GetUserResponse::new)
                .toList();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/normal")
    public ResponseEntity<List<GetUserResponse>> getNormalUser(){
        List<GetUserResponse> results = mockUser.values()
                .stream()
                .filter(user ->
                user.getUsername().toLowerCase().contains("user"))
                .map(GetUserResponse::new)
                .toList();
        return ResponseEntity.ok(results);
    }

    private GetUserResponse toGetUserResponse(User user) {
        return new GetUserResponse(user.getId(),user.getUsername());
    }
}
