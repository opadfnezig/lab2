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
		get(getElementIndex(name));
		
		for(var i : list)
			if(i.getName().equals(name))
				return i;
		return null;
	}
	
	public void addOrder(String name, Order order) { getGoodByName(name).add(order); }
	
	public Good getGoodByName(String name) { return getGoodByIndex(findGood(name)); }
	
	public Pair findGood(String name)
	{
		for(int i = 0;i < list.size();++i)
			for(int j = 0; j < list.get(i).size();++j)
				if(list.get(i).get(j).getName().equals(name))
					return new Pair(list.get(i),list.get(i).get(j));
		return null;
	}
	
	public Good getGoodByIndex(Pair pair) { return list.get((int)pair.arg1).get((int)pair.arg2); }
	
	
	public void applyStockOperation(Order operation) throws NotEnoughGoodException
	{
		Good good = getGoodByIndex(findGood(operation.getGoodName())); 
		if(operation.getType() == OperationType.purchase)
			good.increaseCount(operation.getCount());
		else
			good.decreaseCount(operation.getCount());
	}

	
	public void editGood(String name, Good good) throws NotUniqueElementException
	{
		Pair data;
		if(name != good.getName() && findGood(good.getName()) != null)
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
		File file = new File(path);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) { oos.writeObject(this); }
	      catch(Exception ex) { System.out.println(ex.getMessage()); } 
	}
	
	public GroupBase load()
	{
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) { return (GroupBase)ois.readObject(); }
		catch(Exception ex){ System.out.println(ex.getMessage()); }
		return null;
	}
	
	public void removeElementByName(String name)
	{
		if(findGroup(name) == null)
			throw new NoSuchElementException();
	}
	
}
