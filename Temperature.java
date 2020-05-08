import java.util.ArrayList;
import java.util.List;

/**
 * A class of temperatures
 * 
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/ 
public class Temperature {
	
	
	/**********************************************************
     * constructor
     **********************************************************/
	/**
	 * Initialize a new temperature with given coldness and hotness.
	 * @param 	coldness
	 * 			The coldness of the new temperature.
	 * @param 	hotness
	 * 			The hotness of the new temperature.
	 * 
	 * @effect  The coldness of the temperature is set to the given coldness.
	 * 			If the given coldness is not valid, a default coldness is set.
	 *          | setColdness(coldness) 
	 * @effect  The hotness of the temperature is set to the given hotness.
	 * 			If the given hotness is not valid, a default hotness is set.
	 *          | setHotness(hotness) 
	 */
	public Temperature(long coldness,long hotness) {
		setTemperature(coldness,hotness);
	}
	
	   /**
		 * Variable referencing the coldness of this temperature.
		 */
		private long coldness = 0;
		
	    /**
		 * Variable referencing the hotness of this temperature.
		 */
		private long hotness = 0;
		
		
		
	    /**
	     * Return the temperature.
	     */
	    @Raw @Basic 
	    public List<Long> getTemperature() {
	    	List<Long> temperature = new ArrayList<Long>();
	    	temperature.add(coldness);
	    	temperature.add(hotness);
	        return temperature;
	    }
	    

	    /**
		 * Variable referencing the temperature.
		 */

	    @Raw @Model 
	    private void setTemperature(long coldness,long hotness) {
	        if (isValidTemperature(coldness,hotness)) {
	        		this.coldness = coldness;
	        		this.hotness = hotness;
	        } else {
	        		this.coldness = this.getDefaultTemperature().get(0);
	        		this.hotness = this.getDefaultTemperature().get(1);
	        }
	    }
	    
	    /**
	     * Check whether the given coldness and hotness are legal for a temperature.
	     * 
	     * @param  	coldness
	     *			The coldness to be checked
	     * @param  	hotness
	     *			The hotness to be checked
	     * @return	True if the given coldness and hotness are strictly higher 0 and 
	     * 			the given coldness and hotness are not both different from 0. 
	     */
	    public static boolean isValidTemperature(long coldness,long hotness) {
	        if ((coldness != 0)  && (hotness != 0)) {
	        	return false;
	        }
	        else if ((coldness == 0)  && (hotness == 0)){
	        	return false;
	        }
	        else {
	        	return true;
	        }
	    }
	    
	    /**
	     * Return the temperature which is to be used when the given temperature is not valid.
	     *
	     * @return   A valid temperature.
	     *         | isValidTemperature(result)
	     */
	    @Model
	    private static List<Integer> getDefaultTemperature() {
	    	List<Integer> defaultTemperature = new ArrayList<Integer>();
	    	defaultTemperature.add(0);
	    	defaultTemperature.add(20);
	        return defaultTemperature;
	    }
	    
}
