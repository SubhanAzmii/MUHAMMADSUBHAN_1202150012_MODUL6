package com.example.android.muhammadsubhan_1202150012_modul6.model;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public User() {

        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }



    public User(String username, String email) {
        this.username = username;
        this.email = email;

    }
}
