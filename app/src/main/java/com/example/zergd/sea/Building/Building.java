package Building;

import Astronaut.Astronaut;
import Main.Global;

/*Building 
 * 
 * Must Contain:
 * 		Name
 * 		Description
 * 		Indicator
 * 		AlloyCost
 * 		HydrogenCost
 * 		CarbonCost
 * 
 * 
 */


public abstract class Building implements Cloneable {
	
	protected Astronaut astro;
	protected MainBase base;
	
	protected int xPos;
	protected int yPos;
	
	private String name="Empty";
	private String description;
	private int alloyCost=0;
	private int hydrogenCost=0;
	private int carbonCost=0;
	private int energyCost=0;
	
	private Boolean NConnExists;
	private Boolean SConnExists;
	private Boolean EConnExists;
	private Boolean WConnExists;
	private Building NConnection=null;
	private Building SConnection=null;
	private Building EConnection=null;
	private Building WConnection=null;
	private char indicator='X';
	
	public Building(Astronaut astro, MainBase base) {
		this.astro=astro;
		this.base=base;
	}
	
	public Boolean setPosition(int xPos,int yPos)
	{
		
		if (base.getGrid().getBuildings()[yPos][xPos]==null)
		{
			base.getGrid().getBuildings()[yPos][xPos]=this;
			this.xPos=xPos;
			this.yPos=yPos;
			return true;
		}else{
			Global.DebugMSG(3, "Buildings array is occupied at build position for "+this.getName()+" Position: "+xPos+","+yPos);
			return false;
		}
	}
	
	//Used during Building construction to set NSEW connections and true/false
	protected void setConnections(Boolean north,Boolean south, Boolean east, Boolean west)
	{
		this.NConnExists=north;
		this.SConnExists=south;
		this.EConnExists=east;
		this.WConnExists=west;
	}
	
	//Checks if there a connection of the opposite side given
	//Used during construction before building is placed to see if it has a connection in the right zone
	public Boolean oppositeConnectionAvaliable(String oppositeSide)
	{
		if (oppositeSide.equals("N") && this.SConnExists==true) {return true;}
		if (oppositeSide.equals("S") && this.NConnExists==true) {return true;}
		if (oppositeSide.equals("E") && this.WConnExists==true) {return true;}
		if (oppositeSide.equals("W") && this.EConnExists==true) {return true;}
		return false;
	}
	
	//Checks if the side of the given building is empty, a connection exists and that the edge isn't there
	public Boolean isSideEmpty(String dir)
	{
		int topSide=0;
		int botSide=base.getGrid().getSize();
		int estSide=base.getGrid().getSize();
		int wstSide=0;
		//Global.TextDisp(this.getPosition()[1]+"------------------------------------");
		if (dir.equals("N") && NConnExists==true && NConnection==null && this.getPosition()[1]>topSide) {return true;}
		if (dir.equals("S") && SConnExists==true && SConnection==null && this.getPosition()[1]<botSide) {return true;}
		if (dir.equals("E") && EConnExists==true && EConnection==null && this.getPosition()[0]<estSide) {return true;}
		if (dir.equals("W") && WConnExists==true && WConnection==null && this.getPosition()[0]>wstSide) {return true;}
		return false;
	}
	
	public int[] getPosition()
	{
		int[] tmp={xPos,yPos};
		return tmp;
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	public abstract int cycleModifier();

	public Boolean getNConnExists() {
		return NConnExists;
	}

	public Boolean getSConnExists() {
		return SConnExists;
	}

	public Boolean getEConnExists() {
		return EConnExists;
	}

	public Boolean getWConnExists() {
		return WConnExists;
	}

	public Building getNConnection() {
		return NConnection;
	}

	public Building getSConnection() {
		return SConnection;
	}

	public Building getEConnection() {
		return EConnection;
	}

	public Building getWConnection() {
		return WConnection;
	}

	public void setNConnExists(Boolean nConnExists) {
		NConnExists = nConnExists;
	}

	public void setSConnExists(Boolean sConnExists) {
		SConnExists = sConnExists;
	}

	public void setEConnExists(Boolean eConnExists) {
		EConnExists = eConnExists;
	}

	public void setWConnExists(Boolean wConnExists) {
		WConnExists = wConnExists;
	}

	public void setNConnection(Building nConnection) {
		NConnection = nConnection;
	}

	public void setSConnection(Building sConnection) {
		SConnection = sConnection;
	}

	public void setEConnection(Building eConnection) {
		EConnection = eConnection;
	}

	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}

	public void setWConnection(Building wConnection) {
		WConnection = wConnection;
	}

	public char getIndicator() {
		return indicator;
	}

	public void setIndicator(char indicator) {
		this.indicator = indicator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public int getAlloyCost() {
		return alloyCost;
	}

	public int getHydrogenCost() {
		return hydrogenCost;
	}

	public int getCarbonCost() {
		return carbonCost;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAlloyCost(int alloyCost) {
		this.alloyCost = alloyCost;
	}

	public void setHydrogenCost(int hydrogenCost) {
		this.hydrogenCost = hydrogenCost;
	}

	public void setCarbonCost(int carbonCost) {
		this.carbonCost = carbonCost;
	}

}
