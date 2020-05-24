

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
 * @invar	Each device must have a valid ingredientList
 * 			| isValidInput(getIngredientList)
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/

public abstract class Device {
	
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
	
	/**
	 * Variable referencing the ingredientList of this device
	 */
    protected List<AlchemicIngredient> ingredientList = new ArrayList<AlchemicIngredient>();
    
    
    /**
     * Return the ingredientList of this device
     */
    @Basic @Raw
    public List<AlchemicIngredient> getIngredientList(){
    	return this.ingredientList;
    }
    
    
  /**
   * Check whether the given list is valid (abstract)
   * 
   * @param 	ingredientList
   * 			The ingredientList to be checked
   */
	public abstract boolean isValidInput(List<AlchemicIngredient> ingredientList) ;
		
	
    
    
	
	
    
    
	
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
	 * @throws 	IllegalStateException("This device cannot be terminated")
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
	 * @return  For every type of device check if the laboratory does not already own such
	 * 			device and if the laboratory exists
	 * 			| if (this.getType()=="Oven")
				|	then result == ( (laboratory != null) && (laboratory.getOven()==null));
				| else if (this.getType() == "CoolingBox")
				|	then result == ((laboratory != null) && (laboratory.getCoolingBox() == null));
				| else if (this.getType() == "Transmogrifier")
				|	then result == ((laboratory != null) && (laboratory.getTransmogrifier()==null));
				| else if (this.getType() == "Kettle")
				|	then result == ((laboratory != null) && (laboratory.getKettle()==null));
				| else
				| 	result == (laboratory != null);
	 */
	@Raw 
	public boolean canHaveAsLaboratory(Laboratory laboratory) {
		if (this.isTerminated())
			return false;
		else
			if (this.getType()=="Oven")
				return ( (laboratory != null) && (laboratory.getOven()==null));
			else if (this.getType() == "CoolingBox")
				return ((laboratory != null) && (laboratory.getCoolingBox() == null));
			else if (this.getType() == "Transmogrifier")
				return ((laboratory != null) && (laboratory.getTransmogrifier()==null));
			else if (this.getType() == "Kettle")
				return ((laboratory != null) && (laboratory.getKettle()==null));
			else
				return (laboratory != null);
		
	}
	
	/** 
	 * Check whether this device has a proper laboratory. 
	 * 
	 * @return  True if the laboratory of this device is not equal to null
	 * 			| result == (getLaboratory() != null)
	 */
	@Raw 
	public boolean hasProperLaboratory() {
		return (getLaboratory() != null);
	}
	
	/**
	 * Set the laboratory of this device
	 * @param 	laboratory
	 * 			The new laboratory of this device
	 * @effect	If the device can be moved to this laboratory, depending on the type of the device, the correct device is added to the laboratory.
	 * 			| if (canHaveAsLaboratory(laboratory)) {
	 *			| 	if (this.getType()=="Oven")
	 *			|		then laboratory.addOven((Oven) this);
	 *			|	else if (this.getType() == "CoolingBox")
	 *			|		then laboratory.addCoolingBox((CoolingBox) this);
	 *			|	else if (this.getType() == "Transmogrifier")
	 *			|		then laboratory.addTransmogrifier((Transmogrifier) this);
	 *			|	else  if (this.getType() == "Kettle") {
	 *			|		then laboratory.addKettle((Kettle) this);
	 * @post 	If the device can be moved to this laboratory, the new laboratory of this device is set to the given laboratory.
	 * 			| this.laboratory=laboratory
	 *	
	 * @throws 	IllegalLaboratoryException("This device cannot be placed in the given laboratory",this)
	 * 			This device can not be moved to the given laboratory
	 * 			| !canHaveAsLaboratory(laboratory)
	 */
	private void setLaboratory(Laboratory laboratory) throws IllegalLaboratoryException {
		if (canHaveAsLaboratory(laboratory)) {
			if (this.getType()=="Oven")
				laboratory.addOven((Oven) this);
			else if (this.getType() == "CoolingBox")
				laboratory.addCoolingBox((CoolingBox) this);
			else if (this.getType() == "Transmogrifier")
				laboratory.addTransmogrifier((Transmogrifier) this);
			else  if (this.getType() == "Kettle") {
				laboratory.addKettle((Kettle) this);
			}
			this.laboratory = laboratory;
		}
		
		else {
			throw new IllegalLaboratoryException("This device cannot be placed in the given laboratory",this);
		}
	
		/**
		 * Check whether an ingredient is stored in the same laboratory as this device
		 * 
		 * @param 	ingredient
		 * 			The ingredient to be checked
		 * @return	True if and only if the laboratories are equal.
		 * 			| result == (getLaboratory() == ingredient.getLaboratory())
		 */
		public boolean haveSameLaboratory(AlchemicIngredient ingredient) {
			return (getLaboratory() == ingredient.getLaboratory());
		}
		
		
		
	/**********************************************************
     * Methods
     **********************************************************/
	
	/**
	 * Add an ingredient from a container to this device (abstract)
	 * 
	 * @param 	container
	 * 			The container containing the ingredient to be added to this device.
	 */
	public abstract void addIngredientFrom(IngredientContainer container);
		
	
	
	
	

	/**
	 * Remove the result of a device after the operation has been executed.
	 * 
	 * @return	if the device contains one AlchemicIngredient and has not been terminated, a container containing the result AlchemicIngredient is returned.
	 * 			| 	if (!isTerminated())
	 * 			|	if (ingredientList.size() == 1)
	 * 			|	then IngredientContainer newContainer = new IngredientContainer(ingredientList.get(0),1,ingredientList.get(0).determineCapUnit(),ingredientList.get(0).getState());
	 *			|		 result == newContainer
	 * @post	After the ingredient has been put in a container, the ingredientList of this device is emptied.
	 * 			| ingredientList.clear()	
	 * @throws 	IllegalResultException("There can only be returned one total result from the device")
	 * 			This device has more than one ingredient or no ingredient to return
	 * 			| ! (ingredientList.size() == 1)
	 * @throws	IllegalStateException("This device has been terminated")
	 * 			This device is terminated
	 * 			| isTerminated()
	 * 
	 */
	public IngredientContainer removeAlchemicResult() throws IllegalResultException, IllegalStateException {
		
		if (!isTerminated()) {
			if (ingredientList.size() == 1 ) {
				IngredientContainer newContainer = new IngredientContainer(ingredientList.get(0),1,ingredientList.get(0).determineCapUnit(),ingredientList.get(0).getState());
				ingredientList.clear();
				return newContainer;
			}
			else {
			
				throw new IllegalResultException("There can only be returned one total result from the device");
			}
		}
		else {
			throw new IllegalStateException("This device has been terminated");
		}
	}
	
	
	/**
	 * Execute the alchemic operation. (abstract)
	 */
	public abstract void executeAlchemicOperation(); 
	
	
	
	

	
	
	
	
}



















