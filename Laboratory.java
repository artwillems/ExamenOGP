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
 * @invar	Each laboratory must have a valid capacity.
 * 			| isValidLaboCapacity(getCapacity())
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
	 * Return the capacity of this laboratory expressed in spoons
	 *
	 */
	@Raw @Basic 
	public long getCapacityInSpoons() {
		return 6300*capacity;
	}
	
	/**
	 * Set the capacity for this laboratory to the given capacity expressed in storerooms
	 *
	 * @param	capacity
	 * 		  	The capacity for this laboratory in storerooms
	 * @throws 	IllegalLabCapacity("The given capacity is illegal for this laboratory",this)
	 * 			This laboratory capacity is not valid
	 * 			| !isValidLaboCapacity(capacity)
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
	 * @throws 	IllegalLabCapacity("The given available capacity is illegal for this laboratory",this)
	 * 			This available capacity is not valid
	 * 			| !isValidAvailableCapInSpoons(availableCapInSpoons)
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
	 * @throws 	IllegalListOfIngredients("The given list of ingredients in this laboratory is illegal",this)
	 * 			The given list of ingredients is illegal
	 * 			| !areThereNoDoubleIngredients(listOfIngredients)
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
	 * @throws	AlreadyDeviceInLabException("This laboratorium already has an oven",this)
	 * 			There is already an Oven in this laboratory
	 * 			|!isValidOvenAddition()
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
	 * @throws	AlreadyDeviceInLabException("This laboratory already has a transmogrifier",this)
	 * 			There is already a transmogrifier in this laboratory
	 * 			|!isValidTransmogrifier()
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
	 * @throws	AlreadyDeviceInLabException("This laboratorium already has a coolingbox",this)
	 * 			There is already a coolingBox in this laboratory
	 * 			|!isValidCoolingBoxAddition()
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
	 * @throws	AlreadyDeviceInLabException("This laboratorium already has a kettle",this)
	 * 			There is already a kettle in this laboratory
	 * 			|!isValidKettleAddition()
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
	 * Create a new container with a capacity that exists of a given amount, unit and state
	 *
	 * @param 	amount
	 * 		  	The amount of this container
	 * @param 	unit
	 * 		  	The unit of this container
	 * @param 	state
	 * 		  	The state of this container
	 * @post 	The new container is formed with the correct characteristics
	 * 			newContainer = new IngredientContainer(null, amount, unit, state)
	 * @return 	A new container with a capacity that exists of a given amount, unit and state
	 */
	public IngredientContainer createContainer(int amount, String unit, String state) {
		IngredientContainer newContainer = new IngredientContainer(null, amount, unit, state);
		return newContainer;
	}
	
	/**
	 * Check whether the given ingredient is at its ingredient type's standard temperature.
	 *
	 * @param	ingredient
	 * 			The alchemic ingredient to be checked
	 * @return	True if the ingredient is at its standard temperature
	 * 			| result ==
	 * 			| 	(ingredient.getTemperature() == ingredient.getStandardTemperature())
	 */
	public boolean hasStandardTemperature(AlchemicIngredient ingredient) {
		Temperature standardTemperature = ingredient.getStandardTemperature();
		return (ingredient.getTemperature() == standardTemperature);
	}
	
	/**
	 * Use an oven to heat up the alchemic ingredient from a given ingredient container.
	 * 
	 * @param	container
	 * 			The ingredient container out of which the ingredient to be heated must be taken			
	 * @throws	NoSuchInLabDeviceException("There is no oven present in this laboratory!", this)
	 * 			There is not yet an Oven present in this Laboratory 
	 * 			|isValidOvenAddition()
	 * @return	If there is an oven present in this laboratory, then get the alchemic ingredient from the given container and put it in the oven.
	 * 			Bring the ingredient to its standard temperature by using an oven that is changed to the standard temperature of the ingredient.
	 * 			Put the ingredient that is in its standard temperature in an ingredient container
	 * 			| if(!isValidOvenAddition())
	 * 			|	then Oven oven = getOven()
	 * 			|		 AlchemicIngredient ingredient = container.getAlchemicIngredient()
	 * 			|		 long newColdness = ingredient.getStandardTemperature().getColdness()
	 * 			|		 long newHotness = ingredient.getStandardTemperature().getHotness()
	 * 			|		 oven.changeOvenTemperature(newColdness, newHotness)
	 * 			|		 oven.addIngredientFrom(container)
	 * 			|		 oven.executeAlchemicOperation()
	 * 			|		 IngredientContainer resultContainer = oven.removeAlchemicResult()
	 * 			|		 AlchemicIngredient result = resultContainer.getAlchemicIngredient()
	 * 			|		 resultContainer.delete()
	 */
	private AlchemicIngredient useOven(IngredientContainer container) throws NoSuchInLabDeviceException {
		if(!isValidOvenAddition()) {
			Oven oven = getOven(); 
			AlchemicIngredient ingredient = container.getAlchemicIngredient();
			
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
	 * Use a coolingBox to heat up the alchemic ingredient from a given ingredient container.
	 * 
	 * @param	container
	 * 			The ingredient container out of which the ingredient to be heated must be taken				
	 * @throws	NoSuchInLabDeviceException("There is no coolingbox in this laboratory!", this)
	 * 			There is not yet a coolingBox present in this Laboratory 
	 * 			|isValidCoolingBoxAddition()
	 * @return 	If there is a coolingBox present in this laboratory, then get the alchemic ingredient from the given container and put it in the coolingBox.
	 * 			Bring the ingredient to its standard temperature by using a coolingBox that is changed to the standard temperature of the ingredient.
	 * 			Put the ingredient that is in its standard temperature in an ingredient container
	 * 			| if(!isValidCoolingBoxAddition())
	 * 			|	then CoolingBox fridge = getCoolingBox()
	 * 			|		 AlchemicIngredient ingredient = container.getAlchemicIngredient()
	 * 			|		 long theHotness = ingredient.getStandardTemperature().getHotness()
	 * 			|		 long theColdness = ingredient.getStandardTemperature().getColdness()
	 * 			|		 fridge.changeCoolingBoxTemperature(theColdness, theHotness)
	 * 			|		 fridge.addIngredientFrom(container)
	 * 			|		 fridge.executeAlchemicOperation()
	 * 			|		 IngredientContainer resultContainer = fridge.removeAlchemicResult()
	 * 			|		 AlchemicIngredient result = resultContainer.getAlchemicIngredient()
	 * 			|		 resultContainer.delete()
	 */
	private AlchemicIngredient useCoolingBox(IngredientContainer container) throws NoSuchInLabDeviceException{
		if(!isValidCoolingBoxAddition()) {
			CoolingBox fridge = getCoolingBox();
			AlchemicIngredient ingredient = container.getAlchemicIngredient();
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
			throw new NoSuchInLabDeviceException("There is no coolingbox in this laboratory!", this); 
		}
	}
	
	/**
	 * Bring the alchemic ingredient from a given ingredient container to its standard temperature by the use of an Oven or a CoolingBox.
	 * 
	 *
	 * @param	fromContainer
	 * 			The ingredient container out of which the ingredient must be brought to its standard temperature	
	 * @return	If this ingredient from this container already has its standard temperature, return the ingredient
	 * 			Otherwise if the coldness of the ingredient is greater than its standard coldness or the hotness of the ingredient is smaller than its standard hotness, use an oven to bring it back to its standard temperature, return the adapted ingredient
	 * 					otherwise use a coolingBox, return the adapted ingredient
	 * 			| if(hasStandardTemperature(ingredient))
	 * 			| then result == ingredient;
	 * 			| else 
	 * 			| 		if (ingredient.getTemperature().getColdness() > ingredient.getStandardTemperature().getColdness()) || ingredient.getTemperature().getHotness() < ingredient.getStandardTemperature().getHotness())
	 * 			|			then adaptedIngredient = useOven(fromContainer);
	 * 			|				 result == adaptedIngredient
	 * 			|		else
	 * 			| 			then adaptedIngredient = useCoolingBox(fromContainer);
	 * 			|				 result == adaptedIngredient
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
	 * Remove the given ingredient from the list of ingredients.
	 * 
	 * @param	ingredient
	 * 			The alchemic ingredient that will be removed from the list of ingredients
	 * @post 	Search the given ingredient in the list of ingredients of this laboratory and remove the ingredient from the list
	 * 			| for an i in listOfIngredients.size()
	 * 			| 	if (listOfIngredients.get(i) ==  ingredient)
	 * 			|		listOfIngredients.remove(i)	
	 */
	protected void removeIngredient(AlchemicIngredient ingredient) {
		for (int i = 0; i < listOfIngredients.size(); i++) {
			if (listOfIngredients.get(i) ==  ingredient) {
				listOfIngredients.remove(i);
			}
		}
	}
	

	
	/**
	 * Combine two alchemic ingredients with use of a kettle.  
	 *
	 * @param	ingredientFromList
	 * 			One of the alchemic ingredients that needs to be combined
	 * @param 	ingrToAdd
	 * 			The other alchemic ingredient that needs to be add
	 * @return	Create two new containers, each with a given alchemic ingredient. Combine the ingredients with the kettle from this laboratory.
	 * 			Return the ingredient from the container.
	 * 			|IngredientContainer container1 = new IngredientContainer(ingredientFromList,1,ingredientFromList.determineCapUnit(),ingredientFromList.getState());
	 * 			|IngredientContainer newContainer = new IngredientContainer(ingrToAdd,1,ingrToAdd.determineCapUnit(),ingrToAdd.getState());		
	 *			|Kettle kettle = this.getKettle();
	 *			|kettle.addIngredientFrom(container1);
	 *			|kettle.addIngredientFrom(newContainer);
	 *			|kettle.executeAlchemicOperation();
	 *			|IngredientContainer resultContainer = kettle.removeAlchemicResult();
	 *			|result == resultContainer.getAlchemicIngredient();
	 *			|resultContainer.delete();
	 */
	private AlchemicIngredient combineAmounts(AlchemicIngredient ingredientFromList, AlchemicIngredient ingrToAdd) {
		IngredientContainer container1 = new IngredientContainer(ingredientFromList,1,ingredientFromList.determineCapUnit(),ingredientFromList.getState());
		IngredientContainer newContainer = new IngredientContainer(ingrToAdd,1,ingrToAdd.determineCapUnit(),ingrToAdd.getState());
		Kettle kettle = this.getKettle();
		kettle.addIngredientFrom(container1);
		kettle.addIngredientFrom(newContainer);
		kettle.executeAlchemicOperation();
		IngredientContainer resultContainer = kettle.removeAlchemicResult();
		AlchemicIngredient result = resultContainer.getAlchemicIngredient();
		resultContainer.delete();
		return result;

	}
	
	/**
	 * Store the alchemic ingredient from the given container in this laboratory.
	 *
	 * @param	fromContainer
	 * 			The ingredient container that contains the alchemic ingredient that needs to be stored in this laboratory
	 * @effect	Bring the alchemic ingredient from the given ingredient container to its standard temperature
	 * 			| AlchemicIngredient ingredientToBeAdded = ingredientBroughtToStandardTemp(fromContainer)
	 * @effect 	If there is enough storage available in this laboratory, if there is an alchemic ingredient in the list 
	 * 			with the same ingredient type as the given alchemic ingredient, combine both ingredients to a new ingredient. 
	 * 			Terminate both ingredients and add the new ingredient to the list of this laboratory. Adapt the available capacity of this laboratory.
	 * 			If there exists no alchemic ingredient in this laboratory with the same ingredient type, add the alchemic ingredient from the given
	 * 			container to the list of this laboratory. Adapt the available capacity of this laboratory.
	 * 			| if(isValidSubstraction(ingredientToBeAdded.getQuantity(),ingredientToBeAdded.getUnit())
	 * 			|		for an i in listOfIngredients
	 * 			|			if (listOfIngredients.get(i).getCompleteName() == ingredientToBeAdded.getCompleteName())
	 * 			|				then AlchemicIngredient result = this.combineAmounts(listOfIngredients.get(i), ingredientToBeAdded);
	 * 			| 			 		 listOfIngredients.remove(i);
	 * 			|					 listOfIngredients.get(i).terminate();
	 * 			| 					 ingredientToBeAdded.terminate();
	 * 			|			 		 listOfIngredients.add(result);	
	 * 			|			 		 this.substractFromCapacityLabo(ingredientToBeAdded.getQuantity(), ingredientToBeAdded.getUnit())
	 * 			|			else
	 * 			|			   	then listOfIngredients.add(ingredientToBeAdded);
	 * 			|					 this.substractFromCapacityLabo(ingredientToBeAdded.getQuantity(), ingredientToBeAdded.getUnit());
	 * @throws	IllegalSubstractionException("Illegal substraction of the available capacity of this laboratory",this)
	 * 			There is not enough place to store the alchemic ingredient from the given container in this laboratory
	 * 			| !isValidSubstraction(ingredientToBeAdded.getQuantity(),ingredientToBeAdded.getUnit())
	 */
	public void storeNewIngredient(IngredientContainer fromContainer) throws IllegalSubstractionException{
		AlchemicIngredient ingredientToBeAdded = ingredientBroughtToStandardTemp(fromContainer);
		if(isValidSubstraction(ingredientToBeAdded.getQuantity(),ingredientToBeAdded.getUnit())) {
			for (int i = 0; i < listOfIngredients.size(); i++) {
				if (listOfIngredients.get(i).getCompleteName() == ingredientToBeAdded.getCompleteName()) {
					AlchemicIngredient result = this.combineAmounts(listOfIngredients.get(i), ingredientToBeAdded);
					listOfIngredients.remove(i);
					listOfIngredients.get(i).terminate();
					ingredientToBeAdded.terminate();
					listOfIngredients.add(result);	
					this.substractFromCapacityLabo(ingredientToBeAdded.getQuantity(), ingredientToBeAdded.getUnit());
				}
				else {
					listOfIngredients.add(ingredientToBeAdded);
					this.substractFromCapacityLabo(ingredientToBeAdded.getQuantity(), ingredientToBeAdded.getUnit());
				}
			}
		}
		else {
			throw new IllegalSubstractionException("Illegal substraction of the available capacity of this laboratory ",this);
		}
		
	}
	

	
	/**
	 * Return the alchemic ingredient based on its given complete name.
	 *
	 * @param	ingredientCompleteName
	 * 			The complete name of the AlchemicIngredient
	 * @post 	If there is an ingredient in this laboratory with given complete name, then search the ingredient
	 * 			| if (isIngredientPresentInLab(ingredientCompleteName))
	 * 			| 	 for an i in listOfIngredients.size()
	 * 			|		if (listOfIngredients.get(i).getCompleteName() ==  ingredientCompleteName)
	 * 			|			then ingr = listOfIngredients.get(i);
	 * @throws	IngredientNotPresentInLabException("There is no ingredient present in this laboratory with the given complete name", this)
	 * 			There is no ingredient present in this laboratory with the given complete name
	 * 			|!isIngredientPresentInLab(ingredientCompleteName)
	 * @return	The alchemic ingredient in this laboratory with the given complete name
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
			throw new IngredientNotPresentInLabException("There is no ingredient present in this laboratory with the given complete name", this);
		}
	}
	
	/**
	 * Return an ingredient container with the full amount of alchemic ingredient that has the given complete name. 
	 *
	 * @param	ingrCompleteName
	 * 			The complete name of the ingredient 
	 * @post	Get the ingredient in this lab with given complete name
	 * 			| AlchemicIngredient ingr = this.getIngredientFromName(ingrCompleteName)
	 * @post	If this is a valid addition to the available capacity of this laboratory, then remove this ingredient from this lab.
	 * 			Create a new container with this ingredient and adapt the available laboratory capacity.
	 * 			| if (isValidAddition(ingr.getQuantity(), ingr.getUnit()))
	 * 			|	then this.removeIngredient(ingr);
	 * 			|		 IngredientContainer container = new IngredientContainer(ingr,1,ingr.determineCapUnit(),ingr.getState())
	 * 			|		 this.addToCapacityLabo(ingr.getQuantity(), ingr.getUnit())
	 * @throws	IllegalAdditionException("This is an illegal addition to the available capacity of this laboratory",this)
	 * 			This is an illegal addition to the available capacity of this laboratory
	 * 			| !(isValidAddition(ingr.getQuantity(), ingr.getUnit())
	 * @return	The ingredient container that contains the full amount of alchemic ingredient with given complete name.
	 */
	protected IngredientContainer getFullAmountFromLabo(String ingrCompleteName) throws IllegalAdditionException{
		AlchemicIngredient ingr = this.getIngredientFromName(ingrCompleteName);
		if (isValidAddition(ingr.getQuantity(), ingr.getUnit())) {
			this.removeIngredient(ingr);
			IngredientContainer container = new IngredientContainer(ingr,1,ingr.determineCapUnit(),ingr.getState());
			this.addToCapacityLabo(ingr.getQuantity(), ingr.getUnit());
			return container;
		}
		else {
			throw new IllegalAdditionException("This is an illegal addition to the available capacity of this laboratory",this);
		}
	}
	
	/**
	 * Check whether an alchemic ingredient with given name is present in this laboratory.
	 *
	 * @param	ingredientName
	 * 			The alchemic ingredient's complete name to be checked.
	 * @return	True if the list of ingredients in this laboratory contains an alchemic ingredient with the same name
	 * 			| boolean flag = false
	 * 			| for an i in in listOfIngredients.size()
	 * 			|	if(ingredientName.equals(getIngredients().get(i).getCompleteName())
	 * 			|		then flag = true
	 */
	public boolean isIngredientPresentInLab(String ingredientName) {
		boolean flag = false;
		for(int i= 0; i<listOfIngredients.size(); i++) {
			if(ingredientName.equals(getIngredients().get(i).getCompleteName())) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * Substract a given amount from the available capacity in this laboratory.
	 *
	 * @param	amount
	 * 			The amount that needs to be substract
	 * @param 	unit
	 * 			The unit of the given amount
	 * @post	Get the given amount measured in spoons
	 * 			| int substraction = this.getAmountInSpoons(amount, unit);
	 * @post	Substract the given amount in spoons from the available capacity (in spoons) in this laboratory
	 * 			| long result = this.getAvailableCapInSpoons() - substraction
	 * @effect	Adapt the available capacity in this laboratory
	 * 			| this.changeAvailableCapInSpoons(result)
	 */
	private void substractFromCapacityLabo(int amount,String unit) {
			int substraction = this.getAmountInSpoons(amount, unit);
			long result = this.getAvailableCapInSpoons() - substraction;
			this.changeAvailableCapInSpoons(result);
		}
		
	/**
	 * Check whether the given amount measured in the given unit can be substracted from the available capacity in this laboratory.
	 *
	 * @param	amount
	 * 			The amount that needs to be checked
	 * @param 	unit
	 * 			The unit of the given amount that needs to be checked
	 * @post	Get the given amount measured in spoons
	 * 			| int substraction = this.getAmountInSpoons(amount, unit);
	 * @return 	True if the available capacity (in spoons) from this laboratory is greater then the amount (in spoons) that needs to be substracted
	 * 
	 * 
	 * 			and if the substraction is greater than or equal to zero
	 * 			| result ==	
	 * 			| 		(this.getAvailableCapInSpoons() >= substraction) && (substraction >= 0)
	 */
	public boolean isValidSubstraction(int amount,String unit) {
		int substraction = this.getAmountInSpoons(amount, unit);
		return ((this.getAvailableCapInSpoons() >= substraction ) && (substraction >= 0));
	}
	
	/**
	 * Check whether the given amount measured in the given unit can be added to the available capacity in this laboratory.
	 *
	 * @param	amount
	 * 			The amount that needs to be checked
	 * @param 	unit
	 * 			The unit of the given amount that needs to be checked
	 * @post	Get the given amount measured in spoons
	 * 			| int addition = this.getAmountInSpoons(amount, unit);
	 * @return 	True if the capacity (in spoons) of this laboratory is greater than or equal to the available capacity (in spoons) of this laboratory plus the addition (in spoons)
	 * 			and if the addition is greater than or equal to zero
	 * 			| result ==	
	 * 			| 		(this.getCapacityInSpoons() >= this.getAvailableCapInSpoons() + addition) && (addition >= 0)
	 */
	public boolean isValidAddition(int amount,String unit) {
		int addition = this.getAmountInSpoons(amount, unit);
		return ((this.getCapacityInSpoons() >= this.getAvailableCapInSpoons() + addition) && (addition >= 0));
	}
	
	/**
	 * Add a given amount to the available capacity in this laboratory.
	 *
	 * @param	amount
	 * 			The amount that needs to be added
	 * @param 	unit
	 * 			The unit of the given amount
	 * @post	Get the given amount measured in spoons
	 * 			| int addition = this.getAmountInSpoons(amount, unit);
	 * @post	Add the given amount in spoons to the available capacity (in spoons) in this laboratory
	 * 			| long result = this.getAvailableCapInSpoons() - addition
	 * @effect	Adapt the available capacity in this laboratory
	 * 			| this.changeAvailableCapInSpoons(result)
	 */
	private void addToCapacityLabo(int amount, String unit) {
		int addition = this.getAmountInSpoons(amount, unit);
		long result = this.getAvailableCapInSpoons() + addition;
		this.changeAvailableCapInSpoons(result);
	}
	
	/**
	 * Return a given amount (measured in its given unit) in spoons
	 *
	 * @param	amount
	 * 			The amount 
	 * @param 	unit
	 * 			The unit of the given amount
	 * @return	Return the amount in spoons using the Map unitInSpoons
	 * 			| for every entry in unitInSpoons
	 * 			|	if (entry.getKey() == unit)
	 * 			|		then result = result * entry.getValue()
	 */
	public int getAmountInSpoons(int amount, String unit) {
		int result = amount;
			for (Map.Entry<String,Integer> entry: unitInSpoons.entrySet()) {
				if (entry.getKey() == unit) {
					result = result * entry.getValue();
				}
			}
		
		return result;
	}
	
	/**
	 * This map states the different transitions between a unit and a spoon.
	 */
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
	
	/**
	 * Return an ingredient container that contains an alchemic ingredient with given name, amount and unit
	 * 
	 * @param 	ingrName
	 * 			The name of the alchemic ingredient
	 * @param	amount
	 * 			The amount of the alchemic ingredient
	 * @param	unit
	 * 			The unit of the alchemic ingredient
	 * @post 	Get the alchemic ingredient from this laboratory with given name
	 * 			| AlchemicIngredient ingr = this.getIngredientFromName(ingrName);
	 * @throws	IllegalAdditionException("This is an illegal addition to the available capacity of this laboratory",this)
	 * 			This is an illegal addition to the available capacity of this laboratory
	 * 			| !(isValidAddition(amount,unit)
	 * @throws	InvalidLaboratoryAmountException("There is not enough ingredient in this laboratory", this)
	 * 			There is not enough alchemic ingredient with given name in this laboratory to return a given amount measured in its given unit
	 * 			| !(this.getAmountInSpoons(amount, unit) < ingr.getQuantityInSpoons()) || !(ingr.getQuantityInSpoons() == this.getAmountInSpoons(amount, unit))
	 * @return	If this is a valid addition of a given amount with given unit to the available capacity of this laboratory, then check if there is enough ingredient in this laboratory.
	 * 			If there is enough alchemic ingredient in this laboratory, then put the asked amount in a new container en put the rest of the ingredient back in this laboratory, return the container.
	 * 			If the asked amount of an ingredient is the exact amount available in this laboratory, take the full ingredient en put it in a new container, return the container.
	 * 			| if(isValidAddition(amount,unit))
	 * 			|	if((this.getAmountInSpoons(amount, unit) < ingr.getQuantityInSpoons())
	 * 			|		then AlchemicIngredient ingrForLabo = new AlchemicIngredient(ingr.getQuantityInSpoons()-this.getAmountInSpoons(amount, unit),"spoon",ingr.getIngredientTypeList(),ingr.getTemperature().getHotness(), ingr.getTemperature().getColdness(),ingr.getState(),ingr.getSpecialName());
	 * 			|			 AlchemicIngredient ingrToReturn = new AlchemicIngredient(amount, unit,ingr.getIngredientTypeList(),ingr.getTemperature().getHotness(), ingr.getTemperature().getColdness(),ingr.getState(),ingr.getSpecialName());
	 * 			|			 this.removeIngredient(ingr);
	 * 			|			 ingr.terminate();
	 * 			|			 listOfIngredients.add(ingrForLabo);
	 * 			|			 this.addToCapacityLabo(amount, unit);
	 * 			|			 result == new IngredientContainer(ingrToReturn,1,ingrToReturn.determineCapUnit(),ingrToReturn.getState());
	 * 			|	else	 
	 * 			|		then result == this.getFullAmountFromLabo(ingrName)
	 * 			 
	 */
	public IngredientContainer getAmountFromLabo(String ingrName, int amount, String unit) throws InvalidLaboratoryAmountException, InvalidAdditionException{
		AlchemicIngredient ingr = this.getIngredientFromName(ingrName);
		if(isValidAddition(amount,unit)) {
				if (this.getAmountInSpoons(amount, unit) < ingr.getQuantityInSpoons()) {
					AlchemicIngredient ingrForLabo = new AlchemicIngredient(ingr.getQuantityInSpoons()-this.getAmountInSpoons(amount, unit),"spoon",ingr.getIngredientTypeList(),ingr.getTemperature().getHotness(), ingr.getTemperature().getColdness(),ingr.getState(),ingr.getSpecialName());
					AlchemicIngredient ingrToReturn = new AlchemicIngredient(amount, unit,ingr.getIngredientTypeList(),ingr.getTemperature().getHotness(), ingr.getTemperature().getColdness(),ingr.getState(),ingr.getSpecialName());
					this.removeIngredient(ingr);
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
			throw new InvalidAdditionException("This is an illegal addition to the available capacity of this laboratory", this);
				}
	}
	
	
	
	
	/**
	 * Variable referencing the catalog of this laboratory
	 */
	private Map<String, String> catalog = new HashMap<String, String>();

	
	
	/**
	 * Return a catalog of all the ingredients in this laboratory, where the name of the ingredient is the key, the amount and unit are the values.
	 * 
	 * @return	The catalog of all the ingredients in this laboratory, where the name of the ingredient is the key, the amount and unit are the values.
	 * 			|for i in listOfIngredients
	 * 			|	String name = listOfIngredients.get(i).getCompleteName();
	 * 			|	int amount = listOfIngredients.get(i).getQuantity();
	 * 			|	String unit = listOfIngredients.get(i).getUnit();
	 * 			|	String amoundAndUnit = String.valueOf(amount) + unit;
	 * 			|	catalog.put(name, amoundAndUnit);
	 * 			| 	result == catalog
	 */
	public Map<String, String> getCatalog(){
		for (int i = 0; i < listOfIngredients.size(); i++) {
			 String name = listOfIngredients.get(i).getCompleteName();
			 int amount = listOfIngredients.get(i).getQuantity();
			 String unit = listOfIngredients.get(i).getUnit();
			 String amoundAndUnit = String.valueOf(amount) + unit;
			 catalog.put(name, amoundAndUnit);
		}
		return catalog;
	}

	
	/**
	 * Checks whether there is enough ingredient in this laboratory.
	 *
	 * @param	ingredientCompleteName
	 * 			The name of the ingredient
	 * @param	amount
	 * 			The amount to get from this ingredient
	 * @param	unit
	 * 			The unit of the ingredient
	 * @return	True if the amount is a positive number and if it doesn't exceed the stock amount of ingredient.
	 * 			| ((amount >= 0) && (this.getAmountInSpoons(amount, unit) <= getFullAmountFromLabo(ingredientCompleteName).getAlchemicIngredient().getQuantityInSpoons()))
	 */

	private boolean isValidAmount(String ingredientCompleteName, int amount, String unit) {
		return ((amount > 0) && (this.getAmountInSpoons(amount, unit) <= getFullAmountFromLabo(ingredientCompleteName).getAlchemicIngredient().getQuantityInSpoons()));
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
	
	

	
	public void execute(Recipe recipe,int number)  {
		if (recipe.getOperationList().get(recipe.getOperationList().size() -1) != "mix" ){
			recipe.getOperationList().add("mix");
		}
		
		for (int i = 0; i < recipe.getOperationList().size(); i++) {
			 if (recipe.getOperationList().get(i) == "Add") {
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
				  IngredientContainer result = this.heat(this.getRecipeList().get(lastUsed)); 
				  this.getRecipeList().remove(lastUsed);
				  this.getRecipeList().add(result);
			  }
			  else if(recipe.getOperationList().get(i) == "cool") {
				  IngredientContainer result = this.cool(this.getRecipeList().get(lastUsed)); 
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
				this.storeNewIngredient(this.getAmountFromLabo(recipe.getNameIngredientList().get(i),number*(recipe.getAmountList().get(i)),recipe.getUnitList().get(i)));
			}
		}
		
		for (int i = 0; i < this.getRecipeList().size(); i++) {
			this.storeNewIngredient(this.getRecipeList().get(i));
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
