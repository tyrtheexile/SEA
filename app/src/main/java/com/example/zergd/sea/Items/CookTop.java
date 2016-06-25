package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.TextQueue;

public class CookTop extends Item {

	public CookTop(Astronaut astro, MainBase base) {
		super(astro, base);
		setAlloyCost(50);
		setCarbonCost(20);
		setHydrogenCost(10);
		setName("Food Reclaimer");
		setDescription("Standard issue Food Reclaimer, it will keep you alive in a pinch. Seems to use a lot of Power.");
	}

	@Override
	public int cycleModifier() {
		if (base.payCost(0, 0, 0, 1))
		{
			Global.TextDisp("Cooktop takes 1 energy to make 2 Food");
			TextQueue.putStatus("Cooktop takes 1 energy to make 2 Food");
			astro.setFood(astro.getFood()+2);
		}
		else
			TextQueue.putStatus("Can't Afford to Cooktop this turn");

		return 0;
	}

	@Override
	public int aquisitionModifier() {
		return 0;
	}

}
