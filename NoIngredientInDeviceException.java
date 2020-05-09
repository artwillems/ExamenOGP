import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling the illegal action of using a device without any ingredient. 
 */

/**
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 * @version 1.8 
 */
public class NoIngredientInDeviceException extends RuntimeException{
	
	/**
	 * Variable refencing the version number of the serial class it is invoked upon. 
	 * 
	 */
	
	private static final long serialVersionUID = 2L; 
	
	/**
	 * Variable referencing the oven which was intended to use without any substance. 
	 */
	
	private final Oven oven; 
	
	/**
	 * Initializes the oven involved in the new no ingredient in device 
	 * exception
	 * 
	 * @param	message
	 * 			The message that is returned to the user, containing
	 * 			information about the illegal action		
	 * @param	oven
	 * 			The oven regarding the new no ingredient in device exception
	 * @post	The message involving the new no ingredient in device exception
	 * 			is set to the message as stated in Oven
	 * 			|super(message)
	 * @post	The oven for the new no ingredient in device exception is set
	 * 			to the given oven.
	 * 			|new.getOven() == oven
	 */
	
	public NoIngredientInDeviceException(String message, Oven oven) {
		super(message); 
		this.oven = oven; 
	}
	
	/**
	 * Get the oven involved in the new no ingredient in device exception
	 * 
	 * @return	oven
	 * 			The oven involved in the new exception. 
	 */
	@Basic @Immutable
	public Oven getOven() {
		return this.oven; 
	}
	
	

}
