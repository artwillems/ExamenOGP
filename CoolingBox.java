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
	 * 
	 */
	
	
	@Raw
	public CoolingBox(Laboratory laboratory, List<Long> temperatureSetting) {
		super(laboratory);
		setTemperature(temperatureSetting.get(0), temperatureSetting.get(1));
	}
	
	
	/**********************************************************
	 * Temperature
	 **********************************************************/
	
	
	/**
	 * Variable referencing the max temperature of hotness and coldness of the oven.
	 */
	private long maxTemp = 10000;
	
	
	/**
	 * Variable referencing the hotness of the oven.
	 */
	private long boxHotness = 0;
	
	/**
	 * Variable referencing the coldness of the oven.
	 */
	private long boxColdness = 0;
	
	private void setHotness(long hotness) {
		this.boxHotness = hotness;
	}
	
	private void setColdness(long coldness) {
			this.boxColdness = coldness;
	}
	
	private void setTemperature(long coldness, long hotness) {
		if (isValidTempCombination(coldness,hotness)){
			if (getIngredientList().size() == 0) {
				setColdness(coldness);
				setHotness(hotness);
			}
			else {
				throw new InvalidIngredientListException(this);
			}
		}
	}
	
	public boolean isValidTempCombination(long coldness, long hotness) {
		return (((coldness == 0) || (hotness == 0)) && ((hotness >= 0) && (hotness<= getMaxTemp()) && (coldness >= 0 && coldness <= getMaxTemp())));
		
	}
	
	public long getMaxTemp() {
		return maxTemp;
	}
	
	public long getBoxHotness() {
		return this.boxHotness;
	}
	
	public long getBoxColdness() {
		return this.boxColdness;
	}
	
	public List<Long> getOvenTemperature(){
		List<Long> boxTemperature = new ArrayList<Long>();
		boxTemperature.add(this.getBoxColdness());
		boxTemperature.add(this.getBoxHotness());
		return boxTemperature;
	}
	
	public void changeCoolingBoxTemperature(long coldness,long hotness) {
		setTemperature(coldness, hotness);
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
    		throw new NoIngredientInDeviceException("There is no ingredient in this device");
    	}
    	else {
    		AlchemicIngredient ingredient = this.getIngredientList().get(0);
    		if (getBoxColdness() == 0) {
    			long newHotness = getBoxHotness(); 
    			if (ingredient.getHotness() > newHotness) {
    				ingredient.changeTemp(0, newHotness);
    			}
    		}
    		else {
    			long newColdness = getBoxColdness(); 
    			if (ingredient.getColdness() < newColdness) {
    				ingredient.changeTemp(newColdness,0);
    			}
    		}
    		this.getIngredientList().clear();
    		this.getIngredientList().add(ingredient);
    	}
		
	}
    
    
}
	