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
	public GroupBase(String name, String path) 
	{
		super();
		this.name = name;
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
	
	public Group findGroup(String name)
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
	
	private int getGroupIndex(String name)
	{
		for(int i = 0;i < list.size();++i)

			if(list.get(i).getName().equals(name))
				return i;
		return -1;
	}
	
	public Pair findGood(String name)
	{
		for(int i = 0;i < list.size();++i)
			for(int j = 0; j < list.get(i).size();++j)
				if(list.get(i).get(j).getName().equals(name))
					return new Pair(list.get(i),list.get(i).get(j));
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
	

	public void editGroup(String name, String newName, String newDes) throws NotUniqueElementException
	{
		if(findGroup(newName) == null && name != newName)
			throw new NotUniqueElementException("param 'name' should be unique");
		Group g = findGroup(name);
		g.setName(newName);
		g.setDescription(newDes);
	}
	public void editGroup(String name, Group group) throws NotUniqueElementException
	{
		if( name == group.getName() || getGroupIndex(group.getName()) == -1)
			list.set(getGroupIndex(name),group);
		else
			throw new NotUniqueElementException("param 'name' should be unique");
	}
	
	public void editGood(String name, Good good) throws NotUniqueElementException
	{
		Pair data;
		if(name == good.getName() || findGood(good.getName()) == null)
		{
			data = findGood(name);
		 	list.get((int)data.arg1).set((int)data.arg2,good);
		}
		else
			throw new NotUniqueElementException("param 'name' should be unique");
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
	
	public GroupBase load()
	{
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) { return (GroupBase)ois.readObject(); }
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
				if(list.size() > i)
					return true;
				return false;
			}

			@Override
			public Group next() 
			{
				return list.get(i++);
			}
		};
		return i;
	}

	@Override
	public Object[] toArray() { return list.toArray(); }

	@Override
	public <T> T[] toArray(T[] a) { return (T[]) list.toArray(); }

	@Override
	public boolean add(Group e) { list.add(e); return true; }

	@Override
	public boolean remove(Object o) 
	{	
		remove(findGroup((Group)o));
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for(var a : c)
			if(!contains(a))
				return false;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Group> c) {
		for(var i : c)
			add(i);
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for(var i : c)
			remove(i);
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		for(int i = 0;i < this.size();++i)
			list.remove(i);
	}
}
