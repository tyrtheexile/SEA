package Actions;

import Astronaut.Astronaut;

public abstract class Action {
	
	private int time=10;
	protected Astronaut astro;
	private String actionName;
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Action(Astronaut astro) {
		this.astro=astro;
	}
	
	public int getTime() {return time;}
	protected void setTime(int time) {this.time=time;}
	
	public abstract void doAction();

}
