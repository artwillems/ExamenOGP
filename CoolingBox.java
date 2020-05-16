import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of cooling boxes.
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/

public class CoolingBox extends Device {
	
	/**********************************************************
	 * Constructors
	 **********************************************************/
	
	/**
	 * Initialize a new cooling box with given laboratory, that owns this cooling box, and given temperature setting.
	 * 
	 * @param  	laboratory
	 *         	The laboratory that owns this new cooling box.
	 * @param	presetColdness
	 * 			The preset coldness of the new cooling box.
	 * @param	presetHotness
	 * 			The preset hotness of the new cooling box.
	 * 
	 * @effect 	The new cooling box is a device with given laboratory, preset coldness and preset hotness.
	 *         	| super(laboratory)
	 * @effect  The preset temperature of the cooling box is set to the given preset temperature.
	 *          | setPresetTemperature(presetTemperature) 
	 * @effect	The type is set to coolingBox
	 * 			| setType("CoolingBox")
	 */
	
	
	@Raw
	public CoolingBox(Laboratory laboratory, List<Long> temperatureSetting) {
		super(laboratory);
		setTemperature(temperatureSetting.get(0), temperatureSetting.get(1));
		setType("CoolingBox");
	}
	
	
	/**********************************************************
	 * Temperature
	 **********************************************************/
	
	private Temperature temperature = null;
	
	private void setTemperature(long coldness, long hotness) {
		temperature = new Temperature(coldness,hotness);
	}
	
	public Temperature getCoolingBoxTemperature() {
		return temperature;
	}
	
	public void changeCoolingBoxTemperature(long coldness, long hotness) {
		temperature = new Temperature(coldness,hotness);
	}
	
	
	/**********************************************************
	 * methodes
	 **********************************************************/
	
	 /**
	 * Add an ingredient into this oven.
	 */
    @Override
	public void addIngredientFrom(IngredientContainer container)  {
    	
	}

    /**
	 * Heat the ingredient. 
	 */
    @Override
    public void executeAlchemicOperation() {
    	if (this.countIngredients() < 1) {
    		throw new NoIngredientInDeviceException("There is no ingredient in this device",this);
    	}
    	else {
    		AlchemicIngredient ingredient = this.getIngredientList().get(0);
    		if (getCoolingBoxTemperature().getColdness() == 0) {
    			long newHotness = getCoolingBoxTemperature().getHotness(); 
    			if (ingredient.getTemperature().getHotness() > newHotness) {
    				ingredient.changeTemp(0, newHotness);
    			}
    		}
    		else {
    			long newColdness = getCoolingBoxTemperature().getColdness(); 
    			if (ingredient.getTemperature().getColdness() < newColdness) {
    				ingredient.changeTemp(newColdness,0);
    			}
    		}
    		this.getIngredientList().clear();
    		this.getIngredientList().add(ingredient);
    	}
		
	}
    
    
}
	