package com.example;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserForm {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void encrypt(PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
