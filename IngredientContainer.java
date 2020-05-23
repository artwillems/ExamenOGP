import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
/**
 * A class of ingredient containers.
 *
 * @invar	Each ingredient container must have a valid capacity amount. 
 * 			| isValidAmount()
 * @invar	Each ingredient container must have a valid capacity unit.
 * 			| isValidCapUnit(String capUnit)
 * @invar	Each ingredient container must have a valid capacity state.
 * 			| isValidState
 * 
 * @author Jérôme D'hulst, Marie Levrau en willemsart
 *
 */
public class IngredientContainer {
	
	/**********************************************************
     * Constructors
     **********************************************************/
	/**
	 * Initialize a new ingredient container with a given alchemicIngredient, capacity amount, capacity unit and capacity state. 
	 * 
	 * @param 	ingredient
	 * 			The alchemic ingredient of the new ingredientContainer.
	 * @param 	capAmount
	 * 			The capacity amount of the new ingredientContainer.
	 * @param 	capUnit
	 * 			The capacity unit of the new ingredientContainer.
	 * @param 	capState
	 * 			The capacity state of the new ingredientContainer.
	 * 
	 * @effect	The alchemic ingredient is set to the given alchemic ingredient (must be valid)
	 * 			| setIngredient(ingredient)
	 * @effect	The alchemic ingredient is set to the given capacity amount (must be valid)
	 * 			| setCapAmount(capAmount)
	 * @effect	The alchemic ingredient is set to the given capacity unit (must be valid)
	 * 			| setCapUnit(capUnit)
	 * @effect	The alchemic ingredient is set to the given capacity state (must be valid)
	 * 			| setCapState(capState)
	 * */
	public IngredientContainer(AlchemicIngredient ingredient, int capAmount, String capUnit, String capState){
		setIngredient(ingredient);
		setCapAmount(capAmount);
		setCapUnit(capUnit);
		setCapState(capState);
	}
	
	/**
	 * Initialize a new ingredient container with a given capacity amount, capacity unit, capacity state. 
	 * 
	 * @param 	capAmount
	 * 			The capacity amount of the new ingredientContainer.
	 * @param 	capUnit
	 * 			The capacity unit of the new ingredientContainer.
	 * @param 	capState
	 * 			The capacity state of the new ingredientContainer.
	 * @effect	This new ingredientContainer is initialized with a capacity amount, capacity unit and capacity state.
	 * 			| this(null, capAmount, capUnit, capState);
	 * 
	 * */
	public IngredientContainer(int capAmount, String capUnit, String capState) {
		this(null, capAmount, capUnit, capState);
	}
	
	/**********************************************************
     * alchemic ingredient
     **********************************************************/
	
	/**
	 * Variable referencing the alchemic ingredient of this ingredientContainer.
	 */
	private AlchemicIngredient ingredient = null;
	
	/**
	 * Return the alchemic ingredient of this ingredientContainer.
	 */
	@Basic @Raw
	public AlchemicIngredient getAlchemicIngredient() {
		return ingredient;
	}

	/**
	 * Set the alchemic ingredient of this ingredientContainer to the given alchemic ingredient.
	 *
	 * @param 	ingredient
	 * 			The new alchemic ingredient for this ingredientContainer.
	 * 
	 * @throws 	IllegalStateException("The state of the alchemic ingredient is not the same state as the capacity")
	 *         	The given alchemic ingredient has not the same state as the ingredientContainer
	 *         	| !isValidState()
	 * @throws 	IllegalAmountException("The amount of alchemic ingredient is too big for this container")
	 *         	The amount of the given alchemic ingredient is too big for this ingredientContainer
	 *         	| !isValidAmount()
	 */
	@Model
	private void setIngredient(AlchemicIngredient ingredient) throws IllegalStateException, IllegalAmountException{
		if (!isValidState()){
			throw new IllegalStateException("The state of the alchemic ingredient is not the same state as the capacity");
		}
		else if (!isValidAmount()){
			throw new IllegalAmountException("The amount of alchemic ingredient is too big for this container");
		}
		else {
			 this.ingredient = ingredient; 
		}
		 
		}
	
	/**********************************************************
     * capacity amount
     **********************************************************/
	
	/**
	 * Variable referencing the capacity amount of this ingredientContainer.
	 */
	private int capAmount = 0;
	
	/**
	 * Return the capacity amount of this ingredientContainer.
	 */
	@Basic @Raw
	public int getCapAmount() {
		return capAmount;
	}
	
	
	/**
	 * Check whether this ingredientContainer can contain the amount of alchemic ingredient.
	 *
	 *@return True if the amount (in spoons) of the alchemic ingredient is less than or equal to the capacity amount (in spoons) of this ingredient container.
	 *		  | result ==
	 *		  | 	(this.getAlchemicIngredient().getQuantityInSpoons() <= this.getQuantityCapInSpoons())
	 */
	public boolean isValidAmount() {
		return (this.getAlchemicIngredient().getQuantityInSpoons() <= this.getAmountCapInSpoons()); 
	}
	
	/**
	 * Set the capacity amount of this ingredientContainer to the given capacity amount.
	 *
	 * @param 	capAmount
	 * 			The new capacity amount for this ingredientContainer.
	 * 
	 * @throws 	IllegalCapacityException("The capacity amount is illegal")
	 *         	The given capacity amount for this ingredientContainer is illegal
	 *         	| !isValidAmount()
	 */
	@Model
	private void setCapAmount(int capAmount) throws IllegalCapacityException {
		if (!isValidAmount()){
			throw new IllegalCapacityException("The capacity amount is illegal");
		}
		else {
			this.capAmount = capAmount;
		}
	}
	
	/**
	 * Returns the capacity amount of this ingredient container measured in the unit of spoons.
	 * 
	 * @return 	Return the capacity amount of this ingredient container in spoons using the Map with transitions depending on the state.
	 * 			If an ingredient is a Liquid then the transitions of the liquidCapInSpoons are used otherwise the transitions of 
	 * 			the powderCapInSpoons are used
	 * 			| result = this.getCapAmount()
	 * 			| if (getState() == "Liquid"
	 * 			|	then for every entry in liquidCapInSpoons
	 * 			|		if (entry.getKey() == getUnit())
	 * 			|			then result == result * entry.getValue()
	 * 			| else 
	 * 			|		for every entry in powderCapInSpoons
	 * 			|		if (entry.getKet() == getUnit())
	 * 			|			then result == result * entry.getValue()
	 * 		
	 */
	public int getAmountCapInSpoons() {
		int result = this.getCapAmount();
		if (getCapState()=="Liquid") {
			for (Map.Entry<String,Integer> entry: liquidCapInSpoons.entrySet()) {
				if (entry.getKey() == this.getCapUnit()) {
					result = result * entry.getValue();
				}
			}
		}
		else {
			for(Map.Entry<String, Integer> entry: powderCapInSpoons.entrySet()) {
				if (entry.getKey() == this.getCapUnit()) {
					result = result * entry.getValue();
				}
			}
		}
		return result;
	}
	


	/**
	 * This map states the different transitions between a liquid capacity unit and a spoon. 
	 */
	private static Map<String,Integer> liquidCapInSpoons = new HashMap<String,Integer>(){
		{
			put("vial",5);
			put("bottle",15);
			put("spoon",1);
			put("jug",105);
			put("barrel",1260);
		}
	};
	
	/**
	 * This map states the different transitions between a powder capacity unit and a spoon.
	 */
	private static Map<String,Integer> powderCapInSpoons = new HashMap<String,Integer>(){
		{
	
			put("sachet",7);
			put("box",42);
			put("sack",126);
			put("chest",1260);
			put("spoon",1);
		}
	};
	
	/**********************************************************
     * capacity unit
     **********************************************************/
	
	/**
	 * Variable referencing the capacity unit of this ingredientContainer.
	 */
	private String capUnit = null;
	
	/**
	 * Variable referencing the valid capacity units of ingredient containers for liquids.
	 */
	private static List<String> liquidCapUnits = new ArrayList<String>(Arrays.asList("spoon","vial","bottle","jug","barrel"));
	
	
	/**
	 * Variable referencing the valid capacity units of ingredient containers for powders.
	 */
	private static List<String> powderCapUnits = new ArrayList<String>(Arrays.asList("spoon","sachet","box","sack","chest"));
	
	/**
	 * Return the list of valid capacity liquid units.
	 */
	@Raw @Basic
	public static List<String> getLiquidCapUnits(){
		return liquidCapUnits;
	}
	
	/**
	 * Return the list of valid capacity powder units.
	 */
	@Raw @Basic
	public static List<String> getPowderCapUnits(){
		return powderCapUnits;
		
	}
	
	/**
	 * Return the capacity unit of this ingredientContainer.
	 */
	@Basic @Raw
	public String getCapUnit() {
		return capUnit;
	}
	
	/**
	 * Set the capacity unit of this ingredientContainer to the given capacity unit.
	 *
	 * @param 	capUnit
	 * 			The new capacity unit for this ingredientContainer.
	 * 
	 * @throws 	IllegalCapacityException("The capacity unit is illegal")
	 *         	The given capacity unit for this ingredientContainer is illegal
	 *         	| !isValidCapUnit(capUnit)
	 */
	@Model
	private void setCapUnit(String capUnit) throws IllegalCapacityException {
		if (!isValidCapUnit(capUnit)){
			throw new IllegalCapacityException("The capacity unit is illegal");
		}
		else {
			this.capUnit = capUnit;
		}
	}
	
	
	/**
	 * Check whether the given unit is a valid unit for this ingredient container depending
	 * on its state (powder or liquid).
	 * 
	 * @param 	capUnit
	 * 			The capacity unit to check.
	 * @return	True if this capacity unit is a powder unit and if the ingredient container is made for a powder or  
	 * 			if the capacity unit is a liquid unit and the ingredient is container is made for a liquid.
	 * 			| if (this.getCapState() == "Powder")
	 * 			|	then result == getPowderCapUnits().contains(capUnit)
	 * 			| 	else result == getLiquidCapUnits().contains(capUnit) 
	 * 
	 */
	@Raw
	public boolean isValidCapUnit(String capUnit) {
		if (this.getCapState() == "Powder") {
			return getPowderCapUnits().contains(capUnit);
		}
		else {
			return getLiquidCapUnits().contains(capUnit);
		}
	}
	
	
	
	/**********************************************************
     * capacity state
     **********************************************************/
	
	/**
	 * Variable referencing the capacity state of this ingredientContainer.
	 */
	private String capState = null;
	
	/**
	 * Return the capacity state of this ingredientContainer.
	 */
	@Basic @Raw
	public String getCapState(){
		return this.capState;
	}
	
	/**
	 * Check whether the state of this ingredientContainer is valid.
	 *
	 * @return True if the state of this ingredient container is the same as the alchemic ingredient.
	 *		   | result ==
	 *		   | 	(this.getAlchemicIngredient().getState() == this.getCapState())
	 */
	public boolean isValidState() {
		return (this.getAlchemicIngredient().getState() == this.getCapState());
	}
	
	/**
	 * Set the capacity state of this ingredientContainer to the given capacity state.
	 *
	 * @param 	capState
	 * 			The new capacity state for this ingredientContainer.
	 * 
	 * @throws 	IllegalStateException("The capacity state of this ingredient container is illegal")
	 *         	The given capacity state for this ingredientContainer is illegal
	 *         	| !isValidState()
	 */
	@Model
	private void setCapState(String capState) throws IllegalStateException{
		if (!isValidState()) {
			throw new IllegalStateException("The capacity state of this ingredient container is illegal");
		}
		this.capState = capState;
	}
	
	  
	  /**********************************************************************
	   * Delete
	   ************************************/
	  
	  /**
	   * Variable registering whether or not this ingredient container has been deleted.
	   */
	  private boolean isDeleted = false;

	  /**
	   * Check whether this ingredient container has been deleted.
	   * 
	   * @return	True if the ingredient container has been deleted.
	   * 			| isDeleted
	   */
	  public boolean isDeleted() {
	  	return isDeleted;
	  }

	  /**
	   * 
	   * @param 	isDeleted
	   * 			The new status of isDeleted.
	   * @post		The new given status to isDeleted is registered as the new status to isDeleted for 
	   * 			this ingredient container.
	   * 			| new.isDeleted() == isDeleted
	   */
	  @Model
	  public void setDelete(boolean isDeleted) {
	  	this.isDeleted = isDeleted;
	  }


	  /**
	   * Delete this ingredient container.
	   * 
	   * @effect	This ingredient container has been deleted,
	   * 			if the ingredient container has not already been deleted
	   * 			| if (this.isDeleted() == false)
	   * 			| then this.setDelete(true)
	   * @throws	AlreadyDeletedException(this)
	   *			The ingredient container has already been deleted.
	   *			| this.isDeleted() == true
	   */
	  public void delete() throws AlreadyDeletedException{
	  	if (this.isDeleted() == false) {
	      	this.setDelete(true);	
	  	}
	  	else {
	  		throw new AlreadyDeletedException(this);	
	  		
	  	}
	  }

	  
}
