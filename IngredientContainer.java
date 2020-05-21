import java.util.HashMap;
import java.util.Map;

public class IngredientContainer {
	
	/**********************************************************
     * Constructors
     **********************************************************/
	/**
	 * Initialize a new ingredient container with a given alchemicIngredient, capacity amount, capacity unit, capacity state. 
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
	 * 
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
	 * @throws 	IllegalStateException
	 *         	The given alchemic ingredient has not the same state as the ingredientContainer
	 *         	| !isValidState()
	 * @throws 	IllegalAmountException
	 *         	The amount of the given alchemic ingredient is too big for this ingredientContainer
	 *         	| !isValidAmount()
	 */
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
		return this.capAmount;
	}
	
	/*controleren of de hoeveelheid van alchemicIngredient volledig in hoeveelheid van capacity kan, eerst alles omzetten in spoons om zo te controleren*/
	
	public boolean isValidAmount() {
		return (this.getAlchemicIngredient().getQuantityInSpoons() <= this.getQuantityCapInSpoons()); 
	}
	
	/**
	 * Set the capacity amount of this ingredientContainer to the given capacity amount.
	 *
	 * @param 	capAmount
	 * 			The new capacity amount for this ingredientContainer.
	 * 
	 * @throws 	IllegalCapacityException
	 *         	The given capacity amount for this ingredientContainer is not valid
	 *         	| !isValidCapacity()
	 */
	private void setCapAmount(int capAmount) throws IllegalCapacityException {
		if (!isValidCapacity()){
			throw new IllegalCapacityException("The capacity is illegal");
		}
		else {
			this.capAmount = capAmount;
		}
	}
	
	/**********************************************************
     * capacity unit
     **********************************************************/
	
	private String capUnit = null;
	
	public String getCapUnit() {
		return this.capUnit;
	}
	
	private void setCapUnit(String capUnit) throws IllegalCapacityException {
		if (!isValidCapacity()){
			throw new IllegalCapacityException("The capacity is illegal");
		}
		else {
			this.capUnit = capUnit;
		}
	}
	
	
	/**********************************************************
     * capacity state
     **********************************************************/
	
	private String capState = null;
	
	public String getCapState(){
		return this.capState;
	}
	
	/*Controleer of zowel de state van ingredient als state van capacity gelijk zijn, want het kan niet zijn dat je een liquid wil stockeren in een capacity voor powders*/
	/*indien geen validstate throw error bij setAlchemicIngredient*/
	public boolean isValidState() {
		return (this.getAlchemicIngredient().getState() == this.getCapState());
	}
	
	/*legale containers voor liquid*/
	private static Map<String,Integer> legalCapacityLiquid = new HashMap<String,Integer>(){
		{
			put("spoon",1);
			put("vial",1);
			put("bottle",1);
			put("jug",1);
			put("barrel",1);
		}
	};
	
	/*legale containers voor powders*/
	private static Map<String,Integer> legalCapacityPowder = new HashMap<String,Integer>(){
		{
			put("spoon",1);
			put("sachet",1);
			put("box",1);
			put("sack",1);
			put("chest",1);
		}
	};
	
	/*controleren of het een geldig container is, dus bijvoorbeel voor liquid dat het enkel [1 spoon, 1 vial, 1 bottle, 1 jug, 1 barrel] kan zijn*/
	
	public boolean isValidCapacity() {
		if (this.getCapState() == "Liquid") {
			/*kijk of je capaciteit in de legale lijst van legalCapacityLiquid zit*/
			for (Map.Entry<String,Integer> entry: legalCapacityLiquid.entrySet()) {
				if (entry.getKey() == this.getCapUnit()) {
					if(entry.getValue() == this.getCapAmount()) {
						return true;
					}
				}
			}
		}
		else {
			for (Map.Entry<String,Integer> entry: legalCapacityPowder.entrySet()) {
				if (entry.getKey() == this.getCapUnit()) {
					if(entry.getValue() == this.getCapAmount()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	

	
	/*zet de amount van je capacity van constructor om in spoons*/
	public int getQuantityCapInSpoons() {
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
	


	/*zet de mogelijke capaciteiten van liquid in spoons*/
	private static Map<String,Integer> liquidCapInSpoons = new HashMap<String,Integer>(){
		{
			put("vial",5);
			put("bottle",15);
			put("spoon",1);
			put("jug",105);
			put("barrel",1260);
		}
	};
	
	/*zet de mogelijke capaciteiten van powder in spoons*/
	private static Map<String,Integer> powderCapInSpoons = new HashMap<String,Integer>(){
		{
	
			put("sachet",7);
			put("box",42);
			put("sack",126);
			put("chest",1260);
			put("spoon",1);
		}
	};
	
	
	private void setCapState(String capState) {
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
