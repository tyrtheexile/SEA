package com.example.zergd.sea.Astronaut;

import java.util.ArrayList;

import com.example.zergd.sea.Building.MainBase;

public class HistoricalData implements java.io.Serializable {
	
	private int accuracy=10;
	Astronaut astro;
	MainBase base;
	
	private ArrayList<Integer> airHistory = new ArrayList<Integer>();
	private ArrayList<Integer> waterHistory = new ArrayList<Integer>();
	private ArrayList<Integer> foodHistory = new ArrayList<Integer>();
	private ArrayList<Integer> hungerHistory = new ArrayList<Integer>();
	
	private ArrayList<Integer> alloyHistory = new ArrayList<Integer>();
	private ArrayList<Integer> carbonHistory = new ArrayList<Integer>();
	private ArrayList<Integer> hydrogenHistory = new ArrayList<Integer>();
	private ArrayList<Integer> energyHistory = new ArrayList<Integer>();
	
	public HistoricalData(Astronaut astro) {
		this.astro=astro;
		airHistory.add(astro.getAir());
		waterHistory.add(astro.getWater());
		foodHistory.add(astro.getFood());
		hungerHistory.add(astro.getHunger());

	}
	
	public void baseInit(MainBase base)
	{
		alloyHistory.add(base.getAlloy());
		carbonHistory.add(base.getCarbon());
		hydrogenHistory.add(base.getHydrogen());
		energyHistory.add(base.getEnergy());
	}
	
	public void baseAdds(int alloy,int carbon, int hydrogen, int energy)
	{
		if(alloyHistory.size()>3*accuracy){alloyHistory.remove(0);}
		if(carbonHistory.size()>3*accuracy){carbonHistory.remove(0);}
		if(hydrogenHistory.size()>3*accuracy){hydrogenHistory.remove(0);}
		if(energyHistory.size()>2*accuracy){energyHistory.remove(0);}
		alloyHistory.add(alloy);
		carbonHistory.add(carbon);
		hydrogenHistory.add(hydrogen);
		energyHistory.add(energy);

	}

	public void astroAdds(int air, int water, int food, int hunger)
	{
		if(airHistory.size()>accuracy){airHistory.remove(0);}
		if(waterHistory.size()>accuracy){waterHistory.remove(0);}
		if(foodHistory.size()>accuracy){foodHistory.remove(0);}
		if(hungerHistory.size()>accuracy){hungerHistory.remove(0);}
		airHistory.add(air);
		waterHistory.add(water);
		foodHistory.add(food);
		hungerHistory.add(hunger);
	}
	
	public int getAirChange() {return change(airHistory);}
	public int getWaterChange() {return change(waterHistory);}
	public int getFoodChange() {return change(foodHistory);}
	public int getHungerChange() {return change(hungerHistory);}
	
	public int getAlloyChange() {return change(alloyHistory);}
	public int getCarbonChange() {return change(carbonHistory);}
	public int getHydrogenChange() {return change(hydrogenHistory);}
	public int getEnergyChange() {return change(energyHistory);}
	
	private int change(ArrayList<Integer> list){
		int avg=0;
		double change=0;
		double temp=list.get(0);
		for (int i:list)
		{
			//if (i==list.get(0)) continue;
			avg+=i;
			change+=i-temp;
			//Global.TextDisp(i+"   "+temp+"    "+list.get(0)+"   "+change);
			temp=i;
		}
		avg=(int) avg/list.size();
		//Global.TextDisp(""+change/list.size());
		change=Math.ceil(-change/list.size());
		//Global.TextDisp(change+"");
		return (int)(-change);
	}

}
