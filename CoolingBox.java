import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of cooling boxes.
 * 
 * @invar	Each device must have a valid ingredientList
 * 			| isValidInput(getIngredientList)
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
	 * @param	temperatureSetting
	 * 			The temperature of this new coolingbox
	 * @effect 	The new cooling box is a device with given laboratory, preset coldness and preset hotness.
	 *         	| super(laboratory)
	 * @effect  The preset temperature of the cooling box is set to the given preset temperature.
	 *          | setTemperature(temperatureSetting.get(0), temperatureSetting.get(1));
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
	
	/**
	 * Variable referencing the temperature of this coolingBox
	 */
	private Temperature temperature = null;
	
	
	/**
	 * Set the temperature of this coolingBox to the given temperature.
	 * 
	 * @param 	coldness
	 * 			The coldness for this coolingBox
	 * @param 	hotness
	 * 			The hotness for this coolingBox
	 * @post	The temperature is set to the given coldness and hotness
	 * 			| temperature = new Temperature(coldness,hotness)
	 * 			| getTemperature().getColdness() == coldness
	 * 			| getTemperature().getHotness() == hotness
	 */
	private void setTemperature(long coldness, long hotness) {
		temperature = new Temperature(coldness,hotness);
	}
	
	
	/**
	 * Return the temperature of this coolingBox
	 */
	public Temperature getCoolingBoxTemperature() {
		return temperature;
	}
	
	/**
	 * Change the temperature of this coolingBox
	 * 
	 * @param 	coldness
	 * 			The new coldness of this coolingBox
	 * @param	hotness
	 * 			The new hotness of this coolingBox
	 * @effect 	The temperature of this coolingBox is set to 
	 * 			the giving coldness and hotness
	 * 			| setTemperature(coldness,hotness)
	 */
	public void changeCoolingBoxTemperature(long coldness, long hotness) {
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
	 * 			| if (getIngredientList().size() >= 1) and (haveSameLaboratory(container.getAlchemicIngredient()))
	 * 			|	then ingredientList.add(container.getAlchemicIngredient()
	 * 			|		 this.laboratory.removeIngredient(container.getAlchemicIngredient())
	 * 			|		 container.setDelete(true)
	 * @effect	After the addition of the ingredient to this device, the ingredient is removed from the storage of his laboratory
	 *			| getLaboratory().removeIngredient(container.getAlchemicIngredient())
	 * @effect	After the addition of the ingredient to this device, the container of this device is deleted
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
	   * @effect	if the device has an ingredient to use, the ingredient is cooled. If the ingredient has 
	   * 			a lower temperature than the coolingBox, the ingredient keeps his temperature. Otherwise
	   * 			the ingredient gets cooled to the coolingBoxTemperature. 
	   * 			| if (this.countIngredients()==1)
	   * 			|	if (getCoolingBoxTemperature().getColdness()==0)
	   * 			|		then  newHotness = getCoolingBoxTemperature().getHotness()
	   * 			|			if (ingredient.getTemperature().getHotness() < newHotness)
	   * 			|				then ingredient.changeTemp(0,0)
	   * 			|					 ingredient.changeTemp(0,newHotness)
	   *  			|	else
	   * 			|		new Coldness = getCoolingBoxTemperature().getColdness()
	   * 			|		if (ingredient.getTemperature().getColdness() > newColdness)
	   * 			|		ingredient.changeTemp(0, 0)
	   *			|		ingredient.changeTemp(newColdness,0)
	   * @post		If the size of the list is equal to one the modified ingredient is added to the emptied list
	   * 			| this.getIngredientList().clear();
	   *			| this.getIngredientList().add(ingredient)   		 
	   * @throws	NoIngredientInDeviceException("There is no ingredient in this device",this)
	   * 			The coolingBox does not have an ingredient
	   * 			| this.countIngredients()<1
	   */
    @Override
    public void executeAlchemicOperation() throws NoIngredientInDeviceException{
    	if (getIngredientList().size() == 1) {
    		AlchemicIngredient ingredient = this.getIngredientList().get(0);
    		if (getCoolingBoxTemperature().getColdness() == 0) {
    			long newHotness = getCoolingBoxTemperature().getHotness(); 
    			if (ingredient.getTemperature().getHotness() > newHotness) {
    				ingredient.changeTemp(0, 0);
    				ingredient.changeTemp(0, newHotness);
    			}
    		}
    		else {
    			long newColdness = getCoolingBoxTemperature().getColdness(); 
    			if (ingredient.getTemperature().getColdness() < newColdness) {
    				ingredient.changeTemp(0,0);
    				ingredient.changeTemp(newColdness,0);
    			}
    		}
    		this.getIngredientList().clear();
    		this.getIngredientList().add(ingredient);
    	
    		
    	}
    	else {
    		throw new NoIngredientInDeviceException("There is no ingredient in this device",this);
		
	}
    
    
}
    
}
	