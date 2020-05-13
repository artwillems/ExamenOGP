import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;


/**
 * A class for signaling that the ingredientList is not valid
 */

/**
 * 
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 *
 */

public class InvalidIngredientListException extends RuntimeException{
	
	/**
	 * Variable referencing the version number for the serial class invoked upon. 
	 */
	private final static long serialVersionUID = 3L; 
	
	/**
	 * Variable referencing the device for which the exception is thrown
	 */
	private final Device device;

	/**
	 * Initialize a new invalid ingredient list exception for the given device.
	 * 
	 * @param 	message
	 * 			The error message that will be given to the user.
	 * @param 	device
	 * 			The device for which the new invalid ingredient list exception is thrown
	 * @post	The message for the new invalid ingredient list exception is set to the given message
	 * 			| super(message)
	 * @post	The new device is set to the device for which the new exception is thrown
	 * 			| new.getDevice() == device
	 */
	public InvalidIngredientListException(String message, Device device) {
		super(message);
		this.device = device;
	}
	
	/**
	 * Return the Device involved in this new invalid ingredient list exception
	 */
	@Basic @Immutable
	public Device getDevice() {
		return this.device;
	}
}
