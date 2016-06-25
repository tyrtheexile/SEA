package com.example.zergd.sea.Items;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;

public abstract class Item implements java.io.Serializable{
	
	private String name;
	private int ID;
	private static int IDcount=0;
	private int alloyCost=0;
	private int carbonCost=0;
	private int hydrogenCost=0;
	private int energyCost=0;
	protected Astronaut astro;
	protected MainBase base;
	protected Boolean owned=false;
	private String description="--";
	protected int numberOfThisItem;
	protected String color;
	
	public Item(Astronaut astro, MainBase base)
	{
		this.astro=astro;
		this.base=base;
		color="Gray";
		ID=IDcount++;
	}
	
	public abstract int cycleModifier();
	public abstract int aquisitionModifier();
	
	public String getName() {
		return name;
	}
	public int getID() {
		return ID;
	}
	public int getAlloyCost() {
		return alloyCost;
	}
	public void setName(String namee) {
		this.name = namee;
	}
	public void setAlloyCost(int cost) {
		this.alloyCost = cost;
	}

	public int getCarbonCost() {
		return carbonCost;
	}

	public int getHydrogenCost() {
		return hydrogenCost;
	}

	public Boolean getOwned() {
		return owned;
	}

	public void setCarbonCost(int carbonCost) {
		this.carbonCost = carbonCost;
	}

	public void setHydrogenCost(int hydrogenCost) {
		this.hydrogenCost = hydrogenCost;
	}

	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}

	public void setOwned(Boolean owned) {
		this.owned = owned;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
