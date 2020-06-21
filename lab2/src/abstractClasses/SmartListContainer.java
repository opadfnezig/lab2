package abstractClasses;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;

import exceptions.NotUniqueElementException;
import goods.Group;
import interfaces.IArrayListContainer;

public abstract class SmartListContainer<T extends SmartContainer> extends ListContainer<T> implements IArrayListContainer<T>
{
	@Override
	public int getElementIndex(T element) 
	{
		for(int i = 0;i< list.size();++i)
			if(list.get(i).getName().equals(element.getName()))
				return i;
		return -1;
	}
	
	@Override
	public int getElementIndex(String name)
	{
		for(int i = 0;i< list.size();++i)
			if(list.get(i).getName().equals(name))
				return i;
		return -1;
	}
	 	
	@Override
	public double getPrice()
	{
		double price = 0;
		for(var i : list)
			price += i.getPrice();
		return price;
	}

	@Override
	public void edit(String name, T element) throws NotUniqueElementException
	{
		if(get(element.getName()) != null && name.equals(element.getName()))
			throw new NotUniqueElementException();
		list.set(getElementIndex(name),element);
	}
	

	
	@Override
	public void edit(String name, String newName, String newDes) throws NotUniqueElementException 
	{
		if(get(newName) != null && name.equals(newName))
			throw new NotUniqueElementException("param 'name' should be unique");
		T buff = get(name);
		buff.setDescription(newDes);
		buff.setName(newName);
	}

	@Override
	public T get(String name) 
	{
		if(getElementIndex(name) == -1)
			return null;
		return get(getElementIndex(name)); 
	}

	void set(String name, T element) { set(getElementIndex(name),element); }
}
