package interfaces;

import exceptions.NotUniqueElementException;

public interface IArrayListContainer<T>
{
	public void remove(int index);
	public int getElementIndex(T element);
	public void edit(int index, T element);
	public void edit(int index, String name,String description);
	public void editDescription(int index,String description);
	public void editName(int index,String name);
	public T get(int index);
	public int size();
	public double getPrice();
}
