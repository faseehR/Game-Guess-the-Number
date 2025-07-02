package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView resultMsg, numberReveal;
    Button btnPlayAgain, btnMainMenu, btnExit;
    boolean userWon;
    int correctNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultMsg = findViewById(R.id.resultMsg);
        numberReveal = findViewById(R.id.numberReveal);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnMainMenu = findViewById(R.id.btnMainMenu);
        btnExit = findViewById(R.id.btnExit);

        // Get data from intent
        userWon = getIntent().getBooleanExtra("userWon", false);
        correctNumber = getIntent().getIntExtra("correctNumber", -1);

        if (userWon) {
            resultMsg.setText("You Win!");
            resultMsg.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            resultMsg.setText("Computer Wins!");
            resultMsg.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        numberReveal.setText("The number was: " + correctNumber);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replay based on who won/lost
                if (userWon) {
                    startActivity(new Intent(ResultActivity.this, UserGuessesActivity.class));
                } else {
                    startActivity(new Intent(ResultActivity.this, ComputerGuessesActivity.class));
                }
                finish();
            }
        });

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}
