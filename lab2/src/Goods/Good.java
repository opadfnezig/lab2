package Goods;

import abstractClasses.SmartContainer;

public class Good extends SmartContainer 
{	
	private String manufacturer;
	private int count;
	public String getManufacturer() { return manufacturer; }
	public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
	public int getCount() { return count; }
	public void setCount(int count) { this.count = count; }
}
