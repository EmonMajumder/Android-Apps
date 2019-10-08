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
    private EditText hiddeninput;
    private TextView result;
    private final char ADDITION = '+';
    private final char SUBSTRACTION = '-';
    private final char DIVISION = '/';
    private final char MULTIPLICATION = '*';
    private final char PERCENT = '%';
    private final char DECIMAL = '.';
    private final char EQUAL = '=';
    private double num1 = Double.NaN;
    private double num2 = Double.NaN;
    private static char ACTION;
    private boolean dividebyzeroerror=false;

    //Function to check a number for Validation
    private boolean validateUserInput(String s)
    {
        if(s.matches("^(-?\\d+\\.\\d+)$|^(-?\\d+)$|^(-?\\.\\d+)$"))
        {
            return true;
        }
        else
            return false;
    }

    @Override
    //Generate al the layout elements. And their functionality.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIviews();
        final char[] onetotenchar = {'0','1','2','3','4','5','6','7','8','9',PERCENT,DIVISION,MULTIPLICATION,SUBSTRACTION,ADDITION,DECIMAL};

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[1]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[1]);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[2]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[2]);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[3]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[3]);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[4]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[4]);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[5]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[5]);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[6]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[6]);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[7]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[7]);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[8]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[8]);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[9]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[9]);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString()+onetotenchar[0]);
                hiddeninput.setText(hiddeninput.getText().toString()+onetotenchar[0]);
            }
        });

        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hiddeninput.getText().toString().matches("^(-?\\d+\\.\\d+)$|^(-?\\.\\d+)$|^(-?\\d+\\.)$|^(-?\\.)$")) {
                    input.setText(input.getText().toString() + onetotenchar[15]);
                    hiddeninput.setText(hiddeninput.getText().toString() + onetotenchar[15]);
                }
            }
        });

        negpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateUserInput(input.getText().toString())) {
                    double x = Double.parseDouble(hiddeninput.getText().toString());
                    x = x * (-1);
                    if (x % 1 == 0) {
                        input.setText(String.valueOf(Math.round(x)));
                        hiddeninput.setText(String.valueOf(Math.round(x)));
                    }
                    else
                    {
                        input.setText(Double.toString(x));
                        hiddeninput.setText(Double.toString(x));
                    }
                }
                else if (validateUserInput(hiddeninput.getText().toString()))
                {
                    double x = Double.parseDouble(hiddeninput.getText().toString());
                    x = x * (-1);
                    char[] charArray = input.getText().toString().toCharArray();
                    input.setText(null);
                    for (int i=0;i<charArray.length;i++) {
                        input.setText(input.getText().toString() + charArray[i]);
                        if (charArray[i] == '%' || charArray[i] == '/' ||
                                charArray[i] == '*' || charArray[i] == '-' ||
                                charArray[i] == '+') {
                            if(charArray[i+1] == '(')
                            {
                                break;
                            }
                            else
                            {
                                input.setText(input.getText().toString() + "(");
                                break;
                            }
                        }
                    }
                    if (x % 1 == 0)
                    {
                        input.setText(input.getText().toString() + String.valueOf(Math.round(x)));
                        hiddeninput.setText(String.valueOf(Math.round(x)));
                    }
                    else
                    {
                        input.setText(Double.toString(x));
                        hiddeninput.setText(Double.toString(x));
                    }
                }
                else if (validateUserInput(result.getText().toString()))
                {
                    double x = Double.parseDouble(result.getText().toString());
                    x = x*(-1);
                    if (x % 1 == 0)
                    {
                        input.setText(String.valueOf(Math.round(x)));
                        hiddeninput.setText(String.valueOf(Math.round(x)));
                    }
                    else
                    {
                        input.setText(Double.toString(x));
                        hiddeninput.setText(Double.toString(x));
                    }
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Calculate();
            if(!dividebyzeroerror){
                ACTION = ADDITION;
                if(!Double.isNaN(num1))
                {
                    if(num1%1==0)
                    {
                        input.setText(String.valueOf(Math.round(num1))+"+");
                    }
                    else
                        input.setText(String.valueOf(num1)+"+");
//                    input.setText(null);
                    hiddeninput.setText(null);
                }
            }
            else
            {
                input.setText(null);
                hiddeninput.setText(null);
                num1 = Double.NaN;
                num2 = Double.NaN;
            }

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                if(!dividebyzeroerror) {
                    ACTION = SUBSTRACTION;
                    if (!Double.isNaN(num1)) {
                        if (num1 % 1 == 0) {
                            input.setText(String.valueOf(Math.round(num1)) + "-");
                        } else
                            input.setText(String.valueOf(num1) + "-");
                        hiddeninput.setText(null);
                    }
                }
                else
                {
                    input.setText(null);
                    hiddeninput.setText(null);
                    num1 = Double.NaN;
                    num2 = Double.NaN;
                }
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                if(!dividebyzeroerror) {
                    ACTION = MULTIPLICATION;
                    if (!Double.isNaN(num1)) {
                        if (num1 % 1 == 0) {
                            input.setText(String.valueOf(Math.round(num1)) + "*");
                        } else
                            input.setText(String.valueOf(num1) + "*");
//                    input.setText(null);
                        hiddeninput.setText(null);
                    }
                }
                else
                {
                    input.setText(null);
                    hiddeninput.setText(null);
                    num1 = Double.NaN;
                    num2 = Double.NaN;
                }
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                if(!dividebyzeroerror) {
                    ACTION = DIVISION;
                    if (!Double.isNaN(num1)) {
                        if (num1 % 1 == 0) {
                            input.setText(String.valueOf(Math.round(num1)) + "/");
                        } else
                            input.setText(String.valueOf(num1) + "/");
//                    input.setText(null);
                        hiddeninput.setText(null);
                    }
                }
                else
                {
                    input.setText(null);
                    hiddeninput.setText(null);
                    num1 = Double.NaN;
                    num2 = Double.NaN;
                }
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                if(!dividebyzeroerror) {
                    ACTION = PERCENT;
                    if (!Double.isNaN(num1)) {
                        if (num1 % 1 == 0) {
                            input.setText(String.valueOf(Math.round(num1)) + "%");
                        } else
                            input.setText(String.valueOf(num1) + "%");
//                    input.setText(null);
                        hiddeninput.setText(null);
                    }
                }
                else
                {
                    input.setText(null);
                    hiddeninput.setText(null);
                    num1 = Double.NaN;
                    num2 = Double.NaN;
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiddeninput.setText(null);
                input.setText(null);
                result.setText(null);
                num1 = Double.NaN;
                num2 = Double.NaN;
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hiddeninput.getText().length()>0)
                {
                    String newString = hiddeninput.getText().toString();
                    newString = newString.substring(0,newString.length()-1);
                    hiddeninput.setText(newString);
                }
                if(input.getText().length()>0)
                {
                    String newString = input.getText().toString();
                    newString = newString.substring(0,newString.length()-1);
                    input.setText(newString);
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
                if(!dividebyzeroerror) {
                    if (!Double.isNaN(num1) && !Double.isNaN(num2)) {
                        //ACTION= EQUAL;
                        if (!Double.isNaN(num1)) {
                            if (num1 % 1 == 0) {
                                result.setText(String.valueOf(Math.round(num1)));
                            } else
                                result.setText(String.valueOf(num1));
                        }
                    } else if (!Double.isNaN(num1)) {
                        if (num1 % 1 == 0) {
                            result.setText(String.valueOf(Math.round(num1)));
                        } else {
                            result.setText(String.valueOf(num1));
                        }
                    }
                }
                input.setText(null);
                hiddeninput.setText(null);
                num1 = Double.NaN;
                num2 = Double.NaN;
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
        hiddeninput=(EditText)findViewById(R.id.editTextHidden);
        result=(TextView)findViewById(R.id.textViewResult);
    }

    private void Calculate()
    {
        dividebyzeroerror=false;
        if(!Double.isNaN(num1))
        {
            if(validateUserInput(hiddeninput.getText().toString())){
                num2 = Double.parseDouble(hiddeninput.getText().toString());
                switch (ACTION) {
                    case ADDITION:
                        num1 = Calcequation.Calculate(num1,num2,ADDITION);
                        break;
                    case SUBSTRACTION:
                        num1 = Calcequation.Calculate(num1,num2,SUBSTRACTION);
                        break;
                    case MULTIPLICATION:
                        num1 = Calcequation.Calculate(num1,num2,MULTIPLICATION);
                        break;
                    case DIVISION:
                        if (num2 == 0) {
                            result.setText("Division by Zero error");
                            dividebyzeroerror = true;
                        } else
                            num1 = Calcequation.Calculate(num1,num2,DIVISION);
                        break;
                    case PERCENT:
                        num1 = Calcequation.Calculate(num1,num2,PERCENT);
                        break;
                    case EQUAL:
                        break;
                }
            }
        }
        else if(validateUserInput(hiddeninput.getText().toString()))
        {
            num1=Double.parseDouble(hiddeninput.getText().toString());
        }
        else if(validateUserInput(result.getText().toString()))
        {
            num1=Double.parseDouble(result.getText().toString());
        }
    }
}


