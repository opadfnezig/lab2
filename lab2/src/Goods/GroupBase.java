package Goods;

import java.util.ArrayList;

import abstractClasses.SmartContainer;
import abstractClasses.SmartListContainer;
import exceptions.NotUniqueElementException;
import utils.Pair;

public class GroupBase extends SmartListContainer<Group>
{
	public void addGroup(Group element) throws NotUniqueElementException 
	{
		if(getElementIndex(element) != -1)
			throw new NotUniqueElementException("param 'name' should be unique");
		list.add(element);
	}
	public void addGood(int index,Group element) throws NotUniqueElementException
	{
		if(findGood(element.getName()) != null)
			throw new NotUniqueElementException();
		list.add(element);
	}
	
	public Pair findGood(String name)
	{
		for(int i = 0;i < list.size();++i)
			for(int j = 0; j < list.get(i).size();++j)
				if(list.get(i).get(j).getName().equals(name))
					return new Pair(i,j);
		return null;
	}
}
