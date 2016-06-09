package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;

public class H2ODecoupler extends Item {

	public H2ODecoupler(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("H2ODecoupler");
		setDescription("Uses a bit of water and some energy to slowly gather Hydrogen");
		setAlloyCost(300);
	}

	@Override
	public int cycleModifier() {
		if (base.payCost(0, 0, 0, 2) && astro.payCost(0, 1, 0))
		{
			base.setHydrogen(base.getHydrogen()+1);
			Global.DebugMSG(2,"H20 Decoupler uses 1 water and 2 energy to make 1 Hydrogen");
		}
		else
			Global.DebugMSG(2,"Can't Afford to H20 Decouple this Turn");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		base.dualRequirement("CarbonWash", new SalvageTool(astro,base));
		return 0;
	}

}
