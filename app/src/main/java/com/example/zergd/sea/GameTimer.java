package com.example.zergd.sea;

import com.example.zergd.sea.Astronaut.*;
import com.example.zergd.sea.Building.*;

public class GameTimer {
	
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
		
		//Loop endGameHard!=true
		while (endGameHard!=true)
		{
			//Pause for 1 second on loop start
			try {
			    Thread.sleep(Global.getTimeIncrement());
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			//Update Window
			Global.TextDisp("\n-------------- "+TurnCount+" --------------");
			Global.TextDisp(astro.getStatusString());
			Global.TextDisp(base.getStatusString());
			Global.TextDisp(base.getItems().getItemStatusString());
			
			//Timer countdown
			TurnCount++;
			actionTimer--;
			Global.DebugMSG(5, "Action Timer Countdown: "+actionTimer);
			
			//Reduce given Parameters
			astro.timePulse();
			base.timePulse();
			
			//Check if astro is alive everyloop
			if (astro.isAlive()!=true)
			{
				Global.TextDisp(astro.getStatusString());
				System.out.println("\n\nGame Over!");
				endGameHard=true;
				System.exit(0);
			}
			
			//if Timer <=0
			if (actionTimer<=0)
			{
				//if debug >3, Display Action time
				Global.DebugMSG(3,"\nAction Timer at 0");
				
				//Give choice, pause game, returns wait time
				
				actionTimer=choice.giveChoice();
			}
		
		}
	}

}
