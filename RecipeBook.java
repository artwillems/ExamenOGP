import java.util.ArrayList;
import java.util.List;

/**
 * 
 * A class that bundles Recipes into a recipe book. 
 * 
 * @author Jérôme D'hulst, Marie Levrau, Art Willems 
 * @version 1.8
 *
 */
public class RecipeBook{

	/**
	 * Initializes a new recipe book with a given number of pages, each containing one recipe
	 * 
	 * 
	 * @param	listOfRecipes
	 * 			All the recipes the recipe book is about to contain. 
	 * 
	 * @effect	The recipes in the recipe book, are set to the recipes of listOfRecipes
	 * 			|setRecipes(listOfRecipes) 
	 */
	
	public RecipeBook(ArrayList<Recipe> listOfRecipes){
		setRecipes(listOfRecipes);
	}

	/**
	 * Variable referencing the recipes in the recipe book
	 */
	
	private List<Recipe> recipesInBook = null;

	/**
	 * Set the recipes in this book to the given list of recipes.
	 * 
	 * @param	recipesInBook
	 * 			The recipes that need to be written down in this recipe book
	 */
	
	private void setRecipes(ArrayList<Recipe> recipesInBook){
		this.recipesInBook = recipesInBook;
	}

	/**
	 * Displays all the recipes that are in this recipe book as a list of recipes.
	 * 
	 * @return	The recipes that are in this book. 
	 */
	
	public List<Recipe> displayRecipes(){
		return this.recipesInBook;
	}

	/**
	 * Write a new recipe down in this book by adding it to the existing list of recipes.
	 * 
	 * @param	newRecipe
	 * 			The new Recipe that needs to be added
	 * 
	 * @throws	RecipeAlreadyInBookException
	 * 			If the recipe already exists in this recipe book, then a new recipe already in book exception is thrown. 
	 * 			| throw new RecipeAlreadyInBookException(message, this)
	 */
	
	public void writeRecipeInBook(Recipe newRecipe) throws RecipeAlreadyInBookException{
		if(!thereExistSuchRecipe(newRecipe)){
			displayRecipes().add(newRecipe);
		}
		throw new RecipeAlreadyInBookException("You cannot write the recipe down, it already exists in this book", this);
}
	/**
	 * Remove a recipe from the recipe book
	 * 
	 * @param	oldRecipe
	 * 			The recipe that needs to be torn out of the recipe book
	 * @throws	NoSuchRecipeException
	 * 			If the recipe that needs to be removed is not present in the recipe book, 
	 * 			a new no such recipe exception is thrown
	 * 			|throw new NoSuchRecipeException(message, this)
	 */
	
	public void tearRecipeFromBook(Recipe oldRecipe) throws NoSuchRecipeException{
		if(thereExistSuchRecipe(oldRecipe)){
			int whereToFindRecipe = displayRecipes().indexOf(oldRecipe);
			displayRecipes().remove(whereToFindRecipe);
		}
		throw new NoSuchRecipeException("The recipe you tried to remove does not exist in this recipe book", this);
	}

	/**
	 * 
	 * @param	recipe
	 * 			The recipe for which the presence in the recipe book is checked
	 * @return	true if the recipe is present in the recipe book, false if otherwise
	 * 			| displayRecipes	
	 */
	
	private boolean thereExistSuchRecipe(Recipe recipe){
		return (displayRecipes().contains(recipe));
	}

}
