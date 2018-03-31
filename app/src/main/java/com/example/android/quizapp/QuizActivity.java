package com.example.android.quizapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // All variable and deceleration
    int mainScore = 0;
    String namePlayer;
    String playerScoreMassage;
    Button quizReset;
    Button quizSubmit;

    RadioGroup q1RG;
    RadioGroup q2RG;
    RadioGroup q3RG;
    RadioGroup q6RG;
    RadioGroup q7RG;

    EditText sportsQuestionsFourAns;
    String ansForQuestionsFour = "michael phelps";
    String sportsAnsSTR;

    CheckBox movieQuestionsFiveAns1;
    CheckBox movieQuestionsFiveAns2;
    CheckBox movieQuestionsFiveAns3;
    CheckBox movieQuestionsFiveW1;

    boolean hasCheckedMovieAns1;
    boolean hasCheckedMovieAns2;
    boolean hasCheckedMovieAns3;
    boolean hasCheckedMovieW1;
    boolean hasCheckedAllAnswer;

    EditText movieQuestionsEightAns;
    String movieAnsSTR;
    String ansForQuestionsEight = "simon pegg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Display player name top on the activity
        namePlayer = getIntent().getExtras().getString("PLAYER_NAME");
        TextView textView = findViewById(R.id.name_player);
        textView.setText(getString(R.string.main_player) + " " + namePlayer);


        //Reset activity and score
        quizReset = findViewById(R.id.reset_butt);
        quizReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainScore = 0;
                sportsAnsSTR = "";
                movieAnsSTR = "";
                playerScoreMassage = "";
                finish();
                startActivity(getIntent());
            }
        });

        quizSubmit = findViewById(R.id.submit_butt);
        quizSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Clicking on the submission button, it will first check if you have
                answered all the questions before it looks for all the correct answers.*/
                hasCheckedAllAnswer = checkAnswer();

                if (hasCheckedAllAnswer) {
                    questionOne();
                    questionTwo();
                    questionThree();
                    questionFour();
                    questionFive();
                    questionSix();
                    questionSeven();
                    questionEight();

                    //Displays different toast messages depending on how much points you get
                    if (mainScore > 0 && (mainScore < 10 || mainScore == 10)) {
                        playerScoreMassage = "Better luck next time";
                    } else if (mainScore > 10 && (mainScore < 20 || mainScore == 20)) {
                        playerScoreMassage = " Not bad but not so good either";
                    } else if (mainScore > 20 && (mainScore < 30 || mainScore == 30)) {
                        playerScoreMassage = "Good job you are almost famous";
                    } else if (mainScore > 30 && (mainScore < 40 || mainScore == 40)) {
                        playerScoreMassage = "You are a rock star";
                    } else if (mainScore > 40 && (mainScore < 50 || mainScore == 50)) {
                        playerScoreMassage = "You are a genius, do they call you Einstein?";
                    }

                    Toast.makeText(getApplicationContext(), namePlayer + " you got " + mainScore + " of 50, " + playerScoreMassage, Toast.LENGTH_LONG).show();
                    /*Once you have submitted your scores and received a score, the button closes.
                     In order to play again, you must click on the reset button*/
                    quizSubmit.setClickable(false);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.userToastMassage, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    /*This method looks through all the questions and ensures
    that all questions are answered before calculating the score*/
    public boolean checkAnswer() {
        q1RG = findViewById(R.id.movie_q1_rg);
        q2RG = findViewById(R.id.science_q2_rg);
        q3RG = findViewById(R.id.music_q3_rg);
        sportsQuestionsFourAns = findViewById(R.id.sports_q4_ans);
        sportsAnsSTR = sportsQuestionsFourAns.getText().toString().trim();
        q6RG = findViewById(R.id.science_q6_rg);
        q7RG = findViewById(R.id.music_q7_rg);
        movieQuestionsEightAns = findViewById(R.id.movie_q8_ans);
        movieAnsSTR = movieQuestionsEightAns.getText().toString().trim();
        movieQuestionsFiveAns1 = findViewById(R.id.movie_q5_ans1);
        movieQuestionsFiveAns2 = findViewById(R.id.movie_q5_ans2);
        movieQuestionsFiveAns3 = findViewById(R.id.movie_q5_ans3);
        movieQuestionsFiveW1 = findViewById(R.id.movie_q5_w1);
        hasCheckedMovieAns1 = movieQuestionsFiveAns1.isChecked();
        hasCheckedMovieAns2 = movieQuestionsFiveAns2.isChecked();
        hasCheckedMovieAns3 = movieQuestionsFiveAns3.isChecked();
        hasCheckedMovieW1 = movieQuestionsFiveW1.isChecked();


        if (q1RG.getCheckedRadioButtonId() == -1) {
            return false;
        }
        if (q2RG.getCheckedRadioButtonId() == -1) {
            return false;
        }
        if (q3RG.getCheckedRadioButtonId() == -1) {
            return false;
        }
        if (sportsAnsSTR.isEmpty()) {
            return false;
        }
        if (!hasCheckedMovieAns1 && !hasCheckedMovieAns2 && !hasCheckedMovieAns3 && !hasCheckedMovieW1) {
            return false;
        }
        if (q6RG.getCheckedRadioButtonId() == -1) {
            return false;
        }
        if (q7RG.getCheckedRadioButtonId() == -1) {
            return false;
        }
        if (movieAnsSTR.isEmpty()) {
            return false;
        }

        return true;
    }

    /*Here are all methods for all questions, if you have answered correctly,
      the background becomes green and in the wrong answer the background becomes red*/

    public void questionOne() {
        RadioButton movieQuestionsOneAns = findViewById(R.id.movie_q1_ans);
        RadioButton movieQuestionsOneW1 = findViewById(R.id.movie_q1_w1);
        RadioButton movieQuestionsOneW2 = findViewById(R.id.movie_q1_w2);
        RadioButton movieQuestionsOneW3 = findViewById(R.id.movie_q1_w3);
        if (movieQuestionsOneAns.isChecked()) {
            mainScore = mainScore + 5;
            movieQuestionsOneAns.setBackgroundColor(Color.GREEN);
            movieQuestionsOneAns.setTextColor(Color.WHITE);
        } else if (movieQuestionsOneW1.isChecked()) {
            movieQuestionsOneW1.setBackgroundColor(Color.RED);
            movieQuestionsOneW1.setTextColor(Color.WHITE);
        } else if (movieQuestionsOneW2.isChecked()) {
            movieQuestionsOneW2.setBackgroundColor(Color.RED);
            movieQuestionsOneW2.setTextColor(Color.WHITE);
        } else if (movieQuestionsOneW3.isChecked()) {
            movieQuestionsOneW3.setBackgroundColor(Color.RED);
            movieQuestionsOneW3.setTextColor(Color.WHITE);
        }
    }

    public void questionTwo() {
        RadioButton scienceQuestionsTwoAns = findViewById(R.id.science_q2_ans);
        RadioButton scienceQuestionsTwoW1 = findViewById(R.id.science_q2_w1);
        RadioButton scienceQuestionsTwoW2 = findViewById(R.id.science_q2_w2);
        RadioButton scienceQuestionsTwoW3 = findViewById(R.id.science_q2_w3);
        if (scienceQuestionsTwoAns.isChecked()) {
            mainScore = mainScore + 5;
            scienceQuestionsTwoAns.setBackgroundColor(Color.GREEN);
            scienceQuestionsTwoAns.setTextColor(Color.WHITE);
        } else if (scienceQuestionsTwoW1.isChecked()) {
            scienceQuestionsTwoW1.setBackgroundColor(Color.RED);
            scienceQuestionsTwoW1.setTextColor(Color.WHITE);
        } else if (scienceQuestionsTwoW2.isChecked()) {
            scienceQuestionsTwoW2.setBackgroundColor(Color.RED);
            scienceQuestionsTwoW2.setTextColor(Color.WHITE);
        } else if (scienceQuestionsTwoW3.isChecked()) {
            scienceQuestionsTwoW3.setBackgroundColor(Color.RED);
            scienceQuestionsTwoW3.setTextColor(Color.WHITE);
        }
    }

    public void questionThree() {
        RadioButton musicQuestionsThreeAns = findViewById(R.id.music_q3_ans);
        RadioButton musicQuestionsThreeW1 = findViewById(R.id.music_q3_w1);
        RadioButton musicQuestionsThreeW2 = findViewById(R.id.music_q3_w2);
        RadioButton musicQuestionsThreeW3 = findViewById(R.id.music_q3_w3);
        if (musicQuestionsThreeAns.isChecked()) {
            mainScore = mainScore + 5;
            musicQuestionsThreeAns.setBackgroundColor(Color.GREEN);
            musicQuestionsThreeAns.setTextColor(Color.WHITE);
        } else if (musicQuestionsThreeW1.isChecked()) {
            musicQuestionsThreeW1.setBackgroundColor(Color.RED);
            musicQuestionsThreeW1.setTextColor(Color.WHITE);
        } else if (musicQuestionsThreeW2.isChecked()) {
            musicQuestionsThreeW2.setBackgroundColor(Color.RED);
            musicQuestionsThreeW2.setTextColor(Color.WHITE);
        } else if (musicQuestionsThreeW1.isChecked()) {
            musicQuestionsThreeW3.setBackgroundColor(Color.RED);
            musicQuestionsThreeW3.setTextColor(Color.WHITE);
        }
    }

    public void questionFour() {
        sportsQuestionsFourAns = findViewById(R.id.sports_q4_ans);
        sportsAnsSTR = sportsQuestionsFourAns.getText().toString().trim().toLowerCase();
        if (sportsAnsSTR.equals(ansForQuestionsFour)) {
            mainScore = mainScore + 5;
            sportsQuestionsFourAns.setBackgroundColor(Color.GREEN);
            sportsQuestionsFourAns.setTextColor(Color.WHITE);
        } else {
            sportsQuestionsFourAns.setBackgroundColor(Color.RED);
            sportsQuestionsFourAns.setTextColor(Color.WHITE);
            sportsQuestionsFourAns.setHintTextColor(Color.WHITE);
        }
    }

    public void questionFive() {
        movieQuestionsFiveAns1 = findViewById(R.id.movie_q5_ans1);
        movieQuestionsFiveAns2 = findViewById(R.id.movie_q5_ans2);
        movieQuestionsFiveAns3 = findViewById(R.id.movie_q5_ans3);
        movieQuestionsFiveW1 = findViewById(R.id.movie_q5_w1);

        hasCheckedMovieAns1 = movieQuestionsFiveAns1.isChecked();
        hasCheckedMovieAns2 = movieQuestionsFiveAns2.isChecked();
        hasCheckedMovieAns3 = movieQuestionsFiveAns3.isChecked();
        hasCheckedMovieW1 = movieQuestionsFiveW1.isChecked();

        if (hasCheckedMovieAns1) {
            mainScore = mainScore + 5;
            movieQuestionsFiveAns1.setBackgroundColor(Color.GREEN);
            movieQuestionsFiveAns1.setTextColor(Color.WHITE);
        }
        if (hasCheckedMovieAns2) {
            mainScore = mainScore + 5;
            movieQuestionsFiveAns2.setBackgroundColor(Color.GREEN);
            movieQuestionsFiveAns2.setTextColor(Color.WHITE);
        }
        if (hasCheckedMovieAns3) {
            mainScore = mainScore + 5;
            movieQuestionsFiveAns3.setBackgroundColor(Color.GREEN);
            movieQuestionsFiveAns3.setTextColor(Color.WHITE);
        }
        if (hasCheckedMovieW1) {
            mainScore = mainScore - 5;
            movieQuestionsFiveW1.setBackgroundColor(Color.RED);
            movieQuestionsFiveW1.setTextColor(Color.WHITE);
        }
    }

    public void questionSix() {
        RadioButton scienceQuestionsSixAns = findViewById(R.id.science_q6_ans);
        RadioButton scienceQuestionsSixW1 = findViewById(R.id.science_q6_w1);
        RadioButton scienceQuestionsSixW2 = findViewById(R.id.science_q6_w2);
        RadioButton scienceQuestionsSixW3 = findViewById(R.id.science_q6_w3);
        if (scienceQuestionsSixAns.isChecked()) {
            mainScore = mainScore + 5;
            scienceQuestionsSixAns.setBackgroundColor(Color.GREEN);
            scienceQuestionsSixAns.setTextColor(Color.WHITE);
        } else if (scienceQuestionsSixW1.isChecked()) {
            scienceQuestionsSixW1.setBackgroundColor(Color.RED);
            scienceQuestionsSixW1.setTextColor(Color.WHITE);
        } else if (scienceQuestionsSixW2.isChecked()) {
            scienceQuestionsSixW2.setBackgroundColor(Color.RED);
            scienceQuestionsSixW2.setTextColor(Color.WHITE);
        } else if (scienceQuestionsSixW3.isChecked()) {
            scienceQuestionsSixW3.setBackgroundColor(Color.RED);
            scienceQuestionsSixW3.setTextColor(Color.WHITE);
        }
    }

    public void questionSeven() {
        RadioButton musicQuestionsSevenAns = findViewById(R.id.music_q7_ans);
        RadioButton musicQuestionsSevenW1 = findViewById(R.id.music_q7_w1);
        RadioButton musicQuestionsSevenW2 = findViewById(R.id.music_q7_w2);
        RadioButton musicQuestionsSevenW3 = findViewById(R.id.music_q7_w3);
        if (musicQuestionsSevenAns.isChecked()) {
            mainScore = mainScore + 5;
            musicQuestionsSevenAns.setBackgroundColor(Color.GREEN);
            musicQuestionsSevenAns.setTextColor(Color.WHITE);
        } else if (musicQuestionsSevenW1.isChecked()) {
            musicQuestionsSevenW1.setBackgroundColor(Color.RED);
            musicQuestionsSevenW1.setTextColor(Color.WHITE);
        } else if (musicQuestionsSevenW2.isChecked()) {
            musicQuestionsSevenW2.setBackgroundColor(Color.RED);
            musicQuestionsSevenW2.setTextColor(Color.WHITE);
        } else if (musicQuestionsSevenW3.isChecked()) {
            musicQuestionsSevenW3.setBackgroundColor(Color.RED);
            musicQuestionsSevenW3.setTextColor(Color.WHITE);
        }
    }

    public void questionEight() {
        movieQuestionsEightAns = findViewById(R.id.movie_q8_ans);
        movieAnsSTR = movieQuestionsEightAns.getText().toString().trim().toLowerCase();
        if (movieAnsSTR.equals(ansForQuestionsEight)) {
            mainScore = mainScore + 5;
            movieQuestionsEightAns.setBackgroundColor(Color.GREEN);
            movieQuestionsEightAns.setTextColor(Color.WHITE);
        } else {
            movieQuestionsEightAns.setBackgroundColor(Color.RED);
            movieQuestionsEightAns.setTextColor(Color.WHITE);
            movieQuestionsEightAns.setHintTextColor(Color.WHITE);
        }
    }
}