package com.example.zergd.sea;

import android.app.Activity;
import android.os.Bundle;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class MainGame2 extends Activity {

    private Astronaut astro;
    private MainBase base;
    private GameTimer gTimer;

    private ButtonOps buttonOps;

    //This Handler sets the Button names inside the UI thread
    public Handler buttonNames = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String butt1Name= bundle.getString("button1");
            String butt2Name= bundle.getString("button2");
            buttonOps.updateButtons(butt1Name,butt2Name);
        }
    };

    //This Handler will check if the buttons have been pressed after updating
    public Handler buttonPress = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Global.TextDisp("\nInsdie ButtonPress");
        }
    };

    //This Handler will append to the Text View
    public Handler textUpdate = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String str=bundle.getString("text");
            TextView text = (TextView)findViewById(R.id.DispWin);
            text.append(str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        loadSave();
        loadGTimer();

        Global.setView((TextView)findViewById(R.id.DispWin),(ScrollView)findViewById(R.id.ScrollWin));
        Global.setHandler(buttonNames,buttonPress,textUpdate);
        Global.setActivity(this);

        Global.set_Debug(6);
        Global.setImmortal(true);
        Global.setTimeIncrement(1000);
        Global.setBasesize(10);
        Global.setTestmode(2);
        buttonOps = new ButtonOps(this);
        buttonOps.setOnClickListners();

        Thread mainGame = new Thread(mainGameLoop);
        mainGame.start();
    }

    Runnable mainGameLoop = new Runnable() {
        public void run(){
            ///Main game loop here
            try {
                while(true) {
                    Thread.sleep(Global.getTimeIncrement());
                    gTimer.StartGame();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    protected void loadSave()
    {
        this.astro = new Astronaut("Chris");
        this.base = new MainBase("Alpha",astro);
    }

    protected void loadGTimer()
    {
        gTimer=new GameTimer(astro,base);
    }

    @Override
    public void onStop(){
        super.onStop();

        //Save Astro
        try
        {
            ObjectOutputStream astroOut = new ObjectOutputStream(new FileOutputStream(new File("astroOut.bin"))); //Select where you wish to save the file...
            astroOut.writeObject(this.astro); // write the class as an 'object'
            astroOut.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            astroOut.close();// close the stream
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        //Save mainBase
        try
        {
            ObjectOutputStream baseOut = new ObjectOutputStream(new FileOutputStream(new File("baseOut.bin"))); //Select where you wish to save the file...
            baseOut.writeObject(this.base); // write the class as an 'object'
            baseOut.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            baseOut.close();// close the stream
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
