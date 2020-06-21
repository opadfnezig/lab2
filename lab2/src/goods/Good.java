package goods;

import java.util.NoSuchElementException;

import abstractClasses.ListContainer;
import abstractClasses.SmartContainer;
import abstractClasses.SmartListContainer;
import exceptions.NotEnoughGoodException;
import exceptions.NotUniqueElementException;
import stock.Order;

public class Good extends ListContainer<Order>
{	
	private String manufacturer;
	private int count;
	private double price;
	
	public Good() { }
	
	public Good(String name,String description,String manufacturer,double price)
	{
		if(name == "" || name == null)
			throw new IllegalArgumentException("param 'name' can not be empty");
		if(count < 0)
			throw new IllegalArgumentException("good count can't be less than 0");
		if(price < 0)
			throw new IllegalArgumentException("param 'price' can not be less than zero");
		this.name = name;
		this.description = description;
		this.manufacturer = manufacturer;
		this.count = 0;
		this.price = price;
	}
	
	public String getManufacturer() { return manufacturer; }
	public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
	public int getCount() { return count; }
	public void setCount(int count) { this.count = count; }
	
	@Override
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
	
	public String toString() { 
		return "--" + name + " | Manufacturer: " + manufacturer 
				+ " | Price: " + price + " | Count: " + count 
				+ "\n----" + "Description: " + description; 
		}
	@Override
	public int getElementIndex(Order element) {
		for(int i = 0;i< list.size();++i)
			if(list.get(i).getName().equals(element.getName()))
				return i;
		return -1;
	}
	
	@Override
	public int getElementIndex(String name) {
		for(int i = 0; i < this.size();++i)
			if(get(i).getName().equals(name))
				return i;
		return -1;
	}
	
	@Override
	public void edit(String name, Order element) throws NotUniqueElementException {
		set(getElementIndex(name),element);
		
	}

	@Override
	public void edit(String name, String newName, String newDes) throws NotUniqueElementException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order get(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
