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
	 * Variable referencing the device for which the illegal action was taken. 
	 */
	
	private final Device device; 
	
	/**
	 * Initializes the device involved in the new illegal ingredient addition exception.
	 * 
	 * @param 	message
	 * 			A message that informs to user of his illegal action
	 * @param 	device
	 * 			The device involved in the new illegal ingredient addition exception
	 * @post	A message containing information about the illegal action is given to the user.
	 * 			|super(message) 
	 * @post	The device for which the new illegal ingredient addition exception is raised,
	 * 			is set to this device
	 * 			|new.getDevice() == device
	 */
	
	public IllegalIngredientAdditionException(String message, Device device) {
		super(message); 
		this.device = device; 
	}
	
	/**
	 * Return the device involved in the new illegal ingredient addition exception
	 *
	 * @return	device
	 * 			The device involved in the new illegal ingredient addition exception
	 */
	@Basic @Immutable
	public Device getDevice() {
		return device; 
	}
	

}
