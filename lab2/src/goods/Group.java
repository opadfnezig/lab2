package goods;

import java.util.ArrayList;

import abstractClasses.SmartListContainer;

public class Group extends SmartListContainer<Good>
{
	public Group() {} 
	public Group(String name, String description) 
	{
		if(name == "" || name == null)
			throw new IllegalArgumentException("param 'name' can not be empty");
		this.name = name;
		this.description = description;
		list = new ArrayList<Good>();
	}
	protected void set(int index, Good good) { list.set(index, good); }
	protected void add(Good good)  { list.add(good); }
	public String toString()
	{
		String buff = "--" + this.name + ":";
		buff+="\n--Description: "+description;
		for(var i : list) 
			buff += "\n--" + i.toString();
		return buff;
	}
}
