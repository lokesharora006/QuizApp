package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class QuizLoginPage extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    SignInFragment objectSignInFragment;
    SignUpFragment objectSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_loginpage);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        objectSignUpFragment = new SignUpFragment();
        objectSignInFragment = new SignInFragment();


        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPagerAdaptor adaptor = new ViewPagerAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(adaptor);
        tabLayout.setupWithViewPager(viewPager);
    }
}

//    private void initializeObjects() {
//        {
//            try {
//                objectSignUpFragment = new Signupfragment();
//                objectSignInFragment = new Sign_in_fragment();
//            } catch (Exception e) {
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }
//}