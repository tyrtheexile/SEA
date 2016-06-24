package com.example.zergd.sea;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import com.example.zergd.sea.Astronaut.*;
import com.example.zergd.sea.Building.*;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainGame extends AppCompatActivity {

	public Astronaut astro;
	public MainBase base;
    private GameTimer gTimer;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        
        loadSave();
        loadGTimer();

        Global.setView((TextView)findViewById(R.id.DispWin),(ScrollView)findViewById(R.id.ScrollWin));
        Global.setActivity(this);

		Global.set_Debug(6);
		Global.setImmortal(true);
		Global.setTimeIncrement(1000);

		Global.setTestmode(2);
        
        Thread gameThread = new Thread(tGame);
        Thread dispThread = new Thread(tDisp);
        dispThread.start();
        gameThread.start();
    }

    Runnable tDisp = new Runnable() {
        public void run ()
        {
            while (true)
            {
                runOnUiThread(output);
            }
        }
    };

    Runnable tGame = new Runnable() {

        public void run() {
            //Looper.prepare();
            //Handler testHandle = new Handler();
            try {
                while(true) {
                    Thread.sleep(Global.getTimeIncrement());
                    //runOnUiThread(game);
                    //testHandle.post(game);
                    gTimer.StartGame();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

    Runnable game = new Runnable() {
        public void run() {
            Global.TextDisp("Tester");
            if (Global.getGameInProgress()==false) {
                Global.setGameInProgress(true);
                gTimer.StartGame();
            }
        }
    };

    Runnable output = new Runnable() {
        public void run() {
            TextView text = (TextView)findViewById(R.id.DispWin);
            text.append(Global.getOutputBlock());
            //Global.changeButtons();
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
