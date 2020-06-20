package goods;

import abstractClasses.SmartContainer;
import exceptions.NotEnoughGoodException;

public class Good extends SmartContainer 
{	
	private String manufacturer;
	private int count;
	private double price;
	
	public String getManufacturer() { return manufacturer; }
	public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
	public int getCount() { return count; }
	public void setCount(int count) { this.count = count; }
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	public void increaseCount(int conut) throws NotEnoughGoodException
	{
		if(this.count + count < 0)
			throw new NotEnoughGoodException();
		this.count += count;
	}
	public void decreaseCount(int count) throws NotEnoughGoodException
	{
		if(this.count - count < 0)
			throw new NotEnoughGoodException();
		this.count -= count;
	}
	
	public String toString() { return name + " manufacturer:" + manufacturer + " price: " + price + " count: " + count; }
}
