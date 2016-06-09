package com.example.zergd.sea.Building;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Global;

public class Corridor extends Building {

	public Corridor(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Corridor");
		setAlloyCost(50);
		setDescription("Connects Buildings in the Station - 1 Energy to Maintain");
		setIndicator('+');
		setConnections(true,true,true,true);
	}

	@Override
	public int cycleModifier() {
		base.setEnergy(base.getEnergy()-1);
		Global.DebugMSG(6, "Corridor takes 1 Energy");
		return 0;
	}

}
