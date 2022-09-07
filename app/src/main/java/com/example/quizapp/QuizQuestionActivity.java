package com.example.quizapp;

import static android.util.Log.d;
import static com.example.quizapp.GeoActivity.list;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizQuestionActivity extends AppCompatActivity {


    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    int timerValue = 20;
    DatabaseClass databaseClass;
    List<DatabaseClass> allQuestionList;
    int index = 0;
    TextView card_qustion, optiona, optionb, optionc, optiond;
    CardView cardA, cardB, cardC, cardD;

    int correctcount = 0;
    int wrongcount = 0;
    LinearLayout nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

         Log.d("QuestionListTag", ""+ list);
        Hook();
        allQuestionList = list;
     //   databaseClass = list.get(index);
 //  setallData();

        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));

        nextbtn.setClickable(false);
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
            }
            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(QuizQuestionActivity.this, R.style.Dialog);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialogbox);

                dialog.findViewById(R.id.btn_tryagain).setOnClickListener(v -> {
                    Intent intent = new Intent(QuizQuestionActivity.this, HomeActivity.class);
                    startActivity(intent);
                });
                dialog.show();
            }
        }.start();
    }

    private void getAllData() {

        card_qustion.setText(databaseClass.getQuestion());
        optiona.setText(databaseClass.getQA());
        optionb.setText(databaseClass.getQB());
        optionc.setText(databaseClass.getQC());
        optiond.setText(databaseClass.getQD());
        timerValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();

    }

    private void setallData() {

        card_qustion.setText(databaseClass.getQuestion());
        optiona.setText(databaseClass.getAns());
        optionb.setText(databaseClass.getAns());
        optionc.setText(databaseClass.getAns());
        optiond.setText(databaseClass.getAns());

        timerValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void Hook() {

        allQuestionList =list;
        nextbtn = findViewById(R.id.btnnext);
        progressBar = findViewById(R.id.Quiztimer);

        card_qustion = findViewById(R.id.card_question);
        optiona = findViewById(R.id.option1);
        optionb = findViewById(R.id.option2);
        optionc = findViewById(R.id.option3);
        optiond = findViewById(R.id.option4);


        cardA = findViewById(R.id.cardA);
        cardB = findViewById(R.id.cardB);
        cardC = findViewById(R.id.cardC);
        cardD = findViewById(R.id.cardD);
    }

    public void correct(CardView cardView) {
        cardView.setBackgroundColor(getResources().getColor(R.color.teal_700));
        nextbtn.setOnClickListener(v -> {
            correctcount++;
            index++;
            databaseClass = list.get(index);
            resetcolour();
            getAllData();
            setallData();
            enableButton();
        });
    }


    public void Wrong(CardView cardA) {
        cardA.setBackgroundColor(getResources().getColor(R.color.teal_200));
        nextbtn.setOnClickListener(v -> {
            wrongcount++;
            if (index < list.size() - 1) {
                index++;
                databaseClass = list.get(index);
                resetcolour();
                setallData();
                getAllData();
                enableButton();
            } else {
                GameWon();
            }
        });

    }

    private void GameWon() {

        Intent intent = new Intent(QuizQuestionActivity.this, ResultActivity.class);
        intent.putExtra("correct", correctcount);
        intent.putExtra("wrong", wrongcount);
        startActivity(intent);
    }


    public void enableButton() {
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
    }

    public void disableButton() {

        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
    }

    public void resetcolour() {

        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));

    }

    public void optionaclick(View view) {
        disableButton();
        nextbtn.setClickable(true);
        if (databaseClass.getQA().equals(databaseClass.getAns())) {
            cardA.setBackgroundColor(getResources().getColor(R.color.teal_700));
            if (index < list.size() - 1) {
                correct(cardA);
            } else {
                GameWon();
            }
        } else {
            Wrong(cardA);
        }

    }

    public void optionbclick(View view) {
        disableButton();
        nextbtn.setClickable(true);
        if (databaseClass.getQB().equals(databaseClass.getAns())) {
            cardB.setBackgroundColor(getResources().getColor(R.color.teal_700));
            if (index < list.size() - 1) {
                correct(cardB);

            } else {
                GameWon();
            }
        } else {
            Wrong(cardB);
        }

    }

    public void optioncclick(View view) {
        disableButton();
        nextbtn.setClickable(true);
        if (databaseClass.getQC().equals(databaseClass.getAns())) {
            cardC.setBackgroundColor(getResources().getColor(R.color.teal_700));
            if (index < list.size() - 1) {

                correct(cardC);
            } else {
                GameWon();
            }
        } else {
            Wrong(cardC);
        }
    }

    public void optiondclick(View view) {
        disableButton();
        nextbtn.setClickable(true);
        if (databaseClass.getQD().equals(databaseClass.getAns())) {
            cardD.setBackgroundColor(getResources().getColor(R.color.teal_700));
            if (index < list.size() - 1) {
                correct(cardD);

            } else {
                GameWon();
            }
        } else {
            Wrong(cardD);
        }
    }
}
