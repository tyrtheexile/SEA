package Items;

import java.util.ArrayList;
import Astronaut.Astronaut;
import Building.MainBase;

public class InitItemArray {
	
	public InitItemArray() {
		
	}
	
	public static ArrayList<Item> StarterList(Astronaut astro,MainBase base)
	{
		ArrayList<Item> freeItems=new ArrayList<Item>();
		//freeItems.add(new Rebreather(astro,base));
		freeItems.add(new LifeSupport(astro,base));
		freeItems.add(new CargoManifest(astro,base));
		//freeItems.add(new WaterTank(astro,base));
		//freeItems.add(new CookTop(astro,base));
		//freeItems.add(new PickAxe(astro,base));
		//freeItems.add(new MiningBot(astro,base));
		//freeItems.add(new SmallSolarPanel(astro,base));
		//freeItems.add(new MediumSolarPanel(astro,this));
		//freeItems.add(new Fabricator(astro,base));
		return freeItems;
		
	}

}

/*   Essential: Rebreather, LifeSupport
 * 
 * 	 Survival: Cooktop, WaterTank
 * 
 * 	 Tier 1: Small Solar Panel(m), Pickaxe, Water Pump
 *   
 *   Tier 2: Medium Solar Panel(m)
 *   
 *   Tier 3: Large Solar Panel(m)
 *   
 *   Tier 4: Turbo Synthesizer
 *   
 *   Tier 5: Fabricator, Mining Bot(m)
 * 
 * 
 * 
 * 
 * 
 * 
 */
