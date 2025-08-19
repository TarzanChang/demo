package com.example_tarzan.demo.repositories;

import com.example_tarzan.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
