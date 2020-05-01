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
	  }

	  public IngredientContainer(null, long capacity){
	    setCapacity(capacity);
	  }
	  
	  /**************************************************
	   * Quantity. 
	   ***************************************/

	  /* Ik heb nog geen idee hoe we het teruggeven van de "quantity" gaan noemen,
	  dit is een workaround.
	  */
	  private long quantity = 0;

	  private void setQuantity(AlchemicIngredient ingredient){

	  }
	  
	  public static boolean isValidQuantity(int quantity){

	  }


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

	  private static max_capacity = long.MAX_VALUE; 



	  public static boolean isValidCapacity(long capacity){
	    return((capacity >= 0) && (capacity < max_capacity));
	  }

}
