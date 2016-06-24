package com.example.zergd.sea.Actions;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.Random;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Astronaut.Choice;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Items.*;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.multiChoiceFrag;

public class ActionSynthesize extends Action {
	
	MainBase base;
	ArrayList<Item> freeItems;
	ArrayList<Item> ownedItems;
	ArrayList<Item> buyableItems;
	
	private static int newTime=12;

    private static Handler multiHandler;

    public static void setHandler(Handler h)
    {
        multiHandler=h;
    }
	
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
			int choice = displayMenu();
            Global.log(choice+"");
			//int choice = Choice.getInput();
			if (choice==7 || choice>buyableItems.size()) {
				setTime(1);
				break;
			}
			//Error Check if More than 6 Items
			if (choice>7||choice<0)
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
        // TODO Double check this gets rid of the weird choice exit
        multiChoiceFrag.choice=10;
	}
	
	public int displayMenu()
	{
		
		buyableItems=generateBuyList(freeItems);

		Bundle bund = new Bundle();
		//Display each item in the buy-able array
		Global.TextDisp("\nYou Can Build:  (Alloy,Carbon,Hydrogen)");
		Global.TextDisp("0. Cancel");
		int counter=1;
		for(Item i:buyableItems)
		{
            //Global.log("butOff"+counter+"  "+base.validateCost(i.getAlloyCost(),i.getCarbonCost(),i.getHydrogenCost(),i.getEnergyCost()));
			bund.putString("but"+counter,(counter++)+". "+i.getName()+" ("+i.getAlloyCost()+","+i.getCarbonCost()+","+i.getHydrogenCost()+")");
            bund.putBoolean("butOff"+counter,base.validateCost(i.getAlloyCost(),i.getCarbonCost(),i.getHydrogenCost(),i.getEnergyCost()));
			if (counter>6) break;
		}
		for (int i=7;i>=counter;i--)
		{
			bund.putString("but"+i,"-------");
            bund.putBoolean("butOff"+counter,false);
            //Global.log("butOff"+i + "false");
		}

        Global.log("First Hide message Starting");
        Message frag = new Message();
        Bundle fragb = new Bundle();
        fragb.putInt("hide",1);
        frag.setData(fragb);
        multiHandler.sendMessage(frag);

        Global.log("Hide Complete, Send bundle string");
        Message msg = new Message();
        msg.setData(bund);
        multiChoiceFrag.getHandler().sendMessage(msg);

        Global.log("Before while");
        while (Choice.getInput()==10)
		{
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}

        Global.log("Before Hide");
        Message frg = new Message();
        Bundle frgb = new Bundle();
        frgb.putInt("hide",0);
        frg.setData(frgb);
        multiHandler.sendMessage(frg);

        Global.log("Before Return");
        return multiChoiceFrag.choice;
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
