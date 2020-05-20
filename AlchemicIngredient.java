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
	 * 			The quantity of the new AlchemicIngredient.	
	 * @param 	unit
	 * 			The unit of the new AlchemicIngredient in which the quantity is measured.
	 * @param 	ingredientTypeList
	 * 			The ingredientType list of the new AlchemicIngredient.
	 * @param 	hotness
	 * 			The hotness of the new AlchemicIngredient.
	 * @param 	coldness
	 * 			The coldness of the new AlchemicIngredient.
	 * @param 	state
	 * 			The state of the new AlchemicIngredient.
	 * @param 	specialName
	 * 			The special name of the new AlchemicIngredient.
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
	 * Initialize a new AlchemicIngredient with a quantity, unit and ingredientType list, 
	 * 
	 * @param 	quantity
	 * 			The quantity of the new AlchemicIngredient.	
	 * @param 	unit
	 * 			The unit of the new AlchemicIngredient in which the quantity is measured.
	 * @param 	ingredientTypeList
	 * 			The ingredientType list of the new AlchemicIngredient.
	 * @param	coldness
	 * 			The coldness of the new AlchemicIngredient.
	 * @param 	hotness
	 * 			The hotness of the new AlchemicIngredient.
	 * @pre		The ingredietTypeList can only consist of one ingredientType;
	 * @effect	This new AlchemicIngredient is initialized with a quantity, unit, ingredientTypeList, coldness, hotness
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
	 * Initialize a new AlchemicIngredient with a quantity, unit, ingredientType list, 
	 * hotness, coldness and state.
	 * 
	 * @param 	quantity
	 * 			The quantity of the new AlchemicIngredient.	
	 * @param 	unit
	 * 			The unit of the new AlchemicIngredient in which the quantity is measured.
	 * @param 	ingredientTypeList
	 * 			The ingredientType list of the new AlchemicIngredient.
	 * @param 	hotness
	 * 			The hotness of the new AlchemicIngredient.
	 * @param 	coldness
	 * 			The coldness of the new AlchemicIngredient.
	 * @param 	state
	 * 			The state of the new AlchemicIngredient.
	 *@effect	This new AlchemicIngredient is initialized with a given quantity, unit, ingredientType list, hotness,
	 *			coldness, state and without a special name.
	 *			| this(quantity,unit,ingredientTypeList, hotness, coldness, state ,null)

	 */
	protected AlchemicIngredient(int quantity, String unit, List<IngredientType> ingredientTypeList,long hotness, long coldness, String state) {
		this(quantity,unit,ingredientTypeList, hotness, coldness, state ,null); 
	}
	
	/**
	 * Initialize a new AlchemicIngredient with a quantity, ingredientType list, 
	 * hotness, coldness, state and special name.
	 * 
	 * @param 	quantity
	 * 			The quantity of the new AlchemicIngredient.	
	 * @param 	ingredientTypeList
	 * 			The ingredientType list of the new AlchemicIngredient.
	 * @param 	hotness
	 * 			The hotness of the new AlchemicIngredient.
	 * @param 	coldness
	 * 			The coldness of the new AlchemicIngredient.
	 * @param 	state
	 * 			The state of the new AlchemicIngredient.
	 *@effect	This new AlchemicIngredient is initialized with a given quantity, a spoon unit, ingredientType list, hotness,
	 *			coldness, state and without a special name.
	 *			| this(quantity,"spoon",ingredientTypeList, hotness, coldness, state ,null)

	 */
	protected AlchemicIngredient(int quantity, List<IngredientType> ingredientTypeList, long hotness, long coldness, String state, String specialName) {
		this(quantity, "spoon", ingredientTypeList, hotness, coldness, state, specialName);
	}
	
	
	/**
	 * This new AlchemicIngredient is initialized as an AlchemicIngredient of the ingredientType water with a given 
	 * quantity.
	 * 
	 * @param 	quantity
	 * 			The quantity of the new AlchemicIngredient.
	 * @effect	This new AlchemicIngredient is initialized with a given quantity, a spoon unit,
	 * 			the ingredientType water (null), standard temperature of coldness 0 and hotness 20, Liquid state and without a special name (null)
	 * 			| this(quantity, "spoon", null, (long) 0, (long 20), "Liquid", null)
	 *
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
	private void setQuantity(int quantity) InValidQuantityException{
		if (isCorrectQuantity(quantity)) {
			this.quantity = quantity;
		}
		else {
			throw new InvalidQuantityException("The ingredient must have a valid quantity", this);
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
	
	
	/**
	 * This map states the different transitions between a liquid unit and a spoon. 
	 */
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
	
	
	/**
	 * This map states the different transitions between a powder unit and a spoon.
	 */
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
	 * Variable referencing whether or not an AlchemicIngredient has been terminated. 
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
	 * @effect	The termination is set to true.
	 * 			| setTerminated()
	 */
	protected void terminate() {
		setTerminated();
	}
	
	/**********************************************************
     * Unit
     **********************************************************/
	
	/**
	 * Variable referencing the valid units of liquid ingredients.
	 */
	private static List<String> liquidUnits = new ArrayList<String>(Arrays.asList("drop","spoon","vial","bottle","jug","barrel","storeroom"));
	
	
	/**
	 * Variable referencing the valid units of powder ingredients.
	 */
	private static List<String> powderUnits = new ArrayList<String>(Arrays.asList("pinch","spoon","sachet","box","sack","chest","storeroom"));
	
	/**
	 * Return the list of valid liquid units.
	 */
	public static List<String> getLiquidUnits(){
		return liquidUnits;
	}
	
	/**
	 * Return the list of valid powder units.
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
	 * 			| if (this.getState() == "Powder")
	 * 			|	then result == getPowderUnits().contains(unit)
	 * 			| 	else result == getLiquidUnits().contains(unit) 
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
	 * @effect	If the Alchemic hasn't already been terminated and the unit is valid then
	 * 			the unit is set to the given unit.
	 * 			| if (!isTerminated()) and if (isValidUnit(unit))
	 * 			|	then setUnit(unit)
	 * @throws 	IngredientAlreadyTerminatedException("This ingredient has already been terminated",this)
	 * 			The ingredient has been terminated or the unit is invalid
	 * 			| isTerminated() || ! isValidUnit(unit)
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
	
	
	/**
	 * Change the state of this ingredient.
	 * 
	 * @effect	If the file has not been terminated yet, the state is set 
	 * 			to the opposite state.
	 * 			|	if (!isTerminated)
	 * 			|		then if (getState() == "Liquid")
	 * 			|			then setState("Powder")
	 * 			|			else setState("Liquid")
	 * @throws 	IngredientAlreadyTerminatedException("This ingredient has already been terminated",this)
	 * 			This file has been terminated
	 * 			| isTerminated()
	 */
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
	
	/**
	 * Variable referencing the standard ingredientType water of an AlchemicIngredient 
	 */
	private static final IngredientType water = new IngredientType("Water","Liquid",0,20);
	
	/**
	 * Variable referencing the ingredientTypes of this ingredient.
	 */
	private List<IngredientType> ingredientTypeList = new ArrayList<IngredientType>();

	/**
	 * Set the list of ingredientTypes of this ingredient to the given list.
	 * 
	 * @param 	ingredientTypeList
	 * 			The new list of ingredientTypes of this ingredient.
	 * @post	If the ingredientTypeList is valid then the ingredientTypeList of this ingredient is 
	 * 			registered as the given ingredientTypeList. Otherwise the ingredientType of this AlchemicIngredient
	 * 			is registered as water.
	 * 			| if (isValidIngredientTypeList(ingredientTypeList)
	 * 			|	then new.getIngredientTypeList() == ingredientTypeList
	 * 			|	else new.getIngredientTypeList().get(0) == water
	 * 
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
	
	/**
	 * 
	 * @param 	ingredientTypeList
	 * 			The ingredientTypeList to be checked
	 * @return	True if and only if the list is not empty and does not contain any null values.
	 * 			| (!(ingredientTypeList.contains(null)) && !(ingredientTypeList.isEmpty()))
	 */
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
	 * Return in alphabetical order the simple names of each ingredientType of this AlchemicIngredient.
	 * 
	 * @return	The list containing the names of all ingredientTypes of this alchemicIngredien in alphabetical order
	 * 			
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
		SimpleName = AlphabeticNameList.get(0);
		if (AlphabeticNameList.size()>1) {
			SimpleName = SimpleName + "mixed with";
			for (int i=1; i<=AlphabeticNameList.size();i++) {
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
		}
		return SimpleName;
	}
	
	/**
	 * Return the complete name of this AlchemicIngredient
	 * 
	 * @return
	 */
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
	
	/**
	 * Set the temperature of this AlchemicIngredient to the given temperture
	 * @param 	coldness
	 * 			The new coldness
	 * @param 	hotness
	 * 			The new hotness
	 * @post	The given coldness and hotness are registered as the new temperature 
	 * 			for this AlchemicIngredient
	 * 			| temperature = new Temperature(coldness,hotness)
	 * 			| getTemperature().getColdness() == coldness
	 * 			| getTemperature().getHotness() == hotness
	 */
	private void setTemperature(long coldness, long hotness) {
		temperature = new Temperature(coldness,hotness);
	}
	
	/**
	 * Return the temperature of this AlchemicIgredient 
	 */
	public Temperature getTemperature() {
		return temperature;
	}
	
	/**
	 * Change the temperature of this AlchemicIngredient 
	 * 
	 * @param 	coldness
	 * 			The new coldness for this AlchemicIngredient
	 * @param 	hotness
	 * 			The new hotness for this AlchemicIngredient
	 * @effect 	The temperature of this alchemicIngredient is set to
	 * 			the given coldness and hotness
	 * 			| setTemperature(coldness, hotness)
	 */
	protected void changeTemp(long coldness, long hotness) {
		setTemperature(coldness,hotness);
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
	
	/**
	 * Variable referencing the laboratory where this ingredient is stored
	 */
	private Laboratory laboratory = null;
	
	
	public void moveToLaboratory(Laboratory laboratory) {
		setLaboratory(laboratory);
		
	}
	
	/**
	 * Set the laboratory of this AlchemicIngredient to the given laboratory.
	 * 
	 * @param 	laboratory
	 * 			The new laboratory for this AlchemicIngredient
	 * @post	If the laboratory is valid the laboratory of this alchemicIngredient is 
	 * 			set to the given laboratory
	 * 			| if (isValidLaboratory(laboratory)
	 * 			| 	then new.getLaboratory() == laboratory
	 * @effect	If the ingredient is not yet stored in a laboratory then the ingredient is moved to
	 * 			the given laboratory in a container. Otherwise the ingredient has to be removed from its
	 * 			old laboratory first.
	 * 			| if (getLaboratory() != null)
	 * 			|	then getLaboratory.removeIngrediet(this)
	 * 			| IngredientContainer container = new IngredientContainer(this,getQuantity(),getUnit(),getState());
				|  laboratory.storeNewIngredient(container);	
	 * 			
	 * @throws 	InvalidLaboratoryException("The given laboratory does not exist.",this)
	 * 			The given laboratory is not valid
	 * 			| !isValidLaboratory(laboratory)
	 */
	private void setLaboratory(Laboratory laboratory) throws InvalidLaboratoryException {
		if (isValidLaboratory(laboratory)) {
			if (getLaboratory() != null) {
				getLaboratory().removeIngredient(this);
			}
			IngredientContainer container = new IngredientContainer(this,getQuantity(),getUnit(),getState());
			laboratory.storeNewIngredient(container);
			this.laboratory = laboratory;
			
		}	
		else {
			throw new InvalidLaboratoryException("The given laboratory does not exist.",this);
		}
	}
	
	/**
	 * Check whether the given laboratory is valid
	 * 
	 * @param 	laboratory
	 * 			The laboratory to be checked
	 * @return	True if and only if the given laboratory is not null
	 * 			| result == (laboratory != null)
	 */
	public boolean isValidLaboratory(Laboratory laboratory) {
		return (laboratory != null);
	}
	
	
	/**
	 * Return the laboratory of this AlchemicIngredient
	 */
	public Laboratory getLaboratory() {
		return laboratory;
	}
	
	
	/**********************************************************
     * IngredientContainer
     **********************************************************/
	
	/**
	 * Variable referencing the valid units of liquid containers.
	 */
	private static List<String> liquidUnitsCap = new ArrayList<String>(Arrays.asList("spoon","vial","bottle","jug","barrel"));
	
	
	/**
	 * Variable referencing the valid units of powder containers.
	 */
	private static List<String> powderUnitsCap = new ArrayList<String>(Arrays.asList("spoon","sachet","box","sack","chest"));
	
	/**
	 * Variable referencing the transitionnumbers to transition from a liquid unit to spoons.
	 */
	private static List<Integer> liquidUnitsInSpoons = new ArrayList<Integer>(Arrays.asList(1,5,15,105,1260));
	
	/**
	 *  * Variable referencing the transitionnumbers to transition from a powder unit to spoons.
	 */
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
