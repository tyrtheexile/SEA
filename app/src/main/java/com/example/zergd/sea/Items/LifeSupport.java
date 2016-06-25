package com.example.zergd.sea.Items;

import java.util.Random;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.TextQueue;

public class LifeSupport extends Item {
	
	private int fired=0;
	
	public LifeSupport(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Life Support");
		setDescription("Keeps your resources up when charged.lllllllllllllllllllllllllllllllllllllll");
		setAlloyCost(30);
		setCarbonCost(10);
		setHydrogenCost(10);
		
	}

	@Override
	public int cycleModifier() {
		Random ran = new Random();
		int x = ran.nextInt((100 - 1) + 1) + 1;
		
		if (fired>0) 
			fired--;
			Global.DebugMSG(6, "Life Support Fired: "+fired);
		if (fired<0)
			fired=0;
		// 10% Chance for Lifesupport to fire
		if (x>11 || fired>0)
		{
			return 0;
		}
		//If Air, Water, or Food is less that 50% Fire Life Support
		if ((astro.getAir()<(astro.getAirMax()*.5)) || astro.getWater()<(astro.getWaterMax()*.5) || astro.getFood()<(astro.getFoodMax()*.5))
		{
			if(base.payCost(0, 0, 0, 50))
			{	
				int airinc=(int) (astro.getAirMax()*.45);
				int waterinc= (int) (astro.getWaterMax()*.4);
				int foodinc= (int) (astro.getFoodMax()*.35);
				Global.TextDisp("Life Support Kicking In... \n  +"+airinc+" Air\n  +"+waterinc+" Water\n  +"+foodinc+" Food");
				TextQueue.putStatus("Life Support Kicking In... \n  +"+airinc+" Air\n  +"+waterinc+" Water\n  +"+foodinc+" Food");
				fired=50;
				astro.setAir(astro.getAir()+airinc);
				astro.setWater(astro.getWater()+waterinc);
				astro.setFood(astro.getFood()+foodinc);
				
			}else{
				Global.TextDisp("Not Enough Energy to run LifeSupport");
				TextQueue.putStatus("Not Enough Energy to run LifeSupport");
			}
		}
		
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		base.getItems().addFreeItem(new CookTop(astro,base));
		base.getItems().addFreeItem(new Rebreather(astro,base));
		base.getItems().addFreeItem(new WaterTank(astro,base));
		TextQueue.putMessage("Life Support is charged and ready to save your life...hopefully...");
		TextQueue.putMessage("The Life Support system is holding together really well...",200);
		return 0;
	}

}
