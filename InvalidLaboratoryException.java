import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling the setting of laboratory that does not exist.
 */

/**
 * 
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 *
 */
public class InvalidLaboratoryException extends RuntimeException{
	

	/**
	 * Variable referencing the version number for the serial class invoked upon. 
	 */
	private final static long serialVersionUID = 3L; 
	
	/**
	 * Variable referencing the AlchemicIngredient for which the exception is thrown
	 */
	private final AlchemicIngredient ingredient;
	
	/**
	 * Initialize a new invalid laboratory exception for the given laboratory.
	 * 
	 * @param 	message
	 * 			The error message that will be given to the user.
	 * @param 	ingredient
	 * 			The AlchemicIngredient for which the new invalid laboratory exception is thrown
	 * @post	The message for the new invalid laboratory exception is set to the given message
	 * 			| super(message)
	 * @post	The new ingredient is set to the ingredient for which the new exception is thrown
	 * 			| new.getIngredient() == ingredient
	 */
	public InvalidLaboratoryException(String message, AlchemicIngredient ingredient) {
		super(message);
		this.ingredient = ingredient;
	}
	
	/**
	 * Return the AlchemicIngredient involved in this new invalid laboratory exception.
	 */
	@Basic @Immutable
	public AlchemicIngredient getIngredient() {
		return this.ingredient;
	}
}
