package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizapp.QuizContract.*;

import androidx.annotation.Nullable;

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
            "CREATE TABLE, " +
            QuizContract.QuestionsTable.TABLE_NAME  + " ( " +
            QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            QuestionsTable.COLUMN_ANSWERNUM + " INTEGER " +
            ")";

        db.execSQL(SQL_CREATE_QUESTINS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable()
    {
        Question q1= new Question("Test Question","A","B","C","D",1);
    }

    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION4,question.getOption1());
        cv.put(QuestionsTable.COLUMN_ANSWERNUM,question.getQuestion());

    }
}
