package com.example.zergd.sea.Actions;

import java.util.ArrayList;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Astronaut.Choice;
import com.example.zergd.sea.Building.*;
import com.example.zergd.sea.Global;

public class ActionBuild extends Action {
	
	MainBase base;
	BuildGrid grid;
	
	public ActionBuild(Astronaut astro,MainBase base) {
		super(astro);
		this.base=base;
		setTime(15);
		setActionName("Build...");
		grid=base.getGrid();
	}

	@Override
	public void doAction() {
		
		if (!base.isOwned("Fabricator"))
		{
			Global.TextDisp("You don't have a Fabricator!\nHow did you get here?");
		}

		Boolean success=false;
		do{
			grid.drawBaseMap();
			int col=askColumnNumber()-1;
			int row=askRowNumber()-1;
			String side=askSide()+"";
			if(askBuilding(col,row,side))
				success=true;
			//success=grid.constructBuilding(choice, base.getGrid().getBuilding(row,col),side);
		} while (success==false);
		grid.drawBaseMap();
	}
	
	private int askColumnNumber()
	{
		Boolean success = false;
		int choice = 0;
		while(success==false)
		{
			Global.TextDisp("What Column Number would you like to build at? ");
			///////////////////////////////////////////////////////////////////////////
			///////////////////////Getinputs can't work anymore
			choice = 1;//Choice.getInput();
			if (choice<1 || choice > base.getGrid().getSize())
			{
				Global.TextDisp("Input of of Bounds needs to be Between: 1 and "+base.getGrid().getSize());
				success=false;
			}else{
				success=true;
			}
		}
		return choice;
	}

	private int askRowNumber()
	{
		Boolean success = false;
		char choice = 'a';
		int letnum=0; 
		while(success==false)
		{
			Global.TextDisp("What Row letter do you want to build at? ");

			///////////////////////Major Janky Patch below before building implemented
			choice = 1;//Choice.getCharInput();
			///////////////////////////////////////////////////////////////////////////////////
			char car=(char)choice;
			letnum=(int)car-96;
			if (letnum<1 || letnum > base.getGrid().getSize())
			{
				Global.TextDisp("Input of of Bounds needs to be Between: 1 and "+base.getGrid().getSize());
				success=false;
			}else{
				success=true;
			}
		}
		return letnum;
	}

	private char askSide()
	{
		char ans='Q';
		do{
			Global.TextDisp("What Side Do you want to Build on (N,S,E,W)? ");
			ans = 'N';//Choice.getCharInput();
		} while (ans!='N'&&ans!='W'&&ans!='E'&&ans!='S');
		return ans;
	}
	
	private Boolean askBuilding(int col, int row, String side)
	{
		ArrayList<Building> buyableBuildings=grid.getFreeBuildings();
		
		Boolean buy=false;
		do
		{
			//Display each Building in the buy-able array
			Global.TextDisp("\nYou Can Build:  (Alloy,Carbon,Hydrogen)");
			Global.TextDisp("0. Cancel");
			int counter=1;
			for(Building i:buyableBuildings)
			{
				Global.TextDisp((counter++)+". "+i.getName()+" ("+i.getAlloyCost()+","+i.getCarbonCost()+","+i.getHydrogenCost()+")");
				if (counter>6) break;
			}

			////////////////////////////////////////////////////Another Janky Bug fix/
			int choice = 1;//Choice.getInput();
			if (choice==0) {
				setTime(1);
				break;
			}
			//Error Check if More than 6 Items
			if (choice>buyableBuildings.size()||choice<0)
			{
				Global.TextDisp("\n--------------\nBad Input Number - Try Again\n--------------");
				Global.DebugMSG(3, "\nNumber Free Items: "+buyableBuildings.size()+" - Number entered: "+choice);
				continue;
			}
			//Buys the Item, if It can't be afforded repeats the loop
			Building build = (Building) buyableBuildings.get((choice-1)).clone();
			Global.DebugMSG(3, "Building Cloned: " +build.getName() +"\noldBuilding Location: "+base.getGrid().getBuilding(col,row).getPosition()[0]+","+base.getGrid().getBuilding(col,row).getPosition()[1]);
			Global.DebugMSG(3, "Location inputed: "+row+","+col);
			if (grid.constructBuilding(build, base.getGrid().getBuilding(col,row),side)==true)
				buy = true;
			else
				Global.TextDisp("\nCannot Afford that Item");			
		} while (buy==false);

		return true;
	}
	
}

