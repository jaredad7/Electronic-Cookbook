package recipes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

//An array of recipes
public class Cookbook implements Serializable 
{
	//Serial version id
	private static final long serialVersionUID = 3503104156271192469L;

	//An array of Recipes interfaced with via the Cookbook class
	private ArrayList<Recipe> book;
	
	//Constant strings used by the sample recipe
	private String sample_title = "Recipe Title Here";
	private String sample_ingredients = "Recipe Ingredients Here  (No two recipes can have the same title)";
	private String sample_instructions = "Recipe Instructions Here (Click 'Search' with no title to display available recipes)";
	private String sample_category = "Category";
	
	public Cookbook()
	{
		book = new ArrayList<Recipe>();
		book.add(new Recipe(sample_title, sample_ingredients, sample_instructions, sample_category));
	}
	
	//Adds a new recipe to the cookbook, or overwrites the old one
	public void addRecipe(String title, String ingredients, String instructions, String category)
	{
		boolean isThere = false;
		//Check if the recipe title already exists
		for(Recipe recipe : book)
		{
			if(title.equals(recipe.getTitle()))
			{
				//Overwrite the recipe and exit the loop
				recipe.setIngredients(ingredients);
				recipe.setInstructions(instructions);
				recipe.setCategory(category);
				isThere = true;
				break;
			}
		}
		
		//If the recipe does not yet exist
		if(!isThere)
		{
			book.add(new Recipe(title, ingredients, instructions, category));
		}
	}
	
	//Delete a recipe from the cookbook
	public void delRecipe(String title)
	{
		Iterator<Recipe> iterator = book.iterator();
		while(iterator.hasNext())
		{
			if(title.equals(iterator.next().getTitle()))
			{
				iterator.remove();
			}
		}
	}
	
	//Return a recipe by title
	public Recipe getRecipe(String title)
	{
		for(Recipe recipe : book)
		{
			if(title.equals(recipe.getTitle()))
			{
				return recipe;
			}
		}
		//System.out.println(book.get(0).getTitle());
		//System.out.println(title);
		return new Recipe(title, "No such Recipe", "No such Recipe", "");
	}
	
	//Get a list of all of the recipes available by title
	public ArrayList<String> getRecipeTitles()
	{
		ArrayList<String> titles = new ArrayList<String>();
		for(Recipe recipe : book)
		{
			titles.add(recipe.getTitle());
		}
		return titles;
	}
	
}
