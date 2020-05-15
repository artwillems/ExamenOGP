import be.kuleuven.cs.som.annotate.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A class laboratory for doing Alchemic procedures on AlchemicIngredients with a certain Device.
 * 
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
		this.listOfIngredients = listOfIngredients;
	}

	/**
	 * Checks if the different ingredient types in the laboratory are unique, meaning there is not two times
	 * the same AlchemicIngredient.getCompleteName().
	 *
	 * @param	list
	 * 		  	The list of all the ingredients that are put into this laboratory
	 * @return 	True if some ingredient's complete name does not appear more than once in the list of ingredients,
	 * 			false if otherwise.
	 * 			| (uniqueIngredients.size() == fullNamesOfIngredients.size())
	 */

	private boolean areThereNoDoubleIngredients(List<AlchemicIngredient> list) {
		List<String> fullNamesOfIngredients = new ArrayList<String>();
		for(AlchemicIngredient ingredient : list) {
			String fullNameOfIngredient = ingredient.getCompleteName();
			fullNamesOfIngredients.add(fullNameOfIngredient);
		}
		Set<String> uniqueIngredients = new HashSet<String>(fullNamesOfIngredients);
		return (uniqueIngredients.size() == fullNamesOfIngredients.size());
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
	
	/**
	 * Seek an oven in this laboratory
	 * NOTE: Voorlopig is het Immutable, kan nog veranderen naargelang implementatie
	 * 		
	 * @return	the first oven that is present in this laboratory
	 */
	@Immutable
	protected Oven seekOven() {
		Oven foundOven = null; 
		for(Device someDevice : getDevices()) {
			if(someDevice.getType().equals("Oven")) {
				foundOven = ((Oven) someDevice); 
			}
		}
		return foundOven; 
	}
	
	/**
	 * Seek a Coolingbox in this laboratory
	 * 
	 * @return	The first CoolingBox that is found in this laboratory. 
	 */
	@Immutable
	protected CoolingBox seekCoolingBox() {
		CoolingBox foundFridge = null;
		for(Device aDevice : getDevices()) {
			if(aDevice.getType().equals("CoolingBox")) {
				foundFridge = (CoolingBox) aDevice; 
			}
		}
		return foundFridge; 
	}
	
	/**
	protected Kettle seekKettle() {
		Kettle foundKettle = null; 
		for(Device aDevice : getDevices()) {
			if(aDevice.getType().equals("Kettle")) {
				foundKettle = (Kettle) aDevice; 
			}
		}
		return foundKettle; 
	}
	*/
	
	/**
	 * Seek a transmogrifier in this laboratory
	 * 
	 * @return The first tranmogrifier that is found in the laboratory
	 */
	
	protected Transmogrifier seekTransmogrifier() {
		Transmogrifier foundTransmogrifier = null; 
		for(Device aDevice : getDevices()) {
			if(aDevice.getType().equals("Transmogrifier")) {
				foundTransmogrifier = (Transmogrifier) aDevice; 
			}
		}
		return foundTransmogrifier; 
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
	 * Checks whether the amount of a new AlchemicIngredient can be stored in the laboratory with given capacity.
	 *
	 * @param	ingredient
	 * 			The ingredient that might be added to the laboratory.
	 * @return	True if the quantity of the AlchemicIngredient is a positive number and lower than the capacity of this laboratory.
	 * 			| ((ingredient.getQuantity() >= 0) && (ingredient.getQuantity() <= getCapacity()))
	 */

	private boolean isValidNewAmount(AlchemicIngredient ingredient) {
		int quant = ingredient.getQuantity();
		return((quant >= 0) && (quant <= getCapacity()));
	}
	
	/**
	 * Checks whether the ingredient is at its ingredient type's standard temperature
	 * 
	 * @param	ingredient
	 * 			The AlchemicIngredient that is checked on temperature
	 * @return	true if the ingredient is at its standard temperature, false otherwise
	 * 			| ingredient.getTemperature() == ingredient.getIngredientType().getStandardTemp()
	 */
	
	private boolean hasStandardTemperature(AlchemicIngredient ingredient) {
		Temperature standardTemperature = ingredient.getIngredientTypeList().get(0).getStandardTemp();
		return ingredient.getTemperature() == standardTemperature;
	}
	
	/**
	 * Get the standard hotness out of a certain ingredient type's standard temperature. 
	 * 
	 * @param	ingredient
	 * 			The ingredient we need to get the standard hotness of		 
	 * @return	the standard hotness
	 */
	
	private long getStandardHotness(AlchemicIngredient ingredient) {
		return ingredient.getIngredientTypeList().get(0).getStandardTemp().getHotness();  
	}
	
	/**
	 * Use the oven to bring a new AlchemicIngredient to its standardTemperature.
	 * 
	 * @param	oven
	 * 			The Oven used for heating the AlchemicIngredient		
	 * @param	ingredient
	 * 			The AlchemicIngredient that needs to be cooled
	 */
	
	private void useOven(Oven oven, IngredientContainer container) {
		AlchemicIngredient ingredient = container.getAlchemicIngredient();
		long newColdness = 0;
		long newHotness = ingredient.getIngredientTypeList().get(0).getStandardTemp().getHotness();
		oven.changeOvenTemperature(newColdness, newHotness);
		oven.addIngredientFrom(container);
		oven.executeAlchemicOperation();
	}
	
	/**
	 * Use the cooling box to bring the new AlchemicIngredient for the laboratory to its standardTemperature
	 * 
	 * @param	fridge
	 * 			The fridge used for Cooling the AlchemicIngredient		
	 * @param	ingredient
	 * 			The AlchemicIngredient that needs to be cooled. 
	 */
	
	private void useCoolingBox(CoolingBox fridge, IngredientContainer container) {
		AlchemicIngredient ingredient = container.getAlchemicIngredient(); 
		long theHotness = 0; 
		long theColdness = ingredient.getIngredientTypeList().get(0).getStandardTemp().getColdness(); 
		fridge.changeCoolingBoxTemperature(theColdness, theHotness);
		fridge.addIngredientFrom(container);
		fridge.executeAlchemicOperation();
	}
	
	/**
	 * Brings a new AlchemicIngredient that needs to be introduced to the laboratory to its standardTemperature by
	 * the means of an Oven or a CoolingBox.
	 * 
	 * @param	ingredient
	 * 			The new AlchemicIngredient that needs to be brought back to its standardTemperatures. 
	 * @return	The AlchemicIngredient brought to its standardTemperature. 
	 */

	private AlchemicIngredient ingredientBroughtToStandardTemp(IngredientContainer fromContainer) {	
		AlchemicIngredient ingredient = fromContainer.getAlchemicIngredient(); 
		if(hasStandardTemperature(ingredient)) {
			return ingredient; 
		}
		else {
			
			/*bring the ingredient back to its standard temperature using an oven or cooling box*/
			AlchemicIngredient adaptedIngredient = null; 
			if(ingredient.getTemperature().get(1) < getStandardHotness(ingredient)) {
		
				Oven thisOven = seekOven(); 
				useOven(thisOven, fromContainer); 
				List<AlchemicIngredient> ingredientsInOven = thisOven.getIngredientList();
				
				for(AlchemicIngredient viewed : ingredientsInOven) {
					
					if(ingredient.getCompleteName().equals(viewed.getCompleteName())){
						return adaptedIngredient = viewed; 
					}
				}
			}
			else {
				CoolingBox thisFridge = seekCoolingBox(); 
				useCoolingBox(thisFridge, fromContainer); 
				List<AlchemicIngredient> ingredientsInFridge = thisFridge.getIngredientList(); 
				for(AlchemicIngredient viewed : ingredientsInFridge) {
					
					if(ingredient.getCompleteName().equals(viewed.getCompleteName())){
						return adaptedIngredient = viewed; 
					}
				}
			}
			return adaptedIngredient; 
		}
	}

	/**
	 * Store a new AlchemicIngredient in this laboratory.
	 *
	 * @param	fromContainer
	 * 			The IngredientContainer in which the AlchemicIngredient arrived.
	 */
	
	@Raw
	public void storeNewIngredient(IngredientContainer fromContainer) {
		AlchemicIngredient ingredientToBeAdded = ingredientBroughtToStandardTemp(fromContainer);
		if(isValidNewAmount(ingredientToBeAdded)) {
			getIngredients().add(ingredientToBeAdded);
			int indexOfNew = getIngredients().indexOf(ingredientToBeAdded);
			/* check that the new amount lies within the capacity of this lab, if not, remove the
			 * ingredient again, thus restoring the listOfIngredients and throw an exception
			 */
			if(combineAmounts(ingredientToBeAdded) < getCapacity()) {
				/*destroy the container after the ingredient is retrieved*/
				fromContainer.setDelete(true);

			}
			else {
				getIngredients().remove(indexOfNew);
				/*throw an exception telling the user such an amount cannot be stored.*/
			}
		}
	}
	
	/**
	 * Add a new ingredient to the list of ingredients that already exists for this laboratory
	 * 
	 * @param	ingredient
	 * 			The ingredient that needs to be added to the laboratory.
	 */
	
	protected void addIngredient(AlchemicIngredient ingredient) {
		getIngredients().add(ingredient); 
	}
	
	/**
	 * Remove an AlchemicIngredient from this Laboratory. 
	 * 
	 * @param	ingredient
	 * 			The ingredient to be removed from this laboratory. 		
	 * @throws	IngredientNotPresentInLabException
	 * 			If the ingredient was not present in the lab before the calling of this method, a new
	 * 			ingredient not present in lab exception will be thrown. 
	 * 			| throw new IngredientNotPresentInLabException("message", this)	
	 */

	protected void removeIngredient(AlchemicIngredient ingredient) throws IngredientNotPresentInLabException {
		if(isIngredientPresentInLab(ingredient.getCompleteName())) {
			int indexOfIngredient = getIngredients().indexOf(ingredient); 
			getIngredients().remove(indexOfIngredient); 
		}
		else {
			throw new IngredientNotPresentInLabException("The ingredient cannot be removed, because it isn't in this lab", this); 
		}
	}
	
	/**
	 * Variable referencing the catalog of the laboratory
	 */

	private Map<String, Integer> catalog = new HashMap<String, Integer>();

	/**
	 * Make and get a catalog of all the ingredients present in this laboratory
	 * together with their total amounts
	 *
	 * @return The catalog for this laboratory.
	 */

	public Map<String, Integer> getCatalog(){
		/**
		 * If a certain AlchemicIngredient.getCompleteName() does not appear more than once in the laboratory
		 * we can safely put them in the catalog map. Otherwise we need to take the full amount of a certain ingredient.
		 * A map does not accept two identical keys, so when a duplicate key is added, the first one will be dropped, but
		 * this is of no matter, since we work with getFullAmount for the values.
		 */

		if(areThereNoDoubleIngredients(getIngredients())){
			for(AlchemicIngredient ingredient : getIngredients()) {
				String name = ingredient.getCompleteName();
				int quantity = ingredient.getQuantityInSpoons();
				catalog.put(name, quantity);
			}
		}
		else {
			for(AlchemicIngredient ingredient : getIngredients()) {
				String nameOfIngredient = ingredient.getCompleteName();
				int amount = getFullAmountFromLabo(ingredient.getCompleteName());
				catalog.put(nameOfIngredient, amount);
			}
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
	 * @throws	InvalidLaboratoryAmountException
	 * 			If the amount is a negative number or greater than this laboratory's capacity, a new
	 * 			invalid laboratory amount exception is thrown
	 * 			| throw new InvalidLaboratoryAmountException(message, this)
	 * @return 	a new IngredientContainer with the requested ingredient in its appropriate container
	 */

	public IngredientContainer getAmountFromLabo(String ingr, int amount) throws InvalidLaboratoryAmountException{
		if(isValidAmount(ingr, amount)) {
			/* call corresponding AlchemicIngredient from String ingredient*/
			AlchemicIngredient fullIngredient = getIngredientFromName(ingr);
			IngredientContainer aContainer = new IngredientContainer(fullIngredient, amount);
			return aContainer;
		}
		throw new InvalidLaboratoryAmountException("This amount cannot be returned from lab", this);

	}

	/**
	 * Search for the object AlchemicIngredient based on its complete name.
	 *
	 * @param	ingredientCompleteName
	 * 			The complete name of the alchemic ingredient
	 * @throws	IngredientNotPresentInLabException
	 * 			If the AlchemicIngredient is not present in the laboratory, a new ingredient not present in lab exception
	 * 			will be thrown
	 * 			|throw new IngredientNotPresentInLabException(message, this)
	 * @return	The AlchemicIngredient that has ingredientCompleteName as its complete name.
	 */

	private AlchemicIngredient getIngredientFromName(String ingredientCompleteName) throws IngredientNotPresentInLabException {
		AlchemicIngredient correspondingIngredient = null;
		if(isIngredientPresentInLab(ingredientCompleteName)) {
			for(AlchemicIngredient ingredient : getIngredients()) {
				if(ingredientCompleteName.equals(ingredient.getCompleteName())) {
					correspondingIngredient = ingredient;
				}
			}
			return correspondingIngredient;
		}
		throw new IngredientNotPresentInLabException("Ingredient cannot be retrieved from name, because it is not present", this);
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
	 * Combine all the amounts of a certain AlchemicIngredient in this laboratory into one single amount
	 * expressed in spoons.
	 *
	 * @param	ingredient
	 * 			The AlchemicIngredient for which all its amounts are combined into one.
	 * @return	The total amount of that certain ingredient present in the laboratory.
	 */

	private int combineAmounts(AlchemicIngredient ingredient) {
		int oldAmount = 0;
		for(AlchemicIngredient otherIngredients : getIngredients()) {
			if(ingredient.getCompleteName() == otherIngredients.getCompleteName()) {
				oldAmount += otherIngredients.getQuantityInSpoons();
			}
			else {
				oldAmount = ingredient.getQuantityInSpoons();
			}
		}
		return oldAmount;

	}

	/**
	 * Get the full amount of a specific ingredient stored in various places and containers within this laboratory
	 *
	 * @param	ingredient
	 * 		  	The AlchemicIngredient of which the full amount must be known.
	 * @return 	The full amount of the given ingredient within this laboratory expressed in spoons.
	 */

	public int getFullAmountFromLabo(String ingredientsCompleteName) throws IngredientNotPresentInLabException{
		int full = 0;
		if(isIngredientPresentInLab(ingredientsCompleteName)) {
			AlchemicIngredient ingredient = getIngredientFromName(ingredientsCompleteName);
			for(int i=0; i<getIngredients().size(); i++) {
				if(ingredient.equals(getIngredients().get(i))) {
					full = combineAmounts(ingredient);
				}
			}
		}
		return full;
	}

	/**
	 * Checks whether an alchemic ingredient is present in this laboratory.
	 *
	 * @param	ingredientName
	 * 			The alchemic ingredient's complete name of which the presence in the laboratory needs to be checked.
	 * @return	True if the alchemic ingredient is present in this laboratory
	 * 			| getIngredients().contains(AlchemicIngredient ingredient)
	 */

	private boolean isIngredientPresentInLab(String ingredientName) {
		boolean flag = false;
		for(int i= 0; i<getIngredients().size(); i++) {
			if(ingredientName.equals(getIngredients().get(i).getCompleteName())) {
				flag = true;
			}
		}
		return flag;
	}

}
