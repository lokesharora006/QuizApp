package com.example.quizapp;

import static android.util.Log.d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GeoActivity extends AppCompatActivity {
    public static ArrayList<DatabaseClass> list;
    DatabaseReference databaseReference;

    CardView geographycard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
        geographycard = findViewById(R.id.geographycard);
        list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Question");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    DatabaseClass databaseClass = dataSnapshot.getValue(DatabaseClass.class);
                    list.add(databaseClass);

                }

                geographycard.setOnClickListener(v -> {
                    Intent intent = new Intent(GeoActivity.this, QuizQuestionActivity.class);
                    startActivity(intent);
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(GeoActivity.this, "Data can not retrieved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
