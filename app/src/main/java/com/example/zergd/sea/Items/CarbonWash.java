package Items;

import Astronaut.Astronaut;
import Building.MainBase;
import Main.Global;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cycleMulti() {
		Global.DebugMSG(2, numberOfThisItem+" Carbon Washes gather "+numberOfThisItem +" Carbon");
		base.setCarbon(base.getCarbon()+numberOfThisItem);
		return 0;
	}

}
