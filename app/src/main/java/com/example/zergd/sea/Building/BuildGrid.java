package com.example.zergd.sea.Building;

import java.util.ArrayList;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Global;

public class BuildGrid implements java.io.Serializable{
	private Astronaut astro;
	private MainBase base;
	private int size=5;
	private Building[][] buildings;
	private ArrayList<Building>	freeBuildings = new ArrayList<Building>();
	
	public BuildGrid(Astronaut astro, MainBase base,int size) {
		this.astro=astro;
		this.base=base;
		this.size=size;
		buildings = new Building[size][size];
		placeBaseHub();
		freeBuildings.add(new Corridor(astro,base));
	}
	
	public Boolean constructBuilding(Building newBuilding, Building oldBuilding, String side)
	{
		if(!oldBuilding.isSideEmpty(side)) 
		{
			Global.DebugMSG(3, "Side: "+side+" of Building: "+oldBuilding.getName()+" is full");
			return false;
		}
		if(!newBuilding.oppositeConnectionAvaliable(side))
		{
			Global.DebugMSG(3, "No Connection on the side for: "+newBuilding.getName());
			return false;
		}
		if(base.payCost(newBuilding.getAlloyCost(), newBuilding.getCarbonCost(), newBuilding.getHydrogenCost(), newBuilding.getEnergyCost()))
		{
			int oldX=oldBuilding.getPosition()[0];
			int oldY=oldBuilding.getPosition()[1];
			int xPos=0;
			int yPos=0;
			
			if(side.equals("N")||side.equals("n")){xPos=oldX; yPos=oldY-1;}
			if(side.equals("S")||side.equals("s")){xPos=oldX; yPos=oldY+1;}
			if(side.equals("E")||side.equals("e")){xPos=oldX+1; yPos=oldY;}
			if(side.equals("W")||side.equals("w")){xPos=oldX-1; yPos=oldY;}
			
			if(newBuilding.setPosition(xPos, yPos))
			{
				base.getGrid().getBuildings()[yPos][xPos]=newBuilding;
				Global.DebugMSG(3, "Building: "+newBuilding.getName()+" constructed, at:"+newBuilding.getPosition());
			}
			else
				return false;
			return true;
		} else
			return false;
	}
	
	public void cycleBuildings()
	{
		for(Building[] i:buildings)
		{
			for(Building j:i)
			{
				if (j!=null)
					j.cycleModifier();
			}
		}
	}
	
	private void placeBaseHub()
	{
		int mid=size/2;
		//buildings[mid][mid]=new BaseHub(astro,base);
		Building hub = new BaseHub(astro,base,mid,mid);
		buildings[mid][mid]=hub;
		Global.DebugMSG(3, "Base hub Placed at: "+mid+","+mid);
	}
	
	public void drawBaseMap()
	{
		Global.TextDisp("Base Map:");
		Global.TextDisp("-------------------------------");
		char letter='a';
		String str=(letter++)+" ";
		String linestr="  ";
		String toplin="  ";
		if (buildings[0].length>9)
		{
			for (int i=1;i<=buildings[0].length;i++)
			{
				if (i<=9)
					toplin=toplin+"  ";
				else
					toplin=toplin+(i/10)+" ";
			}
		}
		Global.TextDisp(toplin);
		toplin="  ";
		for(int i=1;i<=buildings[0].length;i++)
		{
			toplin=toplin+(i%10)+"|";
		}
		Global.TextDisp(toplin);
		for(Building[] i:buildings)
		{
			for(Building j:i)
			{
				if (j==null)
					{str=str+"-";}
				else
					{str=str+j.getIndicator();}
				linestr=linestr+"--";
				str=str+"|";
			}
			Global.TextDisp(str);
			Global.TextDisp(linestr);
			str=(letter++)+" ";
			linestr="  ";
		}
	}

	public int getSize() {
		return size;
	}

	public Building getBuilding(int x,int y)
	{
		if (buildings[y][x]!=null)
			return buildings[y][x];
		else
			return null;
	}
	
	public Building[][] getBuildings() {
		return buildings;
	}

	public ArrayList<Building> getFreeBuildings() {
		return freeBuildings;
	}

	public void add2FreeBuildings(Building freeBuilding) {
		Boolean flag=false;
		for (Building i:freeBuildings)
		{
			if (i.getName()!=freeBuilding.getName()){
				continue;
			}else {
				Global.DebugMSG(6, "freeBuildings already contains" +freeBuilding.getName());
				flag=true;
			}
		}
		if (flag==false)
		{
			freeBuildings.add(freeBuilding);
		}
	}
	
	public Boolean ownBuilding(String name)
	{
		for(Building[] i:buildings)
		{
			for(Building j:i)
			{
				if (j==null)
					continue;
				if (j.getName()!=name)
					continue;
				else
					return true;
			}
		}
		return false;
	}
}
