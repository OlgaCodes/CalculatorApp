package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {
    Bundle newBundy = new Bundle();
    EditText screen;
    Boolean newOperator=true;
    String oldNumber="";
    String oper="=";
    DecimalFormat dc = new DecimalFormat();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        initTextViews();
        dc.setMaximumFractionDigits(10);

        if(savedInstanceState!=null){
            oper=savedInstanceState.getString("oper");
            oldNumber=savedInstanceState.getString("oldNumber");
            newOperator=savedInstanceState.getBoolean("newOperator");
            screen.setText(savedInstanceState.getString("editText"));
        }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("editText", screen.getText().toString());
        outState.putString("oper",oper);
        outState.putString("oldNumber",oldNumber);
        outState.putBoolean("newOperator",newOperator);
        newBundy.putBundle("newBundy", outState);

    }

    private void initTextViews(){
        screen=(EditText) findViewById(R.id.screen);
    }

    public void numberEvent(View view){
        if(newOperator)
            screen.setText("");
        if(oldNumber.equals("blad")|| oldNumber.equals("-NaN")){
            screen.setText("");
        }
        newOperator=false;
        String number=screen.getText().toString();
        if(number.equals("0") || number.equals("blad") || number.equals("-NaN"))
            number="";
        switch (view.getId()){
            case R.id.btn0:
                number+="0";
                break;
            case R.id.btn1:
                number+="1";
                break;
            case R.id.btn2:
                number+="2";
                break;
            case R.id.btn3:
                number+="3";
                break;
            case R.id.btn4:
                number+="4";
                break;
            case R.id.btn5:
                number+="5";
                break;
            case R.id.btn6:
                number+="6";
                break;
            case R.id.btn7:
                number+="7";
                break;
            case R.id.btn8:
                number+="8";
                break;
            case R.id.btn9:
                number+="9";
                break;
            case R.id.btnPlusMinus:
                if(number.contains("-"))
                    break;
                else{
                    number="-"+number;
                    break;
                }

        }
        screen.setText(number);
    }

    public void putDot(View view){
        String text = screen.getText().toString().trim();
        if(text.equals("blad")||text.equals("-NaN")){
            screen.setText(dc.format(0));
        }
        else{
            if(!text.contains(".")){
                if(text.length()>0){
                    screen.setText(text+".");
                }
            }
        }
    }
    public void operatorEvent(View view){
    newOperator = true;
    oldNumber=screen.getText().toString();
    switch (view.getId()){
        case R.id.btnDivide:
            oper = "/";
            break;
        case R.id.Multiply:
            oper = "X";
            break;
        case R.id.Minus:
            oper = "-";
            break;
        case R.id.plus:
            oper = "+";
            break;
    }
   }

    public void calculateEvent(View view){
        String newNumber=screen.getText().toString();
        double result= 0.0;
        if(oldNumber.equals("blad")||oldNumber.equals("-NaN")){
            screen.setText(dc.format(result));
        }
        else if(oldNumber.equals("")){
            oldNumber="0";
        }
        else if(oper=="/"&&Double.parseDouble(newNumber)==0.0){
                    screen.setText("blad");
        }
         else {

            try {
                switch (oper) {
                    case "+":
                        result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                        break;
                    case "-":
                        result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                        break;
                    case "X":
                        result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                        break;
                    case "/":
                        result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                        break;

                }
                if (Double.isInfinite(result) || Double.isNaN(result))
                    screen.setText(dc.format(0));
                else
                    screen.setText(dc.format(result));

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
    public void equal(View view){
        calculateEvent(view);
        oper="";
      //  oldNumber=nowOld;
        oldNumber=screen.getText().toString();
    }

    public void ACEvent(View view){
        screen.setText("0");
        newOperator=true;
    }

    public void CEvent(View view){
        int cursorPos=screen.getSelectionStart();
        int textLength=screen.getText().length();
        if(cursorPos!=0 && textLength!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder)screen.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            screen.setText(selection);
            screen.setSelection(cursorPos-1);
        }
    }

}