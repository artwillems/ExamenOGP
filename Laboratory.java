/**
 * A class laboratory for doing Alchemic procedures on AlchemicIngredients with a certain Device
 */
import be.kuleuven.cs.som.annotate.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author willemsart, Jérôme D'hulst en Marie Levrau
 *
 */

public class Laboratory{
	
	/**************************************
	 * Constructors
	 *********************/
	
	/**
	 * 
	 * Initializes a new laboratory with a given storeroom capacity, list of ingredients and devices
	 * 
	 * @param capacity
	 * 		  The capacity of the laboratory. 
	 * @param listOfIngredients
	 * 		  All the ingredients present in the laboratory at time of creation.
	 * @param listOfDevices
	 * 		  All the devices present in the laboratory at time of creation.
	 * 
	 * @effect if the capacity is valid, the capacity of this laboratory will be set to this capacity
	 * 		   |setLabCapaCity(capacity) 
	 * @effect the ingredients present in this laboratory are set to the list of ingredients
	 * 		   |setIngredients(listOfIngredients)
	 * @effect the devices present at the laboratory will be set the list of devices
	 * 		   |setDevices(listOfDevices)
	 * 
	 */
	
	public Laboratory(long capacity, ArrayList<AlchemicIngredient> listOfIngredients, ArrayList<Device> listOfDevices) {
		setLabCapacity(capacity); 
		setIngredients(listOfIngredients); 
		setDevices(listOfDevices); 	
	}
	
	/****************************************
	 * Capacity
	 *************************/
	
	private long capacity = 0; 
	
	/******
	 * 
	 * Set the capacity for this laboratory expressed in storerooms
	 *  
	 * @param capacity
	 * 		  The capacity for this laboratory in storerooms 
	 */
	
	private void setLabCapacity(long capacity) {
		if(isValidLaboCapacity(capacity)) {
			this.capacity = capacity; 
		}
		
	}
	/**
	 * Give the capacity of this laboratory in storerooms
	 * 
	 * @return the capacity of this laboratory in storerooms
	 */
	
	public long getCapacity() {
		return this.capacity; 
	}
	
	/**
	 * Checks whether the capacity for this laboratory is greater than or equal to zero
	 * 
	 * @param capacity
	 * 		  The capacity for this laboratory
	 * @return True if the capacity is larger than or equal to zero.
	 * 		   | if(capacity >= 0)
	 * 		   | return true
	 */
	
	private boolean isValidLaboCapacity(long capacity) {
		return(capacity >= 0); 
	}
	
	/************************************
	 * Ingredients
	 ********************************/
	
	/**
	 * Variable referencing all the ingredients present in the laboratory
	 */
	
	private List<AlchemicIngredient> listOfIngredients = null;
	
	/**
	 * Set a collection of ingredients for this laboratory
	 * 
	 * @param listOfIngredients
	 * 
	 * @post the ingredients present at the laboratory become the new list of ingredients
	 */
	
	private void setIngredients(ArrayList<AlchemicIngredient> listOfIngredients) {
		this.listOfIngredients = listOfIngredients; 
	}
	
	/**
	 * Give a list of all ingredients present at the laboratory. 
	 * 
	 * @return the list of ingredients
	 */
	
	private List<AlchemicIngredient> getIngredients(){
		return this.listOfIngredients; 
	}
	
	/**************************************************
	 * Devices 
	 **************************/
	
	/**
	 * Variable referencing the list of devices for this laboratory
	 */
	
	private List<Device> listOfDevices = null; 
	
	/**
	 * 
	 * Set the devices to be used in the laboratory 
	 * 
	 * @param listOfDevices
	 * 		  All the devices that are stored in the laboratory. 
	 */
	
	private void setDevices(ArrayList<Device> listOfDevices) {
		if(areThereNoDoubleDevices(listOfDevices)) {
			this.listOfDevices = listOfDevices; 
		} 
		
	}
	
	
	/**
	 * 
	 * Checks whether there are no duplicate devices present at the laboratory
	 * 
	 * @param listWithDevices
	 * 		
	 * @return True if there are no double devices present in the laboratory.
	 */
	
	public static boolean areThereNoDoubleDevices(ArrayList<Device> listWithDevices) {
		Set<Device> setOfDevices = new HashSet<Device>(listWithDevices);
		int setLength = setOfDevices.size(); 
		int listLength = listWithDevices.size(); 
		return (listLength == setLength); 
	}
	
	/**
	 * Creates a new container of a given amount
	 * 
	 * @param amount
	 * 		  The capacity for this new container
	 * @return a new container of given capacity 
	 */
	
	public IngredientContainer createContainer(long amount) {
		IngredientContainer newContainer = new IngredientContainer(amount); 
		return newContainer; 
	}
	
	
	public void storeIngredient(IngredientContainer container) {
		
		
	}
	
	private Map<String, Long> catalogue = new HashMap<String, Long>(); 
	
	
	/**
	 * Get a certain amount of AlchemicIngredient from this laboratory and put it in its
	 * appropriate container
	 * 
	 * @param ingr
	 * 		  The AlchemicIngredient to be taken from this laboratory
	 * @param amount
	 * 		  The amount of AlchemicIngredient to be taken from this laboratory
	 * @return a new IngredientContainer with the requested ingredient in its appropriate container
	 */
	
	/* de juiste container zal meegeven worden via setContainer in class IngredientContainer*/
	
	public IngredientContainer getAmountFromLabo(AlchemicIngredient ingr, long amount) {
		IngredientContainer aContainer = new IngredientContainer(ingr, amount); 
		return aContainer; 
	}
	
	/**
	 * Get the full amount of a specific ingredient stored in various places and containers within this laboratory
	 * NOTE: deze getter moet nog throwen, exception nog niet geschreven
	 * 
	 * @param ingredient
	 * 		  The AlchemicIngredient of which the full amount must be known
	 * @return The full amount of the given ingredient within this laboratory
	 */
	
	public int getFullAmountFromLabo(AlchemicIngredient ingredient) {
		List<AlchemicIngredient> allIngredients = this.getIngredients(); 
		if(allIngredients.contains(ingredient)) {
			return 0; 
		}
		return 0; 
	}
	

}
