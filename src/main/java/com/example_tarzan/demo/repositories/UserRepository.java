package com.example_tarzan.demo.repositories;

import com.example_tarzan.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //select * from users where username = "XXX";
    Optional<User> findByUsername (String username);
}
