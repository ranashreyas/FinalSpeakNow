package com.shreyasrana.speaknow3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Suggested extends AppCompatActivity {

    public File entry;
    public File words;
    String Words[][] = new String[1000][4];
    public String mode = "Time";
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;


        for(int x = 0; x < 1000; x++){
            Words[x][0] = "zzzzzzzzzz";
            Words[x][1] = "-1";
            Words[x][2] = "0";
            Words[x][3] = "0";
        }


        //initializing the files
        entry = new File(getApplicationContext().getFilesDir(), "numButtons");
        words = new File(getApplicationContext().getFilesDir(), "Words");


        BottomNavigationView BNV = (BottomNavigationView) findViewById(R.id.navigation);
        BNV.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                abc();
                                return true;
                            case R.id.navigation_dashboard:
                                Intent i = new Intent();
                                i.setClass(getApplicationContext(), Name.class);
                                startActivity(i);
                                finish();
                                return true;
                            case R.id.navigation_header_container:
                                Intent j = new Intent();
                                j.setClass(getApplicationContext(), Suggested.class);
                                startActivity(j);
                                finish();
                                return true;
                        }
                        return false;
                    }
                });

        //creating the files if not so already
        try {
            if(entry.createNewFile()){
                FileOutputStream fos = new FileOutputStream(entry);
                fos.write((124 + " 0 0\n").getBytes());
                //fos.write((5 + " 0 0\n").getBytes());
                fos.close();

            }
            if(words.createNewFile()){
                FileOutputStream fos = new FileOutputStream(words);
                fos.write(("ac-high 0 0 0\nac-low 0 0 0\nac-off 0 0 0\nac-on 0 0 0\nairbed 0 0 0\n").getBytes());
                fos.write(("bathroom 0 0 0\nbecasule 0 0 0\nbed-down 0 0 0\nbed-up 0 0 0\nbreathing-prob 0 0 0\nbed 0 0 0\n").getBytes());
                fos.write(("chair 0 0 0\nchange 0 0 0\nclean 0 0 0\nclose 0 0 0\nclothes 0 0 0\ncold 0 0 0\nconstipation 0 0 0\ncream 0 0 0\ncrushed 0 0 0\ncipralex 0 0 0\n").getBytes());
                fos.write(("dalia 0 0 0\ndettol 0 0 0\ndressing 0 0 0\ndrink 0 0 0\ndry 0 0 0\ndining-room 0 0 0\ndiaper 0 0 0\n").getBytes());
                fos.write(("eat 0 0 0\n").getBytes());
                fos.write(("fan-fast 0 0 0\nfan-off 0 0 0\nfan-on 0 0 0\nfan-slow 0 0 0\nfell 0 0 0\nfood 0 0 0\nfresh 0 0 0\nfull 0 0 0\n").getBytes());
                fos.write(("gate 0 0 0\n").getBytes());
                fos.write(("half 0 0 0\nhand 0 0 0\nhanky 0 0 0\nhappy 0 0 0\nhemu 0 0 0\nhomeopathy 0 0 0\nhospital 0 0 0\nhurt 0 0 0\n").getBytes());
                fos.write(("kirti 0 0 0\n").getBytes());
                fos.write(("less 0 0 0\nlight-off 0 0 0\nlight-on 0 0 0\nlipofen 0 0 0\n").getBytes());
                fos.write(("madhuri 0 0 0\nmassage 0 0 0\nmedicine 0 0 0\nmedicine-cipralex 0 0 0\nmedicine-constipation 0 0 0\nmedicine-lipofen 0 0 0\nmedicine-pain 0 0 0\nmedicine-t-bact/bactroban 0 0 0\nmeera 0 0 0\nmilk 0 0 0\nmore 0 0 0\nmouth 0 0 0\nmouth-wash 0 0 0\nmunakka 0 0 0\nmusic 0 0 0\n").getBytes());
                fos.write(("news 0 0 0\nnose 0 0 0\nnot 0 0 0\nnot-hungry 0 0 0\nnumber 0 0 0\n").getBytes());
                fos.write(("off 0 0 0\noil 0 0 0\non 0 0 0\nopen 0 0 0\n").getBytes());
                fos.write(("pain 0 0 0\npain-ointment 0 0 0\npajama 0 0 0\npapaji 0 0 0\nparafin 0 0 0\nphone 0 0 0\npillow 0 0 0\npillow-different 0 0 0\npillow-thick 0 0 0\npillow-thin 0 0 0\n").getBytes());
                fos.write(("sad 0 0 0\nsit 0 0 0\nscarf 0 0 0\nsleep 0 0 0\nsmall 0 0 0\nsocks 0 0 0\nsongs 0 0 0\nsponge 0 0 0\nspoon 0 0 0\nsuji 0 0 0\nsunita 0 0 0\nswitch 0 0 0\n").getBytes());
                fos.write(("t-shirt 0 0 0\ntailbone 0 0 0\ntea 0 0 0\nthick 0 0 0\nthin 0 0 0\ntime 0 0 0\ntoday 0 0 0\ntoilet 0 0 0\ntomorrow 0 0 0\ntowel 0 0 0\ntube 0 0 0\ntubelight-off 0 0 0\ntubelight-on 0 0 0\nturn 0 0 0\ntv 0 0 0\n").getBytes());
                fos.write(("warm 0 0 0\nwash 0 0 0\nwatch 0 0 0\nwater 0 0 0\nwater-less 0 0 0\nwater-more 0 0 0\nwatery 0 0 0\nwet 0 0 0\nwheelchair 0 0 0\nwhite 0 0 0\n").getBytes());
                fos.write(("yesterday 0 0 0\nyogendra 0 0 0\n").getBytes());

                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshAlpha(mode);
    }



    public void refreshAlpha(String mode){
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        int num = 0;
        FileInputStream fis = null;

        //getting the unsorted words
        int len = 0;
        try {
            //finding the number of buttons stored in the numButtons file
            fis = getApplicationContext().openFileInput("numButtons");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line;
            line = bufferedReader.readLine();
            Log.d("myPath", entry.getAbsolutePath() + " " + line);
            num = Integer.valueOf(line);

            //making array of the strings
            fis = getApplicationContext().openFileInput("Words");
            isr = new InputStreamReader(fis);
            bufferedReader = new BufferedReader(isr);

            int ctr = 0;
            while ((line = bufferedReader.readLine()) != null) {
                Scanner cin = new Scanner(new StringReader(line));
                Words[ctr][0] = cin.next();
                Words[ctr][1] = cin.next();
                Words[ctr][2] = cin.next();
                Words[ctr][3] = cin.next();
                ctr++;
            }
            len = ctr;

//            String s = "";
//            for(int x = 0; x < ctr; x++){
//                s += Words[x][0];
//                s += " | ";
//                s += Words[x][1] + " ";
//            }
//            Log.d("allText", s);

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error yeet", e.toString());
        }


        //sort the words alphabetically
        if(mode.equals("Alphabetic"))
            Arrays.sort(Words, new Comparator<String[]>(){

                @Override
                public int compare(final String[] first, final String[] second){
                    // here you should usually check that first and second
                    // a) are not null and b) have at least two items
                    // updated after comments: comparing Double, not Strings
                    return String.valueOf(first[0]).compareTo(String.valueOf(second[0]));
                }
            });

        else if(mode.equals("Most Used")){
            Arrays.sort(Words, new Comparator<String[]>(){
                //sorts based on usage
                @Override
                public int compare(final String[] first, final String[] second){
                    // here you should usually check that first and second
                    // a) are not null and b) have at least two items
                    // updated after comments: comparing Double, not Strings
                    return Integer.valueOf(second[1]).compareTo(Integer.valueOf(first[1]));
                }
            });
        } else if(mode.equals("Time")){
            //finds the n words that are closest to the current time, and the rest of 12-n words are sorted based on
            //usage.

            //get what time this word was used
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat hour = new SimpleDateFormat("HH");
            SimpleDateFormat minute = new SimpleDateFormat("mm");
            int time = Integer.valueOf(hour.format(new Date())) * 60 + Integer.valueOf(minute.format(new Date()));
            //sorts entire array by usage
            Arrays.sort(Words, new Comparator<String[]>(){
                @Override
                public int compare(final String[] first, final String[] second){
                    // here you should usually check that first and second
                    // a) are not null and b) have at least two items
                    // updated after comments: comparing Double, not Strings
                    return Integer.valueOf(second[1]).compareTo(Integer.valueOf(first[1]));
                }
            });
            int ctr = 0;
            for(int x = 0; x <= len; x++){
                if((Integer.valueOf(Words[x][2]) * 60 + Integer.valueOf(Words[x][3])) >= (time-15) && (Integer.valueOf(Words[x][2]) * 60 + Integer.valueOf(Words[x][3])) <= (time+15)){
                    String d[] = new String[4];
                    d = Words[x];
                    Words[x] = Words[ctr];
                    Words[ctr] = d;
                    ctr++;
                }
            }
        }

        //Toast.makeText(getApplicationContext(), Arrays.deepToString(Words), Toast.LENGTH_SHORT).show();


        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutMain);
        layout.removeAllViews();
        int y = 0;
        //add the buttons
        for(int x = 1; x <= 12; x++){
            if(x % 2 == 1){
                LinearLayout layoutH = new LinearLayout(Suggested.this);
                y = x*10000;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                params.setMargins(0, 0, 0, 10);
                layoutH.setLayoutParams(params);
                layoutH.setId(y);
                layoutH.setOrientation(LinearLayout.HORIZONTAL);
                layout.addView(layoutH);
            }
            //remove button with this previous id:
            //layout.removeView(findViewById(R.id.x));

            //set the properties for button

            final Button btnTag = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if(x%2==1){
                params.setMargins(0,0,5,0);
            }else{
                params.setMargins(5,0,0,0);
            }
            btnTag.setLayoutParams(params);
            btnTag.setText(Words[x-1][0]);
            btnTag.setId(x);
            btnTag.setHeight(40);
            btnTag.setWidth(width/2);
            btnTag.setBackgroundResource(R.drawable.rounded_button_unselected);
            //add button to the layout
            LinearLayout l = (LinearLayout) findViewById(y);
            l.addView(btnTag);


            final int finalNum = num;


            btnTag.setOnLongClickListener(new View.OnLongClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public boolean onLongClick(View v) {
                    Words[btnTag.getId()-1][0] = "zzzzzzzzzz";
                    Words[btnTag.getId()-1][1] = "-1";
                    Words[btnTag.getId()-1][2] = "0";
                    Words[btnTag.getId()-1][3] = "0";

                    FileInputStream fis = null;
                    int num = 0;
                    try {
                        //updates the number of words in a file (subtracts 1)
                        fis = getApplicationContext().openFileInput("numButtons");
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader bufferedReader = new BufferedReader(isr);
                        //StringBuilder sb = new StringBuilder();
                        String line;
                        line = bufferedReader.readLine();
                        //Log.d("myPath", entry.getAbsolutePath() + " " + line);
                        num = Integer.valueOf(line);
                        num--;

                        FileOutputStream fos = new FileOutputStream(entry);
                        fos.write((Integer.toString(num) + "\n").getBytes());
                        fos.close();

                        fos = new FileOutputStream(words);
                        for(int y = 0; y < finalNum; y++){
                            fos.write((Words[y][0] + " " + Words[y][1] + " " + Words[y][2] + " " + Words[y][3] +"\n").getBytes());
                        }

                        fos.close();

                        Intent restart = new Intent();
                        restart.setClass(getApplicationContext(), Suggested.class);
                        startActivity(restart);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("error yeet", e.toString());
                    }



                    return true;
                }
            });


            btnTag.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    Handler handler = new Handler();
                    btnTag.setBackgroundResource(R.drawable.rounded_button_selected);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            btnTag.setBackgroundResource(R.drawable.rounded_button_unselected);
                            // Actions to do after 2 seconds
                            Log.d("clicked", Integer.toString(btnTag.getId()));
                            int i = Integer.valueOf(Words[btnTag.getId()-1][1]);
                            i++;
                            Words[btnTag.getId()-1][1] = Integer.toString(i);
                            FileOutputStream fos = null;

                            //get what time this word was used
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat hour = new SimpleDateFormat("HH");
                            SimpleDateFormat minute = new SimpleDateFormat("mm");
                            Words[btnTag.getId()-1][2] = hour.format(new Date());
                            Words[btnTag.getId()-1][3] = minute.format(new Date());

                            try {
                                fos = new FileOutputStream(words);
                                for(int y = 0; y < finalNum; y++){
                                    fos.write((Words[y][0] + " " + Words[y][1] + " " + Words[y][2] + " " + Words[y][3] +"\n").getBytes());
                                }

                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 700);
                }
            });
        }
    }

    public void add(View v){
        final String[] bText = new String[1];
        int num = 0;
        FileInputStream fis = null;
        try {

            //updates the number of words in a file
            fis = getApplicationContext().openFileInput("numButtons");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            //StringBuilder sb = new StringBuilder();
            String line;
            line = bufferedReader.readLine();
            Log.d("myPath", entry.getAbsolutePath() + " " + line);
            num = Integer.valueOf(line);
            num++;

            FileOutputStream fos = new FileOutputStream(entry);
            fos.write((Integer.toString(num) + "\n").getBytes());
            fos.close();




            //adds the word to another file
            final EditText text = findViewById(R.id.text);
            final Button b = findViewById(R.id.done);

            text.setVisibility(View.VISIBLE);
            b.setVisibility(View.VISIBLE);
            //findViewById(R.id.delete).setVisibility(View.INVISIBLE);



            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bText[0] = text.getText().toString();

                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(words, true);
                        fos.write((bText[0] + " " + 0 + "\n").getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Intent restart = new Intent();
                    restart.setClass(getApplicationContext(), Suggested.class);
                    startActivity(restart);
                    finish();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error yeet", e.toString());
        }
    }


    //    public void alphabetic(){
//        Intent i = new Intent();
//        i.setClass(getApplicationContext(), wordList.class);
//        startActivity(i);
//    }
    public void abc(){
        Intent i = new Intent();
        i.setClass(getApplicationContext(), abc.class);
        startActivity(i);
        finish();
    }
}