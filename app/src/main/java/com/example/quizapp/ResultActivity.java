package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ResultActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resultText;
    int correct, wrong;
    LinearLayout btnShare;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        resultText = findViewById(R.id.resulttextttt);
        btnShare = findViewById(R.id.btnshare);

        circularProgressBar.setProgress(correct);
        resultText.setText(correct + "/20");

        btnShare.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            String shareMessage = "\n I got" + correct + "out of 20 You can also Try";
            intent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(intent, shareMessage);
            startActivity(shareIntent);
        });
    }
}