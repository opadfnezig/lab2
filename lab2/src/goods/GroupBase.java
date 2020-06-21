package goods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import abstractClasses.SmartListContainer;
import exceptions.NotEnoughGoodException;
import exceptions.NotUniqueElementException;
import stock.Order;
import stock.Order.OperationType;
import utils.Pair;

public class GroupBase extends SmartListContainer<Group> 
{
	private String path;
	
	public String getPath() { return path; }
	public void setPath(String path) { this.path = path; }
	
	public GroupBase(String name, String path) 
	{
		super();
		this.name = name;
		this.path = path;
	}
	
	
	public void addGood(String group,Good element) throws NotUniqueElementException
	{
		if(findGood(element.getName()) != null)
			throw new NotUniqueElementException();
		findGroup(group).add(element);
	}
	
	public Group findGroup(String name)
	{
		for(int i = 0; i< list.size();++i)
			if(list.get(i).getName().equals(name))
				return list.get(i);
		return null;
	}
	
	public void addOrder(String name, Order order)  throws NotEnoughGoodException
	{ 
		Good good = getGoodByIndex(findGood(order.getGoodName())); 
		if(order.getType() == OperationType.purchase)
			good.increaseCount(order.getCount());
		else
			good.decreaseCount(order.getCount());
		getGoodByName(name).add(order); 
		
	}
	
	public Good getGoodByName(String name) { return getGoodByIndex(findGood(name)); }
	
	public Pair findGood(String name)
	{
		for(int i = 0;i < list.size();++i)
			for(int j = 0; j < list.get(i).size();++j)
				if(list.get(i).get(j).getName().equals(name))
					return new Pair(i,j);
		return null;
	}
	
	public Good getGoodByIndex(Pair pair) { return list.get((int)pair.arg1).get((int)pair.arg2); }

	
	public void editGood(String name, Good good) throws NotUniqueElementException
	{
		Pair data;
		if(name.equals(good.getName()) && findGood(good.getName()) != null)
			throw new NotUniqueElementException("param 'name' should be unique");
		data = findGood(name);
	 	list.get((int)data.arg1).set((int)data.arg2,good);
			
	}
	
	public String toString()
	{
		String buff = "----All stock:";
		buff +="\n----Price of all goods: " + this.getPrice();
		for(var i : list)
			buff += "\n" + i.toString();
		return buff;
	}
	
	public void save()
	{
		 try
		 { 
			 new File(path).createNewFile();
			 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			 oos.writeObject(this);
		 }
	      catch(Exception ex) { System.out.println(ex.getMessage()); } 
	}
	
	public GroupBase load()
	{
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) { return (GroupBase)ois.readObject(); }
		catch(Exception ex){ System.out.println(ex.getMessage()); }
		return null;
	}
	@Deprecated
	void removeElementByName(String name)
	{
		if(findGroup(name) == null)
			throw new NoSuchElementException();
	}
	
	void removeGoodByName(String name)
	{
		Pair pair = findGood(name);
		list.get((int) pair.arg1).remove(pair.arg2);
	}
	
	void removeOrderById(String name)
	{
		for(int i = 0;i < list.size(); ++i)
			for(int j = 0; j < list.get(i).size();++j)
				for(int k = 0; k < list.get(i).get(j).size(); ++k)
					if(list.get(i).get(j).get(k).getName().equals(name))
						list.get(i).get(j).remove(k);
	}
}
