package Items;

import Astronaut.Astronaut;
import Building.MainBase;
import Main.Global;

public class WaterTank extends Item {

	public WaterTank(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Water Tank");
		setAlloyCost(50);
		setHydrogenCost(10);
	}

	@Override
	public int cycleModifier() {
		Global.DebugMSG(5, "Water Tank Cycle Called");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		astro.setWaterMax(astro.getWaterMax()+75);
		Global.DebugMSG(5, "\nWater Tank Bought - Water Max is :"+astro.getWaterMax());
		base.getItems().addFreeItem(new WaterPump(astro,base));
		return 0;
	}

}
