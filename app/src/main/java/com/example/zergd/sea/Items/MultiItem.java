package Items;

import Astronaut.Astronaut;
import Building.MainBase;

public abstract class MultiItem extends Item {

	protected int numberOfThisItem;
	
	public MultiItem(Astronaut astro, MainBase base) {
		super(astro, base);
		numberOfThisItem=0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int cycleModifier() {
		
		//Call the MultiItem method used with numberofthisitem
		
		cycleMulti();
		
		//Call the method for each item once if needed
		for (int i=0;i<numberOfThisItem;i++)
		{
			cycleModifierOnce();
		}
		return 0;
	}

	@Override
	public abstract int aquisitionModifier();
	
	public abstract int cycleModifierOnce();

	public int getNumberOfThisItem() {
		return numberOfThisItem;
	}

	public void add2NumberOfThisItem(int num) {
		this.numberOfThisItem +=num;
	}
	
	public abstract int cycleMulti();

}
