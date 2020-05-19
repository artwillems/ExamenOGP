
/**
 * A class for signaling that the user wants to take out more than one AlchemicIngredient out of a specific device. 
 * 
 * @author Jérôme D'hulst, Art Willems, Marie Levrau 
 *
 */
public class IllegalResultException extends RuntimeException{
	
	/**
	 * 
	 */
	
	private final static long serialVersionUID = 89067L; 
	
	/**
	 * 
	 * @param	message
	 * 			The message that is returned to the user 
	 * @post	The message for this new illegal result exception is set to the message.
	 * 			|super(message)
	 */
	public IllegalResultException(String message){
		super(message); 
	}

}
