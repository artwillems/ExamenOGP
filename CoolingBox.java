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
	 * @param  laboratory
	 *         The laboratory that owns this new cooling box.
	 * @param	presetColdness
	 * 			The preset coldness of the new cooling box.
	 * @param	presetHotness
	 * 			The preset hotness of the new cooling box.
	 * 
	 * @effect The new cooling box is a device with given laboratory, preset coldness and preset hotness.
	 *         | super(laboratory)
	 * @effect  The preset temperature of the cooling box is set to the given preset temperature.
	 *          | setPresetTemperature(presetTemperature) 
	 * 
	 */
	
	
	@Raw
	public CoolingBox(Laboratory laboratory, int presetColdness,int presetHotness) {
		super(laboratory);
		setPresetTemperature(presetColdness, presetHotness);
	}
	
	/**********************************************************
	 * presetTemperature
	 **********************************************************/
	   /**
		 * Variable referencing the preset hotness of this cooling box.
		 */
		private int presetHotness = 20;
		
	    /**
		 * Variable referencing the preset coldness of this cooling box.
		 */
		private int presetColdness = 0;
		
		

		
	    /**
	     * Return the preset temperature of this cooling box.
	     */
	    @Raw @Basic 
	    public List<Integer> getPresetTemperature() {
	    	List<Integer> presetTemperature = new ArrayList<Integer>();
	    	presetTemperature.add(presetColdness);
	    	presetTemperature.add(presetHotness);
	        return presetTemperature;
	    }
	    

	    /**
		 * Variable referencing the preset temperature of this cooling box.
		 */

	    @Raw @Model 
	    private void setPresetTemperature(int presetColdness,int presetHotness) {
	        		this.presetColdness = presetColdness;
	        		this.presetHotness = presetHotness;   
	    }
	    
		/**********************************************************
		 * methodes
		 **********************************************************/
	    
	    /**
		 * Add an ingredient into this cooling box.
		 */
	    @Override
		public void addIngredientFrom(IngredientContainer container) throws IllegalIngredientAdditionException {
	    	if (this.countIngredients() > 1) {
	    		throw new IllegalIngredientAdditionException("The cooling box allows only one alchemic ingredient");
	    	}
		}
	
	    /**
		 * Cool the ingredient. 
		 */
	    @Override
	    public void executeAlchemicOperation() NoIngredientInDeviceException{
			if (this.countIngredients() < 1) {
				throw new NoIngredientInDeviceException("There is no ingredient in this cooling box");
			}
			else {
				/*VERLAGEN NAAR INGESTELDE TEMPERATUUR*/
			}
		}
	
	
}
