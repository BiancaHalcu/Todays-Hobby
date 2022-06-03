package com.example.todayshobby;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button buton;String mLine;
    private Button next;
    String []result;
    private int val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        buton = findViewById(R.id.genereaza);
        next = findViewById(R.id.next);
        next.setEnabled(false);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                val = r.nextInt(15);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(getAssets().open("activitati.txt")));

                    while ((mLine = reader.readLine()) != null) {
                        String []result=mLine.split("/");
                        if(Integer. parseInt(result[0])==val)
                        {
                            text.setText(result[1]);
                            next.setEnabled(true);
                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                                    intent.putExtra("name", result[1]);
                                    intent.putExtra("linie", mLine);
                                    startActivity(intent);
                                }
                            });
                            break;
                        }
                    }
                } catch (IOException e) {
                    // Exception
                    e.printStackTrace();
                }

            }
        });



    }


}