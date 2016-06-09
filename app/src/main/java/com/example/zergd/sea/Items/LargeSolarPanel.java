package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;

public class LargeSolarPanel extends MultiItem {

	public LargeSolarPanel(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Large Solar Panel");
		setDescription("Gathers 5 Energy a Turn");
		setAlloyCost(50);
	}

	@Override
	public int cycleModifierOnce() {
		return 0;
	}

	@Override
	public int cycleMulti() {
		Global.TextDisp(numberOfThisItem+" Large Solar Panels gather "+numberOfThisItem*5+" Energy");
		base.setEnergy(base.getEnergy()+numberOfThisItem*5);
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		base.getItems().removeFreeItemByName("Small Solar Panel");
		return 0;
	}

}
