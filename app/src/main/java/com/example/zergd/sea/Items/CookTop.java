package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;

public class CookTop extends Item {

	public CookTop(Astronaut astro, MainBase base) {
		super(astro, base);
		setAlloyCost(50);
		setCarbonCost(20);
		setHydrogenCost(10);
		setName("CookTop");
	}

	@Override
	public int cycleModifier() {
		if (base.payCost(0, 0, 0, 1))
		{
			Global.TextDisp("Cooktop takes 1 energy to make 2 Food");
			astro.setFood(astro.getFood()+2);
		}
		else
			Global.TextDisp("Can't Afford to Cooktop this turn");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

}
