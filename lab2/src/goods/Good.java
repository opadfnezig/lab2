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
	
	public Good() { }
	
	public Good(String name,String description,String manufacturer,double price)
	{
		if(name == "" || name == null)
			throw new IllegalArgumentException("param \'name\' can not be empty");
		if(count < 0)
			throw new IllegalArgumentException("good count can't be less than 0");
		if(price < 0)
			throw new IllegalArgumentException("param \'price\' can not be less than zero");
		this.name = name;
		this.description = description;
		this.manufacturer = manufacturer;
		this.count = 0;
		this.price = price;
	}
	
	
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
