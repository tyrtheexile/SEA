package com.example.zergd.sea.Items;

import com.example.zergd.sea.Actions.ActionSynthesize;
import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;

public class TurboSynthesizer extends Item {

	public TurboSynthesizer(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Turbo Synthesizer");
		setDescription("Set Synthesizer time in half");
		setAlloyCost(300);
		setCarbonCost(100);
		setHydrogenCost(100);
	}

	@Override
	public int cycleModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		ActionSynthesize.setNewTime((int) (ActionSynthesize.getNewTime()*.5));
		base.getItems().addFreeItem(new Fabricator(astro,base));
		return 0;
	}

}
