package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;
import com.example.zergd.sea.TextQueue;

public class CarbonWash extends MultiItem {

	public CarbonWash(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Carbon Wash");
		setDescription("Slowly gathers Carbon during the automated Station cleaning");
		setAlloyCost(150);
	}

	@Override
	public int aquisitionModifier() {
		base.getItems().addFreeItem(new Generator(astro,base));
		base.dualRequirement("H2O Decoupler", new SalvageTool(astro,base));
		if (numberOfThisItem>4)
		{
			base.getItems().removeFreeItemByName("Carbon Wash");
			base.getItems().addFreeItem(new H2ODecoupler(astro,base));
		}
		return 0;
	}

	@Override
	public int cycleModifierOnce() {

		return 0;
	}

	@Override
	public int cycleMulti() {
		Global.DebugMSG(2, numberOfThisItem+" Carbon Washes gather "+numberOfThisItem +" Carbon");
		TextQueue.putStatus(numberOfThisItem+" Carbon Washes gather "+numberOfThisItem +" Carbon");
		base.setCarbon(base.getCarbon()+numberOfThisItem);
		return 0;
	}

}
