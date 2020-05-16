import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

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
		setColdness(coldness);
		setHotness(hotness);
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
		 * Variable referencing the maximum possible value.
		 */
		public static long maxValue = 10000;
		
		/**
		 * Return the maximum possible value.
		 */
		@Raw @Basic 
		public static long getMaxValue() {
			return maxValue;
		}
		
		/**
		 * Set the maximum value to the given value.
		 * 
		 * @param	maxValue
		 * 			The new maximum value
		 * @post    The maximum value is set to the given maximum value.
		 */
		private void setMaxValue(long maxValue) {
			this.maxValue = maxValue;
		}
		
		/**
		 * Return the coldness of this temperature.
		 */
		@Raw @Basic 
		public long getColdness() {
			return coldness;
		}
		
		/**
		 * Return the hotness of this temperature.
		 */
		@Raw @Basic 
		public long getHotness() {
			return hotness;
		}
		
		/**
		 * Set the coldness of this temperature to the given coldness.
		 * 
		 * @param 	coldness
		 * 			The new coldness for this temperature.
		 * @post    If the given coldness is valid, the coldness of this temperature is set to the given coldness,
		 *          otherwise the coldness of this temperature is set to a valid coldness (the default).
		 *          | if (isValidValue(coldness))
		 *          |      then new.getColdness().equals(coldness)
		 *          |      else new.getColdness().equals(getDefaultColdness())
		 */
		private void setColdness(long coldness) {
			if(isValidValue(coldness)) {
				this.coldness = coldness;
			}
			else {
				this.coldness = this.getDefaultColdness();
			}		
		}
	    
		/**
		 * Set the hotness of this temperature to the given hotness.
		 * 
		 * @param 	hotness
		 * 			The new hotness for this temperature.
		 * @post    If the given hotness is valid, the hotness of this temperature is set to the given hotness,
		 *          otherwise the hotness of this temperature is set to a valid hotness (the default).
		 *          | if (isValidValue(hotness))
		 *          |      then new.getHotness().equals(hotness)
		 *          |      else new.getHotness().equals(getDefaultHotness())
		 */
		private void setHotness(long hotness) {
			if(isValidValue(hotness)) {
				this.hotness = hotness;
			}
			else {
				this.hotness = this.getDefaultHotness();
			}
			
		}

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
	     * Check whether the given value is a valid value.
	     * 
	     * @param	value
	     * 			The value to be checked
	     * 
	     * @return	True if the given value is greater than or equal to 0 and less than or equal to the maximum value.
	     * 			|result == (value >= 0 || value <= getMaxValue())
	     */
	    public static boolean isValidValue(long value) {
	    	return (value >= 0 || value <= getMaxValue());
	    }
	    
	    /**
	     * Check whether the given coldness and hotness are legal for a temperature.
	     * 
	     * @param  	coldness
	     *			The coldness to be checked
	     * @param  	hotness
	     *			The hotness to be checked
	     * @return	True if the given coldness and hotness are not both different from 0, and the given coldness and hotness are between 0 and 10000. 
	     */
	    public static boolean isValidTemperature(long coldness,long hotness) {
	        if (((coldness != 0)  && (hotness != 0)) || !(isValidValue(coldness)) || !(isValidValue(hotness)))  {
	        	return false;
	        }

	        else {
	        	return true;
	        }
	    }
	    

	    /**
	     * Return the coldness which is to be used when the given coldness is not valid.
	     *
	     * @return   A valid coldness.
	     *         	| isValidValue(result)
	     */
	    @Model
	    private static long getDefaultColdness() {
	    	return 0;
	    }
	    
	    /**
	     * Return the hotness which is to be used when the given hotness is not valid.
	     *
	     * @return   A valid hotness.
	     *         	| isValidValue(result)
	     */
	    @Model
	    private static long getDefaultHotness() {
	    	return 20;
	    }

	    
	    /**
		 * Set the temperature to the given temperature (hotness and coldness)
		 * 
		 * @param 	coldness
		 * 			The new coldness for this temperature.
		 * @param 	hotness
		 * 			The new hotness for this temperature.
		 * @post    If the given coldness and hotness are valid, the coldness and hotness of this temperature is set to the given coldness and hotness,
		 *          otherwise the coldness and hotness of this temperature is set to a valid coldness and hotness (the default).
		 *          | if (isValidTemperature(coldness,hotness))
		 *          |      then new.getColdness().equals(coldness)
		 *          			new.getHotness().equals(hotness)
		 *          |      else new.getColdness().equals(getDefaultColdness())
		 *          			new.getHotness().equals(getDefaultHotness())
		 */
	    @Raw @Model 
	    private void setTemperature(long coldness,long hotness) {
	        if (isValidTemperature(coldness,hotness)) {
	        		this.coldness = coldness;
	        		this.hotness = hotness;
	        } else {
	        		this.coldness = this.getDefaultColdness();
	        		this.hotness = this.getDefaultHotness();
	        }
	    }
	    
		/**
		 * Set the hotness of this temperature to the given hotness.
		 * 
		 * @param 	hotness
		 * 			The new hotness for this temperature.
		 * @effect  The hotness of this temperature is set to the given hotness.
		 *          | setHotness(hotness)
		 */
	    public void heat(long hotness) {
	    	setHotness(hotness);
	    	
	    }
	    
		/**
		 * Set the coldness of this temperature to the given coldness.
		 * 
		 * @param 	coldness
		 * 			The new coldness for this temperature.
		 * @effect  The coldness of this temperature is set to the given coldness.
		 *          | setColdness(coldness)
		 */
	    public void cool(long coldness) {
	    	setColdness(coldness);
	    }
	    public static void main(String[] args) {
			System.out.println("Hallo");
			Temperature temp = new Temperature(0,20);
			System.out.println(temp.getTemperature());

		}

	    
}
