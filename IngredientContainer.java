/**
 * 
 */

/**
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 *
 */
import java.util.HashMap;
import java.util.Map;

import be.kuleuven.cs.som.annotate.*;
public class IngredientContainer {
	
	  /******************************************************
	    *constructor
	    ****************************************************/
	
	/**
	 * Initializes a new IngredientContainer with given AlchemicIngredient and capacity
	 * 
	 * @param		ingredient
	 * 		  		The AlchemicIngredient to be put in a container
	 * @param 		capacity
	 * 		  		The capacity of the given container 
	 * @effect 		if the quantity of a given AlchemicIngredient is valid, then 
	 * 		   		the quantity of the ingredient in the container will be set to this quantity
	 * 		   		| setQuantity(ingredient) 
	 * @effect 		if the capacity of the container is valid, then the 
	 * 		   		capacity of the container will be set to the new capacity
	 * 		   		| setCapacity(capacity)
	 * @effect 		if the state of the ingredient is a liquid, then the container will be for liquids
	 * 		   		if the AlchemicIngredient is a powder, the container will be set for powders.
	 * 		   		| setContainer(ingredient)
	 */


	  public IngredientContainer(AlchemicIngredient ingredient, int capacity){
		setCapacity(capacity); 
		setIngredient(ingredient); 
	    setQuantity(ingredient);
	    setContainer(ingredient); 
	  }
	  
	  /**
	   * Initializes a new IngredientContainer with given capacity but no contents
	   * 
	   * @param		capacity
	   * 			the capacity of the given container
	   * @effect	if the capacity for the container is valid, the capacity of the container
	   * 		 	will be set to the new capacity
	   * 		 	|setCapacity(capacity)
	   */

	  public IngredientContainer(int capacity){ 
	    setCapacity(capacity);
	  }
	  
	  /**************************************************
	   * Alchemic ingredient
	   ***************************************/
	  /**
	   * Variable referencing the alchemic ingredient
	   */
	  
	  private AlchemicIngredient ingredient = null;
	  
	  
	  /**
	   * Sets the AlchemicIngredient for this ingredient container
	   * 
	   * @param	ingredient
	   * 		The AlchemicIngredient present in this IngredientContainer. 
	   */
	  
	  private void setIngredient(AlchemicIngredient ingredient) {
		  this.ingredient = ingredient; 
	  }
	  
	  	
	  
		/**
		 * Return the alchemic ingredient in this ingredient container.
		 *
		 */
		
		@Basic @Raw
		public AlchemicIngredient getAlchemicIngredient() {
			return ingredient;
		}
	  
	  /**************************************************
	   * Quantity. 
	   ***************************************/

	 /**
	   * Variable referencing the quantity of AlchemicIngredient
	   */
	  
	  private int quantity = 0;
	  
	  
	 /**
	   * sets the quantity of AlchemicIngredient for this container
	   * 
	   *@param ingredient
	   *		An Alchemic Ingredient to put in the IngredientContainer
	   *
	   *@post The quantity of the alchemic ingredient for this container
	   *	  is set to the 
	   *
	   */
	  
	  private void setQuantity(AlchemicIngredient ingredient){
		  if(isQuantityNotGreaterThanCapacity(this.getContainerContents(), this.getCapacity())) {
			  this.quantity = ingredient.getQuantityInSpoons(); 
		  }
		  this.quantity = getCapacity(); 
	  }
	  
	  /**
	   * get the quantity of AlchemicIngredient in this container
	   * 
	   * @return the quantity of a certain ingredient in this container
	   * 		 |this.quantity
	   */
	  public int getContainerContents() {
		  return this.quantity; 
	  }
	  
	  /********************************************************
	   * Capacity
	   *****************************/
	  
	  /**
	    * Variable referencing the the capacity of this ingredient IngredientContainer
	    */
	  
	  private int capacity = 0;

	  /**
	    *Set the capacity of this ingredient container to the given capacity
	    *
	    *@param		capacity 
	    *			the capacity of this ingredient container
	    *
	    *@post  	If the capacity for the container is valid,
	    *			the capacity for the container will be set to the given
	    *			capacity, otherwise the capacity will be set to zero.
	    *			| if(isValidCapacity(capacity)
	    *				then new.getContainerCapacity().equals(capacity)
	    *				else new.getContainerCapacity().equals(capacity)
	    */

	  private void setCapacity(int capacity){
	    if(isValidCapacity(capacity)){
	      this.capacity = capacity;
	    }
	    this.capacity = 0; 
	  }

	  /**
	  Variable referencing the highest possible value for setCapacity
	  */

	  private static int maximumCapacity = Integer.MAX_VALUE; 

	  /**
	   * Checks whether the capacity of the ingredient container is valid
	   * @param capacity
	   * 		the capacity of the given ingredient container
	   * @return True if the capacity is zero or a positive number,
	   * 		 false if the capacity is a negative number or larger than
	   * 		 the maximum allowed capacity
	   * 		| if((capacity >= 0) && (capacity < maximumCapacity))
	   * 		| then True
	   * 		
	   */

	  public static boolean isValidCapacity(int capacity){
		  return((capacity >= 0) && (capacity < maximumCapacity));
	  }
	  /**
	   * 
	   * @param quantity
	   * 		The quantity of the AlchemicIngredient to be put in the container
	   * @param capacity
	   * 		The capacity of the container
	   * @return True if the capacity is equal to or larger than the quantity.
	   * 		| if(capacity >= quantity)
	   * 		| then True
	   */
	  
	  public static boolean isQuantityNotGreaterThanCapacity(int quantity, int capacity) {
		  return(capacity >= quantity); 
	  }
	  
	  /**
	   * 
	   * Get the capacity of this container 
	   * 
	   * @return
	   */
	  public int getCapacity() {
		  return this.capacity; 
	  }
	  /**********************************************************************
	   * Container
	   ************************************/
	  
	  /**
	   * Create a map with integer keys and string values to ascribe the right container to a certain AlchemicIngredient,
	   * if the specific AlchemicIngredient.getState() == "Liquid".
	   * 
	   * @return A map with as keys the maximum quantity in spoons the corresponding IngredientContainers can carry
	   * 		 and as values the name of the IngredientContainer, in correspondence with the unit of the AlchemicIngredient. 
	   */
	  
	  private Map<Integer, String> liquidContainersAndContents(){
		  /* AlchemicIngredient.getUnit() == "vial" can contain a maximum amount of 5 spoons, so if 
		   * AlchemicIngredient.getQuantityInSpoons > liquidContainersAndContents.getValue(5), the
		   * AlchemicIngredient will not fit in a vial*/
		  Map<Integer, String> liquidContainersAndContents = new HashMap<Integer, String>(); 
		  liquidContainersAndContents.put(5, "glass vial");
		  liquidContainersAndContents.put(15, "glass bottle");
		  liquidContainersAndContents.put(105, "metal jug"); 
		  liquidContainersAndContents.put(1260, "blue barrel");
		  liquidContainersAndContents.put(3600, "storeroom"); 
		  return liquidContainersAndContents; 
	  }
	  
	  /**
	   * Create a map with integer keys and string values to ascribe the right container to a certain AlchemicIngredient,
	   * if the specific AlchemicIngredient.getState() == "Powder".
	   * 
	   * @return A map that has as keys the maximum quantity in spoons that can be carried by the corresponding values of the map.
	   * 		The values are the names of the IngredientContainers, in correspondence with the the unit of the AlchemicIngredient.
	   */
	  
	  private Map<Integer, String> powderContainersAndContents(){
		  Map<Integer, String> powderContainersAndContents = new HashMap<Integer, String>();
		  /*Maximum 7 spoons in a sachet as unit of measurements, therefore only seven spoons in a sachet as a
		   * physical item.*/
		  powderContainersAndContents.put(7, "felt sachet");
		  powderContainersAndContents.put(36, "wooden box");
		  powderContainersAndContents.put(108, "leather sack"); 
		  powderContainersAndContents.put(1080, "iron chest");
		  powderContainersAndContents.put(3600, "storeroom"); 
		  return powderContainersAndContents; 
	  }
	  
	  /**
	   * Determine the right IngredientContainer for an AlchemicIngredient, based on the state of the ingredient
	   * and its quantity measured in spoons. 
	   * 
	   * 
	   * @param ingredient
	   * 		The AlchemicIngredient for which a container is sought and given. 
	   * @return The IngredientContainer for the AlchemicIngredient. 
	   */
	  
	  private String determineRightContainer(AlchemicIngredient ingredient) {
		  String container = null;
		  if(ingredient.getState().equals("Liquid")) {
			  Map<Integer, String> map = liquidContainersAndContents(); 
			  for(Map.Entry<Integer, String> entry : map.entrySet()) {
				  if((ingredient.getQuantityInSpoons() % entry.getKey()) == 0) {
					  container = entry.getValue(); 
				  }
			  }
		  }
		  Map<Integer, String> map = powderContainersAndContents(); 
		  for(Map.Entry<Integer, String> entry : map.entrySet()) {
			  if((ingredient.getQuantityInSpoons() % entry.getKey()) == 0) {
				  container = entry.getValue(); 
				  	
			  }
		  }
		  
		  return container;  
	  }
	  
	  /**
	   * Variable reference the container for the ingredient in this container
	   */
	  
	  private String container = null; 
	  
	  /**
	   * Sets the container type for an AlchemicIngredient according to the state of the ingredient
	   * 
	   * @param ingredient
	   * 		The AlchemicIngredient to be put in the container
	   * @post  if the AlchemicIngredient is a liquid, the container will be one of the liquid
	   * 		containers like spoon, vial, bottle,… If the AlchemicIngredient is a powder, the container
	   * 		will be one of the powder containers like sachet, sack, chest…
	   * 
	   */
	  
	  private void setContainer(AlchemicIngredient ingredient) {
		  this.container = determineRightContainer(ingredient); 
		 
	  }
	  
	  /**
	   * Get the container unit for the ingredient in this container
	   * 
	   * @return the unit of this ingredient 
	   */
	  
	  public String getContainer() {
		  return this.container; 
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
