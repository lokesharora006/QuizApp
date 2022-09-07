package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    CardView cardrules;
    CardView LogoutButton;
    CardView geographycard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        cardrules = findViewById(R.id.cardrules);
        LogoutButton = findViewById(R.id.LogoutButton1);
        geographycard = findViewById(R.id.geographycard);


        LogoutButton.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            Intent LoginPage = new Intent(HomeActivity.this, QuizLoginPage.class);
            startActivity(LoginPage);

        });

        cardrules.setOnClickListener(v -> {

            Intent Gamerules = new Intent(HomeActivity.this, GameRules.class);
            startActivity(Gamerules);
        });

        geographycard.setOnClickListener(v -> {
            Intent GeoQuiz = new Intent(HomeActivity.this, QuizQuestionActivity.class);
            startActivity(GeoQuiz);
        });
    }

}