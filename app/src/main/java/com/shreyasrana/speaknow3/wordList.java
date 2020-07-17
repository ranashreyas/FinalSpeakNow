package com.shreyasrana.speaknow3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class wordList extends AppCompatActivity {

    public File entry;
    public File words;
    String Words[][] = new String[1000][2];
    public String mode = "Alphabetic";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);


        for(int x = 0; x < 1000; x++){
            Words[x][0] = "zzzzzzzzzz";
            Words[x][1] = "-1";
        }
        BottomNavigationView BNV = (BottomNavigationView) findViewById(R.id.navigation);
        BNV.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                abc();
                                return true;
//                            case R.id.navigation_dashboard:
//                                return true;
                            case R.id.navigation_header_container:
                                suggested();
                                return true;
                        }
                        return false;
                    }
                });
        //get the spinner from the xml.
//        Spinner dropdown = findViewById(R.id.spinner);
//        final String[] items = {"Alphabetic", "Most Used"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        dropdown.setAdapter(adapter);


        //initializing the files
        entry = new File(getApplicationContext().getFilesDir(), "numButtons");
        words = new File(getApplicationContext().getFilesDir(), "Words");

        //creating the files if not so already
        try {
            if(entry.createNewFile()){
                FileOutputStream fos = new FileOutputStream(entry);
                fos.write((124 + "\n").getBytes());
                //fos.write((5 + "\n").getBytes());
                fos.close();

            }
            if(words.createNewFile()){
                FileOutputStream fos = new FileOutputStream(words);
                fos.write(("ac-high 0\nac-low 0\nac-off 0\nac-on 0\nairbed 0\n").getBytes());
                fos.write(("bathroom 0\nbecasule 0\nbed-down 0\nbed-up 0\nbreathing-prob 0\nbed 0\n").getBytes());
                fos.write(("chair 0\nchange 0\nclean 0\nclose 0\nclothes 0\ncold 0\nconstipation 0\ncream 0\ncrushed 0\ncipralex 0\n").getBytes());
                fos.write(("dalia 0\ndettol 0\ndressing 0\ndrink 0\ndry 0\ndining-room 0\ndiaper 0\n").getBytes());
                fos.write(("eat 0\n").getBytes());
                fos.write(("fan-fast 0\nfan-off 0\nfan-on 0\nfan-slow 0\nfell 0\nfood 0\nfresh 0\nfull 0\n").getBytes());
                fos.write(("gate 0\n").getBytes());
                fos.write(("half 0\nhand 0\nhanky 0\nhappy 0\nhemu 0\nhomeopathy 0\nhospital 0\nhurt 0\n").getBytes());
                fos.write(("kirti 0\n").getBytes());
                fos.write(("less 0\nlight-off 0\nlight-on 0\nlipofen 0\n").getBytes());
                fos.write(("madhuri 0\nmassage 0\nmedicine 0\nmedicine-cipralex 0\nmedicine-constipation 0\nmedicine-lipofen 0\nmedicine-pain 0\nmedicine-t-bact/bactroban 0\nmeera 0\nmilk 0\nmore 0\nmouth 0\nmouth-wash 0\nmunakka 0\nmusic 0\n").getBytes());
                fos.write(("news 0\nnose 0\nnot 0\nnot-hungry 0\nnumber 0\n").getBytes());
                fos.write(("off 0\noil 0\non 0\nopen 0\n").getBytes());
                fos.write(("pain 0\npain-ointment 0\npajama 0\npapaji 0\nparafin 0\nphone 0\npillow 0\npillow-different 0\npillow-thick 0\npillow-thin 0\n").getBytes());
                fos.write(("sad 0\nsit 0\nscarf 0\nsleep 0\nsmall 0\nsocks 0\nsongs 0\nsponge 0\nspoon 0\nsuji 0\nsunita 0\nswitch 0\n").getBytes());
                fos.write(("t-shirt 0\ntailbone 0\ntea 0\nthick 0\nthin 0\ntime 0\ntoday 0\ntoilet 0\ntomorrow 0\ntowel 0\ntube 0\ntubelight-off 0\ntubelight-on 0\nturn 0\ntv 0\n").getBytes());
                fos.write(("warm 0\nwash 0\nwatch 0\nwater 0\nwater-less 0\nwater-more 0\nwatery 0\nwet 0\nwheelchair 0\nwhite 0\n").getBytes());
                fos.write(("yesterday 0\nyogendra 0\n").getBytes());

                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                mode = items[position];
//                Toast.makeText(getApplicationContext(), mode, Toast.LENGTH_SHORT).show();
//                refreshAlpha(mode);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                Toast.makeText(getApplicationContext(), mode, Toast.LENGTH_SHORT).show();
//                refreshAlpha(mode);
//            }
//
//        });

        refreshAlpha("Alphabetic");

        //refreshing the layout so it looks the way it should based on the internal files
        //refresh();
    }






    public void refreshAlpha(String mode){
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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

            //sorts based on usage
        else {
            Arrays.sort(Words, new Comparator<String[]>(){

                @Override
                public int compare(final String[] first, final String[] second){
                    // here you should usually check that first and second
                    // a) are not null and b) have at least two items
                    // updated after comments: comparing Double, not Strings
                    return Integer.valueOf(second[1]).compareTo(Integer.valueOf(first[1]));
                }
            });
        }

        //Toast.makeText(getApplicationContext(), Arrays.deepToString(Words), Toast.LENGTH_SHORT).show();



        //add the buttons
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutMain);
        LinearLayout A = (LinearLayout) findViewById(R.id.wordsA);
//        RelativeLayout Arel = findViewById(R.id.Arel);
        LinearLayout B = (LinearLayout) findViewById(R.id.wordsB);
//        RelativeLayout Brel = findViewById(R.id.Brel);
        LinearLayout C = (LinearLayout) findViewById(R.id.wordsC);
        LinearLayout D = (LinearLayout) findViewById(R.id.wordsD);
        LinearLayout E = (LinearLayout) findViewById(R.id.wordsE);
        LinearLayout F = (LinearLayout) findViewById(R.id.wordsF);
        LinearLayout G = (LinearLayout) findViewById(R.id.wordsG);
        LinearLayout H = (LinearLayout) findViewById(R.id.wordsH);
        LinearLayout I = (LinearLayout) findViewById(R.id.wordsI);
        LinearLayout J = (LinearLayout) findViewById(R.id.wordsJ);
        LinearLayout K = (LinearLayout) findViewById(R.id.wordsK);
        LinearLayout L = (LinearLayout) findViewById(R.id.wordsL);
        LinearLayout M = (LinearLayout) findViewById(R.id.wordsM);
        LinearLayout N = (LinearLayout) findViewById(R.id.wordsN);
        LinearLayout O = (LinearLayout) findViewById(R.id.wordsO);
        LinearLayout P = (LinearLayout) findViewById(R.id.wordsP);
        LinearLayout Q = (LinearLayout) findViewById(R.id.wordsQ);
        LinearLayout R1 = (LinearLayout) findViewById(R.id.wordsR);
        LinearLayout S = (LinearLayout) findViewById(R.id.wordsS);
        LinearLayout T = (LinearLayout) findViewById(R.id.wordsT);
        LinearLayout U = (LinearLayout) findViewById(R.id.wordsU);
        LinearLayout V = (LinearLayout) findViewById(R.id.wordsV);
        LinearLayout W = (LinearLayout) findViewById(R.id.wordsW);
        LinearLayout Y = (LinearLayout) findViewById(R.id.wordsY);
        LinearLayout Z = (LinearLayout) findViewById(R.id.wordsZ);

        for(int x = 1; x <= num; x++){

            //remove button with this previous id:
//            Arel.removeAllViews();
//            Brel.removeAllViews();
            A.removeView(findViewById(x));
            B.removeView(findViewById(x));
            C.removeView(findViewById(x));
            D.removeView(findViewById(x));
            E.removeView(findViewById(x));
            F.removeView(findViewById(x));
            G.removeView(findViewById(x));
            H.removeView(findViewById(x));
            I.removeView(findViewById(x));
            J.removeView(findViewById(x));
            K.removeView(findViewById(x));
            L.removeView(findViewById(x));
            M.removeView(findViewById(x));
            N.removeView(findViewById(x));
            O.removeView(findViewById(x));
            P.removeView(findViewById(x));
            Q.removeView(findViewById(x));
            R1.removeView(findViewById(x));
            S.removeView(findViewById(x));
            T.removeView(findViewById(x));
            U.removeView(findViewById(x));
            V.removeView(findViewById(x));
            W.removeView(findViewById(x));
            Y.removeView(findViewById(x));
            Z.removeView(findViewById(x));

            //set the properties for button

            final Button btnTag = new Button(this);

            btnTag.setText(Words[x-1][0]);
            btnTag.setId(x);
            btnTag.setWidth(700);
            btnTag.setHeight(300);
            if(mode.equals("Most Used")){
                //add button to the layout
                layout.addView(btnTag);
            } else {
                if(Words[x-1][0].charAt(0) == 'a'){
                    A.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'b'){
                    B.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'c'){
                    C.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'd'){
                    D.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'e'){
                    E.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'f'){
                    F.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'g'){
                    G.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'h'){
                    H.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'i'){
                    I.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'j'){
                    J.addView(btnTag);
                }

                if(Words[x-1][0].charAt(0) == 'k'){
                    K.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'l'){
                    L.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'm'){
                    M.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'n'){
                    N.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'o'){
                    O.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'p'){
                    P.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'q'){
                    Q.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'r'){
                    R1.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 's'){
                    S.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 't'){
                    T.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'u'){
                    U.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'v'){
                    V.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'w'){
                    W.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'y'){
                    Y.addView(btnTag);
                }
                if(Words[x-1][0].charAt(0) == 'z'){
                    Z.addView(btnTag);
                }
            }


            final int finalNum = num;


            btnTag.setOnLongClickListener(new View.OnLongClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public boolean onLongClick(View v) {
                    Words[btnTag.getId()-1][0] = "zzzzzzzzzz";
                    Words[btnTag.getId()-1][1] = "-1";

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
                            fos.write((Words[y][0] + " " + Words[y][1] + "\n").getBytes());
                        }

                        fos.close();

                        Intent restart = new Intent();
                        restart.setClass(getApplicationContext(), wordList.class);
                        startActivity(restart);
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
                    btnTag.setBackgroundColor(Color.GREEN);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            btnTag.setBackgroundColor(Color.parseColor("#D6D7D7"));
                            // Actions to do after 2 seconds
                            Log.d("clicked", Integer.toString(btnTag.getId()));
                            int i = Integer.valueOf(Words[btnTag.getId()-1][1]);
                            i++;
                            Words[btnTag.getId()-1][1] = Integer.toString(i);
                            FileOutputStream fos = null;
                            try {
                                fos = new FileOutputStream(words);
                                for(int y = 0; y < finalNum; y++){
                                    fos.write((Words[y][0] + " " + Words[y][1] + "\n").getBytes());
                                }

                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 1000);
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
                    restart.setClass(getApplicationContext(), wordList.class);
                    startActivity(restart);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error yeet", e.toString());
        }
    }

    public void suggested(){
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Suggested.class);
        startActivity(i);
    }
    public void abc(){
        Intent i = new Intent();
        i.setClass(getApplicationContext(), abc.class);
        startActivity(i);
    }












//    public void refresh(){
//        int num = 0;
//        FileInputStream fis = null;
//
//
//        try {
//            //finding the number of buttons stored in the numButtons file
//            fis = getApplicationContext().openFileInput("numButtons");
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader bufferedReader = new BufferedReader(isr);
//
//            String line;
//            line = bufferedReader.readLine();
//            Log.d("myPath", entry.getAbsolutePath() + " " + line);
//            num = Integer.valueOf(line);
//
//            //making array of the strings
//            fis = getApplicationContext().openFileInput("Words");
//            isr = new InputStreamReader(fis);
//            bufferedReader = new BufferedReader(isr);
//
//            int ctr = 0;
//            while ((line = bufferedReader.readLine()) != null) {
//                Scanner cin = new Scanner(new StringReader(line));
//                Words[ctr][0] = cin.next();
//                Words[ctr][1] = cin.next();
//                ctr++;
//
//            }
//
////            String s = "";
////            for(int x = 0; x < ctr; x++){
////                s += Words[x][0];
////                s += " | ";
////                s += Words[x][1] + " ";
////            }
////            Log.d("allText", s);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.d("error yeet", e.toString());
//        }
//
//        //adding the appropriate number of buttons to the layout
//        for(int x = 1; x <= num; x++){
//            LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
//            //set the properties for button
//            Button btnTag = new Button(this);
//            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            btnTag.setText(Words[x-1][0]);
//            btnTag.setId(x);
//            btnTag.setWidth(200);
//
//            //add button to the layout
//            layout.addView(btnTag);
//        }
//
//
//    }


}