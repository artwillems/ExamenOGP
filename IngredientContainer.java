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

	  /* Ik heb nog geen idee hoe we het teruggeven van de "quantity" gaan noemen,
	  dit is een workaround.
	  */
	  private long quantity = 0;

	  private void setQuantity(AlchemicIngredient ingredient){

	  }

	  /**
	    * Variable referencing the the capacity of this ingredient IngredientContainer
	    */
	  
	  private long capacity = 0;

	  /**
	    *Set the capacity of this ingredient container to the given capacity
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

	  public static boolean isValidQuantity(int quantity){

	  }

}
