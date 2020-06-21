package stock;

import abstractClasses.SmartContainer;

public class Order extends SmartContainer
{
	private static Integer orderCount = 0;
	public enum OperationType { purchase,sale }
	private int price;
	private String goodName;
	private int count;
	private OperationType type;
	
	public OperationType getType() { return type; }
	public void setType(OperationType type) { this.type = type; } 
	
	public double getPrice() { return price; }
	public void setPrice(int price) { this.price = price; }
	public String getGoodName() { return goodName; }
	public void setGoodName(String goodName) { this.goodName = goodName; }
	public int getCount() { return count; }
	public void setCount(int count) { this.count = count; }
	
	public Order()
	{
		++orderCount; 
		//name = orderCount.toString();
	}
	
	public Order(OperationType type,String goodName,int count)
	{
		this.type = type;
		this.goodName = goodName;
		this.count = count;
		this.price = -1;
		++orderCount; 
		//name = orderCount.toString(); 
	}
	
	public Order(OperationType type,String goodName,int count,int price)
	{
		this.type = type;
		this.goodName = goodName;
		this.count = count;
		this.price = price;
		++orderCount; 
		//name = orderCount.toString(); 
	}
	
	public void add() {  }
}
