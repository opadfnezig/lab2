package abstractClasses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import exceptions.NotUniqueElementException;
import interfaces.IArrayListContainer;
import stock.Order;

public abstract class ListContainer<T> extends SmartContainer implements IArrayListContainer<T>, Collection<T>
{
	protected ArrayList<T> list;
	ArrayList<T> getList() { return list; }
	void setList(ArrayList<T> list) { this.list = list; }
	public ListContainer() { list = new ArrayList<T>(); }
	
	public void remove(int index) { list.remove(index); }
	
	public void edit(int index, T element) throws NotUniqueElementException 
	{
		if(index >= list.size())
			throw new IndexOutOfBoundsException(index);
		if(index < 0)
			throw new IllegalArgumentException();
		list.set(index, element);
	}
	
	@Override
	public T get(int index) { return list.get(index); }
	@Override
	public int size() { return list.size(); }
	
	public void addElement(T element) throws NotUniqueElementException 
	{
		if(getElementIndex(element) != -1)
			throw new NotUniqueElementException("param 'name' should be unique");
		list.add(element);
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
		if(list.contains(o))
			return true;
		return false;
	}

	@Override
	public Iterator<T> iterator() 
	{
		Iterator<T> i = new Iterator<T>()
		{
			int i = 0;
			@Override
			public boolean hasNext() 
			{
				if(list != null && list.size() > i)
					return true;
				return false;
			}

			@Override
			public T next() 
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
	public boolean remove(Object o)  { return list.remove(o); }

	@Override
	public boolean containsAll(Collection<?> c) {
		for(var a : c)
			if(!contains(a))
				return false;
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
	
	@Override
	public boolean add(T e) 
	{
		try 
		{
			addElement(e);
			return true;
		} 
		catch (NotUniqueElementException e1) { return false; }
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean thing = true;
		for(var i : c)
			if(!add(i))
				thing = false;
		return thing;
	}
	
	protected void set(int index, T element) { list.set(index, element); }
	
	@Override
	public void edit(String name, T element) throws NotUniqueElementException { set(getElementIndex(name),element);  }
	@Override
	public T get(String name) { return list.get(getElementIndex(name)); }
	@Override
	public void remove(String name) { list.remove(getElementIndex(name)); }
}
