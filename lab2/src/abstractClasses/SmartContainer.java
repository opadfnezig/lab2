package abstractClasses;

public abstract class SmartContainer 
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
