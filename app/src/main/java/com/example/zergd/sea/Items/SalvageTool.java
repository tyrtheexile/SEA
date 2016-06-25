package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.TextQueue;

public class SalvageTool extends Item {

	public SalvageTool(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Salvage Tool");
		setDescription("Uses some of the bases old Alloy to produce trace amounts of Carbon and Hydrogen");
		setAlloyCost(500);
	}

	@Override
	public int cycleModifier() {
		
		if (base.payCost(20, 0, 0, 0))
		{
			base.setCarbon(base.getCarbon()+2);
			base.setHydrogen(base.getHydrogen()+2);
			TextQueue.putStatus("Salavage Tool Turns 20 Alloy into 2 Carbon and 2 Hydrogen");
		}
		else
			TextQueue.putStatus("Can't Afford to salvage this Turn");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		return 0;
	}

}
