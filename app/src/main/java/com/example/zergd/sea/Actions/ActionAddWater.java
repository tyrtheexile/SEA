package com.example.zergd.sea.Actions;

import com.example.zergd.sea.Astronaut.Astronaut;

public class ActionAddWater extends Action {
	
	private static int newTime=15;
	
	public ActionAddWater(Astronaut astro) {
		super(astro);
		setTime(newTime);
		setActionName("Drink Water");
	}

	@Override
	public void doAction() {
		astro.addWater(100);
	}
	
	public static void changeTime(int i)
	{
		newTime=(newTime-i);
	}

}
