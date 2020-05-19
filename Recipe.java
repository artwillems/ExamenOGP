import java.util.List;

/**
 * A class of recipes involving a list of operations that needs to be executed on a list of quantities of ingredients, and a laboratory where the recipe is executed.
 * 
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/
public class Recipe {
	
	/**********************************************************
     * constructor
     **********************************************************/
	
	public Recipe(List<String> operationList, List<String> ingredientList, List<RecipeBook> recipeBooks) {
		setOperationList(operationList);
		setIngredientList(ingredientList);
		setRecipeBook(recipeBooks);
	}
	
	
	private List<String> operationList = null;
	
	public List<String> getOperationList(){
		return operationList;
	}
	
	private void setOperationList(List<String> operationList) {
		this.operationList = operationList;
	}
	
	private List<String> ingredientList = null;
	
	public List<String> getIngredientList(){
		return ingredientList;
	}
	
	private void setIngredientList(List<String> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	

	
	
	
	
	
	
	
	
	
	/**********************************************************
     * unidirectionele relatie met recipebooks
     **********************************************************/
	
	
	/**
	 * Variable referencing a list of recipebooks where this recipe can be found.
	 */
	private List<RecipeBook> recipeBooks = null;
	
	/**
	 * Return the list of recipebooks where this recipe can be found
	 */
	public List<RecipeBook> getRecipeBook() {
		return this.recipeBooks;
	}
	
	/**
	 * Set the list of recipebooks of this recipe to the given list of recipebooks.
	 * 
	 * @param   recipeBooks
     * 			The new list of recipebooks for this recipe.
	 */
	private void setRecipeBook(List<RecipeBook> recipeBooks) throws IllegalRecipeBookException {
		if (! isValidRecipeBook(recipeBooks)) {
			throw new IllegalRecipeBookException("This recipe does not belong to a recipeBook");
		}
		else {
			this.recipeBooks = recipeBooks;
		}
	}
	
	
	public static boolean isValidRecipeBook(List<RecipeBook> recipeBooks) {
		return (recipeBooks.size()>=1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
