/**
 * A class for signalling the illegal action of retrieving an AlchemicIngredient that was never present in the laboratory. 
 */
import be.kuleuven.cs.som.annotate.*;
/**
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 *
 */
public class NotInLaboratoryException extends RuntimeException{
	
	/**
	 * Variable referencing the version number of the serializable class invoked upon. 
	 */
	
	private static final long serialVersionUID = 1L; 
	
	
	/**
	 * Variable referencing the AlchemicIngredient that is lacking. 
	 */
	
	private final AlchemicIngredient ingredient; 
	
	
	/**
	 * Initialize the new exception for the ingredient for which it is raised.  
	 * 
	 * @param	ingredient
	 * 			The ingredient for the new NotInLaboratoryException
	 * @post	The ingredient involved in the exception is set to 
	 * 			the ingredient in this exception
	 * 			| new.getIngredient() == ingredient			
	 */
	
	public NotInLaboratoryException(AlchemicIngredient ingredient) {
		this.ingredient = ingredient; 
	}
	
	/**
	 * 
	 * @return	The ingredient that is involved with the NotInaboratoryException
	 */
	@Basic @Immutable
	public AlchemicIngredient getIngredient() {
		return ingredient; 
	}

}
