package com.example.zergd.sea.Items;

import java.util.ArrayList;

import com.example.zergd.sea.Astronaut.Astronaut;
import com.example.zergd.sea.Building.MainBase;
import com.example.zergd.sea.Global;

public class ItemOps implements java.io.Serializable{
	Astronaut astro;
	MainBase base;
	private ArrayList<Item> freeItems;
	private ArrayList<Item> ownedItems = new ArrayList<Item>();
	
	public ItemOps(Astronaut astro,MainBase base) {
		this.astro=astro;
		this.base=base;
		freeItems = InitItemArray.StarterList(astro,base);
		if (Global.getTestmode()==2)
		{
			ownedItems.add(new Fabricator(astro,base));
		}
	}
	
	public void moveItem2Owned(Item item)
	{
		if(item instanceof MultiItem)
		{
			if(ownedItems.contains(item)==false) ownedItems.add(item);
			((MultiItem) item).add2NumberOfThisItem(1);
			//freeItems.remove(item);
		} else {
			ownedItems.add(item);
			freeItems.remove(item);
		}
			
	}
	
	public String getItemStatusString()
	{
		int num;
		String str="\nItems in Base: ";
		if (ownedItems.isEmpty())
		{
			str = str + " None";
			return str;
		}
		for (Item i:ownedItems)
		{
			if (i instanceof MultiItem)
				num=((MultiItem) i).getNumberOfThisItem();
			else
				num=1;
			str=str+"\n  -"+num+"- "+i.getName()+"";
		}
		return str;
	}
	
	public int findFreeItemByName(String name)
	{
		for (int i=0;i<freeItems.size();i++)
		{
			if (name==freeItems.get(i).getName())
			{
				return i;
			}
		}
		
		Global.DebugMSG(6, "Cannot Find Item in FreeItems");
		return -1;
	}
	
	public void addFreeItem(Item item)
	{	
		Boolean flag=false;
		for (Item i:freeItems)
		{
			if (i.getName()!=item.getName()){
				continue;
			}else {
				Global.DebugMSG(6, "freeItems already contains" +item.getName());
				flag=true;
			}
		}
		if (flag==false)
		{
			freeItems.add(item);
		}
	}
	
	public void removeFreeItem(int num)
	{
		if (num>0)
		{
		freeItems.remove(num);
		} else {
			Global.DebugMSG(6, "Cannot remove item with index -1");
		}
	}
	
	public Boolean haveItem(String name)
	{
		for (Item i:freeItems)
		{
			if (i.getName()!=name){
				continue;
			}else {
				return true;
			}
		}
		return false;
	}
	
	public Boolean ownItem(String name)
	{
		for (Item i:ownedItems)
		{
			if (i.getName()!=name){
				continue;
			}else {
				return true;
			}
		}
		return false;
	}
	
	public void removeFreeItemByName(String name)
	{
		Global.DebugMSG(6, "Removing (passed) "+name+" at index "+findFreeItemByName(name)+" from array: ");
		Global.DebugMSG(6, ""+freeItems);
		removeFreeItem(findFreeItemByName(name));
	}
	
	public void addOwnedItem(Item item)
	{
		getOwnedItems().add(item);
	}
	
	public ArrayList<Item> getFreeItemArray()
	{
		return getFreeItems();
	}
	
	public ArrayList<Item> getOwnedItemArray()
	{
		return getOwnedItems();
	}
	
	public ArrayList<Item> getFreeItems() {
		return freeItems;
	}

	public ArrayList<Item> getOwnedItems() {
		return ownedItems;
	}

	public void setFreeItems(ArrayList<Item> freeItems) {
		this.freeItems = freeItems;
	}

	public void setOwnedItems(ArrayList<Item> ownedItems) {
		this.ownedItems = ownedItems;
	}

}
