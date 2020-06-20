package goods;

import java.util.ArrayList;

import abstractClasses.SmartListContainer;

public class Group extends SmartListContainer<Good>
{
	public Group() {} 
	public Group(String name, String description) 
	{
		if(name == "" || name == null)
			throw new IllegalArgumentException("param \'name\' can not be empty");
		this.name = name;
		this.description = description;
		list = new ArrayList<Good>();
	}
	
	protected void add(Good good)  { list.add(good); }
	public String toString()
	{
		String buff = "--Group name: " + this.name;
		for(var i : list) 
			buff += "\n" + i.toString();
		return buff;
	}
}
