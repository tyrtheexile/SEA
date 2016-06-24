package com.example.zergd.sea;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;


public class SpaceExplorer
{
	public static void main(String[] args) {
		Global.set_Debug(6);
		Global.setImmortal(true);
		Global.setTimeIncrement(1000);

		Global.setTestmode(2);
		
		Astronaut chris = new Astronaut("Chris");
		MainBase alpha = new MainBase("Alpha",chris);
		
		GameTimer mainGame = new GameTimer(chris,alpha);
		mainGame.StartGame();

	}
}
