package com.shreyasrana.speaknow3;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Name extends AppCompatActivity {

    String f;
    String name;

    File words;
    File numButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        words = new File(getApplicationContext().getFilesDir(), "Words");
        numButtons = new File(getApplicationContext().getFilesDir(), "numButtons");

    }

    public void Return(View v){
        Intent returnToScrolling = new Intent();
        returnToScrolling.setClass(getApplicationContext(), abc.class);
        EditText e = findViewById(R.id.editText);
        Button b = (Button) v;

        if(b.getText().equals("Cancel")){
            startActivity(returnToScrolling);
        }


        if(e.getText().toString().length() < 1){
            return;
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(words, true);
                String s = "";
                for(int x = 0; x < e.getText().toString().length(); x++){
                    if(e.getText().toString().charAt(x) == ' '){
                        s+='-';
                    } else {
                        s+=e.getText().toString().charAt(x);
                    }
                }
                fos.write((s.toLowerCase() + " 0 0 0\n").getBytes());
                fos.close();



                FileInputStream fis = getApplicationContext().openFileInput("numButtons");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                //StringBuilder sb = new StringBuilder();
                String line;
                line = bufferedReader.readLine();
                //Log.d("myPath", entry.getAbsolutePath() + " " + line);
                int num = Integer.valueOf(line);
                num++;

                FileOutputStream fos2 = new FileOutputStream(numButtons);
                fos2.write((Integer.toString(num) + "\n").getBytes());
                fos2.close();
            } catch (java.io.IOException e1) {
                e1.printStackTrace();
            }

        }
        //returnToScrolling.putExtra("name", String.valueOf(e.getText()));
//        returnToScrolling.putExtra("x", Integer.valueOf(Character.toString(f.charAt(0))));
//        returnToScrolling.putExtra("y", Integer.valueOf(Character.toString(f.charAt(2))));

        startActivity(returnToScrolling);
        finish();
    }

}