package com.example.zergd.sea;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.*;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.util.*;


public class MainGame2 extends Activity {

    private Astronaut astro;
    private MainBase base;
    private GameTimer gTimer;

    private ButtonOps buttonOps;
    private FragmentManager fragManager;

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

        loadInFragments();


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

                    ////////////
                    astroFrag astroFrag = (astroFrag) fragManager.findFragmentById(R.id.astroFragId);
                    Handler astroHandler = astroFrag.getHandler();
                    Bundle bundle = new Bundle();
                    Message mes = new Message();
                    bundle.putInt("air",astro.getAir());
                    bundle.putInt("water",astro.getWater());
                    bundle.putInt("food",astro.getFood());
                    mes.setData(bundle);
                    astroHandler.sendMessage(mes);
                    ////////////
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    protected void loadSave()
    {
        File asF = new File("astroOut.bin");
        File bsF = new File("baseOut.bin");
        if(asF.exists() && bsF.exists()) {
            try
            {
                ObjectInputStream aIn = new ObjectInputStream(new FileInputStream(new File("astroOut.bin")));
                astro = (Astronaut)aIn.readObject();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            try
            {
                ObjectInputStream bIn = new ObjectInputStream(new FileInputStream(new File("baseOut.bin")));
                base = (MainBase)bIn.readObject();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        }
        else {
            this.astro = new Astronaut("Chris");
            this.base = new MainBase("Alpha", astro);
    }
    }

    protected void loadGTimer()
    {
        gTimer=new GameTimer(astro,base);
    }

    @Override
    public void onPause(){
        super.onPause();

        //Save Astro
        try
        {
            /*
            Log.e("test","File......");
            File file=new File("astroOut.bin");
            if(!file.exists()) {
                Log.e("test","In if");
                file.createNewFile();
            }
            */
            Log.e("test","File Output Stream");
            FileOutputStream fop = openFileOutput("astroOut.bin", Context.MODE_PRIVATE);

            //FileOutputStream fop = new FileOutputStream("astroOut.bin");
            Log.e("test","Object Output Stream");
            ObjectOutputStream astroOut = new ObjectOutputStream(fop);
            //ObjectOutputStream astroOut = new ObjectOutputStream(new FileOutputStream(new File("astroOut.bin"))); //Select where you wish to save the file...
            Log.e("test","1111111111111111111");
            astroOut.writeObject(this.astro); // write the class as an 'object'
            Log.e("test","2222222222222222222222");
            astroOut.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            Log.e("test","33333333333333333333333");
            astroOut.close();// close the stream
            Log.e("test","44444444444444444444444");
            Log.e("SAE I/O Knp","System Write failed");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Log.e("Test",Log.getStackTraceString(ex));
            Log.e("SAE I/O Knp","System Write failed");
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

    private void loadInFragments() {
        fragManager = getFragmentManager();
    }
}
