package goods;

import abstractClasses.SmartListContainer;
import exceptions.NotEnoughGoodException;
import exceptions.NotUniqueElementException;
import stock.StockOperation;
import stock.StockOperation.OperationType;
import utils.Pair;

public class GroupBase extends SmartListContainer<Group>
{
	public void addGroup(Group element) throws NotUniqueElementException 
	{
		if(getElementIndex(element) != -1)
			throw new NotUniqueElementException("param 'name' should be unique");
		list.add(element);
	}
	public void addGood(int index,Group element) throws NotUniqueElementException
	{
		if(findGood(element.getName()) != null)
			throw new NotUniqueElementException();
		list.add(element);
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
	
	public void applyStockOperation(StockOperation operation) throws NotEnoughGoodException
	{
		Good good = getGoodByIndex(findGood(operation.getGoodName())); 
		if(operation.getType() == OperationType.purchase)
			good.increaseCount(operation.getCount());
		else
			good.decreaseCount(operation.getCount());
		//log?
	}
	
	public String toString()
	{
		String buff = "----All stock:";
		for(var i : list)
			buff += "\n" + i.toString();
		return buff;
	}
}
