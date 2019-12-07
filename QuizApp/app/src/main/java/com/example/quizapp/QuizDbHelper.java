package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.quizapp.QuizContract.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizAppDatabase";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTINS_TABLE =
            "CREATE TABLE " +
            QuestionsTable.TABLE_NAME  + " ( " +
            QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            QuestionsTable.COLUMN_ANSWERNUM + " INTEGER " +
            ")";

        db.execSQL(SQL_CREATE_QUESTINS_TABLE);
        //fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable()
    {
        Question q1= new Question("afsdagfdsg","A","B","C","D",1);
        addQuestion(q1);
        Question q2= new Question("Test Question 2","A","B","C","D",2);
        addQuestion(q2);
        Question q3= new Question("Test Question 3","A","B","C","D",3);
        addQuestion(q3);
        Question q4= new Question("Test Question 4","A","B","C","D",4);
        addQuestion(q4);
        Question q5= new Question("Test Question 5","A","B","C","D",2);
        addQuestion(q5);
        Question q6= new Question("Test Question 6","A","B","C","D",3);
        addQuestion(q6);
        Question q7= new Question("Test Question 7","A","B","C","D",1);
        addQuestion(q7);
        Question q8= new Question("Test Question 8","A","B","C","D",4);
        addQuestion(q8);
        Question q9= new Question("Test Question 9","A","B","C","D",2);
        addQuestion(q9);
        Question q10= new Question("Test Question 10","A","B","C","D",1);
        addQuestion(q10);
    }

    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWERNUM,question.getAnswernum());

        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }
//
//
    public List<Question> getAllQuestions()
        {
            List<Question> questionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

            if(c.moveToFirst())
            {
                do{
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                    question.setAnswernum(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWERNUM)));
                    questionList.add(question);
                }while(c.moveToNext());
            }

            c.close();
            return questionList;
        }
}
