package com.example.android.muhammadsubhan_1202150012_modul6;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.muhammadsubhan_1202150012_modul6.fragment.MyPosts;
import com.example.android.muhammadsubhan_1202150012_modul6.fragment.RecentPosts;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Home";



    private FragmentPagerAdapter mPagerAdapter;

    private ViewPager mViewPager;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            private final Fragment[] mFragments = new Fragment[] {
                    //membuat layout fragment
                    new RecentPosts(),
                    new MyPosts(),
            };

            private final String[] mFragmentNames = new String[] {
                    //memnaggil menu frament
                    getString(R.string.heading_recent),
                    getString(R.string.heading_my_posts),

            };
            @Override
            public Fragment getItem(int position) {
                //mengatur posisi dari fragment
                return mFragments[position];

            }

            @Override

            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }

        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        // Button launches NewPostActivity
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //button untuk membuat post baru
                startActivity(new Intent(MainActivity.this, PostFoto.class));

            }

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        //membuat menu untuk logout akun
        if (i == R.id.action_contact) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, Login_Activity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

}
