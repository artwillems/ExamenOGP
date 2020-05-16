import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class of quantities.
 * 
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/ 

public class Quantity {

	/**********************************************************
     * constructor
     **********************************************************/
	
	/**
	 * Initialize a new quantity with given amount and unit.
	 * @param 	amount
	 * 			The amount of the new quantity.
	 * @param 	unit
	 * 			The unit of the new quantity.
	 */
	public Quantity(int amount, String unit) {
		setAmount(amount);
		setUnit(unit);
	}
	
	
	/**
	 * Variable referencing the amount of this quantity.
	 */
	private int amount = 0;
	
	
	/**
	 * Variable referencing the maximum amount of an quantity.
	 */
	private static int maximumAmount = Integer.MAX_VALUE;
	
	/**
	 * Return the amount of this quantity.
	 */
	public int getAmount() {
		return this.amount;
	}
	
	/**
	 * Set the amount of this quantity to the given amount
	 * 
	 * @param 	amount 
	 * 			The new amount for this quantity.
	 * @pre 	The given amount must be legal
	 * 			| isCorrectAmount(amount)
	 * @post	The given amount is registered as the amount of this quantity.
	 * 			| new.getAmount() == amount
	 */
	private void setAmount(int amount) {
		this.amount = amount;
		}
	
	
	/**
	 * Check whether the given amount is a valid amount for a quantity.
	 * 
	 * @param 	amount
	 * 			The amount to check.
	 * @return	True if and only if the given amount is strictly positive and 
	 * 			does not exceed the maximum amount.
	 * 			| result == ((amount > 0) && (amount <= getMaximumAmount()))
	 */
	public boolean isCorrectAmount(int amount) {
		return ((amount > 0) && (amount <= getMaximumAmount()));
	}
	
	/**
	 * Return the maximum amount of a quantity.
	 */
	public int getMaximumAmount() {
		return maximumAmount;
	}
	
	
	/**
	 * Variable referencing the unit of this quantity.
	 */
	private String unit = null;
	
	/**
	 * Return the unit of this quantity.
	 */
	public String getUnit() {
		return this.unit;
	}
	
	/**
	 * Set the unit of this quantity to the given unit
	 * 
	 * @param 	unit 
	 * 			The new unit for this quantity.
	 * @pre 	The given unit must be legal
	 * 			| isCorrectUnit(unit)
	 * @post	The given unit is registered as the unit of this quantity.
	 * 			| new.getUnit() == unit
	 */
	private void setUnit(String unit) {
		this.unit = unit;
		}
	
	
	
	public boolean isCorrectUnit(String unit) {
		return (unit == drop);
	}
	
}
