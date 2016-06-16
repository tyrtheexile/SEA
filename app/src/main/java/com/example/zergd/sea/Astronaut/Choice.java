package com.example.zergd.sea.Astronaut;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Scanner;
import com.example.zergd.sea.Actions.*;
import com.example.zergd.sea.Building.*;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.R;

public class Choice {
	
	private Astronaut astro;
	private MainBase base;
    private static int choice;
    private static Boolean choiceFlag;
	//public static Scanner inputStream = new Scanner(System.in);
	
	Action act1=null;
	Action act2=null;
	Action selectedAct;
	
	//Initialize with Astro and Base
	public Choice(Astronaut astro,MainBase base) {
		this.astro=astro;
		astro.setChoice(this);
		this.base=base;
	}
	
	//Asks for and returns a int - Static Choice Question
	public static int getInput(Action act1,Action act2)
	{
		Activity mainAct = Global.getActivity();

		Button button1=(Button)mainAct.findViewById(R.id.CH1);
		Button button2=(Button)mainAct.findViewById(R.id.CH2);
        Global.changeButtons(act1.getActionName(),act2.getActionName());

        Choice.choiceFlag=false;
        int choicenum;
		button1.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Choice.choice=1;
                Choice.choiceFlag=true;
            }
        });

        button2.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Choice.choice=2;
                Choice.choiceFlag=true;
            }
        });

		while(choiceFlag==false)
        {
            try {
                    Thread.sleep(100);
                    Global.TextDisp("Waiting for Choice");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        choicenum=Choice.choice;
		return choicenum;
	}

	/////// Place holder for all the bad inputs
	public static int getInput()
	{
		return 1;
	}

	/*
	public static char getCharInput()
	{
		Global.TextDisp("\nWhat is your choice?: ");
		char choicenum=inputStream.next().charAt(0);

		return choicenum;
	}
	*/

	//This is the Main Method of Choice
	//It uses inter logic in choiceLogic() to make two actions and do the one selected Returns  Wait time
	public int giveChoice()
	{
		int wait=10;
		Boolean temp=true;
		act1=null;
		act2=null;
		
		//Display Choices
		choiceLogic();
		
		//Print out actions
		Global.TextDisp("\nYou may either:");
		Global.TextDisp("1. "+act1.getActionName()+" ("+act1.getTime()+")");
		Global.TextDisp("2. "+act2.getActionName()+" ("+act2.getTime()+")");
		
		//Get Input - 1 or 2 Otherwise repeats
		while (temp==true)
		{
			int input=getInput(act1,act2);
			if (input==1){
				selectedAct=act1;
				temp=false;
			}
			else if (input==2){
				selectedAct=act2;
				temp=false;
			}
			else {
				Global.TextDisp("\nInvalid Selection");
			}
		}
		
		//Do the Action Selected
		selectedAct.doAction();
		
		//Send back input time
		wait=selectedAct.getTime();
		
		return wait;
	}
	
	//Checks if act1 or act2 should be set with the next action
	//Used within choiceLogic() to improve readability
	private void setActions(Action act)
	{
		//Takes a new Action
		if (act1!=null) 			//If Action1 is full
		{
			//Check for Duplicate Action on Act1
			if (act1.getActionName()!=act.getActionName())
			{
				if(act2!=null)			//If Action2 is Also Full
					Global.DebugMSG(6, "\nToo many Actions- Failed to add: "+act.getActionName());
				else					//Otherwise
					act2=act;   		//Give Act to Action2
			}
		}
		else 						//
			act1=act;
	}
	
	//This is the meat of how the 2 choices are selected, gonna be a cluster****
	private void choiceLogic()
	{
		//Low Air, Air Action
		Global.DebugMSG(5, "\nAir Threshold: "+((int)(.2*astro.getAirMax()))+"");
		if (astro.getAir()<((int)(.4*astro.getAirMax())))
		{
			setActions(new ActionAddAir(astro));
		}
		
		//Low Water, Drink Action
		if (astro.getWater()<((int)(.3*astro.getWaterMax())))
		{
			setActions(new ActionAddWater(astro));
		}
		
		//Low Food, Eat Action
		if (astro.getFood()<((int)(.3*astro.getFoodMax())))
		{
			setActions(new ActionAddFood(astro));
		}
		
		//Both Actions Still Empty
		if (act1==null && act2==null)
		{
			setActions(new ActionSynthesize(astro,base));
			if (base.isOwned("Fabricator"))
				setActions(new ActionBuild(astro,base));
			else
				setActions(new ActionWait(astro));
		}
		//One Action Still Empty
		if (act1==null || act2==null)
		{
			setActions(new ActionAddAir(astro));
			setActions(new ActionWait(astro));
		}
	}
}
