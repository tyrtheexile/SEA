package com.example.zergd.sea.Actions;

import com.example.zergd.sea.Astronaut.Astronaut;

public class ActionAddFood extends Action {

	public ActionAddFood(Astronaut astro) {
		super(astro);
		setTime(10);
		setActionName("Eat Food");
	}

	@Override
	public void doAction() {
		
		astro.addFood((int)(astro.getFoodMax()*.75));

	}

}
