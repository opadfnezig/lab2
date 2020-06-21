package exceptions;

public class NotUniqueElementException extends Exception
{
	String message;
	public String getMessage() { return message; }
	public NotUniqueElementException() { super(); }
	public NotUniqueElementException(String message) 
	{
		super(message); 
		this.message = message;
	}
}
