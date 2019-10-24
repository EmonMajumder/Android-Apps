package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewScore;
    private TextView textViewQuestionnum;
    private TextView textViewTime;
    private TextView textViewQuestion;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button btnNext;
    private ColorStateList textColorDefaultRb;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewScore = findViewById(R.id.txtViewScore);
        textViewQuestionnum = findViewById(R.id.txtViewQuestionNo);
        textViewTime = findViewById(R.id.txtViewTime);
        textViewQuestion = findViewById(R.id.txtViewQuestion);
        rbGroup = findViewById(R.id.radiogrpOptions);
        rb1 = findViewById(R.id.radioBtnOption1);
        rb2 = findViewById(R.id.radioBtnOption2);
        rb3 = findViewById(R.id.radioBtnOption3);
        rb4 = findViewById(R.id.radioBtnOption4);
        btnNext = findViewById(R.id.btnNext);

        textColorDefaultRb = rb1.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered)
                {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked())
                    {
                        checkAnswer();
                    }
                    else
                    {
                        Toast.makeText(QuizActivity.this,"Select Answer first",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    showNextQuestion();
                }
            }
        });

    }

    private void showNextQuestion()
    {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter<questionCountTotal)
        {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCounter++;

            textViewQuestionnum.setText("Question: "+questionCounter+"/"+questionCountTotal);
            answered = false;
            btnNext.setText("Confirm");
        }
        else
        {
            finishQuiz();
        }
    }

    private void checkAnswer()
    {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int selectedoption = rbGroup.indexOfChild(rbSelected)+1;
        if(selectedoption == currentQuestion.getAnswernum())
        {
            score++;
            textViewScore.setText("Score: "+score);
        }

        showAnswer();
    }

    private void showAnswer()
    {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch(currentQuestion.getAnswernum())
        {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb1.setTextColor(Color.GREEN);
                break;
            case 3:
                rb1.setTextColor(Color.GREEN);
                break;
            case 4:
                rb1.setTextColor(Color.GREEN);
                break;
        }

        if(questionCounter<questionCountTotal)
        {
            btnNext.setText("Next");
        }
        else
        {
            btnNext.setText("Finish");
        }
    }

    private void finishQuiz()
    {
        finish();
    }
}
