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

	public Recipe(String t, String ingred, String instruct) 
	{
		title = t;
		ingredients = ingred;
		instructions = instruct;
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
	
	//Set ingredients and instructions to new strings
	public void setIngredients(String i)
	{
		ingredients = i;
	}
	public void setInstructions(String i)
	{
		instructions = i;
	}

}
