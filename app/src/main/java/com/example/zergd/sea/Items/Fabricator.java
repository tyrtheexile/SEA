package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.*;

public class Fabricator extends Item {

	public Fabricator(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Fabricator");
		setDescription("Allows Construction of Base Structures...NOT IMPLEMENTED IN ANDROID YET");
		setAlloyCost(5000);
		setCarbonCost(2000);
		setHydrogenCost(2500);
	}

	@Override
	public int cycleModifier() {
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		base.getGrid().add2FreeBuildings(new Corridor(astro,base));
		return 0;
	}

}
