import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import filesystem.DiskItem;


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
     * Alchemic Ingredient
     **********************************************************/
	
	
	/**
	 * Variable referencing a list collecting all alchemic ingredients that are in this device.
	 * 
	 * @invar ingredientList references an effective list. 
	 *        | alchemicIngredients != null
	 */	
	private final List<AlchemicIngredient> ingredientList = new ArrayList<AlchemicIngredient>();
	
	/**
	 * Checks whether the ingredientList is a valid list.
	 * @param 	IngredientList
	 * 			The list to be checked.
	 * @return	True if and only if the ingredient list contains maximum one ingredient.
	 * 			| result ==
	 * 			|	IngredientList.size() <=1
	 */
	public boolean isValidIngredientList(List<AlchemicIngredient> IngredientList) {
		return (IngredientList.size() <= 1);
	}
	
	/**
	 * Return the list of Alchemic Ingredients of this device.
	 */
	public List<AlchemicIngredient> getIngredientList(){
		return ingredientList;
	}
	
	
	
	
	/**
	 * Return the number of ingredients in this device.
	 */
	@Basic @Raw 
	public int countIngredients() {
		return ingredientList.size();
	}
	
	
	
	/**
	 * Variable referencing a list collecting all quantities of the alchemic ingredients that are in this device.
	 * 
	 * @invar quantityList references an effective list. 
	 *        | quantities != null
	 */	
	private final List<Integer> quantityList = new ArrayList<Integer>();
    
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
			throw new IllegalLaboratoryException("This device cannot be placed in the given laboratory!");
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
    		throw new IllegalIngredientAdditionException("The device allows only one alchemic ingredient");
    	}
		else {
			if (haveSameLaboratory(container.getAlchemicIngredient())) {
				ingredientList.add(container.getAlchemicIngredient());
				quantityList.add(container.getContainerContents());
				container.setDelete(true);
			}
			else {
				throw new DifferentLaboratoryException(this);
			}
		}

	}
	
	public boolean haveSameLaboratory(AlchemicIngredient ingredient) {
		return (getLaboratory() == ingredient.getLaboratory());
	}
	
	/*NOG KIJKEN WAT IK BIJ newAlchemicIngredient zet, want dit moet resultaat zijn van de hele ingredientList*/
	public IngredientContainer removeAlchemicResult() {
		AlchemicIngredient newAlchemicIngredient = ;
		int newQuantity = this.sumOfQuantities();
		IngredientContainer newContainer = new IngredientContainer(newAlchemicIngredient,newQuantity);
		ingredientList.clear();
		quantityList.clear();
		return newContainer;
	}
	
	public int sumOfQuantities() {
		int sum = 0;
		for (int i = 0; i < quantityList.size(); i++) {
			  sum = sum + quantityList.get(i);
			}
		return sum;
	}

	/*HEEFT HET NUT DEZE IN SUPERCLASS TE ZETTEN? WANT BIJ IEDERE SUBCLASS ANDERS, EN HIER GEVEN WE NIKS VAN CODE IN*/
	public void executeAlchemicOperation() throws NoIngredientInDeviceException {
		if (this.countIngredients() < 1) {
			throw new NoIngredientInDeviceException("There is no ingredient in this device");
		}
		
	}
	
	

	
	
	
	
}



















