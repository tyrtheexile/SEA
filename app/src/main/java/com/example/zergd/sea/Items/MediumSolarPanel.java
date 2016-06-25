package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.TextQueue;

public class MediumSolarPanel extends MultiItem {

	public MediumSolarPanel(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Medium Solar Panel");
		setDescription("Gathers a decent amount of Energy every Turn");
		setAlloyCost(35);
	}

	@Override
	public int cycleModifierOnce() {
		return 0;
	}

	@Override
	public int cycleMulti() {
		TextQueue.putStatus(numberOfThisItem+" Medium Solar Panels gather "+numberOfThisItem*3+" Energy");
		base.setEnergy(base.getEnergy()+numberOfThisItem*3);
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		base.getItems().addFreeItem(new LargeSolarPanel(astro,base));
		
		return 0;
	}

}
