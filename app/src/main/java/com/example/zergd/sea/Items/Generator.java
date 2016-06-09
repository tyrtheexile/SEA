package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;

public class Generator extends Item {

	public Generator(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Generator");
		setDescription("Generates power for the station, allows power to flow to buildings");
		setAlloyCost(1000);
		setHydrogenCost(300);
		setCarbonCost(500);
	}

	@Override
	public int cycleModifier() {
		Global.DebugMSG(2, "Generator makes 50 Energy");
		base.setEnergy(base.getEnergy()+50);
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		base.getItems().addFreeItem(new TurboSynthesizer(astro,base));
		
		return 0;
	}

}
