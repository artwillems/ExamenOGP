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
	
	/**
	 * Variable referencing the ingredientList of this device
	 */
    protected List<AlchemicIngredient> ingredientList = new ArrayList<AlchemicIngredient>();
    
    
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
	
	
	/**
	 * Set the ingredientList of this device
	 * @param 	ingredientList
	 * 			The new ingredientList for this device
	 * @post 	if the ingredientList is a valid list, the new ingredientList is set to the given ingredientList
	 * 			| if (isValidInput(ingredientList)
	 * 			| 	then new.getIngredientList() == ingredientList
	 * @throws 	InvalidIngredientListException("There are no ingredients put in this device",this)
	 * 			The ingredientList is invalid
	 * 			| ! isValidInput(ingredientList)
	 */
    private void setContainerList(List<AlchemicIngredient> ingredientList) throws InvalidIngredientListException {
    	if (!isValidInput(ingredientList)) {
    		throw new InvalidIngredientListException("There are no ingredients put in this device",this);
    	}
    	else {
    		this.ingredientList = ingredientList;
    	}
    }
    
    /**
     * Check whether the given ingredientList is a valid ingredient list for this device
     * @param 	ingredientList
     * 			The ingredientList to be checked
     * @return	true if and only if the ingredientList is smaller than or equal to one 
     * 			and does not contain any null values.
     * 			| result == (ingredientList.size() <= 1 && !ingredientList.contains(null))
     */
	public boolean isValidInput(List<AlchemicIngredient> ingredientList) {
		return (ingredientList.size() <= 1 && !ingredientList.contains(null));
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
		/*remove device from laboratory   overwrite in subclasses*/
		setLaboratory(laboratory);
		
	}
	
	/**********************************************************
     * Methods
     **********************************************************/
	
	/**
	 * Add an ingredient from a container to this device
	 * 
	 * @param 	container
	 * 			The container containing the AlchemicIngredient
	 * @post	If there isn't already an ingredient stored in this device and the given ingredient is stored in the same labo as this device
	 * 			the ingredient is added to the device, removed from the storage of his laboratory and his container is deleted.
	 * 			| if (this.countIngredients() > 1) and (haveSameLaboratory(container.getAlchemicIngredient()))
	 * 			|	then ingredientList.add(container.getAlchemicIngredient()
	 * 			|		 this.laboratory.removeIngredient(container.getAlchemicIngredient())
	 * 			|		 container.setDelete(true)
	 * @throws 	IllegalIngredientAdditionException("The device allow only one alchemic ingredient",this)
	 * 			There already is an ingredient in this device
	 * 			| this.countIngredients > 1
	 * @throws 	DifferentLaboratoryException("The device and the ingredient have to be stored in the same laboratory.",this);
	 * 			The ingredient and device have different laboratory
	 * 			| !haveSameLaboratory(container.getAlchemicIngredient())
	 */
	public void addIngredientFrom(IngredientContainer container) throws IllegalIngredientAdditionException, DifferentLaboratoryException{
		if (this.countIngredients() > 1) {
    		throw new IllegalIngredientAdditionException("The device allows only one alchemic ingredient",this);
    	}
		else {
			if (haveSameLaboratory(container.getAlchemicIngredient())) {
				ingredientList.add(container.getAlchemicIngredient());
				this.laboratory.removeIngredient(container.getAlchemicIngredient());
				container.setDelete(true);
			}
			else {
				throw new DifferentLaboratoryException("The device and the ingredient have to be stored in the same laboratory.",this);
			}
		}

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
	
	
	/**
	 * Remove the result of a device after the operation has been executed.
	 * 
	 * @return	if the device contains one AlchemicIngredient, a container containing the result AlchemicIngredient is returned.
	 * 			| if (ingredientList.size() == 1)
	 * 			|	then IngredientContainer newContainer = new IngredientContainer(ingredientList.get(0),1,ingredientList.get(0).determineCapUnit(),ingredientList.get(0).getState());
	 *			|		 result == newContainer
	 * @post	After the ingredient has been put in a container, the ingredientList of this device is emptied.
	 * 			| ingredientList.clear()	
	 * @throws 	IllegalResultException(There can only be returned one total result from the device")
	 * 			This device has more than one ingredient or no ingredient to return
	 * 			| ! (ingredientList.size() == 1)
	 * 
	 */
	public IngredientContainer removeAlchemicResult() throws IllegalResultException {
		if (ingredientList.size() == 1 ) {
			IngredientContainer newContainer = new IngredientContainer(ingredientList.get(0),1,ingredientList.get(0).determineCapUnit(),ingredientList.get(0).getState());
			ingredientList.clear();
			return newContainer;
		}
		else {
			
			throw new IllegalResultException("There can only be returned one total result from the device");
		}
	}
	
	
	/**
	 * Execute the alchemicOperation of this device
	 * 
	 * @throws 	NoIngredientInDeviceException("There is no ingredient in this device",this)
	 * 		  	The device does not have an ingredient
	 * 			| ! (this.countIngredients() == 1)
	 */	
	public void executeAlchemicOperation() throws NoIngredientInDeviceException {
		if (! (this.countIngredients() == 1)) {
			throw new NoIngredientInDeviceException("There is no ingredient in this device",this);
		}
		
	}
	
	

	
	
	
	
}



















