package Items;

import Actions.ActionAddWater;
import Astronaut.Astronaut;
import Building.MainBase;

public class WaterPump extends Item {

	public WaterPump(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Water Pump");
		setAlloyCost(50);
		setCarbonCost(10);
	}

	@Override
	public int cycleModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		ActionAddWater.changeTime(7);
		return 0;
	}

}
