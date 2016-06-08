package com.example.zergd.sea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class MainGame extends AppCompatActivity {

	public Astronaut astro = new Astronaut("Chris");
	public MainBase base = new MainBase("Alpha",astro);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        TextView text = (TextView)findViewById(R.id.DispWin);
        Thread thr = new Thread(mTask);
        thr.start();
    }

    Runnable mTask = new Runnable() {
        public void run() {
            // just sleep for 30 seconds.
            for (int i=0;i<100;i++) {
                try {
                    Thread.sleep(1000);
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
            TextView text = (TextView)findViewById(R.id.DispWin);
            text.append("\nTesting 123------------Testing 123--------BEEP");
        }
    };
}
