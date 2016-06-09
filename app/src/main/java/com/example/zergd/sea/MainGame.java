package com.example.zergd.sea;

import android.support.v7.app.AppCompatActivity;
import Astronaut.*;
import Building.*;
import android.os.Bundle;
import android.widget.*;

public class MainGame extends AppCompatActivity {

	public Astronaut astro;
	public MainBase base;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        
        loadSave();
		Global.set_Debug(6);
		Global.setImmortal(true);
		Global.setTimeIncrement(10);
		Global.setBasesize(10);
		Global.setTestmode(2);
        
        Thread thr = new Thread(mTask);
        thr.start();
    }

    Runnable mTask = new Runnable() {
        public void run() {
            // just sleep for 30 seconds.
            for (int i=0;i<100;i++) {
                try {
                    Thread.sleep(Global.getTimeIncrement());
                    runOnUiThread(done);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    };

    Runnable done = new Runnable() {
        public void run() {
        	Global.TextDisp("Testing")
        }
    };

    protected void loadSave()
    {
    	this.astro = new Astronaut("Chris");
    	this.base = new MainBase("Alpha",astro);
    }
    
    

}
