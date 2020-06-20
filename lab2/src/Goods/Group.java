package Goods;

import java.util.ArrayList;

public class Group extends ArrayList<Good>
{
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public Group() { super(); }
	public Group(String name, ArrayList<Good> goods)
	{
		this.name = name;
		this.addAll(goods);
	}
}
