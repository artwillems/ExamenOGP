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
	@Basic
	public long getCapacity() {
		return this.capacity;
	}

	/**
	 * Checks whether the capacity for this laboratory is greater than or equal to zero
	 *
	 * @param	capacity
	 * 		  	The capacity for this laboratory
	 * @return 	True if the capacity is larger than zero and smaller than or equal to the largest possible number.
	 * 		   	| if(capacity >= 0)
	 * 		  	| return true
	 */

	private boolean isValidLaboCapacity(long capacity) {
		return (capacity > 0 && capacity <= Long.MAX_VALUE);
	}

	/************************************
	 * Ingredients
	 ********************************/

	/**
	 * Variable referencing all the ingredients present in the laboratory
	 */

	private List<AlchemicIngredient> listOfIngredients = new ArrayList<AlchemicIngredient>();

	/**
	 * Check if the different ingredient types in the laboratory are unique, meaning there is not two times
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
	@Basic
	protected List<AlchemicIngredient> getIngredients(){
		return this.listOfIngredients;
	}

	/**************************************************
	 * Devices
	 **************************/

	/**
	 * Variable referencing the Oven in this lab.
	 */

	private Oven ovenInLab = null;


	/**
	 * Initialize a new oven for 
	 * 
	 * @param ovenInLab
	 * @throws
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
	 * Checks whether there is already an oven present in this Laboratory.
	 * 
	 * @return 	True if there is no oven already present in the lab.
	 * 			| this.ovenInLab.equals(null);
	 */
	
	private boolean isValidOvenAddition() {
		return this.ovenInLab.equals(null); 
	}
	
	/**
	 * Add a new oven to this laboratory
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
	 * Checks if there is already a Transmogrifier present in this Laboratory.
	 * 
	 * @return	true if there is no transmogrifier already present in this lab, false otherwise
	 * 			|this.transmogrifierInLab.equals(null)		
	 */
	
	private boolean transmogCanBeAdded() {
		return this.transmogrifierInLab.equals(null); 
	}

	/**
	 * Initialize a new Transmogrifier for this laboratory
	 * 
	 * @param	transmogrifierInLab
	 * 			the transmogrifier that needs to be added to this laboratory
	 * @throws
	 */
	
	private void setTransmogrifier(Transmogrifier transmogrifierInLab) throws AlreadyDeviceInLabException {
		if(transmogCanBeAdded()) {
			this.transmogrifierInLab = transmogrifierInLab;
		}
		else {
			throw new AlreadyDeviceInLabException("This lab already has a transmogrifier",this);
		}
	}

	/**
	 * Add a new Transmogrifier to this Laboratory
	 * 
	 * @param	transmo
	 * 			The transmogrifier that needs to be added to this Laboratory		
	 */
	
	public void addTransmogrifier(Transmogrifier transmo) {
		setTransmogrifier(transmo); 
	}
	
	/**
	 * Variable referencing the CoolingBox in this lab
	 */

	private CoolingBox coolingBoxInLab = null;
	
	/**
	 * Checks whether a CoolingBox can be added to this laboratory
	 * 
	 * @return	True if there is not already a CoolingBox present in this laboratory
	 * 			|this.coolingBoxInLab.equals(null)
	 */
	
	private boolean isValidCoolingAddition() {
		return this.coolingBoxInLab.equals(null); 
	}

	/**
	 * Initialize a new CoolingBox for this Laboratory
	 * 
	 * @param	coolingBoxInLab
	 * 			The CoolingBox that needs to be added to the laboratory. 		
	 */
	
	private void setCoolingBox(CoolingBox coolingBoxInLab) throws AlreadyDeviceInLabException{
		if(isValidCoolingAddition()) {
			this.coolingBoxInLab = coolingBoxInLab;
		}
		else {
			throw new AlreadyDeviceInLabException("This laboratorium already has a coolingbox",this);
		}
	}
	
	/**
	 * Add a new CoolingBox to this laboratory
	 * 
	 * @param	coolingBox
	 * 			The coolingbox to be added to this Laboratory
	 */
	
	public void addCoolingBox(CoolingBox coolingBox) {
		setCoolingBox(coolingBox); 
	}

	
	private Kettle kettleInLab = null;

	private void setKettle(Kettle kettleInLab) throws AlreadyDeviceInLabException {
		if (isValidKettle(kettleInLab)) {
			this.kettleInLab = kettleInLab;
		}
		else {
			throw new AlreadyDeviceInLabException("This laboratorium already has a kettle",this);
		}
	}
	
	public void addKettle(Kettle kettle) {
		setKettle(kettle);
	}
		
		
		
	private boolean canKettleBeAdded(){
		return this.kettleInLab.equals(null); 
	}
	
	public Kettle getKettle(){
		return this.kettleInLab; 
	}
	

	/**
	 * Seek an oven in this laboratory
	 * NOTE: Voorlopig is het Immutable, kan nog veranderen naargelang implementatie
	 *
	 * @return	the first oven that is present in this laboratory
	 */
	@Immutable @Basic
	public Oven getOven() {
		return this.ovenInLab; 
	}

	/**
	 * Seek a CoolingBox in this laboratory
	 *
	 * @return	The first CoolingBox that is found in this laboratory.
	 */
	@Immutable @Basic
	public CoolingBox getCoolingBox() {
		return this.coolingBoxInLab; 
	}

	/**
	 * Seek a transmogrifier in this laboratory
	 *
	 * @return The first tranmogrifier that is found in the laboratory
	 */
	@Immutable @Basic 
	public Transmogrifier getTransmogrifier() {
		return this.transmogrifierInLab; 
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
			AlchemicIngredient result = resultContainer..getAlchemicIngredient();
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
			long theHotness = ingredient.getStandardTemperature().getHotness();
			long theColdness = ingredient.getStandardTemperature().getColdness();
			fridge.changeCoolingBoxTemperature(theColdness, theHotness);
			fridge.addIngredientFrom(container);
			fridge.executeAlchemicOperation();
			AlchemicIngredient result = fridge.removeAlchemicResult().getAlchemicIngredient();
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

			/*bring the ingredient back to its standard temperature using an oven or cooling box*/
			AlchemicIngredient adaptedIngredient;
			if(ingredient.getTemperature().getHotness() < ingredient.getStandardTemperature().getHotness()) {

				
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
	 * Add a new ingredient to the list of ingredients that already exists for this laboratory
	 *
	 * @param	ingredient
	 * 			The ingredient that needs to be added to the laboratory.
	 */

	protected void addIngredient(AlchemicIngredient ingredient) {
		getIngredients().add(ingredient);
	}
	
	/**
	 * Combine all the amounts of a certain AlchemicIngredient in this laboratory into one single amount
	 * expressed in spoons.
	 *
	 * @param	ingredient
	 * 			The AlchemicIngredient for which all its amounts are combined into one.
	 * @return	The total amount of that certain ingredient present in the laboratory.
	 */
	@Model
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
	 * Store a new AlchemicIngredient in this laboratory.
	 *
	 * @param	fromContainer
	 * 			The IngredientContainer in which the AlchemicIngredient arrived.
	 * @throws	InvalidLaboratoryAmountException
	 * 			If the combined amount of the ingredient to be added to this laboratory exceeds the capacity of the laboratory
	 * 			a new invalid laboratory amount exception is thrown.
	 * 			| throw new InvalidLaboratoryAmountException
	 */
	
	public void storeNewIngredient(IngredientContainer fromContainer) throws InvalidLaboratoryAmountException{
		AlchemicIngredient ingredientToBeAdded = ingredientBroughtToStandardTemp(fromContainer);
		if(isValidNewAmount(ingredientToBeAdded)) {
			addIngredient(ingredientToBeAdded);
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
				throw new InvalidLaboratoryAmountException("The amount of ingredient you want to add, exceeds the laboratory capacity", this);
			}
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
