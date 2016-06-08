package Items;

import java.util.Random;

import Astronaut.Astronaut;
import Building.MainBase;
import Main.Global;

public class CargoManifest extends Item {

	public CargoManifest(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Cargo Manifest");
		setAlloyCost(5);
	}

	@Override
	public int cycleModifier() {
		Random ran = new Random();
		int x = ran.nextInt((100 - 1) + 1) + 1;
		if (x<5)
		{
			Global.TextDisp("Found 2 Carbon in the Cargo Manifest");
			base.setCarbon(base.getCarbon()+2);
		}
		if (x>=5 && x<10)
		{
			Global.TextDisp("Found 2 Hydrogen in the Cargo Manifest");
			base.setHydrogen(base.getHydrogen()+2);
		}
		if (x>=10 && x<30)
		{
			Global.TextDisp("Found 2 Alloy in the Cargo Manifest");
			base.setAlloy(base.getAlloy()+2);
		}
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		base.getItems().addFreeItem(new SmallSolarPanel(astro,base));
		base.getItems().addFreeItem(new PickAxe(astro,base));
		return 0;
	}

}
