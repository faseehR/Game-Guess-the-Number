package com.example.guessthenumber;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class UserGuessesActivity extends AppCompatActivity {

    EditText guessInput;
    Button submitBtn;
    TextView resultText, remainingText;
    int numberToGuess;
    int guessesLeft = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guesses);

        guessInput = findViewById(R.id.guessInput);
        submitBtn = findViewById(R.id.submitBtn);
        resultText = findViewById(R.id.resultText);
        remainingText = findViewById(R.id.remainingText);

        // Random number from 0 to 10000
        numberToGuess = new Random().nextInt(10001);
        updateRemainingText();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = guessInput.getText().toString();
                guessInput.setText("");
                if (input.isEmpty()) {
                    Toast.makeText(UserGuessesActivity.this, "Enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                int guess = Integer.parseInt(input);
                guessesLeft--;
                if(guess>10000){
                    resultText.setText("Enter guess in given range!");
                    resultText.setTextColor(Color.BLUE);

                }
                else {
                    if (guess < numberToGuess) {
                        resultText.setText("Number is Bigger!");
                        resultText.setTextColor(Color.BLUE);
                    } else if (guess > numberToGuess) {
                        resultText.setText("Number is Smaller!");
                        resultText.setTextColor(Color.YELLOW);
                    } else {
                        resultText.setText("Correct! You win!");
                        resultText.setTextColor(Color.GREEN);
                        goToResultScreen(true);
                        return;
                    }
                }
                updateRemainingText();

                if (guessesLeft == 0) {
                    Toast.makeText(UserGuessesActivity.this, "You lost! Number was " + numberToGuess, Toast.LENGTH_LONG).show();
                    goToResultScreen(false);
                }
            }
        });
    }

    void updateRemainingText() {
        remainingText.setText("Guesses Remaining: " + guessesLeft);
        remainingText.setTextColor(Color.parseColor("#FFA500")); // Orange
    }

    void goToResultScreen(boolean userWon) {
        Intent intent = new Intent(UserGuessesActivity.this, ResultActivity.class);
        intent.putExtra("userWon", userWon);
        intent.putExtra("correctNumber", numberToGuess);
        startActivity(intent);
        finish();
    }
}
