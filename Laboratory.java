import be.kuleuven.cs.som.annotate.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A class of laboratories.
 *
 * @author Jérôme D'hulst, Marie Levrau en willemsart
 *
 */

public class Laboratory{

	/**************************************
	 * Constructors
	 *********************/

	/**
	 *
	 * Initialize a new laboratory with given store room capacity.
	 *
	 * @param 	capacity
	 * 		  	The capacity of the laboratory in storerooms.
	 *
	 * @effect 	The capacity is set to the given capacity in storerooms (must be valid)
	 * 		   	|setLabCapaCity(capacity)
	 */

	public Laboratory(long capacity) {
		setLabCapacity(capacity);
	}

	/****************************************
	 * Capacity
	 *************************/

	/**
	 * Variable referencing the capacity of this laboratory.
	 */
	private long capacity = 0;
	
	/**
	 * Return the capacity of this laboratory expressed in storerooms
	 *
	 */
	@Raw @Basic 
	public long getCapacity() {
		return capacity;
	}

	/**
	 * Set the capacity for this laboratory to the given capacity expressed in storerooms
	 *
	 * @param	capacity
	 * 		  	The capacity for this laboratory in storerooms
	 * @throws 	IllegalLabCapacity
	 * 			This laboratory capacity is not valid
	 * 			| isValidLaboCapacity(capacity)
	 */
	private void setLabCapacity(long capacity) throws IllegalLabCapacity{
		if(isValidLaboCapacity(capacity)) {
			this.capacity = capacity;
		}
		else {
			throw new IllegalLabCapacity("The given capacity is illegal for this laboratory",this);
		}

	}


	/**
	 * Check whether the capacity for this laboratory is a legal capacity.
	 *
	 * @param	capacity
	 * 		  	The capacity for this laboratory (expressed in storerooms) to be checked
	 * @return 	True if the capacity is larger than zero and smaller than or equal to the largest possible number.
	 * 		   	| result == 
	 * 			| 	(capacity > 0) && (capacity <= Long.MAX_VALUE)
	 */
	public boolean isValidLaboCapacity(long capacity) {
		return ((capacity > 0) && (capacity <= Long.MAX_VALUE));
	}

	/**************************************************************
	 * The available capacity in this laboratory measured in spoons
	 *************************************************************/
	
	/**
	 * Variable referencing the available capacity of this laboratory measured in spoons.
	 */
	private long availableCapInSpoons = 0;
	
	/**
	 * Return the available capacity of this laboratory measured in spoons
	 */
	@Raw @Basic
	public long getAvailableCapInSpoons() {
		return 6300*this.capacity;
	}
	
	/**
	 * Set the available capacity of this laboratory to the given available capacity expressed in spoons
	 *
	 * @param	availableCapInSpoons
	 * 		  	The available capacity of this laboratory in spoons
	 * @throws 	IllegalLabCapacity
	 * 			This available capacity is not valid
	 * 			| isValidAvailableCapInSpoons(availableCapInSpoons)
	 */
	private void setAvailableCapInSpoons(long availableCapInSpoons) throws IllegalLabCapacity {
		if(isValidAvailableCapInSpoons(availableCapInSpoons)) {
			this.availableCapInSpoons = availableCapInSpoons;
		}
		else {
			throw new IllegalLabCapacity("The given available capacity is illegal for this laboratory",this);
		}

	}
	
	/**
	 * Set the available capacity of this laboratory to the given available capacity expressed in spoons
	 *
	 * @param	availableCapInSpoons
	 * 		  	The available capacity of this laboratory in spoons
	 * @effect 	The available capacity is set to the given available capacity in spoons 
	 * 		   	|setLabCapaCity(availableCapInSpoons)
	 */
	public void changeAvailableCapInSpoons(long availableCapInSpoons) {
		setAvailableCapInSpoons(availableCapInSpoons);
	}
	
	/**
	 * Check whether the available capacity of this laboratory is a legal available capacity.
	 *
	 * @param	availableCapInSpoons
	 * 		  	The available capacity of this laboratory (expressed in spoons) to be checked
	 * @return 	True if the available capacity is larger than zero and smaller than or equal to the largest possible number.
	 * 		   	| result == 
	 * 			| 	(availableCapInSpoons > 0) && (availableCapInSpoons <= Long.MAX_VALUE)
	 */
	public boolean isValidAvailableCapInSpoons(long availableCapInSpoons) {
		return ((availableCapInSpoons > 0) && (availableCapInSpoons <= Long.MAX_VALUE));
	}
	
	/************************************
	 * Ingredients
	 ********************************/

	/**
	 * Variable referencing a list of all the ingredients present in this laboratory
	 */
	private List<AlchemicIngredient> listOfIngredients = new ArrayList<AlchemicIngredient>();
	
	/**
	 * Return the list of all ingredients present in this laboratory.
	 */
	@Raw @Basic
	protected List<AlchemicIngredient> getIngredients(){
		return this.listOfIngredients;
	}

	/**
	 * Check if there are no ingredients in the given list with the same ingredient type.
	 *
	 * @param	list
	 * 		  	The list of the ingredients to be checked
	 * @return 	True if for each ingredient in this list the complete name does not appear more than once in this list and the given list is not empty.
	 * 			| result ==
	 * 			| 	(uniqueIngredients.size() == fullNamesOfIngredients.size()) && (list.size() >= 1)
	 */
	private boolean areThereNoDoubleIngredients(List<AlchemicIngredient> list) {
		List<String> fullNamesOfIngredients = new ArrayList<String>();
		for(AlchemicIngredient ingredient : list) {
			String fullNameOfIngredient = ingredient.getCompleteName();
			fullNamesOfIngredients.add(fullNameOfIngredient);
		}
		Set<String> uniqueIngredients = new HashSet<String>(fullNamesOfIngredients);
		return ((uniqueIngredients.size() == fullNamesOfIngredients.size()) && (list.size() >= 1));
	}

	/**
	 * Set the list of ingredients for this laboratory to the given list of ingredients
	 *
	 * @param	listOfIngredients
	 * 		  	The list of ingredients for this laboratory
	 * @throws 	IllegalListOfIngredients
	 * 			The given list of ingredients is illegal
	 * 			| areThereNoDoubleIngredients(listOfIngredients)
	 */
	private void setListOfIngredients(List<AlchemicIngredient> listOfIngredients) throws IllegalListOfIngredients {
		if(areThereNoDoubleIngredients(listOfIngredients)) {
			this.listOfIngredients = listOfIngredients;
		}
		else {
			throw new IllegalListOfIngredients("The given list of ingredients in this laboratory is illegal",this);
		}

	}

	/**************************************************
	 * Devices
	 **************************/

	/**
	 * Variable referencing the Oven in this lab.
	 */
	private Oven ovenInLab = null;

	/**
	 * Return the oven in this laboratory
	 */
	@Raw @Basic
	public Oven getOven() {
		return ovenInLab; 
	}
	
	/**
	 * Set the oven in this lab to the given oven. 
	 * 
	 * @param 	ovenInLab
	 * 		  	The oven for this laboratory
	 * @throws	AlreadyDeviceInLabException
	 * 			There is already an Oven in this laboratory
	 * 			|isValidOvenAddition()
	 */
	private void setOven(Oven ovenInLab) throws AlreadyDeviceInLabException {
		if(isValidOvenAddition()) {
			this.ovenInLab = ovenInLab;
		}
		else {
			throw new AlreadyDeviceInLabException("This laboratorium already has an oven",this);
		}
		
	}

	/**
	 * Check whether there is already an oven present in this laboratory.
	 * 
	 * @return 	True if there is no oven already present in this laboratory.
	 * 			| result ==
	 * 			| 		ovenInLab.equals(null);
	 */
	public boolean isValidOvenAddition() {
		return ovenInLab.equals(null); 
	}
	
	/**
	 * Add the given oven to this laboratory
	 * 
	 * @param 	newOven
	 * 			The newOven added in this laboratory.
	 * @effect	The new oven is set to the given oven.
	 * 			| setOven(ovenInLab)
	 */
	public void addOven(Oven newOven) {
		setOven(ovenInLab); 
	}

	/**
	 * Variable referencing the Transmogrifier in this laboratory
	 */
	private Transmogrifier transmogrifierInLab = null;
	
	/**
	 * Return the transmogrifier in this laboratory
	 */
	@Raw @Basic
	public Transmogrifier getTransmogrifier() {
		return transmogrifierInLab; 
	}
	
	/**
	 * Check whether there is already a transmogrifier present in this laboratory.
	 * 
	 * @return	True if there is no transmogrifier already present in this laboratory.
	 * 			| result == 
	 * 			|		transmogrifierInLab.equals(null)		
	 */
	public boolean isValidTransmogrifier() {
		return transmogrifierInLab.equals(null); 
	}

	/**
	 * Set the transmogrifier in this lab to the given transmogrifier. 
	 * 
	 * @param 	transmogrifierInLab
	 * 		  	The transmogrifier for this laboratory
	 * @throws	AlreadyDeviceInLabException
	 * 			There is already a transmogrifier in this laboratory
	 * 			|isValidTransmogrifier()
	 */
	private void setTransmogrifier(Transmogrifier transmogrifierInLab) throws AlreadyDeviceInLabException {
		if(isValidTransmogrifier()) {
			this.transmogrifierInLab = transmogrifierInLab;
		}
		else {
			throw new AlreadyDeviceInLabException("This laboratory already has a transmogrifier",this);
		}
	}

	/**
	 * Add the given transmogrifier to this laboratory
	 * 
	 * @param 	newTransmogrifier
	 * 			The newTransmogrifier added in this laboratory.
	 * @effect	The new transmogrifier is set to the given transmogrifier.
	 * 			| setTransmogrifier(newTransmogrifier)
	 */
	public void addTransmogrifier(Transmogrifier newTransmogrifier) {
		setTransmogrifier(newTransmogrifier); 
	}
	
	/**
	 * Variable referencing the CoolingBox in this lab
	 */
	private CoolingBox coolingBoxInLab = null;
	
	/**
	 * Return the coolingBox in this laboratory
	 */
	@Raw @Basic
	public CoolingBox getCoolingBox() {
		return coolingBoxInLab; 
	}

	
	/**
	 * Check whether there is already a coolingBox present in this laboratory.
	 * 
	 * @return 	True if there is no coolingBox already present in this laboratory.
	 * 			| result ==
	 * 			| 		 coolingBoxInLab.equals(null)
	 */
	public boolean isValidCoolingBoxAddition() {
		return coolingBoxInLab.equals(null); 
	}

	/**
	 * Set the coolingBox in this lab to the given coolingBox. 
	 * 
	 * @param 	coolingBoxInLab
	 * 		  	The coolingBox for this laboratory
	 * @throws	AlreadyDeviceInLabException
	 * 			There is already a coolingBox in this laboratory
	 * 			|isValidCoolingBoxAddition()
	 */
	private void setCoolingBox(CoolingBox coolingBoxInLab) throws AlreadyDeviceInLabException{
		if(isValidCoolingBoxAddition()) {
			this.coolingBoxInLab = coolingBoxInLab;
		}
		else {
			throw new AlreadyDeviceInLabException("This laboratorium already has a coolingbox",this);
		}
	}
	
	/**
	 * Add the given coolingBox to this laboratory
	 * 
	 * @param 	newCoolingBox
	 * 			The newCoolingBox added in this laboratory.
	 * @effect	The new coolingBox is set to the given coolingBox.
	 * 			| setCoolingBox(newCoolingBox)
	 */
	public void addCoolingBox(CoolingBox newCoolingBox) {
		setCoolingBox(newCoolingBox); 
	}

	/**
	 * Variable referencing the kettle in this lab
	 */
	private Kettle kettleInLab = null;

	/**
	 * Return the kettle in this laboratory
	 */
	@Raw @Basic
	public Kettle getKettle(){
		return kettleInLab; 
	}
	
	/**
	 * Set the kettle in this lab to the given kettle. 
	 * 
	 * @param 	kettleInLab
	 * 		  	The kettle for this laboratory
	 * @throws	AlreadyDeviceInLabException
	 * 			There is already a kettle in this laboratory
	 * 			|isValidKettleAddition()
	 */
	private void setKettle(Kettle kettleInLab) throws AlreadyDeviceInLabException {
		if (isValidKettleAddition()) {
			this.kettleInLab = kettleInLab;
		}
		else {
			throw new AlreadyDeviceInLabException("This laboratorium already has a kettle",this);
		}
	}
	
	/**
	 * Add the given kettle to this laboratory
	 * 
	 * @param 	newKettle
	 * 			The newKettle added in this laboratory.
	 * @effect	The new kettle is set to the given kettle.
	 * 			| setKettle(newKettle)
	 */
	public void addKettle(Kettle newKettle) {
		setKettle(newKettle);
	}
		
		
	/**
	 * Check whether there is already a kettle present in this laboratory.
	 * 
	 * @return 	True if there is no kettle already present in this laboratory.
	 * 			| result ==
	 * 			| 		kettleInLab.equals(null)
	 */
	public boolean isValidKettleAddition() {
		return kettleInLab.equals(null); 
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

	public IngredientContainer createContainer(int amount, String unit, String state) {
		IngredientContainer newContainer = new IngredientContainer(null, amount, unit, state);
		return newContainer;
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
		Temperature standardTemperature = ingredient.getStandardTemperature();
		return ingredient.getTemperature() == standardTemperature;
	}
	
	/**
	 * Use an oven to heat up an AlchemicIngredient
	 * 
	 * @param	container
	 * 			The IngredientContainer out of which the AlchemicIngredient to be heated must be taken			
	 * @throws	NoSuchInLabDeviceException
	 * 			If there is not yet an Oven present in this Laboratory, 
	 * 			then a new no such device in lab exception is thrown.
	 * 			|throw new NoSuchInLabDeviceException("message", this)
	 */
	
	private AlchemicIngredient useOven(IngredientContainer container) {
		Oven oven = getOven(); 
		if(!isValidOvenAddition()) {
			AlchemicIngredient ingredient = container.getAlchemicIngredient();
			container.delete();
			long newColdness = ingredient.getStandardTemperature().getColdness();
			long newHotness = ingredient.getStandardTemperature().getHotness();
			oven.changeOvenTemperature(newColdness, newHotness);
			oven.addIngredientFrom(container);
			oven.executeAlchemicOperation();
			IngredientContainer resultContainer = oven.removeAlchemicResult();
			AlchemicIngredient result = resultContainer.getAlchemicIngredient();
			resultContainer.delete();
			return result;
		}
		else {
			throw new NoSuchInLabDeviceException("There is no oven present in this laboratory!", this);
		}
	
		
	}
	
	/**
	 * Use a coolingbox to cool down an AlchemicIngredient
	 * 
	 * @param	container
	 * 			The IngredientContainer out of which the AlchemicIngredient to be cooled must be taken			
	 * @throws	NoSuchInLabDeviceException
	 * 			If there is not yet a CoolingBox present in this Laboratory, 
	 * 			then a new no such device in lab exception is thrown.
	 * 			|throw new NoSuchInLabDeviceException("message", this)
	 */
	
	private AlchemicIngredient useCoolingBox(IngredientContainer container) throws NoSuchInLabDeviceException{
		CoolingBox fridge = getCoolingBox();
		if(!isValidCoolingAddition()) {
			AlchemicIngredient ingredient = container.getAlchemicIngredient();
			container.delete();
			long theHotness = ingredient.getStandardTemperature().getHotness();
			long theColdness = ingredient.getStandardTemperature().getColdness();
			fridge.changeCoolingBoxTemperature(theColdness, theHotness);
			fridge.addIngredientFrom(container);
			fridge.executeAlchemicOperation();
			IngredientContainer resultContainer = fridge.removeAlchemicResult();
			AlchemicIngredient result = resultContainer.getAlchemicIngredient();
			return result;
		}
		else {
			throw new NoSuchInLabDeviceException("There is no coolingbox in this laboratory! Please make one", this); 
		}
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
			AlchemicIngredient adaptedIngredient = null;
			if ((ingredient.getTemperature().getColdness() > ingredient.getStandardTemperature().getColdness()) || ingredient.getTemperature().getHotness() < ingredient.getStandardTemperature().getHotness()) {
				adaptedIngredient = useOven(fromContainer);
			}
			else {
				adaptedIngredient = useCoolingBox(fromContainer);
			}
		return adaptedIngredient;
		}
	}
	
	/**
	 * Checks whether the amount of a new AlchemicIngredient can be stored in the laboratory with given capacity.
	 *
	 * @param	ingredient
	 * 			The ingredient that might be added to the laboratory.
	 * @return	True if the quantity of the AlchemicIngredient is a positive number and lower than the capacity of this laboratory.
	 * 			| ((ingredient.getQuantityInSpoons() >= 0) && (ingredient.getQuantityInSpoons() <= 6300*getCapacity()))
	 */
	private boolean isValidNewAmount(AlchemicIngredient ingredient) {
		int quant = ingredient.getQuantityInSpoons();
		return((quant >= 0) && (quant <= 6300*getCapacity()));
	}
	

	
	
	/**
	 * Combine all the amounts of a certain AlchemicIngredient in this laboratory into one single amount
	 * expressed in spoons.
	 *
	 * @param	ingredient
	 * 			The AlchemicIngredient for which all its amounts are combined into one.
	 * @return 
	 * @return	The total amount of that certain ingredient present in the laboratory.
	 */
	@Model
	private void combineAmounts(AlchemicIngredient ingredient) throws InvalidLaboratoryAmountException{
		this.addToCapacityLabo(ingredient.getQuantity(),ingredient.getUnit());
		for (int i = 0; i < listOfIngredients.size(); i++) {
			if (listOfIngredients.get(i).getCompleteName() == ingredient.getCompleteName()) {
				AlchemicIngredient originalIngr = listOfIngredients.get(i);
				IngredientContainer container1 = new IngredientContainer(originalIngr,1,originalIngr.determineCapUnit(),originalIngr.getState());
				IngredientContainer newContainer = new IngredientContainer(ingredient,1,ingredient.determineCapUnit(),ingredient.getState());
				Kettle kettle = this.getKettle();
				kettle.addIngredientFrom(container1);
				kettle.addIngredientFrom(newContainer);
				kettle.executeAlchemicOperation();
				IngredientContainer resultContainer = kettle.removeAlchemicResult();
				AlchemicIngredient result = resultContainer.getAlchemicIngredient();
				if (result.getQuantityInSpoons() < this.getCapacityInSpoons()) {
					this.substractFromCapacityLabo(ingredient.getQuantity(), ingredient.getUnit());
					listOfIngredients.remove(i);
					originalIngr.terminate();
					ingredient.terminate();
					resultContainer.delete();
					listOfIngredients.add(result);
				
				}
				
				else {
					throw new InvalidLaboratoryAmountException("The amount of ingredient you want to add, exceeds the laboratory capacity", this);
				}
				
				
				
			}
			else {
				listOfIngredients.add(ingredient);
				this.substractFromCapacityLabo(ingredient.getQuantity(), ingredient.getUnit());
			}
		
		}

	}
	
	/**
	 * Store a new AlchemicIngredient in this laboratory.
	 *
	 * @param	fromContainer
	 * 			The IngredientContainer in which the AlchemicIngredient arrived.
	 * @throws	InvalidLaboratoryAmountException
	 * 			If the combined amount of the ingredient to be added to this laboratory exceeds the capacity of the laboratory
	 * 			a new invalid laboratory amount exception is thrown.
	 * 			| throw new InvalidLaboratoryAmountException
	 */
	
	public void storeNewIngredient(IngredientContainer fromContainer) throws IllegalAdditionException{
		AlchemicIngredient ingredientToBeAdded = ingredientBroughtToStandardTemp(fromContainer);
		if(isValidAddition(ingredientToBeAdded.getQuantity(),ingredientToBeAdded.getUnit())) {
			this.combineAmounts(ingredientToBeAdded);
			fromContainer.setDelete(true);
		}
		else {
			throw new IllegalAdditionException("There is not enough space available to store this alchemic ingredient in this laboratory",this);
		}
		
	}
	
	/**
	 * Search for the object AlchemicIngredient based on its complete name.
	 *
	 * @param	ingredientCompleteName
	 * 			The complete name of the AlchemicIngredient
	 * @throws	IngredientNotPresentInLabException
	 * 			If the AlchemicIngredient is not present in the laboratory, a new ingredient not present in lab exception
	 * 			will be thrown
	 * 			|throw new IngredientNotPresentInLabException(message, this)
	 * @return	The AlchemicIngredient that has ingredientCompleteName as its complete name.
	 */

	private AlchemicIngredient getIngredientFromName(String ingredientCompleteName) throws IngredientNotPresentInLabException {
		AlchemicIngredient ingr = null;
		if(isIngredientPresentInLab(ingredientCompleteName)) {
			for (int i = 0; i < listOfIngredients.size(); i++) {
				if (listOfIngredients.get(i).getCompleteName() ==  ingredientCompleteName) {
					ingr = listOfIngredients.get(i);
				}
			}
			return ingr;
		}
		else {
			throw new IngredientNotPresentInLabException("Ingredient cannot be retrieved from name, because it is not present", this);
		}
	}
	
	@Basic @Immutable
	protected IngredientContainer getFullAmountFromLabo(String ingrCompleteName){
		/*mss hier nog isValidAmount controleren*/
		AlchemicIngredient ingr = this.getIngredientFromName(ingrCompleteName);
		for (int i = 0; i < listOfIngredients.size(); i++) {
			if (listOfIngredients.get(i) ==  ingr) {
				listOfIngredients.remove(i);
			}
		}
		IngredientContainer container = new IngredientContainer(ingr,1,ingr.determineCapUnit(),ingr.getState());
		this.addToCapacityLabo(ingr.getQuantity(), ingr.getUnit());
		return container;
		
	}
	
	public int getAmountInSpoons(int amount, String unit) {
		int result = amount;
			for (Map.Entry<String,Integer> entry: unitInSpoons.entrySet()) {
				if (entry.getKey() == unit) {
					result = result * entry.getValue();
				}
			}
		
		return result;
	}
	
	private static Map<String,Integer> unitInSpoons = new HashMap<String,Integer>(){

		private static final long serialVersionUID = 1L;

		{
			put("drop",1/8);
			put("vial",5);
			put("bottle",15);
			put("spoon",1);
			put("jug",105);
			put("barrel",1260);
			put("storeroom",6300);
			put("pinch",1/6);
			put("sachet",7);
			put("box",42);
			put("sack",126);
			put("chest",1260);
		}
	};
	
	
	public IngredientContainer getAmountFromLabo(String ingrName, int amount, String unit) throws InvalidLaboratoryAmountException, InvalidAdditionException{
		AlchemicIngredient ingr = this.getIngredientFromName(ingrName);
		if(isValidAddition(amount,unit)) {
				if (ingr.getQuantityInSpoons() < this.getAmountInSpoons(amount, unit)) {
					AlchemicIngredient ingrForLabo = new AlchemicIngredient(ingr.getQuantityInSpoons()-this.getAmountInSpoons(amount, unit),"spoon",ingr.getIngredientTypeList(),ingr.getTemperature().getHotness(), ingr.getTemperature().getColdness(),ingr.getState(),ingr.getSpecialName());
					AlchemicIngredient ingrToReturn = new AlchemicIngredient(amount, unit,ingr.getIngredientTypeList(),ingr.getTemperature().getHotness(), ingr.getTemperature().getColdness(),ingr.getState(),ingr.getSpecialName());
					for (int i = 0; i < listOfIngredients.size(); i++) {
						if (listOfIngredients.get(i).getCompleteName() ==  ingrName) {
							listOfIngredients.remove(i);
						}
					}
					ingr.terminate();
					listOfIngredients.add(ingrForLabo);
					this.addToCapacityLabo(amount, unit);
					IngredientContainer container = new IngredientContainer(ingrToReturn,1,ingrToReturn.determineCapUnit(),ingrToReturn.getState());
					return container;
				}
				else if (ingr.getQuantityInSpoons() == this.getAmountInSpoons(amount, unit)) {
					return this.getFullAmountFromLabo(ingrName);
				}
				else {
					throw new InvalidLaboratoryAmountException("There is not enough ingredient in this laboratory", this);
				}
		}
		else {
			throw new InvalidAdditionException("The capacity of this laboratory is too sma", this);
				}
	}
	
	/*mss nog ergens controleren of het mogelijk is om die substraction af te trekken*/
	private void substractFromCapacityLabo(int amount,String unit) {
			int substraction = this.getAmountInSpoons(amount, unit);
			long result = this.getAvailableCapInSpoons() - substraction;
			this.changeAvailableCapInSpoons(result);
		}
		
	
	public boolean isValidSubstraction(int amount,String unit) {
		int substraction = this.getAmountInSpoons(amount, unit);
		return ((this.getAvailableCapInSpoons() >= substraction) && (substraction != 0));
	}
	
	public boolean isValidAddition(int amount,String unit) {
		int addition = this.getAmountInSpoons(amount, unit);
		return ((this.getAvailableCapInSpoons() >= addition) && (addition != 0));
	}
	
	/*mss nog ergens controleren of het mogelijk is om die addition te doen*/
	private void addToCapacityLabo(int amount, String unit) {
		int addition = this.getAmountInSpoons(amount, unit);
		long result = this.getAvailableCapInSpoons() + addition;
		this.changeAvailableCapInSpoons(result);
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
	@Basic
	public Map<String, Integer> getCatalog(){
		/**
		 * If a certain AlchemicIngredient.getCompleteName() does not appear more than once in the laboratory
		 * we can safely put them in the catalog map. Otherwise we need to take the full amount of a certain ingredient.
		 * A map does not accept two identical keys, so when a duplicate key is added, the first one will be dropped and its
		 * corresponding value, the full amount, but this is of no concern, since we work with getFullAmount, which will
		 * calculate the combined amount time and again.
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
	 * Checks whether an AlchemicIngredient is present in this laboratory.
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
	

	
	
	
	
	/**********************************************************
     * recipe
     **********************************************************/
	
	private int lastUsed = 0;
	
	public int getLastUsed() {
		return lastUsed;
	}
	
	private void setLastUsed(int lastUsed) {
		this.lastUsed = lastUsed;
	}
	
	private List<IngredientContainer> recipeList = null;
	
	public List<IngredientContainer> getRecipeList(){
		return recipeList;
	}
	
	private void setRecipeList(List<IngredientContainer> recipeList) {
		this.recipeList = recipeList;
	}
	
	
	public void add(int amount, String unit, String ingredientName) {
		IngredientContainer container = this.getAmountFromLabo(ingredientName, amount, unit);
		this.getRecipeList().add(container);
		lastUsed = this.getRecipeList().size() - 1;
	}
	
	public IngredientContainer heat(IngredientContainer container) {
		Oven oven = this.getOven();
		oven.changeOvenTemperature(0,50);
		oven.addIngredientFrom(container);
		oven.executeAlchemicOperation();
		IngredientContainer result = oven.removeAlchemicResult();
		return result;

	}
	
	public IngredientContainer cool(IngredientContainer container) {
		CoolingBox coolingBox = this.getCoolingBox();
		coolingBox.changeCoolingBoxTemperature(50,0);
		coolingBox.addIngredientFrom(container);
		coolingBox.executeAlchemicOperation();
		IngredientContainer result = coolingBox.removeAlchemicResult();
		return result;
	}
	

	public IngredientContainer mix(List<IngredientContainer> listContainers) {
		Kettle kettle = this.getKettle(); 
		for (int i = 0; i < recipeList.size(); i++) {
			kettle.addIngredientFrom(listContainers.get(i));
		}
		kettle.executeAlchemicOperation();
		IngredientContainer result = kettle.removeAlchemicResult();
		lastUsed = 0;
		return result;	
	}
	
	
	public static boolean isValidOperation(String operation) {
		return ((operation == "Add") || (operation == "heat") || (operation == "cool") || (operation == "mix"));
	}
	
	public void execute(Recipe recipe,int number) throws IllegalOperationException {
		if (recipe.getOperationList().get(recipe.getOperationList().size() -1) != "mix" ){
			recipe.getOperationList().add("mix");
		}
		
		for (int i = 0; i < recipe.getOperationList().size(); i++) {
			  if (!isValidOperation(recipe.getOperationList().get(i))){
				  throw new IllegalOperationException("This is an illegal operation");
			  }
			  else if (recipe.getOperationList().get(i) == "Add") {
				  String ingredientName = recipe.getNameIngredientList().get(0);
				  int amount = number*(recipe.getAmountList().get(0));
				  String unit = recipe.getUnitList().get(0);
				  
				  if (isValidAmount(ingredientName, amount, unit)) {
					  this.add(amount, unit, ingredientName);
					  recipe.removeIngredientName(0);
					  recipe.removeAmount(0);
					  recipe.removeUnit(0);
				  }
				  else {
					  break;
				  }
				  
			  }
			  else if(recipe.getOperationList().get(i) == "heat") {
				  IngredientContainer result = this.heat(this.getRecipeList().get(lastUsed)); /*container werd gestopt in recipeList, dus haal die eruit maar aan de hand van LastUsed*/
				  this.getRecipeList().remove(lastUsed);
				  this.getRecipeList().add(result);
			  }
			  else if(recipe.getOperationList().get(i) == "cool") {
				  IngredientContainer result = this.cool(this.getRecipeList().get(lastUsed)); /*container werd gestopt in recipeList, dus haal die eruit maar aan de hand van LastUsed*/
				  this.getRecipeList().remove(lastUsed);
				  this.getRecipeList().add(result);
			  }
			  
			  else {
				  IngredientContainer result = this.mix(this.getRecipeList());
				  this.getRecipeList().clear();
				  this.getRecipeList().add(result); 
			  }
			}
		
		if (recipe.getNameIngredientList().size() >= 1) {
			for (int i = 0; i < recipe.getNameIngredientList().size(); i++) {
				if (this.isValidAmount(recipe.getNameIngredientList().get(i), number*(recipe.getAmountList().get(i)),recipe.getUnitList().get(i))){
					this.storeNewIngredient(this.getAmountFromLabo(recipe.getNameIngredientList().get(i),number*(recipe.getAmountList().get(i)),recipe.getUnitList().get(i)));
				}
				else {
					this.storeNewIngredient(this.getFullAmountFromLabo(recipe.getNameIngredientList().get(i));
				}
				
			}
		}
		
		for (int i = 0; i < this.getRecipeList().size(); i++) {
			this.storeNewIngredient(this.getRecipeList().get(i));
		}
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
