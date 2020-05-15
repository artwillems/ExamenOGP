import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


 
/**
 * A class for signaling the illegal action of writing down a recipe in a recipe book which already exists
 * 
 * @author Jérôme D'hulst, Marie Levrau, Art Willems 
 * @version 1.8
 *
 */
public class RecipeAlreadyInBookException extends RuntimeException{
	
	/**
	 * 
	 */
	
	private final static long serialVersionUID = 3457L; 
	
	/**
	 * Variable referencing the recipe book involved in this recipe already in book
	 * exception
	 */
	private final RecipeBook recipeBook; 
	
	/**
	 * Initialize a new recipe already in book exception
	 * 
	 * @param	message
	 * 			The message that notifies the user of the illegal action		
	 * @param	recipeBook
	 * 			The recipe book for  which the new excpetion is thrown.
	 * @post	The message that is returned to the user is set to the given message.
	 * 			|super(message)	 	
	 * @post	The recipe book involved in the new recipe already in book exception is set to
	 * 			the given recipe book.
	 * 			|new.getRecipeBook = recipeBook
	 */

	public RecipeAlreadyInBookException(String message, RecipeBook recipeBook) {
		super(message); 
		this.recipeBook = recipeBook; 
	}
	/**
	 * 
	 * @return the recipe book involved in the new recipe already in book exception
	 */
	
	@Basic @Raw
	public RecipeBook getRecipeBook() {
		return this.recipeBook; 
	}
}
