import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling that the ingredient has already been terminated.
 */

/**
 * 
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 *
 */
public class IngredientAlreadyTerminatedException extends RuntimeException{
	

	/**
	 * Variable referencing the version number for the serial class invoked upon. 
	 */
	private final static long serialVersionUID = 3L; 
	
	/**
	 * Variable referencing the AlchemicIngredient for which the exception is thrown
	 */
	private final AlchemicIngredient ingredient;
	
	/**
	 * Initialize a new ingredient already terminated exception for the given laboratory.
	 * 
	 * @param 	message
	 * 			The error message that will be given to the user.
	 * @param 	ingredient
	 * 			The AlchemicIngredient for which the new ingredient already terminated exception is thrown
	 * @post	The message for the new ingredient already terminated exception is set to the given message
	 * 			| super(message)
	 * @post	The new ingredient is set to the ingredient for which the new exception is thrown
	 * 			| new.getIngredient() == ingredient
	 */
	public IngredientAlreadyTerminatedException(String message, AlchemicIngredient ingredient) {
		super(message);
		this.ingredient = ingredient;
	}
	
	/**
	 * Return the AlchemicIngredient involved in this new ingredient already terminated exception.
	 * 
	 * @return	The ingredient for the given ingredient already terminated exception.
	 */
	@Basic @Immutable
	public AlchemicIngredient getIngredient() {
		return this.ingredient;
	}
}
