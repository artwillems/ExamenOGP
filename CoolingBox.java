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
	    
	    /**
	     * Check whether the given standard temperature is a legal standard temperature for a ingredientType.
	     * 
	     * @param  	standardTemperature
	     *			The standard temperature to be checked
	     * @return	True if the given standard temperature is strictly higher than [0,0] and 
	     * 			the coldness and hotness are not both different from 0. 
	     */
	    public static boolean isValidStandardTemperature(int standardColdness,int standardHotness) {
	        if ((standardColdness != 0)  && (standardHotness != 0)) {
	        	return false;
	        }
	        else if ((standardColdness == 0)  && (standardHotness == 0)){
	        	return false;
	        }
	        else {
	        	return true;
	        }
	    }
	    
	    /**
	     * Return the standard temperature for a new ingredientType which is to be used when the
	     * given standard temperature is not valid.
	     *
	     * @return   A valid standard temperature.
	     *         | isValidStandardTemperature(result)
	     */
	    @Model
	    private static List<Integer> getDefaultStandardTemperature() {
	    	List<Integer> defaultStandardTemperature = new ArrayList<Integer>();
	    	defaultStandardTemperature.add(0);
	    	defaultStandardTemperature.add(20);
	        return defaultStandardTemperature;
	    }
	
	
	
}
