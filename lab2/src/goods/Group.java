package goods;

import abstractClasses.SmartListContainer;

public class Group extends SmartListContainer<Good>
{
	public String toString()
	{
		String buff = "--Group name: " + this.name;
		for(var i : list) 
			buff += "\n" + i.toString();
		return buff;
	}
}
