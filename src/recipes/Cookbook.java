package recipes;

import java.io.Serializable;
import java.util.ArrayList;

//An array of recipes
public class Cookbook implements Serializable 
{
	//An array of Recipes interfaced with via the Cookbook class
	private ArrayList<Recipe> book;
	
	//Constant strings used by the sample recipe
	private String sample_title = "Recipe Title Here";
	private String sample_ingredients = "Recipe Ingredients Here  (No two recipes can have the same title)";
	private String sample_instructions = "Recipe Instructions Here (Click 'Search' with no title to display available recipes)";
	
	public Cookbook()
	{
		book = new ArrayList();
		book.add(new Recipe(sample_title, sample_ingredients, sample_instructions));
	}
	
	//Adds a new recipe to the cookbook, or overwrites the old one
	public void addRecipe(String title, String ingredients, String instructions)
	{
		boolean isThere = false;
		//Check if the recipe title already exists
		for(int i = 0; i < book.size(); i++)
		{
			if(title.equals(book.get(i).getTitle()))
			{
				//Overwrite the recipe and exit the loop
				book.get(i).setIngredients(ingredients);
				book.get(i).setInstructions(instructions);
				isThere = true;
				break;
			}
		}
		
		//If the recipe does not yet exist
		if(!isThere)
		{
			book.add(new Recipe(title, ingredients, instructions));
		}
	}
	
	//Delete a recipe from the cookbook
	public void delRecipe(String title)
	{
		for(int i = 0; i < book.size(); i++)
		{
			if(title.equals(book.get(i).getTitle()))
			{
				book.remove(i);
			}
		}
	}
	
	//Return a recipe by title
	public Recipe getRecipe(String title)
	{
		for(int i = 0; i < book.size(); i++)
		{
			if(title.equals(book.get(i).getTitle()))
			{
				return book.get(i);
			}
		}
		//System.out.println(book.get(0).getTitle());
		//System.out.println(title);
		return new Recipe(title, "No such Recipe", "No such Recipe");
	}
	
	//Get a list of all of the recipes available by title
	public ArrayList<String> getRecipeTitles()
	{
		ArrayList<String> titles = new ArrayList();
		for(int i = 0; i < book.size(); i++)
		{
			titles.add(book.get(i).getTitle());
		}
		return titles;
	}
	
}
