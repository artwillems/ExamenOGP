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
		
	}
	
	
	
	
	/**********************************************************
     * Name
     **********************************************************/
	
	
	
	
	/**********************************************************
     * Quantity
     **********************************************************/
	
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
	
	/*
	 * dit wordt nog aangepast zodat dit altijd in spoons is.
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**********************************************************
     * Unit
     **********************************************************/
	private static List<String> liquidUnits = new ArrayList<String>(Arrays.asList("drop","spoon","vial","bottle","jug","barrel","storeroom"));
	private static List<String> powderUnits = new ArrayList<String>(Arrays.asList("pinch","spoon","sachet","box","sack","chest","storeroom"));
	
	public static List<String> getLiquidUnits(){
		return liquidUnits;
	}
	
	public static List<String> getPowderUnits(){
		return powderUnits;
		
	}
	
	
	private String unit = null;
	
	
	private void setUnit(String unit) {
		
	}
	
	public boolean isValidUnit(String unit) {
		if (this.getState() == "Powder") {
			return (getPowderUnits().contains(unit));
		}
	}
	
	
	
	
	
	
	
	
	/**********************************************************
     * State
     **********************************************************/
	private String state = null;
	
	public String getState() {
		return state;
	}
	
	public static boolean isValidState(String state) {
		return ((state == "Liquid") || (state == "Powder"));
	}
	
	private void setState(String state) {
		if (isValidState(state)) {
			this.state = state;
		}
		else {
			this.state = this.getDefaultState();
		}
	}
	
	private String getDefaultState() {
		return ingredientType.getState();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**********************************************************
     * Temperature
     **********************************************************/
	private long coldness = 0;
	private long hotness = 0;
	public static long maxTemp = 10000;
	
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
	
	private void setHotness(long hotness) throws InvalidTemperatureException{
		if (isValidTemperature(hotness)) {
			this.hotness = hotness;
		}
		else {
			throw new InvalidTemperatureException(this);
		}
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
	
	
	
	
	

}
