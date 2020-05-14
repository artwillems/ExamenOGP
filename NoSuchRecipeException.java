/**
 * A class for signaling the illegal action of removing a recipe from a recipe book which is not present.
 * 
 * @author Jérôme D'hulst, Marie Levrau, Art Willems
 * @version 1.8
 *
 */

public class NoSuchRecipeException extends RuntimeException{
	
	/**
	 * 
	 */
	
	private final static long serialVersionUID = 7890L; 
	
	/**
	 * Variable referencing 
	 */
	private final RecipeBook recipeBook; 
	
	/**
	 * Initialize a new no such recipe exception
	 * 
	 * @param	message
	 * 			The message that notifies the user of the illegal action taken.
	 * @param	recipeBook
	 * 			The recipebook involved in the new no such recipe exception
	 * @post	The message that notifies the user 
	 */
	public NoSuchRecipeException(String message, RecipeBook recipeBook) {
		super(message);
		this.recipeBook = recipeBook; 
	}
	
	public RecipeBook getRecipeBook() {
		return recipeBook; 
	}
	
	
	

}
