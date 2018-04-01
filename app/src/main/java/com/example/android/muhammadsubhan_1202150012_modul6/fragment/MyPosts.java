package com.example.android.muhammadsubhan_1202150012_modul6.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;



public class MyPosts extends PostList {

    public MyPosts() {}



    @Override

    public Query getQuery(DatabaseReference databaseReference) {

        // All my posts

        return databaseReference.child("user-posts")

                .child(getUid());

    }
}
