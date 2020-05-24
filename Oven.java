import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;


/**
 * A class of ovens.
 * 
 * @invar	Each device must have a valid ingredientList
 * 			| isValidInput(getIngredientList)
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/

public class Oven extends Device {
	
	/**********************************************************
	 * Constructors
	 **********************************************************/
	
	/**
	 * Initialize a new oven with given laboratory where this oven is stored, and given temperature setting.
	 * 
	 * @param  	laboratory
	 *         	The laboratory that owns this new oven.
	 * @param  	temperatureSetting
	 *         	The temperature setting of the new oven.
	 * @effect 	The new oven is a device with given laboratory and temperature setting.
	 *         	| super(laboratory)
	 * @effect	The temperature of this oven is set to the given coldness and hotness
	 * 			| setTemperature(temperatureSetting.get(0), temperatureSetting.get(1));
	 * @effect	The type is set to Oven
	 * 			| setType("Oven")
	 * 
	 */
	
	public Oven(Laboratory laboratory, List<Long> temperatureSetting) {
		super(laboratory);
		setTemperature(temperatureSetting.get(0), temperatureSetting.get(1));
		setType("Oven");
	}
	
	
	/**********************************************************
	 * Temperature
	 **********************************************************/
	
	/**
	 * The temperature of this oven
	 */
	private Temperature temperature = null;
	
	
	/**
	 * Set the temperature of this oven to the given temperature
	 * 
	 * @param 	coldness
	 * 			The coldness for this oven
	 * @param 	hotness
	 * 			The hotness for this oven
	 * @post	The temperature is set to the given coldness and hotness
	 * 			| temperature = new Temperature(coldness,hotness)
	 * 			| getTemperature().getColdness() == coldness
	 * 			| getTemperature().getHotness() == hotness
	 */
	private void setTemperature(long coldness, long hotness) {
		temperature = new Temperature(coldness, hotness);
	}
	
	
	/**
	 * Return the temperature of this oven.
	 */
	public Temperature getOvenTemperature() {
		return temperature;
	}
	
	
	/**
	 * Change the temperature of this oven
	 * 
	 * @param 	coldness
	 * 			The new coldness of this oven
	 * @param	hotness
	 * 			The new hotness of this oven
	 * @effect 	The temperature of this oven is set to 
	 * 			the giving coldness and hotness
	 * 			| setTemperature(coldness,hotness)
	 */
	public void changeOvenTemperature(long coldness, long hotness) {
		setTemperature(coldness, hotness);
	}
	
	
	
	/**********************************************************
	 * methodes
	 **********************************************************/
	   /**
     * Check whether the given ingredientList is a valid ingredient list for this device
     * @param 	ingredientList
     * 			The ingredientList to be checked
     * @return	true if and only if the ingredientList is smaller than or equal to one 
     * 			and does not contain any null values.
     * 			| result == (ingredientList.size() <= 1 && !ingredientList.contains(null))
     */
	@Override
	public boolean isValidInput(List<AlchemicIngredient> ingredientList) {
		return (ingredientList.size() <= 1 && !ingredientList.contains(null));
	
	}
	
	
	
	/**
	 * Add an ingredient from a container to this device
	 * 
	 * @param 	container
	 * 			The container containing the AlchemicIngredient
	 * @post	If there isn't already an ingredient stored in this device and the given ingredient is stored in the same labo as this device
	 * 			the ingredient is added to the device
	 * 			| if (getIngredientList(� >= 1) and (haveSameLaboratory(container.getAlchemicIngredient()))
	 * 			|	then ingredientList.add(container.getAlchemicIngredient()
	 *@effect	After the addition of the ingredient to this device, the ingredient is removed from the storage of his laboratory
	 *			| getLaboratory().removeIngredient(container.getAlchemicIngredient())
	 *@effect	After the addition of the ingredient to this device, the container of this device is deleted
	 *			| container.setDelete(true)
	 * @throws 	IllegalIngredientAdditionException("The device allow only one alchemic ingredient",this)
	 * 			There already is an ingredient in this device
	 * 			| this.countIngredients > 1
	 * @throws 	DifferentLaboratoryException("The device and the ingredient have to be stored in the same laboratory.",this);
	 * 			The ingredient and device have different laboratory
	 * 			| !haveSameLaboratory(container.getAlchemicIngredient())
	 */
	@Override
	public void addIngredientFrom(IngredientContainer container) throws IllegalIngredientAdditionException, DifferentLaboratoryException{
		if (getIngredientList().size() >= 1) {
    		throw new IllegalIngredientAdditionException("The device allows only one alchemic ingredient",this);
    	}
		else {
			if (haveSameLaboratory(container.getAlchemicIngredient())) {
				ingredientList.add(container.getAlchemicIngredient());
				getLaboratory().removeIngredient(container.getAlchemicIngredient());
				container.setDelete(true);
			}
			else {
				throw new DifferentLaboratoryException("The device and the ingredient have to be stored in the same laboratory.",this);
			}
		}

	}
	
	
	

    /**
	 * Heat the ingredient.
	 * 
	 * @effect	if the device has an ingredient to use, the ingredient is heated. If the ingredient has 
	 * 			a higher temperature than the oven, the ingredient keeps his temperature. Otherwise
	 * 			the ingredient get heated within a range of 5 percent of the ovenTemperature.
	 * 			| if (this.countIngredients()==1)
	 * 			|	range =  (new Random().nextInt(10+1)) - 5
	 * 			|	if (getOvenTemperature().getColdness()==0)
	 * 			|		then  newHotness = getOvenTemperature().getHotness() * (1 + (range/100)) 
	 * 			|			if (ingredient.getTemperature().getHotness() < newHotness)
	 * 			|				then ingredient.changeTemp(0,0)
	 * 			|					 ingredient.changeTemp(0,newHotness)
	 * 			|	else
	 * 			|		new Coldness = getOvenTemperature().getColdness() * (1 + (range/100))
	 * 			|		if (ingredient.getTemperature().getColdness() > newColdness)
	 * 			|		ingredient.changeTemp(0, 0)
     *			|		ingredient.changeTemp(newColdness,0)
     * @post	After the heating of the ingredient, the list of this device is emptied and the ingredient is added to the list
     * 			this.getIngredientList().clear();
    			this.getIngredientList().add(ingredient);
     *@throws 	NoIngredientInDeviceException("There is no ingredient or more than two ingredients in this device",this)
     *			(getIngredientList().size() == 1)
	 */
    @Override
    public void executeAlchemicOperation() throws NoIngredientInDeviceException{
    	if (getIngredientList().size() == 1) {
    		int range = (new Random().nextInt(10+1)) - 5;
    		AlchemicIngredient ingredient = this.getIngredientList().get(0);
    		if (getOvenTemperature().getColdness() == 0) {
    			
    			long newHotness = getOvenTemperature().getHotness() * (1 + (range/100)) ; 
    			if (ingredient.getTemperature().getHotness() < newHotness) {
    				ingredient.changeTemp(0,0);
    				ingredient.changeTemp(0, newHotness);
    			}
    		}
    		else {
    			long newColdness = getOvenTemperature().getColdness() * (1 + (range/100)); 
    			if (ingredient.getTemperature().getColdness() > newColdness) {
    				ingredient.changeTemp(0, 0);
    				ingredient.changeTemp(newColdness,0);
    			}
    		}
    		this.getIngredientList().clear();
    		this.getIngredientList().add(ingredient);
    	}
		
    	
    		
    	else {
    		throw new NoIngredientInDeviceException("There is no ingredient or more than two ingredients in this device",this);
	}
    
    

	
	
}
}