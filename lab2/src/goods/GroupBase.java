package goods;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import abstractClasses.SmartListContainer;
import exceptions.NotEnoughGoodException;
import exceptions.NotUniqueElementException;
import stock.Order;
import stock.Order.OperationType;
import utils.Pair;

public class GroupBase extends SmartListContainer<Group>
{
	String path;
	public GroupBase(String path) 
	{
		super();
		this.path = path;
	}
	
	public void addGroup(Group element) throws NotUniqueElementException 
	{
		if(getElementIndex(element) != -1)
			throw new NotUniqueElementException("param 'name' should be unique");
		list.add(element);
	}
	
	public void addGood(String group,Good element) throws NotUniqueElementException
	{
		if(findGood(element.getName()) != null)
			throw new NotUniqueElementException();
		findGroup(group).add(element);
	}
	
	private Group findGroup(String name)
	{
		for(var i : list)
			if(i.getName().equals(name))
				return i;
		return null;
	}
	
	public Pair findGood(String name)
	{
		for(int i = 0;i < list.size();++i)
			for(int j = 0; j < list.get(i).size();++j)
				if(list.get(i).get(j).getName().equals(name))
					return new Pair(i,j);
		return null;
	}
	
	public Good getGoodByIndex(int i,int j) { return list.get(i).get(j); }
	public Good getGoodByIndex(Pair pair) { return list.get((int)pair.arg1).get((int)pair.arg2); }
	
	
	public void applyStockOperation(Order operation) throws NotEnoughGoodException
	{
		Good good = getGoodByIndex(findGood(operation.getGoodName())); 
		if(operation.getType() == OperationType.purchase)
			good.increaseCount(operation.getCount());
		else
			good.decreaseCount(operation.getCount());
	}
	
	public String toString()
	{
		String buff = "----All stock:";
		for(var i : list)
			buff += "\n" + i.toString();
		return buff;
	}
	
	public void save()
	{
		 try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) { oos.writeObject(this); }
	      catch(Exception ex) { System.out.println(ex.getMessage()); } 
	}
}
