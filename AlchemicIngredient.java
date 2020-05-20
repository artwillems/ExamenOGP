import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import be.kuleuven.cs.som.annotate.*;

public class AlchemicIngredient {
	/**********************************************************
     * Constructors
     **********************************************************/
	
	/**
	 * Initialize a new ingredient with a quantity, unit, ingredientType list, 
	 * hotness, coldness, state and special name.
	 * 
	 * @param 	quantity
	 * 			The quantity of the new ingredient.	
	 * @param 	unit
	 * 			The unit of the new ingredient in which the quantity is measured.
	 * @param 	ingredientTypeList
	 * 			The ingredientType list of the new ingredient.
	 * @param 	hotness
	 * 			The hotness of the new ingredient.
	 * @param 	coldness
	 * 			The coldness of the new ingredient.
	 * @param 	state
	 * 			The state of the new ingredient.
	 * @param 	specialName
	 * 			The special name of the new ingredient.
	 * @effect	The ingredientType list is set to the given ingredientType list (must be valid)
	 * 			| setIngredientTypeList(ingredientTypeList)
	 * @effect	The state is set to the given state (must be valid)
	 * 			| setState(state)
	 * @effect	The quantity is set to the given quantity (must be valid)
	 * 			| setQuantity(quantity)
	 * @effect	The unit is set to the given unit.
	 * 			| setUnit(unit)
	 * @effect	The temperature is set to the given hotness and coldness (must be valid)
	 * 			| setTemperature(hotness,coldness)
	 * @effect	The special name is set to the given name (must be valid)
	 * 			| setSpecialName(specialName)
	 */
	protected AlchemicIngredient(int quantity,String unit, List<IngredientType> ingredientTypeList, long hotness, long coldness, String state, String specialName) {
		setIngredientTypeList(ingredientTypeList);
		setState(state);
		setQuantity(quantity);
		setUnit(unit);
		setTemperature(coldness,hotness);
		setSpecialName(specialName);
		
		
		
	}
	
	
	
	/**
	 * Initialize a new ingredient with a quantity, unit and ingredientType list, 
	 * 
	 * @param 	quantity
	 * 			The quantity of the new ingredient.	
	 * @param 	unit
	 * 			The unit of the new ingredient in which the quantity is measured.
	 * @param 	ingredientTypeList
	 * 			The ingredientType list of the new ingredient.
	 * @param	coldness
	 * 			The coldness of the new ingredient.
	 * @param 	hotness
	 * 			The hotness of the new ingredient.
	 * @pre		The ingredietTypeList can only consist of one ingredientType;
	 * @effect	This new ingredient is initialized with a quantity, unit, ingredientTypeList, coldness, hotness
	 * 			and without a special name (null)
	 * 			| this(quantity,unit,ingredientTypeList,coldness,hotness,"Powder",null);
	 * @effect 	The state is set to the standard state of its ingredientType
	 * 			| setState(ingredientTypeList.get(0).getState())
	 * 
	 */
	public AlchemicIngredient(int quantity, String unit, List<IngredientType> ingredientTypeList,long coldness,long hotness) {
			this(quantity,unit,ingredientTypeList,coldness,hotness,"Powder");
			setState(ingredientTypeList.get(0).getState());
		}
		
	
	/**
	 * Initialize a new ingredient with a quantity, unit, ingredientType list, 
	 * hotness, coldness and state.
	 * 
	 * @param 	quantity
	 * 			The quantity of the new ingredient.	
	 * @param 	unit
	 * 			The unit of the new ingredient in which the quantity is measured.
	 * @param 	ingredientTypeList
	 * 			The ingredientType list of the new ingredient.
	 * @param 	hotness
	 * 			The hotness of the new ingredient.
	 * @param 	coldness
	 * 			The coldness of the new ingredient.
	 * @param 	state
	 * 			The state of the new ingredient.
	 *@effect	This new ingredient is initialized with a given quantity, unit, ingredientType list, hotness,
	 *			coldness, state and without a special name.
	 *			| this(quantity,unit,ingredientTypeList, hotness, coldness, state ,null)

	 */
	protected AlchemicIngredient(int quantity, String unit, List<IngredientType> ingredientTypeList,long hotness, long coldness, String state) {
		this(quantity,unit,ingredientTypeList, hotness, coldness, state ,null); 
	}
	
	/**
	 * Initialize a new ingredient with a quantity, ingredientType list, 
	 * hotness, coldness, state and special name.
	 * 
	 * @param 	quantity
	 * 			The quantity of the new ingredient.	
	 * @param 	ingredientTypeList
	 * 			The ingredientType list of the new ingredient.
	 * @param 	hotness
	 * 			The hotness of the new ingredient.
	 * @param 	coldness
	 * 			The coldness of the new ingredient.
	 * @param 	state
	 * 			The state of the new ingredient.
	 *@effect	This new ingredient is initialized with a given quantity, a spoon unit, ingredientType list, hotness,
	 *			coldness, state and without a special name.
	 *			| this(quantity,"spoon",ingredientTypeList, hotness, coldness, state ,null)

	 */
	protected AlchemicIngredient(int quantity, List<IngredientType> ingredientTypeList, long hotness, long coldness, String state, String specialName) {
		this(quantity, "spoon", ingredientTypeList, hotness, coldness, state, specialName);
	}
	
	
	/**
	 * This new ingredient is initialized as an ingredient of the ingredientType water with a given 
	 * quantity.
	 * 
	 * @param 	quantity
	 * 			The quantity of the new ingredient.
	 * @effect	This new ingredient is initialized with a given quantity, a spoon unit,
	 * 			the ingredientType water, standard temperature, Liquid state and without a special name (null)
	 * @effect	The temperature is set to the standard temperature of water
	 * 			| setTemperature(water.getTemperature().get(1), water.getTemperature().get(0))
	 */
	public AlchemicIngredient(int quantity) {
		this(quantity, "spoon", null, (long) 0, (long) 20, "Liquid",null);
		
	} 
	
	
	
	
	
	
	/**********************************************************
     * Quantity
     **********************************************************/
	/**
	 * Variable referencing the quantity of this ingredient.
	 */
	private int quantity = 0;
	
	/**
	 * Variable referencing the maximum quantity of an ingredient.
	 */
	private static int maximumQuantity = Integer.MAX_VALUE;
	
	
	
	/**
	 * Return the quantity of this ingredient measured in its own unit.
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	
	/**
	 * Set the quantity of this ingredient to the given quantity
	 * 
	 * @param 	quantity 
	 * 			The new quantity for this file.
	 * @pre 	The given quantity must be legal
	 * 			| isCorrectQuantity(quantity)
	 * @post	The given quantity is registered as the quantity of this ingredient.
	 * 			| new.getQuantity() == quantity
	 */
	private void setQuantity(int quantity) {
		this.quantity = quantity;
		}
	
	/**
	 * Check whether the given quantity is a valid quantity for an ingredient.
	 * 
	 * @param 	quantity
	 * 			The quantity to check.
	 * @return	True if and only if the given quantity is strictly positive and 
	 * 			does not exceed the maximum quantity.
	 * 			| result == ((quantity > 0) && (quantity <= getMaximumQuantity()))
	 */
	public boolean isCorrectQuantity(int quantity) {
		return ((quantity > 0) && (quantity <= getMaximumQuantity()));
	}
	
	/**
	 * Return the maximum quantity of an ingredient.
	 */
	public int getMaximumQuantity() {
		return maximumQuantity;
	}
	
	/**
	 * Returns the quantity of this ingredient measured in the unit of spoons.
	 */
	public int getQuantityInSpoons() {
		int result = getQuantity();
		if (getState()=="Liquid") {
			for (Map.Entry<String,Integer> entry: liquidLibrary.entrySet()) {
				if (entry.getKey() == this.unit) {
					result = result * entry.getValue();
				}
			}
		}
		else {
			for(Map.Entry<String, Integer> entry: powderLibrary.entrySet()) {
				if (entry.getKey() == this.unit) {
					result = result * entry.getValue();
				}
			}
		}
		return result;
	}
	
	
	private static Map<String,Integer> liquidLibrary = new HashMap<String,Integer>(){

		private static final long serialVersionUID = 1L;

		{
			put("drop",1/8);
			put("vial",5);
			put("bottle",15);
			put("spoon",1);
			put("jug",105);
			put("barrel",1260);
			put("storeroom",6300);
		}
	};
	
	private static Map<String,Integer> powderLibrary = new HashMap<String,Integer>(){
		
		private static final long serialVersionUID = 1L;

		{
			put("pinch",1/6);
			put("sachet",7);
			put("box",42);
			put("sack",126);
			put("chest",1260);
			put("storeroom",6300);
			put("spoon",1);
		}
	};
	
	
	
	
	
	/**********************************************************
     * Terminated
     **********************************************************/
	
	
	/**
	 * Variable referencing whether or not an AlchemicIngredient has been termintated. 
	 */
	private boolean terminated = false;
	
	
	/**
	 * Set the termination of this ingredientType.
	 * 
	 * @post	If the AlchemicIngredient hasn't already been terminated,
	 * 			the termination is set to true.
	 * @throws 	IngredientAlreadyTerminatedException
	 */
	private void setTerminated() throws IngredientAlreadyTerminatedException {
		if (!isTerminated()) {
			this.terminated = true;
		}
		else {
			throw new IngredientAlreadyTerminatedException("This ingredient has already been terminated",this);
		}
	}
	

	
	
	/**
	 * Check whether this AlchemicIngredient has already been terminated.
	 */
	public boolean isTerminated() {
		return terminated;
	}
	
	/**
	 * Terminate this alchemicIngredient.
	 * 
	 * @effect	
	 */
	protected void terminate() {
		setTerminated();
	}
	
	/**********************************************************
     * Unit
     **********************************************************/
	
	/**
	 * Variable referencing the possible units of liquid ingredients.
	 */
	private static List<String> liquidUnits = new ArrayList<String>(Arrays.asList("drop","spoon","vial","bottle","jug","barrel","storeroom"));
	
	
	/**
	 * Variable referencing the possible units of powder ingredients.
	 */
	private static List<String> powderUnits = new ArrayList<String>(Arrays.asList("pinch","spoon","sachet","box","sack","chest","storeroom"));
	
	/**
	 * Return the list of possible liquid units.
	 */
	public static List<String> getLiquidUnits(){
		return liquidUnits;
	}
	
	/**
	 * Return the list of possible powder units.
	 */
	public static List<String> getPowderUnits(){
		return powderUnits;
		
	}
	
	/**
	 * Variable referencing the unit of this ingredient.
	 */
	private String unit = null;
	
	/**
	 * Set the unit of this ingredient to the given unit.
	 * 
	 * @param 	unit
	 * 			The new unit for this ingredient.
	 * @pre		The given unit must be legal.	
	 * 			| isValidUnit(unit)
	 * @post 	If the given unit is a valid unit, the unit of this ingredient
	 * 			is set to the given unit.
	 * 			| new.getUnit() == unit
	 */
	private void setUnit(String unit) {
		if (isValidUnit(unit)) {
			this.unit = unit;
		}
		
	}
	
	/**
	 * Check whether the given unit is a valid unit for this ingredient depending
	 * on its state (powder or liquid).
	 * 
	 * @param 	unit
	 * 			The unit to check.
	 * @return	True if this unit is a powder unit if the ingredient is a powder or  
	 * 			if the unit is a liquid unit if the ingredient is a liquid.
	 * 			
	 */
	public boolean isValidUnit(String unit) {
		if (this.getState() == "Powder") {
			return getPowderUnits().contains(unit);
		}
		else {
			return getLiquidUnits().contains(unit);
		}
	}
	
	/**
	 * Return the unit of this ingredient.
	 */
	public String getUnit() {
		return this.unit;
	}
	
	
	/**
	 * Change the unit of this AlchemicIngredient to the given unit.
	 * 
	 * @param 	unit
	 * 			The new unit of this alchemicIngredient
	 * @effect	If the Alchemic hasn't already been terminated the unit 
	 * 			| setUnit
	 * @throws IngredientAlreadyTerminatedException
	 */
	protected void changeUnit(String unit) throws IngredientAlreadyTerminatedException {
		if(!isTerminated()) {
			if (isValidUnit(unit)) {
				setUnit(unit);
			}
		}
		else {
			throw new IngredientAlreadyTerminatedException("This ingredient has already been terminated",this);
		}
	}
	
	
	
	/**********************************************************
     * State
     **********************************************************/
	/**
	 * Variable referencing the state of this ingredient.
	 */
	private String state = null;
	
	
	/**
	 * Return the state of this ingredient.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Check whether the given state is a legal state for an ingredient
	 * 
	 * @param 	state
	 * 			The state to be checked.
	 * @return	True if the given state is either liquid or a powder.
	 * 			| result ==
	 * 			|	(state == "Liquid") || (state == "Powder")
	 */
	public boolean isValidState(String state) {
		return ((state == "Liquid") || (state == "Powder"));
	}
	
	/**
	 * Set the state of this ingredient to the given state.
	 * 
	 * @param 	state 
	 * 			The new state for this ingredient.
	 * @post	If the given state is valid, the state of 
	 * 			this ingredient is set to the given state,
	 * 			otherwise the state of the ingredient is set to a valid state (the default).
	 * 			| if (isValidState(name))
     *          |      then new.getState().equals(state)
     *          |      else new.getState().equals(getDefaultState())
	 */
	private void setState(String state) {
		if (isValidState(state)) {
			this.state = state;
		}
		else {
			this.state = this.getDefaultState();
		}
	}
	
	/**
	 * Return the state for a new ingredient which is to be used when the
     * given state is not valid or not given.
     *
     * @return   A valid state.
     *         | isValidState(result)
	 * 
	 */
	private String getDefaultState() {
		List<IngredientType> potentialIngredients = getReferenceIngredientType();
		IngredientType type = potentialIngredients.get(0);
		for (int i = 0; i<= potentialIngredients.size();i++) {
			if (potentialIngredients.get(i).getState() == "Liquid") {
				type = potentialIngredients.get(i);
				break;
			}
		}
		return type.getState();
	}
	
	
	
	protected void changeState() throws IngredientAlreadyTerminatedException {
		if (!isTerminated()) {
			if (getState()=="Liquid") {
				setState("Powder");
			}
			else {
				setState("Liquid");
			}
		}
		else {
			throw new IngredientAlreadyTerminatedException("This ingredient has already been terminated",this);
		}
	}
	
	
	
	/**********************************************************
     * IngredientType
     **********************************************************/
	
	private static final IngredientType water = new IngredientType("Water","Liquid",0,20);
	
	/**
	 * Variable referencing the ingredientType of this ingredient.
	 */
	private List<IngredientType> ingredientTypeList = new ArrayList<IngredientType>();

	/**
	 * Set the list of ingredientTypes of this ingredient to the given list.
	 * 
	 * @param 	ingredientTypeList
	 * 			The new list of ingredientTypes of this ingredient.
	 * @post	
	 */
	private void setIngredientTypeList(List<IngredientType> ingredientTypeList) {
		if (isValidIngredientTypeList(ingredientTypeList)) {
			this.ingredientTypeList = ingredientTypeList;
			}
		else {
			this.ingredientTypeList.add(water);
		}
	}
	
	/**
	 * Return the list of ingredientTypes of this ingredient.
	 */
	public List<IngredientType> getIngredientTypeList(){
		return this.ingredientTypeList;
	}
	
	public boolean isValidIngredientTypeList(List<IngredientType> ingredientTypeList) {
		return (!(ingredientTypeList.contains(null)) && !(ingredientTypeList.isEmpty()));
	}
	
	
	
	private List<IngredientType> getReferenceIngredientType(){
		List<IngredientType> ingredientList = getIngredientTypeList();
		List<Long> temperatureList = new ArrayList<Long>();
		long minimum = Long.MAX_VALUE;
		for (int i = 0; i <= ingredientList.size();i++) {
			long coldness = ingredientList.get(i).getStandardTemp().getColdness();
			long hotness  = ingredientList.get(i).getStandardTemp().getHotness();
			long difference = Math.abs(20 - (hotness - coldness));
			temperatureList.add(i,difference);
			if (difference < minimum) {
				minimum = difference;
			}
		}	
		List<IngredientType> potentialIngredients = new ArrayList<IngredientType>();
		for (int i = 0; i<= ingredientList.size();i++) {
			if (temperatureList.get(i)==minimum) {
				potentialIngredients.add(ingredientList.get(i));
			}
		}
		
		return potentialIngredients;
	}
	
	/**********************************************************
     * Name
     **********************************************************/
	
	
	
	
	
	/**
     * Check whether the given name is a legal name for an ingredientType.
     * 
     * @param  	name
     *			The name to be checked
     * @return	True if 
     * (name.charAt(0)).matches("[A-Z^()]+")
     * String.valueOf(name.charAt(0))
     */
	public static boolean isValidName(String name) {
    	String[] splitName = name.split(" ");
    	boolean result = true;
    	if (splitName.length > 1) {
    		for (int i = 0; i < splitName.length ; i++) {
    			  if  ((splitName[i] == null) || (splitName[i].length() < 2) || (String.valueOf(splitName[i].charAt(0)).matches("[A-Z^()]+")) == false || (splitName[i].substring(1).matches("[a-z]+") == false)) {
    				  result = false;
    				  break;
    			  }
    			}
    		return result;
    	}
    	else {
    		return (name != null && String.valueOf(name.charAt(0)).matches("[A-Z^()]+") && name.substring(1).matches("[a-z]+") && (name.length() >= 3)  );
    	}
    }
    
        
	/**
	 * Return in alphabetical order the simple names of each ingredientType of this ingredient.
	 */
	public List<String> getAlphabeticNameList(){
		List<String> AlphabeticNameList = new ArrayList<String>();
		for (int i=0; i<getIngredientTypeList().size();i++) {
			AlphabeticNameList.add(getIngredientTypeList().get(i).getName());
		}
		Collections.sort(AlphabeticNameList);
		return AlphabeticNameList;
	}

	
	 /**
	  * Return the simple name of this ingredient.
	  */
	public String getSimpleName() {
		List<String> AlphabeticNameList = getAlphabeticNameList();
		String SimpleName = null;
		SimpleName = AlphabeticNameList.get(0) + " mixed with";
		for (int i=1; i<AlphabeticNameList.size();i++) {
			if (i==AlphabeticNameList.size() - 1) {
				SimpleName = SimpleName + " and" + AlphabeticNameList.get(i);
			}
			else if (i == AlphabeticNameList.size() -2) {
				SimpleName = SimpleName + AlphabeticNameList.get(i);
			}
			else {
				SimpleName = SimpleName + AlphabeticNameList.get(i) +", ";
			}
		}
		return SimpleName;
	}
	
	public String getCompleteName() {
		String completeName = null;
		Temperature standardTemp = getStandardTemperature();
		if (getTemperature().getHotness() > standardTemp.getHotness()) {
			completeName = "Heated";
		}
		else if (getTemperature().getColdness()>standardTemp.getColdness()) {
			completeName = "Cooled";
		}
		completeName = completeName + getSimpleName();
		return completeName;
	}
	/**
	 * Variable referencing the specialName of this ingredient.
	 */
	private String specialName = null;
	
	/**
	 * Set the special name of this ingredient to the given special name.
	 * 
	 * @param 	specialName
	 * 			The new special name of this ingredient.
	 * @post	If the given special name is valid, the special name of this ingredient is
	 * 			set to the given special name, otherwise the special name is unchanged.
	 * 			| if (isValidName(specialName))
	 * 			|		then new.getSpecialName().equals(name)
	 */
	private void setSpecialName(String specialName) {
		if (isValidName(specialName)) {
			this.specialName = specialName;
		}
	}
	
	/**
	 * Return the special name of this ingredient.
	 */
	public String getSpecialName() {
		return specialName;
	}
	
	
    
	
	
	
	
	
	
	/**********************************************************
     * Temperature
     **********************************************************/
	
	/**
	 * Variable referencing the temperature of an AlchemicIngredient
	 */
	private Temperature temperature = null;
	
	
	private void setTemperature(long coldness, long hotness) {
		temperature = new Temperature(coldness,hotness);
	}
	
	
	public Temperature getTemperature() {
		return temperature;
	}
	
	protected void changeTemp(long coldness, long hotness) {
		temperature = new Temperature(coldness,hotness);
	}
	
	public Temperature getStandardTemperature(){
		List<IngredientType> potentialIngredients = getReferenceIngredientType();
		IngredientType ingredientType = potentialIngredients.get(0);
		for (int i = 0; i<=potentialIngredients.size();i++) {
			if (potentialIngredients.get(i).getStandardTemp().getHotness()>0) {
				ingredientType = potentialIngredients.get(i);
				break;
			}
		}
		return ingredientType.getStandardTemp();
		
	}
	
	/**********************************************************
     * Laboratory
     **********************************************************/
	
	private Laboratory laboratory = null;
	
	public void moveToLaboratory(Laboratory laboratory) {
		setLaboratory(laboratory);
		
	}
	
	private void setLaboratory(Laboratory laboratory) throws InvalidLaboratoryException {
		if (isValidLaboratory(laboratory)) {
			if (getLaboratory() == null) {
				IngredientContainer container = new IngredientContainer(this,getQuantity(),getUnit(),getState());
				laboratory.storeNewIngredient(container);
				this.laboratory = laboratory;
			}
			else {
				getLaboratory().removeIngredient(this);
				laboratory.addIngredient(this);
				this.laboratory = laboratory;
			}
		}	
		else {
			throw new InvalidLaboratoryException("The given laboratory does not exist.",this);
		}
	}
	
	public boolean isValidLaboratory(Laboratory laboratory) {
		return (laboratory != null);
	}
	
	
	public Laboratory getLaboratory() {
		return laboratory;
	}
	
	
	/**********************************************************
     * IngredientContainer
     **********************************************************/
	
	/**
	 * Variable referencing the possible units of liquid containers.
	 */
	private static List<String> liquidUnitsCap = new ArrayList<String>(Arrays.asList("spoon","vial","bottle","jug","barrel"));
	
	
	/**
	 * Variable referencing the possible units of powder containers.
	 */
	private static List<String> powderUnitsCap = new ArrayList<String>(Arrays.asList("spoon","sachet","box","sack","chest"));
	
	private static List<Integer> liquidUnitsInSpoons = new ArrayList<Integer>(Arrays.asList(1,5,15,105,1260));
	
	private static List<Integer> powderUnitsInSpoons = new ArrayList<Integer>(Arrays.asList(1,7,42,126,1260));
	
	
	public String determineCapUnit() {
		String capUnit = null;
		if (state == "Liquid") {
			int teller = 0;
			for (int i = 0; i < liquidUnitsInSpoons.size(); i++) {
				
				if (this.getQuantityInSpoons() < liquidUnitsInSpoons.get(i)) {
					capUnit = liquidUnitsCap.get(i);
					break;
				}
				teller = teller +1;
			}
			if (teller >= (liquidUnitsInSpoons.size() -1) ) {
				capUnit = "barrel";
				this.quantity = 1;
				this.unit = "barrel";
			}
			
		}
		else {
			int teller = 0;
			for (int i = 0; i < powderUnitsInSpoons.size(); i++) {
				if (this.getQuantityInSpoons() < powderUnitsInSpoons.get(i)) {
					capUnit = powderUnitsCap.get(i);
					break;
				}
				teller = teller +1;
			}
			if (teller >= (powderUnitsInSpoons.size() -1) ) {
				capUnit = "chest";
				this.quantity = 1;
				this.unit = "chest";
			}
		}
	return capUnit;
	}	
	

	
}
