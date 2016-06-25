package com.example.zergd.sea.Items;

import com.example.zergd.sea.Actions.ActionSynthesize;
import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;

public class TurboSynthesizer extends Item {

	public TurboSynthesizer(Astronaut astro, MainBase base) {
		super(astro, base);
		setName("Turbo Synthesizer");
		setDescription("The Syntesizer takes forever to build stuff, maybe you could Amp it up!");
		setAlloyCost(300);
		setCarbonCost(100);
		setHydrogenCost(100);
	}

	@Override
	public int cycleModifier() {
		return 0;
	}

	@Override
	public int aquisitionModifier() {
		ActionSynthesize.setNewTime((int) (ActionSynthesize.getNewTime()*.5));
		base.getItems().addFreeItem(new Fabricator(astro,base));
		return 0;
	}

}
