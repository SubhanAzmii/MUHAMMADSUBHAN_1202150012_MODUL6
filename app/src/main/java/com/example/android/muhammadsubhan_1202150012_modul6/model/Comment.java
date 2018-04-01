package com.example.android.muhammadsubhan_1202150012_modul6.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by kizuna on 3/31/2018.
 */
@IgnoreExtraProperties
public class Comment {
    public String uid;
    public String author;
    public String text;

    public Comment() {

        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)

    }



    public Comment(String uid, String author, String text) {

        this.uid = uid;

        this.author = author;

        this.text = text;

    }
}
