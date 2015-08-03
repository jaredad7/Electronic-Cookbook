package recipes;

import java.io.Serializable;

//A single recipe in a cookbook
public class Recipe implements Serializable
{
	//Serial version id
	private static final long serialVersionUID = 2174540422411710151L;
	
	private String title;
	private String ingredients;
	private String instructions;
	private String category;

	public Recipe(String title, String ingredients, String instructions, String category) 
	{
		this.title = title;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.category = category;
	}

	//Get recipe components
	public String getTitle() 
	{
		return title;
	}
	public String getIngredients()
	{
		return ingredients;
	}
	public String getInstructions()
	{
		return instructions;
	}
	public String getCategory()
	{
		return category;
	}
	
	//Set ingredients and instructions to new strings
	public void setIngredients(String i)
	{
		ingredients = i;
	}
	public void setInstructions(String i)
	{
		instructions = i;
	}
	public void setCategory(String c)
	{
		category = c;
	}

}
