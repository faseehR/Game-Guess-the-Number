package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imgUser, imgComputer;
    TextView txtUser, txtComputer;
    Button btnHint, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgUser = findViewById(R.id.imgUser);
        imgComputer = findViewById(R.id.imgComputer);
        txtUser = findViewById(R.id.txtUser);
        txtComputer = findViewById(R.id.txtComputer);
        btnHint = findViewById(R.id.btnHint);
        btnExit = findViewById(R.id.btnExit);

        View.OnClickListener userModeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserGuessesActivity.class));
            }
        };

        View.OnClickListener computerModeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ComputerGuessesActivity.class));
            }
        };

        imgUser.setOnClickListener(userModeListener);
        txtUser.setOnClickListener(userModeListener);
        imgComputer.setOnClickListener(computerModeListener);
        txtComputer.setOnClickListener(computerModeListener);

        btnHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HintActivity.class));
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Close the app
            }
        });
    }
}
