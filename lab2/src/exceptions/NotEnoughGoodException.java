package exceptions;

public class NotEnoughGoodException extends Exception 
{
	String message;
	public String getMessage() { return message; }
	
	public NotEnoughGoodException() { super(); }
	public NotEnoughGoodException(String message) 
	{
		super(message); 
		this.message = message;  
	}
}
