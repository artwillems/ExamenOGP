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
	
	
	/**
	 * Variable referencing the max temperature of hotness and coldness of the oven.
	 */
	private long maxTemp = 10000;
	
	
	/**
	 * Variable referencing the hotness of the oven.
	 */
	private long ovenHotness = 20;
	
	/**
	 * Variable referencing the coldness of the oven.
	 */
	private long ovenColdness = 0;
	
	private void setHotness(long hotness) {
		this.ovenHotness = hotness;
	}
	
	private void setColdness(long coldness) {
			this.ovenColdness = coldness;
	}
	
	private void setTemperature(long coldness, long hotness) throws InvalidIngredientListException {
		if (isValidTempCombination(coldness,hotness)){
			if (getIngredientList().size()==0) {
				setColdness(coldness);
				setHotness(hotness);
			}
			else { 
				throw new InvalidIngredientListException("The device can not hold any ingredients when changing its temperature",this);
			}
		}
	}
	
	public boolean isValidTempCombination(long coldness, long hotness) {
		return (((coldness == 0) || (hotness == 0)) && ((hotness >= 0) && (hotness<= getMaxTemp()) && (coldness >= 0 && coldness <= getMaxTemp())));
		
	}
	
	public long getMaxTemp() {
		return maxTemp;
	}
	
	public long getOvenHotness() {
		return this.ovenHotness;
	}
	
	public long getOvenColdness() {
		return this.ovenColdness;
	}
	
	public List<Long> getOvenTemperature(){
		List<Long> ovenTemperature = new ArrayList<Long>();
		ovenTemperature.add(this.getOvenColdness());
		ovenTemperature.add(this.getOvenHotness());
		return ovenTemperature;
	}
	
	
	public void changeOvenTemperature(long coldness, long hotness) {
		setTemperature(coldness,hotness);
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
    		if (getOvenColdness() == 0) {
    			long newHotness = getOvenHotness(); /*Hier komt nog een term voor de 5 procent*/
    			if (ingredient.getHotness() < newHotness) {
    				ingredient.changeTemp(0, newHotness);
    			}
    		}
    		else {
    			long newColdness = getOvenColdness(); /*Hier komt nog een term voor de 5 procent*/
    			if (ingredient.getColdness() > newColdness) {
    				ingredient.changeTemp(newColdness,0);
    			}
    		}
    		this.getIngredientList().clear();
    		this.getIngredientList().add(ingredient);
    	}
		
	}
    
    

	
	
}