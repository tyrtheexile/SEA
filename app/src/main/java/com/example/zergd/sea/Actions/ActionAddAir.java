package Actions;

import Astronaut.Astronaut;

public class ActionAddAir extends Action {

	public ActionAddAir(Astronaut astro) {
		super(astro);
		setTime(7);
		setActionName("Add Air");
	}

	@Override
	public void doAction() {
		astro.addAir(50);
	}

}
