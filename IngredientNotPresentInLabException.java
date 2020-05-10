import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling the illegal action of retrieving an AlchemicIngredient which does not exist in the 
 * given laboratory at the time. 
 */

/**
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 * @version 1.8 
 */
public class IngredientNotPresentInLabException extends RuntimeException{
	
	/**
	 * Variable referencing the version number for the serial class invoked upon. 
	 */
	
	private final static long serialVersionUID = 3L; 
	
	/**
	 * Variable referencing the laboratory for which the exception is thrown
	 */
	
	private final Laboratory lab; 
	
	/**
	 * Initialize a new ingredient not present in lab exception for the given laboratory. 
	 * 
	 * @param	message
	 * 			The error message that will be given to the user. 
	 * 		
	 * @param	lab
	 * 			The laboratory for which the new ingredient not present exception is thrown.
	 * @post	The message for the new ingredient not present exception is set to the message in
	 * 			laboratory
	 * 			|super(message)
	 * @post	The new laboratory is set to the laboratory for which the new exception is thrown. 
	 * 			|new.getLab() == lab
	 */
	public IngredientNotPresentInLabException(String message, Laboratory lab) {
		super(message); 
		this.lab = lab; 
	}
	
	/**
	 * Return the laboratory involved in this new ingredient not present exception
	 * 
	 * @return The laboratory for the given new ingredient not present in lab exception. 
	 */
	@Basic @Immutable
	public Laboratory getLabo() {
		return this.lab; 
	}

}
