package stock;

public class StockOperation
{
	public enum OperationType { purchase,sale }
	private int price;
	private String goodName;
	private int count;
	private OperationType type;
	
	public OperationType getType() { return type; }
	public void setType(OperationType type) { this.type = type; } 
	
	public int getPrice() { return price; }
	public void setPrice(int price) { this.price = price; }
	public String getGoodName() { return goodName; }
	public void setGoodName(String goodName) { this.goodName = goodName; }
	public int getCount() { return count; }
	public void setCount(int count) { this.count = count; }
	
	public StockOperation() { }
	public StockOperation(OperationType type,String goodName,int count)
	{
		this.type = type;
		this.goodName = goodName;
		this.count = count;
		this.price = -1;
	}
	
	public StockOperation(OperationType type,String goodName,int count,int price)
	{
		this.type = type;
		this.goodName = goodName;
		this.count = count;
		this.price = price;
	}
}
