package com.shreyasrana.speaknow3;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SpellOut extends AppCompatActivity {

    //private TextToSpeech myTTS = new TextToSpeech(getApplicationContext(), (TextToSpeech.OnInitListener) this);
    TextView words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_out);
        words  = findViewById(R.id.words);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        words.setText(getIntent().getStringExtra("text"));


//        myTTS.setLanguage(Locale.US);
//        myTTS.speak(words.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

    }

    public void addLetter(View v){
        Button b = (Button) v;
        String text = words.getText().toString();
        text += (b.getText().toString()) ;
        words.setText(text);
    }

    public void addNumber(View v){
        Button b = (Button) v;
        String text = words.getText().toString();
        text += b.getText().toString();
        words.setText(text);
    }

    public void addSpace(View v){
        String text = words.getText().toString();
        text += " ";
        words.setText(text);
    }

    public void delete(View v){
        String text = words.getText().toString();
        if(words.getText().length() == 0){
            return;
        }
        text = text.substring(0, text.length() - 1);
        words.setText(text);
    }

    public void change(View v){
        Intent i = new Intent();
        i.putExtra("text", words.getText().toString());
        //i.setClass(getApplicationContext(), Pictures.class);
        startActivity(i);
    }
}