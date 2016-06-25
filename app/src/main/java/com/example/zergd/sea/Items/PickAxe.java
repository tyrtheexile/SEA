package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.TextQueue;

public class PickAxe extends Item {

	public PickAxe(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Pickaxe");
		setDescription("It's Hungry work, but you need alloys to keep this Station up and running.");
		setAlloyCost(10);
		setCarbonCost(20);
	}

	@Override
	public int cycleModifier() {
		if (astro.payCost(0, 0, 2))
		{
			base.setAlloy(base.getAlloy()+5);
			TextQueue.putStatus("Pickaxe Mining is Hard Work - Lose 2 Food - Gain 5 Alloy");
		}
		else
			TextQueue.putStatus("Can't Afford to Pickaxe this Turn");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		Global.DebugMSG(2, "\nPickAxe Bought");
		base.getItems().addFreeItem(new CarbonWash(astro,base));
		base.getItems().addFreeItem(new H2ODecoupler(astro,base));
		return 0;
	}

}
