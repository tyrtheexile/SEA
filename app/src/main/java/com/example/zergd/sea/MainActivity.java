package com.example.zergd.sea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global.set_Debug(1);
        Global.setImmortal(false);
        Global.setTimeIncrement(1000);
        Global.setTestmode(1);

        Button startGame = (Button) this.findViewById(R.id.StartGame);
        Button newGame = (Button) this.findViewById(R.id.newGame);

        if (new File(getFilesDir()+"/astroOut.bin").exists()) {
            startGame.setText("Continue");
        }else{
            newGame.setAlpha(.5f);
            newGame.setClickable(false);
            newGame.setEnabled(false);
        }
        startGame.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, MainGame2.class);
                startActivity(i);
            }
        }
        );
        newGame.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                   Global.newGame=true;
                   Intent i = new Intent(MainActivity.this, MainGame2.class);
                   startActivity(i);
               }
           }
            );
    }
}
