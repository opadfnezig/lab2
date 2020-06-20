package goods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;

import abstractClasses.SmartListContainer;
import exceptions.NotEnoughGoodException;
import exceptions.NotUniqueElementException;
import stock.Order;
import stock.Order.OperationType;
import utils.Pair;

public class GroupBase extends SmartListContainer<Group> implements Collection<Group>
{
	private String path;
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
	
	private Group findGroup(Group group)
	{
		for(var i : list)
			if(i.getName().equals(group.getName()))
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
	
	/*public void editGroup(String name, Group group)
	{
		findGroup(name) = group;
	}*/
	
	
	
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
	
	public GroupBase load()
	{
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat"))) { return (GroupBase)ois.readObject(); }
		catch(Exception ex){ System.out.println(ex.getMessage()); }
		return null;
	}

	@Override
	public boolean isEmpty() 
	{
		if(list.size() == 0)
			return true;
		return false;
	}

	@Override
	public boolean contains(Object o) 
	{
		if(findGroup((Group)o) != null)
			return true;
		return false;
	}

	@Override
	public Iterator<Group> iterator() 
	{
		Iterator<Group> i = new Iterator<Group>()
		{
			int i = 0;
			@Override
			public boolean hasNext() 
			{
				if(list.size() > i+1)
					return true;
				return false;
			}

			@Override
			public Group next() 
			{
				i++;
				return list.get(i);
			}
			
		};
		return i;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Group e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Group> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
