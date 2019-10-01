package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button clear;
    private Button percent;
    private Button del;
    private Button division;
    private Button multiplication;
    private Button minus;
    private Button plus;
    private Button equal;
    private Button decimal;
    private Button negpos;
    private EditText input;
    private TextView result;
    private final char ADDITION = '+';
    private final char SUBSTRACTION = '-';
    private final char DIVISION = '/';
    private final char MULTIPLICATION = '*';
    private final char PERCENT = '%';
    private final char DECIMAL = '.';
    private final char EQUAL = '=';
    private double num1 = Double.NaN;
    private double num2;
    private char ACTION;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIviews();
        final char[] onetotenchar = {'0','1','2','3','4','5','6','7','8','9',PERCENT,DIVISION,MULTIPLICATION,SUBSTRACTION,ADDITION,DECIMAL};

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[1]);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[2]);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[3]);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[4]);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[5]);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[6]);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[7]);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[8]);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[9]);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[0]);
            }
        });

//        percent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                input.setText(input.getText().toString()+onetotenchar[10]);
//            }
//        });
//
//        division.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                input.setText(input.getText().toString()+onetotenchar[11]);
//            }
//        });
//
//        multiplication.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                input.setText(input.getText().toString()+onetotenchar[12]);
//            }
//        });
//
//        minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                input.setText(input.getText().toString()+onetotenchar[13]);
//            }
//        });
//
//        plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                input.setText(input.getText().toString()+onetotenchar[14]);
//            }
//        });

        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[15]);
            }
        });

        negpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double x = Double.parseDouble(input.getText().toString())*(-1);
                input.setText(Double.toString(x));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                ACTION = ADDITION;
                result.setText(String.valueOf(num1)+"+");
                input.setText(null);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                ACTION = SUBSTRACTION;
                result.setText(String.valueOf(num1)+"-");
                input.setText(null);
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                ACTION = MULTIPLICATION;
                result.setText(String.valueOf(num1)+"*");
                input.setText(null);
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                ACTION = DIVISION;
                result.setText(String.valueOf(num1)+"/");
                input.setText(null);
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                ACTION = PERCENT;
                result.setText(String.valueOf(num1)+"%");
                input.setText(null);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(null);
                result.setText(null);
                num1 = Double.NaN;
                num2 = Double.NaN;
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(input.getText().length()>0)
                {
                    String newString = input.getText().toString();
                    newString = newString.substring(0,newString.length()-1);
                    input.setText(null);
                    input.setText(newString);
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                ACTION= EQUAL;
                result.setText(result.getText().toString()+String.valueOf(num2)+" = "+String.valueOf(num1));
                input.setText(null);
            }
        });
    }

    private void UIviews()
    {
        one=(Button)findViewById(R.id.btn1);
        two=(Button)findViewById(R.id.btn2);
        three=(Button)findViewById(R.id.btn3);
        four=(Button)findViewById(R.id.btn4);
        five=(Button)findViewById(R.id.btn5);
        six=(Button)findViewById(R.id.btn6);
        seven=(Button)findViewById(R.id.btn7);
        eight=(Button)findViewById(R.id.btn8);
        nine=(Button)findViewById(R.id.btn9);
        zero=(Button)findViewById(R.id.btn0);
        clear=(Button)findViewById(R.id.btnClear);
        percent=(Button)findViewById(R.id.btnPercent);
        del=(Button)findViewById(R.id.btnRemoveDigit);
        division=(Button)findViewById(R.id.btnDivision);
        multiplication=(Button)findViewById(R.id.btnMultiply);
        minus=(Button)findViewById(R.id.btnMinus);
        plus=(Button)findViewById(R.id.btnPlus);
        equal=(Button)findViewById(R.id.btnEqual);
        decimal=(Button)findViewById(R.id.btnDecimal);
        negpos=(Button)findViewById(R.id.btnNegPos);
        input=(EditText)findViewById(R.id.editTextInput);
        result=(TextView)findViewById(R.id.textViewResult);
    }

    private void Calculate()
    {
        if(!Double.isNaN(num1))
        {
            num2 = Double.parseDouble(input.getText().toString());

            switch(ACTION)
            {
                case ADDITION:
                    num1=num1+num2;
                    break;
                case SUBSTRACTION:
                    num1=num1-num2;
                    break;
                case MULTIPLICATION:
                    num1=num1*num2;
                    break;
                case DIVISION:
                    num1=num1/num2;
                    break;
                case PERCENT:
                    num1=(num2/num1)*100;
                    break;
                case EQUAL:
                    break;
            }

        }
        else
        {
            num1=Double.parseDouble(input.getText().toString());
        }
    }
}
