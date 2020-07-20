package com.shreyasrana.speaknow3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class abc extends AppCompatActivity {

    public File entry;
    public File words;
    String Words[][] =  new String[1000][4];
    public String mode = "Alphabetic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc);

        entry = new File(getApplicationContext().getFilesDir(), "numButtons");
        words = new File(getApplicationContext().getFilesDir(), "Words");

        for(int x = 0; x < 1000; x++){
            Words[x][0] = "zzzzzzzzzz";
            Words[x][1] = "-1";
            Words[x][2] = "0";
            Words[x][3] = "0";
        }


        BottomNavigationView BNV = (BottomNavigationView) findViewById(R.id.navigation);
        BNV.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Intent j = new Intent();
                                j.setClass(getApplicationContext(), abc.class);
                                startActivity(j);
                                finish();
                                return true;
                            case R.id.navigation_dashboard:
                                Intent i = new Intent();
                                i.setClass(getApplicationContext(), Name.class);
                                startActivity(i);
                                finish();
                                return true;
                            case R.id.navigation_header_container:
                                suggested();
                                return true;
                        }
                        return false;
                    }
                });


        try {
            if(entry.createNewFile()){
                FileOutputStream fos = new FileOutputStream(entry);
                fos.write((91 + "\n").getBytes());
                //fos.write((5 + "\n").getBytes());
                fos.close();

            }
            if(words.createNewFile()){
                FileOutputStream fos = new FileOutputStream(words);
                fos.write(("ac-high 0 0 0\nac-low 0 0 0\nac-off 0 0 0\nac-on 0 0 0\nairbed 0 0 0\n").getBytes());
                fos.write(("bathroom 0 0 0\nbed-down 0 0 0\nbed-up 0 0 0\nbreathing-prob 0 0 0\nbed 0 0 0\n").getBytes());
                fos.write(("chair 0 0 0\nchange 0 0 0\nclean 0 0 0\nclose 0 0 0\nclothes 0 0 0\ncold 0 0 0\ncream 0 0 0\ncrushed 0 0 0\n").getBytes());
                fos.write(("dressing 0 0 0\ndrink 0 0 0\ndry 0 0 0\ndining-room 0 0 0\ndiaper 0 0 0\n").getBytes());
                fos.write(("eat 0 0 0\n").getBytes());
                fos.write(("fan-fast 0 0 0\nfan-off 0 0 0\nfan-on 0 0 0\nfan-slow 0 0 0\nfell 0 0 0\nfood 0 0 0\nfresh 0 0 0\nfull 0 0 0\n").getBytes());
                fos.write(("gate 0 0 0\n").getBytes());
                fos.write(("half 0 0 0\nhand 0 0 0\nhappy 0 0 0\nhospital 0 0 0\nhurt 0 0 0\n").getBytes());
                //fos.write(("kirti 0 0 0\n").getBytes());
                fos.write(("less 0 0 0\nlight-off 0 0 0\nlight-on 0 0 0\n").getBytes());
                fos.write(("massage 0 0 0\nmedicine 0 0 0\nmedicine-pain 0 0 0\nmore 0 0 0\nmouth 0 0 0\nmouth-wash 0 0 0\nmusic 0 0 0\n").getBytes());
                fos.write(("news 0 0 0\nnose 0 0 0\nnot 0 0 0\nnot-hungry 0 0 0\nnumber 0 0 0\n").getBytes());
                fos.write(("off 0 0 0\noil 0 0 0\non 0 0 0\nopen 0 0 0\n").getBytes());
                fos.write(("pain 0 0 0\npain-ointment 0 0 0\npajama 0 0 0\nphone 0 0 0\npillow 0 0 0\npillow-different 0 0 0\npillow-thick 0 0 0\npillow-thin 0 0 0\n").getBytes());
                fos.write(("sad 0 0 0\nsit 0 0 0\nscarf 0 0 0\nsleep 0 0 0\nsmall 0 0 0\nsocks 0 0 0\nsongs 0 0 0\nspoon 0 0 0\nswitch 0 0 0\n").getBytes());
                fos.write(("t-shirt 0 0 0\nthick 0 0 0\nthin 0 0 0\ntime 0 0 0\ntoday 0 0 0\ntoilet 0 0 0\ntomorrow 0 0 0\ntowel 0 0 0\ntube 0 0 0\nturn 0 0 0\ntv 0 0 0\n").getBytes());
                fos.write(("warm 0 0 0\nwash 0 0 0\nwatch 0 0 0\nwater 0 0 0\nwater-less 0 0 0\nwater-more 0 0 0\nwet 0 0 0\nwheelchair 0 0 0\n").getBytes());
                fos.write(("yesterday 0 0 0\n").getBytes());

//                fos.write(("ac-high 0 0 0\nac-low 0 0 0\nac-off 0 0 0\nac-on 0 0 0\nairbed 0 0 0\n").getBytes());
//                fos.write(("bathroom 0 0 0\nbecasule 0 0 0\nbed-down 0 0 0\nbed-up 0 0 0\nbreathing-prob 0 0 0\nbed 0 0 0\n").getBytes());
//                fos.write(("chair 0 0 0\nchange 0 0 0\nclean 0 0 0\nclose 0 0 0\nclothes 0 0 0\ncold 0 0 0\nconstipation 0 0 0\ncream 0 0 0\ncrushed 0 0 0\ncipralex 0 0 0\n").getBytes());
//                fos.write(("dalia 0 0 0\ndettol 0 0 0\ndressing 0 0 0\ndrink 0 0 0\ndry 0 0 0\ndining-room 0 0 0\ndiaper 0 0 0\n").getBytes());
//                fos.write(("eat 0 0 0\n").getBytes());
//                fos.write(("fan-fast 0 0 0\nfan-off 0 0 0\nfan-on 0 0 0\nfan-slow 0 0 0\nfell 0 0 0\nfood 0 0 0\nfresh 0 0 0\nfull 0 0 0\n").getBytes());
//                fos.write(("gate 0 0 0\n").getBytes());
//                fos.write(("half 0 0 0\nhand 0 0 0\nhanky 0 0 0\nhappy 0 0 0\nhemu 0 0 0\nhomeopathy 0 0 0\nhospital 0 0 0\nhurt 0 0 0\n").getBytes());
//                fos.write(("kirti 0 0 0\n").getBytes());
//                fos.write(("less 0 0 0\nlight-off 0 0 0\nlight-on 0 0 0\nlipofen 0 0 0\n").getBytes());
//                fos.write(("madhuri 0 0 0\nmassage 0 0 0\nmedicine 0 0 0\nmedicine-cipralex 0 0 0\nmedicine-constipation 0 0 0\nmedicine-lipofen 0 0 0\nmedicine-pain 0 0 0\nmedicine-t-bact/bactroban 0 0 0\nmeera 0 0 0\nmilk 0 0 0\nmore 0 0 0\nmouth 0 0 0\nmouth-wash 0 0 0\nmunakka 0 0 0\nmusic 0 0 0\n").getBytes());
//                fos.write(("news 0 0 0\nnose 0 0 0\nnot 0 0 0\nnot-hungry 0 0 0\nnumber 0 0 0\n").getBytes());
//                fos.write(("off 0 0 0\noil 0 0 0\non 0 0 0\nopen 0 0 0\n").getBytes());
//                fos.write(("pain 0 0 0\npain-ointment 0 0 0\npajama 0 0 0\npapaji 0 0 0\nparafin 0 0 0\nphone 0 0 0\npillow 0 0 0\npillow-different 0 0 0\npillow-thick 0 0 0\npillow-thin 0 0 0\n").getBytes());
//                fos.write(("sad 0 0 0\nsit 0 0 0\nscarf 0 0 0\nsleep 0 0 0\nsmall 0 0 0\nsocks 0 0 0\nsongs 0 0 0\nsponge 0 0 0\nspoon 0 0 0\nsuji 0 0 0\nsunita 0 0 0\nswitch 0 0 0\n").getBytes());
//                fos.write(("t-shirt 0 0 0\ntailbone 0 0 0\ntea 0 0 0\nthick 0 0 0\nthin 0 0 0\ntime 0 0 0\ntoday 0 0 0\ntoilet 0 0 0\ntomorrow 0 0 0\ntowel 0 0 0\ntube 0 0 0\ntubelight-off 0 0 0\ntubelight-on 0 0 0\nturn 0 0 0\ntv 0 0 0\n").getBytes());
//                fos.write(("warm 0 0 0\nwash 0 0 0\nwatch 0 0 0\nwater 0 0 0\nwater-less 0 0 0\nwater-more 0 0 0\nwatery 0 0 0\nwet 0 0 0\nwheelchair 0 0 0\nwhite 0 0 0\n").getBytes());
//                fos.write(("yesterday 0 0 0\nyogendra 0 0 0\n").getBytes());


                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        refreshAlpha("Alphabetic");
    }

    public void findWords(View v){
        Button b = (Button) v;
        Character s = b.getText().toString().toLowerCase().charAt(0);

        int num = 0;
        FileInputStream fis = null;

        //getting the unsorted words
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


        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutWords);
        layout.removeAllViews();
        for(int x = 1; x <= num; x++){
            //layout.removeView(findViewById(x));
            if(Words[x-1][0].charAt(0) > s){
                break;
            }
            if(Words[x-1][0].charAt(0) == s){
                final Button btnTag = new Button(this);
                LinearLayout.LayoutParams params = (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                params.setMargins(0, 0, 0, 10);
                btnTag.setLayoutParams(params);
                btnTag.setText(Words[x-1][0]);
                btnTag.setId(x);
                btnTag.setWidth(700);
                btnTag.setHeight(40);
                btnTag.setBackgroundResource(R.drawable.rounded_button_unselected);
                layout.addView(btnTag);

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
                            restart.setClass(getApplicationContext(), abc.class);
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

                                //get what time this word was used
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat hour = new SimpleDateFormat("HH");
                                SimpleDateFormat minute = new SimpleDateFormat("mm");
                                Words[btnTag.getId()-1][2] = hour.format(new Date());
                                Words[btnTag.getId()-1][3] = minute.format(new Date());

                                FileOutputStream fos = null;
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
    }

    public void suggested(){
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Suggested.class);
        startActivity(i);
        finish();
    }
//    public void alphabetic(){
//        Intent i = new Intent();
//        i.setClass(getApplicationContext(), wordList.class);
//        startActivity(i);
//    }
}