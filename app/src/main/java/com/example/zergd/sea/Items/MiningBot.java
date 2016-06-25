package com.example.zergd.sea.Items;

import java.util.Random;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.TextQueue;

public class MiningBot extends Item {

	public MiningBot(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Mining Bot");
		setDescription("Mining Bot Extracts Alloy and occasionally more every turn, at the cost of some energy");
		setAlloyCost(75);
		setHydrogenCost(20);
		setCarbonCost(20);
	}

	@Override
	public int cycleModifier() {
		if (base.payCost(0, 0, 0, 10))
		{
			Random ran = new Random();
			int x = ran.nextInt((100 - 1) + 1) + 1;
			String str;
			Global.DebugMSG(3, "Random Number in Mining Bot is: "+x);
			str="Mining Bot found ";
			if (x<5) 
			{
				base.setHydrogen(base.getHydrogen()+20);
				str=str+"20 Hydrogen and ";
			}
			else if (x<10 && x>=5)
			{
				base.setCarbon(base.getCarbon()+25);
				str=str+"25 Carbon and ";
			}
			x = ran.nextInt((10 - 1) + 1) + 1;
			base.setAlloy(base.getAlloy()+x);
			str=str+x+" Alloy!";
			TextQueue.putStatus(str);
		}else
			TextQueue.putStatus("Not Enough Energy to run Mining Bot");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		return 0;
	}

}
