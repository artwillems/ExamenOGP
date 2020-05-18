import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
	 * Initialize a new oven with given laboratory, that owns this oven, and given temperature setting.
	 * 
	 * @param  	laboratory
	 *         	The laboratory that owns this new oven.
	 * @param  	temperatureSetting
	 *         	The temperature setting of the new oven.
	 * @effect 	The new oven is a device with given laboratory and temperature setting.
	 *         	| super(laboratory)
	 * @effect	The type is set to Oven
	 * 			| setType("Oven")
	 * 
	 */
	
	/**NOG AANPASSEN VOOR TEMPERATUUR*/
	@Raw
	public Oven(Laboratory laboratory, List<Long> temperatureSetting) {
		super(laboratory);
		setTemperature(temperatureSetting.get(0), temperatureSetting.get(1));
		setType("Oven");
	}
	
	
	/**********************************************************
	 * Temperature
	 **********************************************************/
	
	private Temperature temperature = null;
	
	private void setTemperature(long coldness, long hotness) {
		temperature = new Temperature(coldness, hotness);
	}
	
	public Temperature getOvenTemperature() {
		return temperature;
	}
	
	public void changeOvenTemperature(long coldness, long hotness) {
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
    		if (getOvenTemperature().getColdness() == 0) {
    			
    			long newHotness = getOvenTemperature().getHotness(); /*Hier komt nog een term voor de 5 procent*/
    			if (ingredient.getTemperature().getHotness() < newHotness) {
    				ingredient.changeTemp(0, newHotness);
    			}
    		}
    		else {
    			long newColdness = getOvenTemperature().getColdness(); /*Hier komt nog een term voor de 5 procent*/
    			if (ingredient.getTemperature().getColdness() > newColdness) {
    				ingredient.changeTemp(newColdness,0);
    			}
    		}
    		this.getIngredientList().clear();
    		this.getIngredientList().add(ingredient);
    	}
		
	}
    
    

	
	
}