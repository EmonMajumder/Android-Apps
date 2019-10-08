package com.example.calculator;

public class Calcequation {

    public static double Calculate(double firstnumber, double secondnumber, char ACTION)
    {
        double num = 0;
        switch (ACTION) {
            case '+':
                num = firstnumber+secondnumber;
                break;
            case '-':
                num = firstnumber-secondnumber;
                break;
            case '*':
                num = firstnumber*secondnumber;
                break;
            case '/':
                num = firstnumber/secondnumber;
                break;
            case '%':
                num = (secondnumber/firstnumber) * 100;
                break;
        }
        return num;
    }
}
