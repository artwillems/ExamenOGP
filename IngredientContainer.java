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


	  public IngredientContainer(AlchemicIngredient ingredient, long capacity){
	    setQuantity(ingredient);
	    setCapacity(capacity);
	    setContainer(ingredient); 
	  }

	  public IngredientContainer(null, long capacity){ 
	    setCapacity(capacity);
	  }
	  
	  /**************************************************
	   * Quantity. 
	   ***************************************/

	 /**
	   * Variable referencing the quantity of AlchemicIngredient
	   */
	  
	  private long quantity = 0;
	  
	  
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
		  if(isQuantityNotGreaterThanCapacity(ingredient.getQuantity(), this.capacity)) {
			  this.quantity = ingredient.getQuantity(); 
		  }
		  this.quantity = this.capacity; 
	  }
	  
	  /********************************************************
	   * Capacity
	   *****************************/
	  
	  /**
	    * Variable referencing the the capacity of this ingredient IngredientContainer
	    */
	  
	  private long capacity = 0;

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

	  private void setCapacity(long capacity){
	    if(isValidCapacity(capacity)){
	      this.capacity = capacity;
	    }
	    this.capacity = 0; 
	  }

	  /**
	  Variable referencing the highest possible value for setCapacity
	  */

	  private static long maximumCapacity = Long.MAX_VALUE; 

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

	  public static boolean isValidCapacity(long capacity){
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
	  
	  public static boolean isQuantityNotGreaterThanCapacity(int quantity, long capacity) {
		  return(capacity >= quantity); 
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
	  private void setContainer(AlchemicIngredient ingredient) {
		 
	  }
	  
}
