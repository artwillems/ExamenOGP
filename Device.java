import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;



/**
 * An abstract class of devices involving a laboratory that owns this device.
 * 
 * @invar	Each device must have a proper laboratory. 
 * 			| hasProperLaboratorium()
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/

public class Device {
	
	/**********************************************************
     * constructor
     **********************************************************/
	
	/**
	 * Initialize a new device with given laboratory that owns this device.
	 * @param 	laboratory
	 * 			The laboratory that owns this new device.
	 * @effect  This new device is moved to the given laboratory. 
	 * 			If the given laboratory is not valid, a default laboratory is set.
	 *          | moveTo(laboratory)    
	 * @post    The new device is not terminated.
	 *          | !new.isTerminated()      
	 */
	@Model @Raw
	protected Device(Laboratory laboratory) {
		setLaboratory(laboratory);
	}
	
	
	/**********************************************************
     * Type
     **********************************************************/
	
	/**
	 * Variable referencing the type of a device.
	 */
	private String type = null;
	
	/**
	 * Set the type of this device to the given type
	 * 
	 * @param 	type
	 * 			The new type for this device
	 * @post	The given type is registered as the type of this device
	 * 			| new.getType() == type
	 */
	protected void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Return the type of this device.
	 */
	protected String getType() {
		return type;
	}
	

	
	/**********************************************************
     * ingredientList
     **********************************************************/
	

    private List<AlchemicIngredient> ingredientList = new ArrayList<AlchemicIngredient>();
    
    
    public List<AlchemicIngredient> getIngredientList(){
		return ingredientList;
	}
    
    /**
	 * Return the number of ingredients that are put in this device.
	 */
	@Basic @Raw 
	public int countIngredients() {
		return ingredientList.size();
	}
	
    private void setContainerList(List<AlchemicIngredient> ingredientList) throws InvalidIngredientListException {
    	if (!isValidInput(ingredientList)) {
    		throw new InvalidIngredientListException("There are no ingredients put in this device");
    	}
    	else {
    		this.ingredientList = ingredientList;
    	}
    }
    
	public boolean isValidInput(List<AlchemicIngredient> ingredientList) {
		return (ingredientList.size() <= 1);
	}
	/**********************************************************
     * laboratory
     **********************************************************/
	
	/**
	 * An object of the class Laboratory that refers to the laboratory that owns this device.
	 */
	private Laboratory laboratory = null;
	
	
	
	/**********************************************************
     * termination
     **********************************************************/
	
	/**
	 * Variable registering whether or not this device has been terminated.
	 */
	private boolean isTerminated = false;



	/**
	 * Check whether this device is already terminated.
	 */
	@Raw @Basic
	public boolean isTerminated() {
		return isTerminated;
	}
	
	/**
	 * Check whether this device can be terminated.
	 * 
	 * @return	
	 */
	public boolean canBeTerminated(){
		return (!isTerminated() && ingredientList.isEmpty());
	}
	
	/**
	 * Terminate this device.
	 * 
	 * @post 	This device is terminated.
	 *       	| new.isTerminated()
	 * @throws 	IllegalStateException
	 * 		   	This device is not yet terminated and it can not be terminated.
	 * 		   	| !isTerminated() && !canBeTerminated()
	 */
	public void terminate() throws IllegalStateException {
		if(!isTerminated()){
			if (!canBeTerminated()) {
				throw new IllegalStateException("This device cannot be terminated");
			}
			this.isTerminated = true;
		}
	}	
	
	/**********************************************************
     * bidirection relation with laboratory
     **********************************************************/
	
	/**
	 * Return the laboratory that has this device.
	 *
	 */
	
	@Basic @Raw
	public Laboratory getLaboratory() {
		return laboratory;
	}
	
	
	/** 
	 * Check whether this device can have the given laboratory as
	 * its laboratory.
	 * 
	 * @param  	laboratory
	 *          The laboratory to check.
	 * @return  
	 */
	@Raw 
	public boolean canHaveAsLaboratory(Laboratory laboratory) {
		if (this.isTerminated())
			return (laboratory == null);
		else 
			return ( (laboratory != null) && (!this.isTerminated()));
	}
	
	/** 
	 * Check whether this device has a proper laboratory. 
	 * 
	 * @return  
	 */
	@Raw 
	public boolean hasProperLaboratory() {
		return (canHaveAsLaboratory(getLaboratory()) NOG DINGEN TOEVOEGEN);
	}
	
	@Raw
	private void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}
	
	/**
	 * Move this device into the given laboratory.
	 *
	 * @param   laboratory
	 *          The laboratory where this device is moved to.
	 * @post    This device is moved into the given laboratory
	 *          | new.getLaboratory() == laboratory
	 * @throws  IllegalLaboratoryException
	 *          This device cannot have the given laboratory as the laboratory where this device can move into.
	 *          | !canHaveAsLaboratory(laboratory)
	 * @throws 	IllegalStateException
	 * 			This device is already terminated
	 * 			| isTerminated()
	 * 
	 */
	@Raw @Model
	public void moveTo(Laboratory laboratory)
			throws IllegalLaboratoryException, IllegalStateException {
		if (isTerminated()) 
			throw new IllegalStateException("Device is terminated!");
		if (!canHaveAsLaboratory(laboratory)) {
			throw new IllegalLaboratoryException("This device cannot be placed in the given laboratory!",this);
		}
		setLaboratory(laboratory);
	}
	
	/**********************************************************
     * Methods
     **********************************************************/
	
	/**
	 * Add an ingredient into this device.
	 */
	public void addIngredientFrom(IngredientContainer container) throws IllegalIngredientAdditionException, DifferentLaboratoryException{
		if (this.countIngredients() > 1) {
    		throw new IllegalIngredientAdditionException("The device allows only one alchemic ingredient",this);
    	}
		else {
			if (haveSameLaboratory(container.getAlchemicIngredient())) {
				ingredientList.add(container.getAlchemicIngredient());
				container.setDelete(true);
			}
			else {
				throw new DifferentLaboratoryException("The device and the ingredient have to be stored in the same laboratory.",this);
			}
		}

	}
	
	public boolean haveSameLaboratory(AlchemicIngredient ingredient) {
		return (getLaboratory() == ingredient.getLaboratory());
	}
	
	/*NOG KIJKEN WAT IK BIJ newAlchemicIngredient zet, want dit moet resultaat zijn van de hele ingredientList*/
	public IngredientContainer removeAlchemicResult() throws IllegalResultException {
		if (ingredientList.size()>1) {
			throw new IllegalResultException("There can only be returned one total result from the device");
		}
		else {
			IngredientContainer newContainer = new IngredientContainer(ingredientList.get(0),1,ingredientList.get(0).determineCapUnit(),ingredientList.get(0).getState());
			ingredientList.clear();
			return newContainer;
		}
	}
	
	
	public void executeAlchemicOperation() throws NoIngredientInDeviceException {
		if (this.countIngredients() < 1) {
			throw new NoIngredientInDeviceException("There is no ingredient in this device",this);
		}
		
	}
	
	

	
	
	
	
}



















