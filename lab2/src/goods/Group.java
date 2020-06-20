package goods;

import abstractClasses.SmartListContainer;

public class Group extends SmartListContainer<Good>
{
	protected void add(Good good)  { list.add(good); }
	public String toString()
	{
		String buff = "--Group name: " + this.name;
		for(var i : list) 
			buff += "\n" + i.toString();
		return buff;
	}
}
