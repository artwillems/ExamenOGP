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
	 * Initializes a new laboratory with a given store room capacity, list of ingredients and devices
	 * 
	 * @param 	capacity
	 * 		  	The capacity of the laboratory. 
	 * @param 	listOfIngredients
	 * 		  	All the ingredients present in the laboratory at time of creation.
	 * @param 	listOfDevices
	 * 		  	All the devices present in the laboratory at time of creation.
	 * 
	 * @effect 	if the capacity is valid, the capacity of this laboratory will be set to this capacity
	 * 		   	|setLabCapaCity(capacity) 
	 * @effect 	the ingredients present in this laboratory are set to the list of ingredients
	 * 		   	|setIngredients(listOfIngredients)
	 * @effect 	the devices present at the laboratory will be set the list of devices
	 * 		   	|setDevices(listOfDevices)
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
	 * @param	capacity
	 * 		  	The capacity for this laboratory in storerooms 
	 */
	
	private void setLabCapacity(long capacity) {
		if(isValidLaboCapacity(capacity)) {
			this.capacity = capacity; 
		}
		
	}
	
	/**
	 * Give the capacity of this laboratory in storerooms
	 * 
	 * @return	the capacity of this laboratory in storerooms
	 */
	
	public long getCapacity() {
		return this.capacity; 
	}
	
	/**
	 * Checks whether the capacity for this laboratory is greater than or equal to zero
	 * 
	 * @param	capacity
	 * 		  	The capacity for this laboratory
	 * @return 	True if the capacity is larger than or equal to zero.
	 * 		   	| if(capacity >= 0)
	 * 		  	| return true
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
	 * @param	listOfIngredients
	 * 
	 * @post	the ingredients present at the laboratory become the new list of ingredients
	 */
	
	private void setIngredients(ArrayList<AlchemicIngredient> listOfIngredients) {
		if(!areThereDoubleIngredients(listOfIngredients)) {
			this.listOfIngredients = listOfIngredients; 
		}
		this.listOfIngredients = null; 
	}
	
	/**
	 * Checks if the different ingredient types in the laboratory are unique, meaning there is not two times
	 * the same AlchemicIngredient
	 * 
	 * @param	theIngredients
	 * 		  	The list of all the ingredients that are put into this laboratory
	 * @return 	True if some ingredientType does not appear more than once in the list of ingredients, false if otherwise.
	 */
	
	private boolean areThereDoubleIngredients(ArrayList<AlchemicIngredient> theIngredients) {
		List<IngredientType> typesOfIngredients = new ArrayList<IngredientType>(); 
		for(AlchemicIngredient ingredient : theIngredients) {
			IngredientType typeOfIngredient = ingredient.getIngredientType();
			typesOfIngredients.add(typeOfIngredient); 
		}
		Set<IngredientType> uniqueIngredients = new HashSet<IngredientType>(typesOfIngredients); 
		return (uniqueIngredients.size() == typesOfIngredients.size()); 
	}
	
	private void combineDoubles(List<AlchemicIngredient> listOfIngredients) {
		
		/*find the double IngredientType in the list*/
		/*this implementation is largely incorrect, what with triples, quadruples?*/
		
		for(int currentIngredient=0; currentIngredient<listOfIngredients.size(); currentIngredient++) {
			for(int nextIngredient=currentIngredient + 1; nextIngredient<listOfIngredients.size(); nextIngredient++) {
				if(listOfIngredients.get(currentIngredient).equals(listOfIngredients.get(nextIngredient))) {
					int firstAmount = listOfIngredients.get(currentIngredient).getQuantityInSpoons(); 
					int secondAmount = listOfIngredients.get(nextIngredient).getQuantityInSpoons(); 
					int combinedAmount = firstAmount + secondAmount; 
				}
			}
		}
	}
	
	/**
	 * Give a list of all ingredients present at the laboratory. 
	 * 
	 * @return	the list of ingredients
	 */
	
	protected List<AlchemicIngredient> getIngredients(){
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
	 * @param	listOfDevices
	 * 		 	All the devices that are stored in the laboratory. 
	 */
	
	private void setDevices(ArrayList<Device> listOfDevices) {
		if(areThereNoDoubleDevices(listOfDevices)) {
			this.listOfDevices = listOfDevices; 
		} 
		
	}
	
	/**
	 * Get the list of devices present in the laboratory
	 * 
	 * @return	The list of devices in the laboratory. All the devices for this laboratory are unique. 
	 */
	
	protected List<Device> getDevices(){
		return this.listOfDevices; 
	}
	
	/**
	 * 
	 * Checks whether there are no duplicate devices present at the laboratory
	 * 
	 * @param 	listWithDevices
	 * 		
	 * @return 	True if there are no double devices present in the laboratory. False if otherwise.
	 */
	
	public static boolean areThereNoDoubleDevices(ArrayList<Device> listWithDevices) {
		Set<Device> setOfDevices = new HashSet<Device>(listWithDevices);
		int setLength = setOfDevices.size(); 
		int listLength = listWithDevices.size(); 
		return (listLength == setLength); 
	}
	
	/********************************************
	 * storing and adding ingredients 
	 ***************/
	
	/**
	 * Creates a new container of a given amount
	 * 
	 * @param 	amount
	 * 		  	The capacity for this new container
	 * @return 	a new container of given capacity 
	 */
	
	public IngredientContainer createContainer(int amount) {
		IngredientContainer newContainer = new IngredientContainer(amount); 
		return newContainer; 
	}
	
	
	/**
	 * Store a new amount of AlchemicIngredient in the laboratory from the container it came in.
	 * 
	 * @param	fromContainer
	 * 			The container the AlchemicIngredient arrived in			
	 * @return	the total amount of the specific AlchemicIngredient
	 */
	
	private int storeNewAmountIngredient(IngredientContainer fromContainer) {
		int newAmount = 0; 
		
		/*When adding a new amount of ingredient to the laboratory, check if that ingredient was present before,
		 * by checking the current catalogue.*/
		
		String newIngredientName = fromContainer.getAlchemicIngredient().getCompleteName(); 
		Map<String, Integer> currentCatalogue = getCatalog(); 
		for(Map.Entry<String, Integer> entry : currentCatalogue.entrySet()) {
			if(newIngredientName == entry.getKey()) {
				newAmount = entry.getValue() + fromContainer.getContainerContents();
				
			}
			else {
				newAmount = fromContainer.getContainerContents();
			} 
		}
		/*once the ingredient is taken out of its container, destroy the container*/
		
		fromContainer.setDelete(true);
		return newAmount; 
	}
		
	/**
	 * Variable referencing the catalog of the laboratory
	 */
	
	private Map<String, Integer> catalog = new HashMap<String, Integer>(); 
	
	/**
	 * Make and get a catalogue of all the ingredients present in this laboratory
	 * together with their total amounts
	 * 
	 * @return The catalogue for this laboratory. 
	 */
	
	public Map<String, Integer> getCatalog(){
		for(AlchemicIngredient ingredient : getIngredients()) {
			String nameOfIngredient = ingredient.getCompleteName(); 
			int amount = getFullAmountFromLabo(ingredient.getCompleteName()); 
			catalog.put(nameOfIngredient, amount); 
		}
		return catalog; 
	}
	
	
	/**
	 * Get a certain amount of AlchemicIngredient from this laboratory and put it in its
	 * appropriate container
	 * 
	 * @param 	ingr
	 * 		  	The complete name of the AlchemicIngredient to be taken from this laboratory
	 * @param 	amount
	 * 		  	The amount of AlchemicIngredient to be taken from this laboratory
	 * @return 	a new IngredientContainer with the requested ingredient in its appropriate container
	 */
		
	public IngredientContainer getAmountFromLabo(String ingr, int amount) { 
		if(isValidAmount(ingr, amount)) {
			AlchemicIngredient fullIngredient = getIngredientFromName(ingr); 
			IngredientContainer aContainer = new IngredientContainer(fullIngredient, amount); 
			return aContainer; 
		}
		/* exception zal hiervoor in de plaats komen*/
		return null; 
		
	}
	
	/**
	 * Search for the object AlchemicIngredient based on its complete name. 
	 * 
	 * @param	ingredientCompleteName
	 * 			The complete name of the alchemic ingredient			
	 * @return	The AlchemicIngredient that has ingredientCompleteName as its complete name. 
	 */
	
	private AlchemicIngredient getIngredientFromName(String ingredientCompleteName) {
		AlchemicIngredient correspondingIngredient = null; 
		for(AlchemicIngredient ingredient : getIngredients()) {
			if(ingredientCompleteName.equals(ingredient.getCompleteName())) {
				correspondingIngredient = ingredient; 
			}
		}
		return correspondingIngredient; 
	}
	
	/**
	 * Checks whether the amount of ingredient that is retrieved is valid. 
	 * 
	 * @param	ingredient
	 * 			The ingredient of which an amount is taken from the laboratory
	 * @param	amount
	 * 			An amount of ingredient that is taken from the storage of the ingredient
	 * @return	True if the amount is a positive number and if it doesn't exceed the stock amount of ingredient.
	 * 			| ((amount >= 0) && (amount <= getFullAmountFromLabo(AlchemicIngredient ingredient)))
	 */
	
	private boolean isValidAmount(String ingredientCompleteName, int amount) {
		return ((amount >= 0) && (amount <= getFullAmountFromLabo(ingredientCompleteName))); 
	}
	

	/**
	 * Get the full amount of a specific ingredient stored in various places and containers within this laboratory
	 * NOTE: deze getter moet nog throwen, exception nog niet geschreven
	 * 
	 * @param	ingredient
	 * 		  	The AlchemicIngredient of which the full amount must be known
	 * @return 	The full amount of the given ingredient within this laboratory
	 */
	
	public int getFullAmountFromLabo(String ingredientsCompleteName) {
		AlchemicIngredient ingredient = getIngredientFromName(ingredientsCompleteName); 
		if(isIngredientPresentInLab(ingredient)) {
			for(int i=0; i<getIngredients().size(); i++) {
				if(ingredient.equals(getIngredients().get(i))) {
					return getIngredients().get(i).getQuantityInSpoons(); 
				}
			}
		}
		return 0; 
	}
	
	/**
	 * Checks whether an alchemic ingredient is present in this laboratory. 
	 * 
	 * @param	ingredient
	 * 			The alchemic ingredient for which the presence in the laboratory needs to be checked. 		
	 * @return	True if the alchemic ingredient is present in this laboratory
	 * 			| getIngredients().contains(AlchemicIngredient ingredient)
	 */
	
	private boolean isIngredientPresentInLab(AlchemicIngredient ingredient) {
		return getIngredients().contains(ingredient); 
	}
	

}
