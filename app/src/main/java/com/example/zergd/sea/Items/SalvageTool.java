package Items;

import Astronaut.Astronaut;
import Building.MainBase;
import Main.Global;

public class SalvageTool extends Item {

	public SalvageTool(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Salvage Tool");
		setDescription("Uses some of the bases old Alloy to produce trace amounts of Carbon and Hydrogen");
		setAlloyCost(500);
	}

	@Override
	public int cycleModifier() {
		
		if (base.payCost(20, 0, 0, 0))
		{
			base.setCarbon(base.getCarbon()+2);
			base.setHydrogen(base.getHydrogen()+2);
			Global.DebugMSG(2, "Salavage Tool Turns 20 Alloy into 2 Carbon and 2 Hydrogen");
		}
		else
			Global.DebugMSG(2,"Can't Afford to salvage this Turn");
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

}
