
/**
 * A class for signaling the illegal actions of retrieving an amount of AlchemicIngredient from given Laboratory
 * that is either exceeding the Laboratory capacity, or a negative number.
 *
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 * @version 1.8
 *
 */
public class InvalidLaboratoryAmountException extends RuntimeException {

	/**
	 * Variable referencing the version number of the serial class invoked upon.
	 */

	private static final long serialVersionUID = 45L;

	/**
	 * Variable referencing the laboratory for which the new amount of ingredient could not be added.
	 */

	private final Laboratory lab;

	/**
	 *
	 * @param	message
	 * 			The message that informs the user that the amount cannot be added
	 * @param	lab
	 * 			The laboratory for which the given invalid laboratory amount exception is thrown
	 * @post	The message to the user is set to the message
	 * 			|super(message)
	 * @post	The laboratory for which the exception is thrown is set to this laboratory.
	 * 			|new.getLab() == lab
	 */
	public InvalidLaboratoryAmountException(String message, Laboratory lab) {
		super(message);
		this.lab = lab;
	}

	/**
	 *
	 * @return the laboratory involved in the new invalid laboratory amount exception
	 */
	public Laboratory getLab() {
		return lab;
	}

}
