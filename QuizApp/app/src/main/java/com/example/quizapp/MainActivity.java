package com.example.quizapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String name="";
    private EditText EditName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditName = findViewById(R.id.txtEditName);
        Button buttonStartQuiz = findViewById(R.id.btnStartQuiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz()
    {
        Intent intent = new Intent(MainActivity.this,QuizActivity.class);
        name = EditName.getText().toString();
        if(name.isEmpty())
        {
            Toast.makeText(MainActivity.this,"Please give your name",Toast.LENGTH_LONG).show();
        }
        else if(name.matches("^\\S+(\\s\\S+)*$"))
        {
            Bundle b = new Bundle();
            b.putString("username",name);
            intent.putExtras(b);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this,"Invalid input for Name.",Toast.LENGTH_LONG).show();
        }
    }
}
