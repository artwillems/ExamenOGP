/**
 * 
 */

/**
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 *
 */
public class IngredientContainer {
	
	  /******************************************************
	    *constructor
	    ****************************************************/
	
	/**
	 * Initializes a new IngredientContainer with given AlchemicIngredient and capacity
	 * 
	 * @param ingredient
	 * 		  The AlchemicIngredient to be put in a container
	 * @param capacity
	 * 		  The capacity of the given container 
	 * @effect if the quantity of a given AlchemicIngredient is valid, then 
	 * 		   the quantity of the ingredient in the container will be set to this quantity
	 * 		   | setQuantity(ingredient) 
	 * @effect if the capacity of the container is valid, then the 
	 * 		   capacity of the container will be set to the new capacity
	 * 		   | setCapacity(capacity)
	 * @effect if the state of the ingredient is a liquid, then the container will be for liquids
	 * 		   if the AlchemicIngredient is a powder, the container will be set for powders.
	 * 		   | setContainer(ingredient)
	 */


	  public IngredientContainer(AlchemicIngredient ingredient, int capacity){
		setCapacity(capacity);  
	    setQuantity(ingredient);
	    setContainer(ingredient); 
	  }
	  
	  /**
	   * Initializes a new IngredientContainer with given capacity but no contents
	   * 
	   * @param capacity
	   * 		the capacity of the given container
	   * @effect if the capacity for the container is valid, the capacity of the container
	   * 		 will be set to the new capacity
	   * 		 |setCapacity(capacity)
	   */

	  public IngredientContainer(int capacity){ 
	    setCapacity(capacity);
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
			  this.quantity = ingredient.getQuantity(); 
		  }
		  this.quantity = this.capacity; 
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
	    *@param capacity 
	    *		the capacity of this ingredient container
	    *
	    *@post  If the capacity for the container is valid,
	    *		the capacity for the container will be set to the given
	    *		capacity, otherwise the capacity will be set to zero.
	    *		| if(isValidCapacity(capacity)
	    *			then new.getContainerCapacity().equals(capacity)
	    *			else new.getContainerCapacity().equals(capacity)
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
	   * Sets the container type for an AlchemicIngredient according to the state of the ingredient
	   * 
	   * @param ingredient
	   * 		The AlchemicIngredient to be put in the container
	   * @post  if the AlchemicIngredient is a liquid, the container will be one of the liquid
	   * 		containers like spoon, vial, bottle,… If the AlchemicIngredient is a powder, the container
	   * 		will be one of the powder containers like sachet, sack, chest…
	   * 
	   */
	  
	  private String container = null; 
	  
	  private void setContainer(AlchemicIngredient ingredient) {
		  
		
	  }
	  
}
