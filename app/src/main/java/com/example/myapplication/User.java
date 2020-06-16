package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.Exclude;

public class User extends AppCompatActivity {

    private String username, email, password, phone;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }


}
