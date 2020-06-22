package goods;

import java.util.ArrayList;

import abstractClasses.SmartListContainer;

public class Group extends SmartListContainer<Good>
{
	public Group() {} 
	public Group(String name, String description) 
	{
		if(name.equals("") || name == null)
			throw new IllegalArgumentException("param 'name' can not be empty");
		this.name = name;
		this.description = description;
		list = new ArrayList<Good>();
	}
	
	protected void set(int index, Good good) { list.set(index, good); }
	
	public String toString()
	{
		String buff = "--" + this.name + ":";
		buff += "\n--Price of all: " + getPrice();
		for(var i : list) 
			buff += "\n--" + i.toString();
		buff+="\n--Description: "+description;
		return buff;
	}
}
