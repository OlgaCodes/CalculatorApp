package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnSimple, btnAdvanced, btnAbout, btnExit, btnBack;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView designedBy, name, infoAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimple = (Button) findViewById(R.id.button1);
        btnSimple.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(intent);

        });
        btnAdvanced=(Button)findViewById(R.id.button2);
        btnAdvanced.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            startActivity(intent);
        });

        btnAbout=(Button)findViewById(R.id.button3);
        btnAbout.setOnClickListener(v->{
            createAboutDialog();
        });

        btnExit=(Button)findViewById(R.id.button4);
        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    public void createAboutDialog(){
        dialogBuilder=new AlertDialog.Builder(this);
        final View aboutView = getLayoutInflater().inflate(R.layout.popup,null);
        designedBy = (TextView) aboutView.findViewById(R.id.designedBy);
        name = (TextView) aboutView.findViewById(R.id.name);
        infoAbout = (TextView) aboutView.findViewById(R.id.name);

        btnBack = (Button)aboutView.findViewById(R.id.btnBack);

        dialogBuilder.setView(aboutView);
        dialog=dialogBuilder.create();
        dialog.show();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}