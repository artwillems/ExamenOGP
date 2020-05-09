import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling the illegal operation of adding a second ingredient to a device. 
 */

/**
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 *
 */
public class IllegalIngredientAdditionException extends RuntimeException{
	
	/**
	 * Variable referencing the version number for the serialized class invoked upon. 
	 * 
	 */
	
	private static final long serialVersionUID= 1L; 
	
	/**
	 * Variable referencing the oven for which the illegal action was taken. 
	 */
	
	private final Oven oven; 
	
	/**
	 * Initialize a new illegal ingredient addition exception for the container for which the 
	 * exception is thrown. 
	 * 
	 * @param 	message
	 * 			A message that informs to user of his illegal action
	 * @param 	oven
	 * 			The oven involved in the new illegal ingredient addition exception
	 * @post	A message containing information about the illegal action is given to the user.
	 * 			|super(message) 
	 * @post	The oven for which the new illegal ingredient addition exception is raised,
	 * 			is set to this oven
	 * 			|new.getOven() == oven
	 */
	
	public IllegalIngredientAdditionException(String message, Oven oven) {
		super(message); 
		this.oven = oven; 
	}
	
	/**
	 * Return the oven involved in the new illegal ingredient addition exception
	 *
	 * @return	oven
	 * 			The oven involved in the new illegal ingredient addition exception
	 */
	@Basic @Immutable
	public Oven getOven() {
		return oven; 
	}
	

}
