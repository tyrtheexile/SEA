package com.example.zergd.sea;

import android.os.Bundle;
import android.os.Message;

import com.example.zergd.sea.Astronaut.*;
import com.example.zergd.sea.Building.*;

import java.io.File;

public class GameTimer implements java.io.Serializable {
	
	private Boolean endGameHard=false;
	private int actionTimer=10;
	
	private Astronaut astro;
	private MainBase base;
	
	private int TurnCount=0;
	
	//Initialize the Game-play loop with a default Astronaut and Base
	public GameTimer(Astronaut astro,MainBase base) 
	{
		this.astro=astro;
		this.base=base;
	}
	
	//Starts the Actual game-play loop
	public void StartGame() 
	{
		Choice choice =new Choice(astro,base);

		//Update Window
		Global.TextDisp("\n-------------- "+TurnCount+" --------------");
		//Global.TextDisp(astro.getStatusString());
		//Global.TextDisp(base.getStatusString());
		Global.TextDisp(base.getItems().getItemStatusString()+"\n");

		//Timer countdown
		TurnCount++;
		actionTimer--;
		Global.DebugMSG(5, "Action Timer Countdown: "+actionTimer);

        //Set Progress bar
        Global.log(actionTimer+"");
        Message msg = new Message();
        Bundle bund = new Bundle();
        bund.putInt("progress",actionTimer);
        bund.putInt("max",0);
        msg.setData(bund);
        Global.getHandler("progress").sendMessage(msg);

		//Reduce given Parameters
		astro.timePulse();
		base.timePulse();

		//Check if astro is alive everyloop
		if (astro.isAlive()!=true)
		{
			Global.TextDisp(astro.getStatusString());
			System.out.println("\n\nGame Over!");
			endGameHard=true;
            File asF = new File(Global.getActivity().getFilesDir()+"/astroOut.bin");
            File bsF = new File(Global.getActivity().getFilesDir()+"/baseOut.bin");
            File tsF = new File(Global.getActivity().getFilesDir()+"/timerOut.bin");
            asF.delete();
            bsF.delete();
            tsF.delete();
			Global.getActivity().finish();
		}

		//if Timer <=0
		if (actionTimer<=0)
		{
			//if debug >3, Display Action time
			Global.DebugMSG(3,"\nAction Timer at 0");

			//Give choice, pause game, returns wait time

			actionTimer=choice.giveChoice();

            //Remax Progress bar
            Message msg2 = new Message();
            Bundle bund2 = new Bundle();
            bund2.putInt("progress",0);
            bund2.putInt("max",actionTimer);
            msg2.setData(bund2);
            Global.getHandler("progress").sendMessage(msg2);
		}

		Global.setGameInProgress(false);
	}

}
