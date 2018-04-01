package com.example.android.muhammadsubhan_1202150012_modul6.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Kizuna on 3/31/2018.
 */

public class RecentPosts extends PostList {
    public RecentPosts(){}

    @Override

    public Query getQuery(DatabaseReference databaseReference) {

        // [START recent_posts_query]

        // Last 100 posts, these are automatically the 100 most recent

        // due to sorting by push() keys

        Query recentPostsQuery = databaseReference.child("posts")

                .limitToFirst(100);

        // [END recent_posts_query]



        return recentPostsQuery;

    }
}
