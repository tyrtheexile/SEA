package com.example.zergd.sea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global.set_Debug(1);
        Global.setImmortal(false);
        Global.setTimeIncrement(100);
        Global.setTestmode(1);

        Button startGame = (Button) this.findViewById(R.id.StartGame);
        Button newGame = (Button) this.findViewById(R.id.newGame);

        final Spinner spin = (Spinner) findViewById(R.id.testMode);
        final TextView time = (TextView) findViewById(R.id.timeInc);

        if (new File(getFilesDir()+"/astroOut.bin").exists()) {
            startGame.setText("Continue");
        }else{
            newGame.setAlpha(.5f);
            newGame.setClickable(false);
            newGame.setEnabled(false);
        }
        startGame.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                if (spin.getSelectedItemPosition()>0 && spin.getSelectedItemPosition()<5)
                    Global.setTestmode(spin.getSelectedItemPosition());
                if (time.getText().equals("Turn Time (s)")==false)
                    Global.setTestmode(Integer.parseInt(time.getText().toString())*1000);

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
