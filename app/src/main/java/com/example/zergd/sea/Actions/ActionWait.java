package com.example.zergd.sea.Actions;

import com.example.zergd.sea.Astronaut.Astronaut;

public class ActionWait extends Action {

	public ActionWait(Astronaut astro) {
		super(astro);
		setTime(5);
		setActionName("Wait...");
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub

	}

}
