package abstractClasses;

import java.util.ArrayList;

import exceptions.NotUniqueElementException;
import interfaces.IArrayListContainer;

public abstract class SmartListContainer<T extends SmartContainer> extends SmartContainer implements IArrayListContainer<T>
{
	protected ArrayList<T> list;
	public SmartListContainer() { list = new ArrayList<T>(); }
	public SmartListContainer(String name, String description, ArrayList<T> list) 
	{
		super(name,description);
		list = new ArrayList<T>();
	}
	
	@Override
	public void remove(int index) { list.remove(index); }
	@Override
	public int getElementIndex(T element) 
	{
		for(int i = 0;i< list.size();++i)
			if(list.get(i).getName().equals(element.getName()))
				return i;
		return -1;
	}
	@Override
	public void edit(int index, T element) 
	{
		if(index >= list.size())
			throw new IndexOutOfBoundsException(index);
		if(index < 0)
			throw new IllegalArgumentException();
		list.set(index, element);
	}
	@Override
	public void edit(int index, String name, String description) 
	{ 
		T buff = list.get(index);
		buff.setName(name);
		buff.setDescription(description);
		edit(index,buff);
	}
	@Override
	public void editDescription(int index, String description) 
	{
		T buff = list.get(index);
		buff.setDescription(description);
		edit(index,buff);
	}
	@Override
	public void editName(int index, String name) 
	{
		T buff = list.get(index);
		buff.setName(name);
		edit(index,buff);
	}
	@Override
	public T get(int index) { return list.get(index); }
	@Override
	public int size() { return list.size(); }
	
	@Override
	public double getPrice()
	{
		double price = 0;
		for(var i : list)
			price += i.getPrice();
		return price;
	}
}
