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

    @Column(name="password")
    //todo 實際應用環境切勿使用明碼儲存
    private String password;

    @Column(name="role")
    private String role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
