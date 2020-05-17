import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * A class for signaling the illegal action of using a Device in a Laboratory which is not present.
 * 
 * @author Jérôme D'hulst, Art Willems, Marie Levrau
 *
 */
public class NoSuchInLabDeviceException extends RuntimeException{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 456678L; 
	
	/**
	 * Variable referencing the laboratory for which the new exception is thrown
	 */
	
	private final Laboratory lab; 
	
	/**
	 * 
	 * @param	message
	 * 			The message to be returned to the user notifying of the illegal action
	 * @param	labo
	 * 			The laboratory for which the new no such device in lab exception is thrown. 
	 * @post	The message to be returned to the user is set to the given message. 
	 * 			|super message
	 * @post	The laboratory involved in the new no such ingredient exception is set to the given laboratory
	 * 			|new.getLab() == lab
	 */
	
	public NoSuchInLabDeviceException(String message, Laboratory lab) {
		super(message);
		this.lab = lab; 
	}
	
	/**
	 * @return the laboratory involved in the new no such in lab device exception
	 */
	@Basic @Raw
	public Laboratory getLab() {
		return lab; 
	}
}
