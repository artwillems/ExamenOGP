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
	 * @param  laboratory
	 *         The laboratory that owns this new oven.
	 * @param  temperatureSetting
	 *         The temperature setting of the new oven.
	 * @effect The new oven is a device with given laboratory and temperature setting.
	 *         | super(laboratory)
	 * 
	 */
	
	/**NOG AANPASSEN VOOR TEMPERATUUR*/
	@Raw
	public Oven(Laboratory laboratory, int temperatureSetting) {
		super(laboratory);
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
		
	}

	
	
}