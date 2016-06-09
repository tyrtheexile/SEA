package com.example.zergd.sea.Astronaut;

import com.example.zergd.sea.Global;

public class Astronaut {
	
	private String name;
	private int air;
	private int water;
	private int food;
	private int hunger;
	private int airMax;
	private int waterMax;
	private int foodMax;
	private int hungerMax;
	private int airRate;
	private int waterRate;
	private Choice choice;
	private HistoricalData changeData;
	
	public Astronaut(String name) {
		setName(name);
		createAstronaut();
	}
	private void createAstronaut() {
		this.air = 100;
		this.water = 100;
		this.food = 100;
		this.hunger = 1;
		this.airMax=100;
		this.waterMax=100;
		this.foodMax=100;
		this.hungerMax=100;
		this.airRate=3;
		this.waterRate=2;
		changeData = new HistoricalData(this);
		Global.DebugMSG(5, "\nAstronaut: "+name+" has been created with default stats");
	}
	
	public void timePulse()
	{
		Global.DebugMSG(5, "\nTimePulse for: "+getName());
		addAir(-getAirRate());
		addWater(-getWaterRate());
		addFood(-getHunger());
		if (getAir()>getAirMax()) setAir(getAirMax());
		if (getWater()>getWaterMax()) setWater(getWaterMax());
		if (getFood()>getFoodMax()) setFood(getFoodMax());
		changeData.astroAdds(air, water, food, hunger);
		
	}
	
	public Boolean isAlive()
	{
		if ((air<=0 || water <=0 || food<=0) && Global.getImmortal()==false)
		{
			System.out.println("\nAstronaut " +this.name+ " is dead. Game Over");
			return false;
		} else {
			return true;
		}
	}
	
	
	public String getStatusString()
	{
		String str,str1,str2,str3,str4;
		str1 = "\nStatus of: "+getName()+"\n Air: "+getAir()+"/"+getAirMax() +" ("+changeData.getAirChange()+")";
		str2 = "\n Water: "+getWater()+"/"+getWaterMax() +" ("+changeData.getWaterChange()+")";
		str3 = "\n Food: "+getFood()+"/"+getFoodMax() +" ("+changeData.getFoodChange()+")";
		str4 = "\n Hunger: "+getHunger()+"/"+getHungerMax() +" ("+changeData.getHungerChange()+")";
		str=str1+str2+str3+str4;
		return str;
	}
	
	public Boolean payCost(int airCost, int waterCost, int foodCost)
	{
		if (airCost>getAir())
		{
			Global.TextDisp("\nAir cost too high");
			return false;
		}
		if (waterCost>getWater())
		{
			Global.TextDisp("\nWater cost too high");
			return false;
		}
		if (foodCost>getFood())
		{
			Global.TextDisp("\nFood cost too high");
			return false;
		}
		air-=airCost;
		water-=waterCost;
		food-=foodCost;
		return true;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAir(){
		return air;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public void setAir(int air) {
		this.air = air;
	}
	public void addAir(int air) {
		this.air+=air;
	}
	public void addWater(int water) {
		this.water+=water;
	}
	public void addFood(int food){
		this.food+=food;
	}
	public void addHunger(int hunger){
		this.hunger+=hunger;
	}
	public int getAirMax() {
		return airMax;
	}
	public int getWaterMax() {
		return waterMax;
	}
	public int getFoodMax() {
		return foodMax;
	}
	public int getHungerMax() {
		return hungerMax;
	}
	public void setAirMax(int airMax) {
		this.airMax = airMax;
	}
	public void setWaterMax(int waterMax) {
		this.waterMax = waterMax;
	}
	public void setFoodMax(int foodMax) {
		this.foodMax = foodMax;
	}
	public void setHungerMax(int hungerMax) {
		this.hungerMax = hungerMax;
	}
	public int getAirRate() {
		return airRate;
	}
	public int getWaterRate() {
		return waterRate;
	}
	public void setAirRate(int airRate) {
		this.airRate = airRate;
	}
	public void setWaterRate(int waterRate) {
		this.waterRate = waterRate;
	}
	public Choice getChoice() {
		return choice;
	}
	public void setChoice(Choice choice) {
		this.choice = choice;
	}
	public HistoricalData getChangeData() {
		return changeData;
	}
	public void setChangeData(HistoricalData changeData) {
		this.changeData = changeData;
	}


}
