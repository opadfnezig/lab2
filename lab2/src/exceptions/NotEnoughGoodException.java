package exceptions;

public class NotEnoughGoodException extends Exception 
{
	public NotEnoughGoodException(String message) { super(message); }
	public NotEnoughGoodException() { super(); }
}
