package com.example.zergd.sea.Items;

import com.example.zergd.sea.Actions.ActionAddWater;
import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;

public class WaterPump extends Item {

	public WaterPump(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Water Pump");
		setDescription("Fixes the Station's water pump, should make getting a glass much quicker.");
		setAlloyCost(50);
		setCarbonCost(10);
	}

	@Override
	public int cycleModifier() {
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		ActionAddWater.changeTime(7);
		return 0;
	}

}
