package com.shreyasrana.speaknow3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class scrolling extends AppCompatActivity {

    File directory;
    public String filename;

    Button allButtons[][] = new Button[6][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        directory = getExternalFilesDir(null);
        allButtons[0][0] = findViewById(R.id.button25);
        allButtons[0][1] = findViewById(R.id.button26);
        allButtons[0][2] = findViewById(R.id.button44);

        allButtons[1][0] = findViewById(R.id.button41);
        allButtons[1][1] = findViewById(R.id.button42);
        allButtons[1][2] = findViewById(R.id.button45);

        allButtons[2][0] = findViewById(R.id.button46);
        allButtons[2][1] = findViewById(R.id.button47);
        allButtons[2][2] = findViewById(R.id.button48);

        allButtons[3][0] = findViewById(R.id.button52);
        allButtons[3][1] = findViewById(R.id.button51);
        allButtons[3][2] = findViewById(R.id.button50);

        allButtons[4][0] = findViewById(R.id.button53);
        allButtons[4][1] = findViewById(R.id.button55);
        allButtons[4][2] = findViewById(R.id.button54);

        allButtons[5][0] = findViewById(R.id.button58);
        allButtons[5][1] = findViewById(R.id.button56);
        allButtons[5][2] = findViewById(R.id.button57);

        //setup() puts all the images in the buttons
        //TODO put button names as well


        //when a new name is set for a button
        String bname = getIntent().getStringExtra("name");
        int x2 = getIntent().getIntExtra("x", -1);
        int y2 = getIntent().getIntExtra("y", -1);

        //writes name to file
        if(x2 > -1){
            allButtons[x2][y2].setText(bname);
            File f = new File(directory, Integer.toString(x2) + " " + Integer.toString(y2) + "text");
            try{
                if(f.exists()){
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bname.getBytes());
                } else {
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bname.getBytes());
                }
            } catch (IOException e){
                e.printStackTrace();
            }

        }


        TextView t = findViewById(R.id.textView);
        //t.setText(directory.toString());

        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 3; y++){
                final int x1 = x;
                final int y1 = y;
                final Button b = allButtons[x][y];
                b.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        selectImage(arg0);
                        String x = Integer.toString(x1) + " " + Integer.toString(y1);
                        filename = x;
                        return true;
                    }
                });
            }
        }
        setup();
    }




    private void setup() {
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 3; y++){

                //setting up the image in the button, if possible
                String s = Integer.toString(x) + " " + Integer.toString(y);

                File f = new File(directory, s);
                //Toast.makeText(getApplicationContext(), s + Boolean.toString(f.exists()), Toast.LENGTH_SHORT).show();
                if(f.exists()){
                    try {
                        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                        allButtons[x][y].setBackground(new BitmapDrawable(getResources(), b));

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }


                //setting up the word inside the button
                s = Integer.toString(x) + " " + Integer.toString(y) + "text";
                File g = new File(directory, s);

                if(g.exists()){
                    Log.d("works", "yeeeet");
                    try {
                        FileInputStream fis = getApplicationContext().openFileInput(s);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader bufferedReader = new BufferedReader(isr);

                        String line;


                        line = bufferedReader.readLine();
                        Log.d("text", line);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }



    public void addText(View v){
        Button b = (Button) v;
        TextView t = findViewById(R.id.textView);
        String s = (String) t.getText();
        s += b.getText() + " ";
        t.setText(s);
    }






    public static int i;

    public void selectImage(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        TextView t = findViewById(R.id.textView);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String path = pictureDirectory.getPath();

        Uri data = Uri.parse(path);

        photoPickerIntent.setDataAndType(data, "image/*");
        i = v.getId();
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode > -1){
            Uri pic = data.getData();

            InputStream is;

            try {
                is = getContentResolver().openInputStream(pic);

                Bitmap image = BitmapFactory.decodeStream(is);

                findViewById(i).setBackground(new BitmapDrawable(getResources(), image));



                File entry = new File(directory, filename);
                try (FileOutputStream out = new FileOutputStream(entry)) {
                    if (!entry.exists()) {
                        entry.createNewFile();
                        Toast.makeText(getApplicationContext(), "created file", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "file exists", Toast.LENGTH_LONG).show();
                    }

                    image.compress(Bitmap.CompressFormat.PNG, 100, out);

                    Intent i1 = new Intent();
                    i1.setClass(getApplicationContext(), Name.class);
                    i1.putExtra("fname", filename);
                    startActivity(i1);
                } catch (IOException e) {
                    e.printStackTrace();
                }



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
            }
        }
    }
}