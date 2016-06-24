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

        Global.set_Debug(6);
        Global.setImmortal(false);
        Global.setTimeIncrement(1000);
        Global.setBasesize(5);
        Global.setTestmode(1);

        Button startGame = (Button) this.findViewById(R.id.StartGame);
        if (new File(getFilesDir()+"/astroOut.bin").exists())
            startGame.setText("Continue");
        startGame.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, MainGame2.class);
                startActivity(i);
            }
        }
        );
    }
}
