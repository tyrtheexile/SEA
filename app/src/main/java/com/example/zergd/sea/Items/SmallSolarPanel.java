package Items;

import Astronaut.Astronaut;
import Building.MainBase;
import Main.Global;

public class SmallSolarPanel extends MultiItem {

	public SmallSolarPanel(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Small Solar Panel");
		setDescription("Gathers 1 Energy a Turn");
		setAlloyCost(15);
	}

	@Override
	public int cycleModifierOnce() {
		return 0;
	}

	@Override
	public int cycleMulti() {
		Global.TextDisp(numberOfThisItem+" Small Solar Panels gather "+numberOfThisItem+" Energy");
		base.setEnergy(base.getEnergy()+numberOfThisItem);
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		base.getItems().addFreeItem(new MediumSolarPanel(astro,base));
		return 0;
	}

}
