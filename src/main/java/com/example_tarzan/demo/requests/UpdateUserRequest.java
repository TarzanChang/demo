package com.example_tarzan.demo.requests;

public class UpdateUserRequest {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UpdateUserRequest(){
    }

    public UpdateUserRequest(String username) {
        this.username = username;
    }
}
