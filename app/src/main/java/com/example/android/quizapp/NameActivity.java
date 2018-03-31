package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity {

    String playerName;
    Button startQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        //Hide keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /*Listen to startQuiz button and when it pressed, it takes input name and store it first then
        send it to next activity*/

        startQuiz = (Button) findViewById(R.id.button1);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameInput = (EditText) findViewById(R.id.input_name);
                playerName = nameInput.getText().toString().trim();
                Intent quiz = new Intent(NameActivity.this, QuizActivity.class);
                quiz.putExtra("PLAYER_NAME", playerName);
                startActivity(quiz);
            }
        });
    }
}