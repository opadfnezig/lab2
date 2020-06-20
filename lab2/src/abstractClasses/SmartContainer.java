package abstractClasses;

import java.io.Serializable;

import interfaces.IGood;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

public abstract class SmartContainer implements IGood, Serializable
{
	protected String name;
	protected String description;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public SmartContainer() {  }
	public SmartContainer(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
}
