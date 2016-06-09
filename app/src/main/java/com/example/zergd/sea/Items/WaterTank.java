package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;

public class WaterTank extends Item {

	public WaterTank(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Water Tank");
		setAlloyCost(50);
		setHydrogenCost(10);
	}

	@Override
	public int cycleModifier() {
		Global.DebugMSG(5, "Water Tank Cycle Called");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		astro.setWaterMax(astro.getWaterMax()+75);
		Global.DebugMSG(5, "\nWater Tank Bought - Water Max is :"+astro.getWaterMax());
		base.getItems().addFreeItem(new WaterPump(astro,base));
		return 0;
	}

}
