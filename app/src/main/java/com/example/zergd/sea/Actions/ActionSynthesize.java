package com.example.zergd.sea.Actions;

import java.util.ArrayList;
import java.util.Random;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Astronaut.Choice;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Items.*;
import com.example.zergd.sea.Global;

public class ActionSynthesize extends Action {
	
	MainBase base;
	ArrayList<Item> freeItems;
	ArrayList<Item> ownedItems;
	ArrayList<Item> buyableItems;
	
	private static int newTime=12;
	
	public ActionSynthesize(Astronaut astro, MainBase base) {
		super(astro);
		setTime(newTime);
		setActionName("Synthesize");
		this.base=base;
		freeItems=base.getItems().getFreeItemArray();
		ownedItems=base.getItems().getOwnedItemArray();
	}

	@Override
	public void doAction() {
		Boolean buy=false;
		do
		{
			displayMenu();
			int choice = Choice.getInput();
			if (choice==0) {
				setTime(1);
				break;
			}
			//Error Check if More than 6 Items
			if (choice>6||choice<0)
			{
				Global.TextDisp("\n--------------\nBad Input Number - Try Again\n--------------");
				Global.DebugMSG(3, "\nNumber Free Items: "+freeItems.size()+" - Number entered: "+choice);
				continue;
			}
			//Buys the Item, if It can't be afforded repeats the loop
			if (buyItem(buyableItems.get((choice-1)))==true)
				buy = true;
			else
				Global.TextDisp("\nCannot Affor that Item");			
		} while (buy==false);
	}
	
	public void displayMenu() 
	{
		
		buyableItems=generateBuyList(freeItems);
		
		//Display each item in the buy-able array
		Global.TextDisp("\nYou Can Build:  (Alloy,Carbon,Hydrogen)");
		Global.TextDisp("0. Cancel");
		int counter=1;
		for(Item i:buyableItems)
		{
			Global.TextDisp((counter++)+". "+i.getName()+" ("+i.getAlloyCost()+","+i.getCarbonCost()+","+i.getHydrogenCost()+")");
			if (counter>6) break;
		}
	}
	
	public Boolean buyItem(Item item)
	{	
		if (base.payCost(item.getAlloyCost(), item.getCarbonCost(), item.getHydrogenCost(), item.getEnergyCost()))
		{
			item.aquisitionModifier();
			item.setOwned(true);
			base.getItems().moveItem2Owned(item);
			return true;
		}
		return false;
	}
	
	//Pulls num Items from items at random and returns them, Defaults num to 6
	private ArrayList<Item> generateBuyList(ArrayList<Item> items)
	{
		return generateBuyList(items,6);
	}
	private ArrayList<Item> generateBuyList(ArrayList<Item> items,int num)
	{
		Global.DebugMSG(3, "freeItems List at BuyableItems Generation is: "+items);
		ArrayList<Item> buyable = new ArrayList<Item>();
		ArrayList<Item> temp = new ArrayList<Item>();
		Random ran = new Random();
		int x;
		temp.add(items.get(0));
		temp.add(items.get(1));
		//Generate 6 random nums
		for(int i=0;i<num;i++)
		{
			//rand.nextInt((max - min) + 1) + min;
			x= ran.nextInt((items.size() - 0) + 0) + 0;
			Global.DebugMSG(6, "In GenerateBuyList x = "+x);
			if(temp.contains(items.get(x))==true){
				//Do Nothing
			}else{
				temp.add(items.get(x));
			}
		}
		buyable=temp;
		Global.DebugMSG(6, "Buyable Item List is: "+buyable);
		return buyable;
	}

	public static int getNewTime() {
		return newTime;
	}

	public static void setNewTime(int newTime) {
		ActionSynthesize.newTime = newTime;
	}
}
