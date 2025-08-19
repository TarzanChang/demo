package com.example_tarzan.demo.controllers;

import com.example_tarzan.demo.models.User;
import com.example_tarzan.demo.requests.CreateUserRequest;
import com.example_tarzan.demo.requests.UpdateUserRequest;
import com.example_tarzan.demo.responses.CreateUserResponse;
import com.example_tarzan.demo.responses.GetUserResponse;
import com.example_tarzan.demo.responses.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin("*")
public class UserV1Controller {

    //由annotation 注入
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    //由建構子注入
    private final JdbcTemplate jbdcTemplate;

    @Autowired
    public UserV1Controller(JdbcTemplate jbdcTemplate) {
        this.jbdcTemplate = jbdcTemplate;
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {

        String sql = "insert into users (username,email) values (?, ?)";
        int rowsAffected = jbdcTemplate.update(sql, request.getUsername(), request.getEmail());
        if (rowsAffected > 0) {
            CreateUserResponse response = new CreateUserResponse(request.getUsername());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        //user 序列化回傳 json
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAllUsers(){
        String sql = "select * from users";
        List<User> users = jbdcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return ResponseEntity.ok(users.stream().map(GetUserResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable int id){
        String sql = "select * from users where id = ?";
        try{
            User user = jbdcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
            GetUserResponse response = new GetUserResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUserById(@PathVariable int id, @RequestBody UpdateUserRequest request){
        String sql = "update users set username = ?  where id = ?";
        int rowAffected = jbdcTemplate.update(sql,request.getUsername(),id);
        if (rowAffected > 0){
            UpdateUserResponse response = new UpdateUserResponse(request.getUsername());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id){
        String sql = "delete from users where id = ?";
        int rowAffected = jbdcTemplate.update(sql,id);
        if (rowAffected > 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetUserResponse>> searchUser(@RequestParam String keyword){
        //sol1
//        String sql = "select * from users where username like ?";
//        try{
//            List<User> users = jbdcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class), "%" + keyword + "%");
//            return ResponseEntity.ok(users.stream().map(GetUserResponse::new).toList());
//        } catch (EmptyResultDataAccessException e){
//            return ResponseEntity.notFound().build();
//        }
        //sol2
        String sql = "select * from users";
        List<User> users = jbdcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
        List<GetUserResponse> results = users
                .stream()
                .filter(user ->
                        user.getUsername().toLowerCase().contains(keyword.toLowerCase()))
                .map(GetUserResponse::new)
                .toList();

        return ResponseEntity.ok(results);

    }
}
