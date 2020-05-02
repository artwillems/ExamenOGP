import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;

public class AlchemicIngredient {
	/**********************************************************
     * Constructors
     **********************************************************/
	public AlchemicIngredient(int quantity,String unit, IngredientType ingredientType, long hotness, long coldness, String state) {
		setIngredientType(ingredientType);
		setState(state);
		setQuantity(quantity);
		setUnit(unit);
		setHotness(hotness);
		setColdness(coldness);
		
		
		
	}
	
	public AlchemicIngredient(int quantity, IngredientType ingredientType) {
		this(quantity,"spoons",ingredientType)
	}
	
	
	
	
	/**********************************************************
     * Name
     **********************************************************/
	
	
	
	
	
	
	
	
	/**********************************************************
     * Quantity
     **********************************************************/
	/**
	 * Variable referencing the quantity of this ingredient.
	 */
	private int quantity = 0;
	
	
	private void setQuantity(int quantity) throws InvalidQuantityException {
		if (isCorrectQuantity(quantity)) {
			this.quantity = quantity;
		}
		else {
			throw new InvalidQuantityException(this);
		}
	}
	
	
	public boolean isCorrectQuantity(int quantity) {
		return (quantity >= 0);
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
	
	public int getQuantity() {
		return this.quantity;
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
	 * @post 	If the given unit is a valid unit, the unit of this ingredient
	 * 			is set to the given unit.
	 */
	private void setUnit(String unit) {
		if (isValidUnit(unit)) {
			this.unit = unit;
		}
		
	}
	
	public boolean isValidUnit(String unit) {
		if (this.getState() == "Powder") {
			return getPowderUnits().contains(unit);
		}
		else {
			return getLiquidUnits().contains(unit);
		}
	}
	
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
	private IngredientType ingredientType = null;
	
	
	/**
	 * Set the ingredientType of this ingredient to the given ingredientType.
	 * 
	 * @param 	ingredientType
	 * 			The new ingredientType of this ingredient.
	 * @post	If the given ingredientType is valid, the ingredientType of this ingredient
	 * 			is set to the given ingredientType otherwise an exception is thrown.
	 * @throws 	InvalidIngredientTypeException
	 */
	private void setIngredientType(IngredientType ingredientType) throws InvalidIngredientTypeException {
		if (isValidIngredientType(ingredientType)) {
			this.ingredientType = ingredientType;
		}
		else {
			throw new InvalidIngredientTypeException(this);
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
	
	
	private void setColdness(long coldness) throws InvalidTemperatureException{
		if (isValidTemperature(coldness)) {
			this.coldness = coldness;
		}
		else {
			throw new InvalidTemperatureException(this);
		}
	}
	
	public long getColdness() {
		return coldness;
	}
	
	private void setHotness(long hotness) throws InvalidTemperatureException{
		if (isValidTemperature(hotness)) {
			this.hotness = hotness;
		}
		else {
			throw new InvalidTemperatureException(this);
		}
	}
	
	public long getHotness() {
		return hotness;
	}
	
	
	public boolean isValidTemperature(long temperature) {
		return (temperature > 0 && temperature <= getMaxTemp());
	}
	
	
	private void setTemperature(long hotness, long coldness) throws InvalidTempCombinationException {
		if (isValidTempCombination(hotness,coldness)) {
			setHotness(hotness);
			setColdness(coldness);
		}
		else {
			throw new InvalidTempCombinationException(this);
		}
	}
	
	public boolean isValidTempCombination(long hotness, long coldness) {
		return !(hotness > 0 && coldness > 0);
	}
	
	/*
	 * public getTemperature
	 */
	
	public int[][] getTemperature(){
		return {getColdness(),getHotness()};
	}
	
	
	
	

}
