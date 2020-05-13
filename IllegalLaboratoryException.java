import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling the illegal action moving a device to an invalid laboratory. 
 */

/**
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 * @version 1.8 
 */
public class IllegalLaboratoryException extends RuntimeException{
	
	/**
	 * Variable refencing the version number of the serial class it is invoked upon. 
	 * 
	 */
	
	private static final long serialVersionUID = 2L; 
	
	/**
	 * Variable referencing the device which was intended to use without any substance. 
	 */
	
	private final Device device; 
	
	/**
	 * Initializes the device involved in the new illegal laboratory exception
	 * 
	 * @param	message
	 * 			The message that is returned to the user, containing
	 * 			information about the illegal action		
	 * @param	device
	 * 			The device regarding the new illegal laboratory exception
	 * @post	The message involving the new illegal laboratory exception
	 * 			is set to the message as stated in device
	 * 			|super(message)
	 * @post	The device for the new illegal laboratory exception is set
	 * 			to the given device.
	 * 			|new.getDevice() == device
	 */
	
	public IllegalLaboratoryException(String message, Device device) {
		super(message); 
		this.device = device; 
	}
	
	/**
	 * Get the device involved in the new illegal laboratory exception
	 * 
	 * @return	device
	 * 			The device involved in the new exception. 
	 */
	@Basic @Immutable
	public Device getDevice() {
		return this.device; 
	}
	
	

}
