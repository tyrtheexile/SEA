package com.example.zergd.sea;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.*;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zergd.sea.Actions.ActionSynthesize;
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
    protected FragmentManager fragManager;

    multiChoiceFrag mChoice;

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

    public Handler showHideMainFrag = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bund = msg.getData();
            int hide = bund.getInt("hide");
            if (hide==0)
                fragManager.beginTransaction().hide(mChoice).commit();
            else
                fragManager.beginTransaction().show(mChoice).commit();
        }
    };

    public Handler getMainFragHandler()
    {
        return showHideMainFrag;
    }

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
                    updateUI();

                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    protected void loadSave()
    {
        File asF = new File(getFilesDir()+"/astroOut.bin");
        //Log.e("Test",getFilesDir()+"/astroOut.bin");
        File bsF = new File(getFilesDir()+"/baseOut.bin");
        if(asF.exists() && bsF.exists()) {
            //Log.e("test","inside if2.00000000000");
            try
            {
                FileInputStream fop = openFileInput("astroOut.bin");
                ObjectInputStream aIn = new ObjectInputStream(fop);
                //ObjectInputStream aIn = new ObjectInputStream(new FileInputStream(new File("astroOut.bin")));
                this.astro = (Astronaut)aIn.readObject();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                Log.e("Test",Log.getStackTraceString(ex));
            }
            //Log.e("test","\n\n\n Astro loade\n\n");
            try
            {
                FileInputStream fop2 = openFileInput("baseOut.bin");
                ObjectInputStream bIn = new ObjectInputStream(fop2);
                //ObjectInputStream bIn = new ObjectInputStream(new FileInputStream(new File("baseOut.bin")));
                this.base = (MainBase)bIn.readObject();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                Log.e("Test",Log.getStackTraceString(ex));
            }
            //Log.e("Test","End of Load in"+astro.getName());

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
            FileOutputStream fop = openFileOutput("astroOut.bin", Context.MODE_PRIVATE);
            ObjectOutputStream astroOut = new ObjectOutputStream(fop);
            astroOut.writeObject(this.astro); // write the class as an 'object'
            astroOut.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            astroOut.close();// close the stream
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Log.e("Test",Log.getStackTraceString(ex));
        }

        //Save mainBase
        try
        {
            FileOutputStream fop2 = openFileOutput("baseOut.bin", Context.MODE_PRIVATE);
            ObjectOutputStream baseOut = new ObjectOutputStream(fop2);
            baseOut.writeObject(this.base); // write the class as an 'object'
            baseOut.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            baseOut.close();// close the stream
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Log.e("Test",Log.getStackTraceString(ex));
        }
    }

    private void loadInFragments() {

        fragManager = getFragmentManager();
        mChoice = new multiChoiceFrag();
        //mChoice.addHandler(Handler astro.getChoice().getHandler());
        ActionSynthesize.setHandler(showHideMainFrag);
        fragManager.beginTransaction().add(R.id.mainFrame, mChoice).commit();
        fragManager.beginTransaction().hide(mChoice).commit();
    }

    private void updateUI(){
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
        ///////////
        ////////////
        baseFrag baseFrag = (baseFrag) fragManager.findFragmentById(R.id.baseFrag);
        Handler baseHandler = baseFrag.getHandler();
        Bundle bundleb = new Bundle();
        Message mesb = new Message();
        bundleb.putInt("alloy",base.getAlloy());
        bundleb.putInt("carbon",base.getCarbon());
        bundleb.putInt("hydrogen",base.getHydrogen());
        bundleb.putInt("energy",base.getEnergy());
        mesb.setData(bundleb);
        baseHandler.sendMessage(mesb);
        ////////////
    }
}
