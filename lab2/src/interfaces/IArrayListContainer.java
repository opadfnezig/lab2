package interfaces;

import java.util.NoSuchElementException;

import exceptions.NotUniqueElementException;
import goods.Group;

public interface IArrayListContainer<T>
{
	public void remove(int index);
	public int getElementIndex(T element);
	public int getElementIndex(String name);
	public void edit(int index, T element) throws NotUniqueElementException;
	public void edit(String name, T element) throws NotUniqueElementException;
	public T get(int index);
	public T get(String name);
	public double getPrice();
	public void remove(String name);
}
