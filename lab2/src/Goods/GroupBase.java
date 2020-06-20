package Goods;

import java.util.ArrayList;

import exceptions.NotUniqueElementException;

public class GroupBase
{
	
	private ArrayList<Group> list;
	
	public GroupBase() 
	{
		list = new ArrayList<Group>();
		
	}
	
	public void add(Group group) throws NotUniqueElementException
	{
		if(find(group) != -1)
			throw new NotUniqueElementException("param 'name' should be unique");
		list.add(group);
	}
	
	private int find(Group group)
	{
		for(int i = 0;i< list.size();++i)
			if(list.get(i).getName().equals(group.getName()))
				return i;
		return -1;
	}
	
	public void edit(int index,Group group)
	{
		if(index >= list.size())
			throw new IndexOutOfBoundsException(index);
		if(index < 0)
			throw new IllegalArgumentException();
		list.set(index, group);
	}
	
	public void editName(int index,String name) { edit(index,new Group(name,list.get(index))); }
	
}
