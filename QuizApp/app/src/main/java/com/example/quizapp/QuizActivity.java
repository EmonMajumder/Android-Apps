package com.example.quizapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    private List<Question> questionList = new ArrayList<>();
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private TextView textViewName;
    private Button btnFinish;
    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewScore = findViewById(R.id.txtViewScore);
        Bundle b = getIntent().getExtras();
        textViewName = findViewById(R.id.txtViewName);
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
        btnFinish = findViewById(R.id.btnFinish);

        String playername = b.getString("username");
        textViewName.setText(playername);
        if(playername.isEmpty())
        {
            Toast.makeText(QuizActivity.this,"Please give your name",Toast.LENGTH_LONG).show();
            finishQuiz();
        }
        else if(playername.matches("^\\S+(\\s\\S+)*$"))
        {
            readfile();
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

            btnFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishQuiz();
                }
            });
        }
        else
        {
            Toast.makeText(QuizActivity.this,"Invalid input for Name.",Toast.LENGTH_LONG).show();
            finishQuiz();
        }
        //QuizDbHelper dbHelper = new QuizDbHelper(this);
        //questionList = dbHelper.getAllQuestions();
    }

    public void readfile()
    {
        ArrayList<String[]> qes = new ArrayList<>();
        ArrayList<String> options = new ArrayList<>();
        ArrayList<String> quesop = new ArrayList<>();
        String str="";
        try
        {
            InputStream inputStream = getAssets().open("Questions.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            str = new String(buffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.w("Emon Quiz App",e);
        }

        try
        {
            String[] lines = str.split("\\r?\\n");

            for(int i=0;i<lines.length;i++)
            {
                String[] s = lines[i].split(":");
                qes.add(s);
                options.add(s[1]);
                s = null;
            }
            lines = null;

            for(int i=0;i<qes.size();i++)
            {
                Question question = new Question();
                Collections.shuffle(options);
                quesop.add(qes.get(i)[1]);
                for(int k=1;k<=3;k++)
                {
                    if(!qes.get(i)[1].matches(options.get(k)))
                    {
                        quesop.add(options.get(k));
                    }
                    else
                    {
                        quesop.add(options.get(k+5));
                    }
                }

                Collections.shuffle(quesop);
                question.setQuestion(qes.get(i)[0]);
                question.setOption1(quesop.get(0));
                question.setOption2(quesop.get(1));
                question.setOption3(quesop.get(2));
                question.setOption4(quesop.get(3));

                for(int j=0;j<=3;j++)
                {
                    if(qes.get(i)[1].matches(quesop.get(j)))
                    {
                        question.setAnswernum(j+1);
                        break;
                    }
                }
                questionList.add(question);
                quesop.clear();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.w("Emon Quiz App",e);
        }
    }

    private void showNextQuestion()
    {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
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
            Toast.makeText(QuizActivity.this,"Correct",Toast.LENGTH_LONG).show();
            score++;
        }
        else
        {
            Toast.makeText(QuizActivity.this,"Incorrect",Toast.LENGTH_LONG).show();
        }

        showAnswer(selectedoption);
    }

    private void showAnswer(int selected)
    {
//        rb1.setTextColor(Color.RED);
//        rb2.setTextColor(Color.RED);
//        rb3.setTextColor(Color.RED);
//        rb4.setTextColor(Color.RED);

        if(selected == currentQuestion.getAnswernum())
        {
            switch(selected)
            {
                case 1:
                    rb1.setTextColor(Color.GREEN);
                    break;
                case 2:
                    rb2.setTextColor(Color.GREEN);
                    break;
                case 3:
                    rb3.setTextColor(Color.GREEN);
                    break;
                case 4:
                    rb4.setTextColor(Color.GREEN);
                    break;
            }
        }
        else
        {
            switch(selected)
            {
                case 1:
                    rb1.setTextColor(Color.RED);
                    break;
                case 2:
                    rb2.setTextColor(Color.RED);
                    break;
                case 3:
                    rb3.setTextColor(Color.RED);
                    break;
                case 4:
                    rb4.setTextColor(Color.RED);
                    break;
            }
        }

        textViewScore.setText("Score: "+score);

        if(questionCounter<questionCountTotal)
        {
            btnNext.setText("Next");
        }
        else
        {
            btnNext.setText("Finish");
            btnFinish.setVisibility(View.GONE);
        }
    }

    private void finishQuiz()
    {

        Intent intent = new Intent();
        intent.putExtra("HighScore",Integer.toString(score));
        setResult(RESULT_OK,intent);
        finish();
    }
}
