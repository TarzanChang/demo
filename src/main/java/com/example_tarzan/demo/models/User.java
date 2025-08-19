package com.example_tarzan.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")

public class User {
    //ORM setting
    @Id
    //如果是 auto_increment 須讓程式去辨識 @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //ORM setting
    @Column(name="username")
    private String username;
    //ORM setting
    @Column(name="email")
    private String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "User{" +
                "id = " + id +
                ", username = '" + username + '\'' +
                ", email = '" + email + '\'' +
                '}';
    }
}
