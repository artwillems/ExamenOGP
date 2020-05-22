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
	 * Heat the ingredient.
	 * 
	 * @post	if the device has an ingredient to use, the ingredient is heated. If the ingredient has 
	 * 			a higher temperature than the oven, the ingredient keeps his temperature. Otherwise
	 * 			the ingredient get heated within a range of 5 percent of the ovenTemperature. Afterwards
	 * 			the ingredient is added to the cleared list of this oven
	 * 			| if (!this.countIngredients()<1)
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
     *			| this.getIngredientList().clear();
     *			|  this.getIngredientList().add(ingredient)
	 */
    @Override
    public void executeAlchemicOperation() throws NoIngredientInDeviceException{
    	if (this.countIngredients() < 1) {
    		throw new NoIngredientInDeviceException("There is no ingredient in this device",this);
    	}
    	else {
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
		
	}
    
    

	
	
}