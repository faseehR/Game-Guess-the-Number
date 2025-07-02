package com.example.guessthenumber;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ComputerGuessesActivity extends AppCompatActivity {

    TextView guessText, guessInfoText;
    Button btnLess, btnGreater, btnEqual;
    int low = 0, high = 10000, mid, guessesLeft = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_guesses);

        guessText = findViewById(R.id.guessText);
        guessInfoText = findViewById(R.id.guessInfoText);
        btnLess = findViewById(R.id.btnLess);
        btnGreater = findViewById(R.id.btnGreater);
        btnEqual = findViewById(R.id.btnEqual);

        makeGuess(); // initial guess

        btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                high = mid - 1;
                guessesLeft--;
                makeGuess();
            }
        });

        btnGreater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                low = mid + 1;
                guessesLeft--;
                makeGuess();
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResultScreen(true); // computer wins
            }
        });
    }

    private void makeGuess() {
        if (guessesLeft <= 0 || low > high) {
            Toast.makeText(this, "Computer failed to guess!", Toast.LENGTH_SHORT).show();
            goToResultScreen(false);
            return;
        }

        mid = (low + high) / 2;
        guessText.setText("Is your number " + mid + "?");
        guessInfoText.setText("Guesses Left: " + guessesLeft);
        guessInfoText.setTextColor(Color.parseColor("#FFA500")); // orange
    }

    private void goToResultScreen(boolean computerWon) {
        Intent intent = new Intent(ComputerGuessesActivity.this, ResultActivity.class);
        intent.putExtra("userWon", !computerWon); // if computer wins, user loses
        intent.putExtra("correctNumber", mid);
        startActivity(intent);
        finish();
    }
}
