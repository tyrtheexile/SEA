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


public class MainGame2 extends Activity {

    private Astronaut astro;
    private MainBase base;
    private GameTimer gTimer;

    private ButtonOps buttonOps = new ButtonOps(this);

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
}
