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
	public AlchemicIngredient(int quantity,String unit, List<IngredientType> ingredientTypeList, long hotness, long coldness, String state, String specialName) {
		setIngredientTypeList(ingredientTypeList);
		setState(state);
		setQuantity(quantity);
		setUnit(unit);
		setTemperature(hotness,coldness);
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
	 * @effect	This new ingredient is initialized with a quantity, unit, ingredientTypeList
	 * 			and without a special name (null)
	 * 			| this(quantity,unit,ingredientTypeList,0,0,"Powder",null);
	 * @effect	The temperature is set to the standard temperature of its ingredientType
	 * 			| setTemperature(ingredientType.getTemperature().get(1),ingredientType.getTemperature().get(0));
	 * @effect 	The state is set to the standard state of its ingredientType
	 * 			| setState(ingredientType.getState())
	 * 
	 */
	public AlchemicIngredient(int quantity, String unit, List<IngredientType> ingredientTypeList) {
		this(quantity,unit,ingredientTypeList,0,0,"Powder",null);
		setTemperature(ingredientType.getTemperature().get(1),ingredientType.getTemperature().get(0));
		setState(ingredientType.getState());
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
	public AlchemicIngredient(int quantity, String unit, List<IngredientType> ingredientTypeList,long hotness, long coldness, String state) {
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
	public AlchemicIngredient(int quantity, List<IngredientType> ingredientTypeList, long hotness, long coldness, String state, String specialName) {
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
	 * @effect	The temperature is set to the standard temperaturen of water
	 * 			| setTemperature(water.getTemperature().get(1), water.getTemperature().get(0))
	 */
	public AlchemicIgredient(int quantity) {
		this(quantity, "spoon", water, 0, 0, "Liquid",null);
		setTemperature(water.getTemperature().get(1), water.getTemperature().get(0));
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
			if (getUnit()=="drop") {
				result = result * (1/8);
			}
			if (getUnit()=="vial") {
				result = result * 5;
			}
			if (getUnit()=="bottle") {
				result = result * 15;
			}
			if (getUnit()=="jug") {
				result = result * 105;
			}
			if (getUnit()=="barrel") {
				result = result * 1260;
			}
			if (getUnit()=="storeroom") {
				result = result * 6300;
			}
		}
		else {
			if (getUnit()=="pinch") {
				result = result * (1/6);
			}
			if (getUnit()=="sachet") {
				result = result * 7;
			}
			if (getUnit()=="box") {
				result = result * 42;
			}
			if (getUnit()=="sack") {
				result = result * 126;
			}
			if (getUnit()=="chest") {
				result = result * 1260;
			}
			if (getUnit()=="storeroom") {
				result = result * 6300;
			}
			
		}
		return result;
		
	}
	
	private static Map<String,Integer> unitLibrary = new HashMap<String,Integer>(){
		{
			put("drop",1/8);
			put("vial",5);
			put()
		}
	};
	
	
	
	
	
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
		return ingredientType.getState();
	}
	
	
	
	
	
	
	
	/**********************************************************
     * IngredientType
     **********************************************************/
	
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
	
	/* volgende functies zijn wrs overbodig*/
	/**
	 * Set the ingredientType of this ingredient to the given ingredientType.
	 * 
	 * @param 	ingredientType
	 * 			The new ingredientType of this ingredient.
	 * @post	If the given ingredientType is valid, the ingredientType of this ingredient
	 * 			is set to the given ingredientType otherwise an exception is thrown.
	 * @throws 	InvalidIngredientTypeException
	 */
	private void setIngredientType(IngredientType ingredientType) {
		if (isValidIngredientType(ingredientType)) {
			this.ingredientType = ingredientType;
		}
		else {
			
		}
	}
	
	/**
	 * Check whether the given ingredientType is a legal ingredientType.
	 * 
	 * @param 	ingredientType
	 * 			The ingredientType to be checked.
	 * @return	True if the given ingredientType is not null
	 * 			| result == 
	 * 			|	(ingredientType != null)
	 */
	public boolean isValidIngredientType(IngredientType ingredientType) {
		return (ingredientType != null);
	}
	
	/**
	 * Return the ingredientType of this ingredient.
	 */
	public IngredientType getIngredientType() {
		return this.ingredientType;
	}
	
	
	
	
	
	/**********************************************************
     * Name
     **********************************************************/
	
	
	
	
	
    /**
     * Check whether the given name is a legal name for an ingredient.
     * 
     * @param  	name
     *			The name to be checked
     * @return	True if 
     */
    public static boolean isValidName(String name) {
        return (name != null && name.matches("[a-zA-Z^()]+") && (name.length() >= 2) );
    
        
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
	 * Return the simple name/ simple names of the ingredientType/Types of this ingredient. 
	 */
	public List<String> getSimpleName(){
		List<String> SimpleNameList = getAlphabeticNameList();
		for (int i = 0; i < getIngredientTypeList().size();i++) {
			SimpleNameList.add(getIngredientTypeList().get(i).getName());
		}
		return SimpleNameList;
	}
	
	 /**
	  * Return the complete name of this ingredient.
	  */
	public String getCompleteName() {
		List<String> AlphabeticNameList = getAlphabeticNameList();
		String CompleteName = null;
		if (/*Standaardtemperatuur afkomstig van welk ingredientType*/)
		CompleteName = AlphabeticNameList.get(0) + " mixed with";
		for (int i=1; i<AlphabeticNameList.size();i++) {
			if (i==AlphabeticNameList.size() - 1) {
				CompleteName = CompleteName + " and" + AlphabeticNameList.get(i);
			}
			else if (i == AlphabeticNameList.size() -2) {
				CompleteName = CompleteName + AlphabeticNameList.get(i);
			}
			else {
				CompleteName = CompleteName + AlphabeticNameList.get(i) +", ";
			}
		}
		return CompleteName;
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
		if (isValidSpecialName(specialName)) {
			this.specialName = specialName;
		}
	}
	
	/**
	 * Return the special name of this ingredient.
	 */
	public String getSpecialName() {
		return specialName;
	}
	
	
	public static boolean isValidSpecialName(String name) {
        return (name.matches("[a-zA-Z^()]+") && (name.length() >= 2) );
    
	
	
	
	
	
	
	/**********************************************************
     * Temperature
     **********************************************************/
	
	/**
	 * Variable referencing the coldness of this ingredient.
	 */
	private long coldness = 0;
	
	/**
	 * Variable referencing the hotness of this ingredient.
	 */
	private long hotness = 0;
	
	/**
	 * Variable referencing the maximum possible temperature for every ingredient.
	 */
	public static long maxTemp = 10000;
	
	/**
	 * Return the maximum possible temperature.
	 */
	public static long getMaxTemp() {
		return maxTemp;
	}
	
	
	/**
	 * Set the coldness of this ingredient to the given coldness.
	 * 
	 * @param 	coldness
	 * 			The new coldness for this ingredient.
	 * @pre 	The given coldness must be legal.
	 * 			| isValidTemperature(coldness)
	 * @post	The given coldness is registered as the coldness of this ingredient.
	 * 			| new.getColdness() == coldness
	 */
	private void setColdness(long coldness) {
		this.coldness = coldness;
	}
	
	/**
	 * Return the coldness of this ingredient.
	 */
	public long getColdness() {
		return coldness;
	}
	
	/**
	 * Set the hotness of this ingredient to the given hotness.
	 * 
	 * @param 	hotness
	 * 			The new hotness for this ingredient.
	 * @pre    	The given hotness must be legal.
	 * 			| isValidTemperature(hotness)
	 * @post	The given hotness is registered as the hotness of this ingredient.
	 * 			| new.getHotness() == hotness
	 */
	private void setHotness(long hotness) {
			this.hotness = hotness;
	}
	
	/**
	 * Return the hotness of this ingredient.
	 */
	public long getHotness() {
		return hotness;
	}
	
	/**
	 * Check whether the given temperature (hotness or coldness) is a valid temperature
	 * for an ingredient
	 *  
	 * @param 	temperature
	 * 			The temperature to check
	 * @return	True if and only if the given temperature is positive and does not
	 * 			exceed the maximum temperature.
	 * 			| result == (temperature >= 0 && temperature <= getMaxTemp())
	 */
	public boolean isValidTemperature(long temperature) {
		return (temperature >= 0 && temperature <= getMaxTemp());
	}
	
	/**
	 * Set the temperature of this ingredient to the given temperature (hotness and coldness)
	 * 
	 * @param 	hotness
	 * 			The new hotness for this ingredient.
	 * @param 	coldness
	 * 			The new coldness for this ingredient.
	 */
	private void setTemperature(long hotness, long coldness) {
		if (isValidTempCombination(hotness,coldness)) {
			setHotness(hotness);
			setColdness(coldness);
		}
		else {
			setHotness(ingredientType.getStandardHotness());
			setColdness(ingredientType.getStandardColdness());
		}
	}
	
	/**
	 * Check whether the given combination of hotness and coldness is a legal combination.
	 * 
	 * @param 	hotness
	 * 			The hotness to check.
	 * @param 	coldness
	 * 			The coldness to check.
	 * @return	True if and only if the hotness and the coldness are not greater than zero at the same time.
	 * 			| result == !(hotness>0 && coldness >0)
	 */
	public boolean isValidTempCombination(long hotness, long coldness) {
		return !(hotness > 0 && coldness > 0);
	}
	
	
	
	public List<Long> getTemperature(){
		List<Long> Temperature = new ArrayList<Long>();
		Temperature.add(getColdness());
		Temperature.add(getHotness());
		return Temperature;
		
	}
	
	
	
	

}
