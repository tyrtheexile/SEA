package com.example.zergd.sea.Building;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Global;

public class BaseHub extends Building {

	public BaseHub(Astronaut astro, MainBase base,int xPos,int yPos) {
		super(astro, base);
		setConnections(true,true,true,true);
		setIndicator('H');
		setName("BaseHub");
		setDescription("Basic Base Hub");
		this.xPos=xPos;
		this.yPos=yPos;
	}

	@Override
	public int cycleModifier() {
		Global.DebugMSG(6, "BaseHub Cycle Modifier Connections- N:"+getNConnection()+" S:"+getSConnection()+" E:"+getEConnection()+" W:"+getWConnection());
		return 0;
	}

}
