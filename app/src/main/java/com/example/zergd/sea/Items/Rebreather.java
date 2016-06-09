package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;

public class Rebreather extends Item {

	public Rebreather(Astronaut astro, MainBase base)
	{
		super(astro,base);
		setAlloyCost(20);
		setName("Rebreather");
		
	}

	@Override
	public int cycleModifier() {
		Global.DebugMSG(5, "Rebreather Cycle Called");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		astro.setAirRate(astro.getAirRate()-2);
		Global.DebugMSG(5, "\nRebreather Bought - Air Rate is :"+astro.getAirRate());
		return 0;
	}

}
