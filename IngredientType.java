import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of ingredientTypes
 * 
 * @invar	Each ingredientType must have a properly spelled name.
 * 			| isValidName(getName())
 * @invar	Each ingredientType must have a valid state.
 * 			| isValidState(getState())
 * @invar	Each ingredientType must have a valid standard temperature.
 * 			| isValidStandardTemp(getStandardTemp())
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/

public class IngredientType {
	
	/**********************************************************
     * constructor
     **********************************************************/
	
	/**
	 * Initialize a new ingredientType with given name, state and standard temperature.
	 * @param 	name
	 * 			The name of the new ingredientType.
	 * @param 	state
	 * 			The state of the new ingredientType.
	 * @param	standardTemp
	 * 			The standard temperature of the new ingredientType.
	 * 
	 * @effect  The name of the ingredient type is set to the given name.
	 * 			If the given name is not valid, a default name is set.
	 *          | setName(name) 
	 * @effect  The state of the ingredient type is set to the given state.
	 * 			If the given state is not valid, a default state is set.
	 *          | setState(state) 
	 * @effect  The standard temperature of the ingredient type is set to the given standard temperature.
	 * 			If the given standard temperature is not valid, a default standard temperature is set.
	 *          | setStandardTemp(standardTemp) 	 
	 */
	public IngredientType(String name, String state, long standardHotness, long standardColdness) {
		setName(name);
		setState(state);
		setStandardTemp(standardTemp);
	}
	
	/**
	 * Initialize a new ingredientType with given name and standard temperature.
	 * @param 	name
	 * 			The name of the new ingredientType.
	 * @param	standardTemp
	 * 			The standard temperature of the new ingredientType.
	 * @effect	The new ingredient type is initialized with the given name, state and standard temperature.
	 * 			| this(name,null,standardTemp)
	 *  
	 */
	public IngredientType(String name, long standardHotness, long standardColdness) {
		this(name,null,standardHotness,standardColdness);
	}
	
	/**********************************************************
     * name 
     **********************************************************/
	
	/**
	 * Variable referencing the name of this ingredientType.
	 */
	private String name = null;
	
    /**
     * Return the name of this ingredientType.
     */
    @Raw @Basic 
    public String getName() {
        return name;
    }
    
    /**
     * Check whether the given name is a legal name for an ingredientType.
     * 
     * @param  	name
     *			The name to be checked
     * @return	True if 
     * (name.charAt(0)).matches("[A-Z^()]+")
     * String.valueOf(name.charAt(0))
     */
    public static boolean isValidName(String name) {
    	String[] splitName = name.split(" ");
    	boolean result = true;
    	if (splitName.length > 1) {
    		for (int i = 0; i < splitName.length ; i++) {
    			  if  ((splitName[i] == null) || (splitName[i].length() < 2) || (String.valueOf(splitName[i].charAt(0)).matches("[A-Z^()]+")) == false || (splitName[i].substring(1).matches("[a-z]+") == false)) {
    				  result = false;
    				  break;
    			  }
    			}
    		return result;
    	}
    	else {
    		return (name != null && String.valueOf(name.charAt(0)).matches("[A-Z^()]+") && name.substring(1).matches("[a-z]+") && (name.length() >= 3)  );
    	}
    }
    
    /**
     * Set the name of this ingredientType to the given name.
     *
     * @param   name
     * 			The new name for this ingredientType.
     * @post    If the given name is valid, the name of
     *          this ingredientType is set to the given name,
     *          otherwise the name of the ingredientType is set to a valid name (the default).
     *          | if (isValidName(name))
     *          |      then new.getName().equals(name)
     *          |      else new.getName().equals(getDefaultName())
     */
    @Raw @Model 
    private void setName(String name) {
        if (isValidName(name)) {
        		this.name = name;
        } else {
        		this.name = getDefaultName();
        }
    }
    
    /**
     * Return the name for a new ingredientType which is to be used when the
     * given name is not valid.
     *
     * @return   A valid name.
     *         | isValidName(result)
     */
    @Model
    private static String getDefaultName() {
        return "new_name";
    }
    
    

	/**********************************************************
     * state
     **********************************************************/
	
	/**
	 * Variable referencing the name of this ingredientType.
	 */
	private String state = null;
	
	 /**
     * Return the state of this ingredientType.
     */
    @Raw @Basic 
    public String getState() {
        return state;
    }
    
    /**
     * Check whether the given state is a legal state for an ingredientType.
     * 
     * @param  	state
     *			The state to be checked
     * @return	True if the given state is either a Liquid or a Powder
     * 			| result ==
     * 			|	(state == "Liquid") || (state == "Powder")		
     */
    public static boolean isValidState(String state) {
        return ((state == "Liquid") || (state == "Powder"));
    }
	
    /**
     * Set the state of this ingredientType to the given state.
     *
     * @param   state
     * 			The new state for this ingredientType.
     * @post    If the given state is valid, the state of
     *          this ingredientType is set to the given state,
     *          otherwise the state of the ingredientType is set to a valid state (the default).
     *          | if (isValidState(name))
     *          |      then new.getState().equals(state)
     *          |      else new.getState().equals(getDefaultState())
     */
    @Raw @Model 
    private void setState(String state) {
        if (isValidState(state)) {
        		this.state = state;
        } else {
        		this.state = getDefaultState();
        }
    }
    
    /**
     * Return the state for a new ingredientType which is to be used when the
     * given state is not valid.
     *
     * @return   A valid state.
     *         | isValidState(result)
     */
    @Model
    private static String getDefaultState() {
        return "Liquid";
    }
    
	/**********************************************************
     * standardTemperature
     **********************************************************/
    
	/**
	 * Variable referencing the standard coldness of this temperature.
	 */
	private long standardColdness = 0;
	
    /**
	 * Variable referencing the standardHotness of this temperature.
	 */
	private long standardHotness = 0;
	
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
	 * Return the standard coldness of this temperature.
	 */
	@Raw @Basic 
	public long getColdness() {
		return standardColdness;
	}
	
	/**
	 * Return the standardHotness of this temperature.
	 */
	@Raw @Basic 
	public long getHotness() {
		return standardHotness;
	}
	
	/**
	 * Set the standard coldness of this temperature to the given standard coldness.
	 * 
	 * @param 	standardColdness
	 * 			The new standardColdness for this temperature.
	 * @post    If the given standardColdness is valid, the standardColdness of this temperature is set to the given standardColdness,
	 *          otherwise the standardColdness of this temperature is set to a valid standardColdness (the default).
	 *          | if (isValidValue(standardColdness))
	 *          |      then new.getStandardColdness().equals(standardStandardColdness)
	 *          |      else new.getStandardColdness().equals(getDefaultStandardColdness())
	 */
	private void setStandardColdness(long standardColdness) {
		if(isValidValue(standardColdness)) {
			this.standardColdness = standardColdness;
		}
		else {
			this.standardColdness = this.getDefaultStandardColdness();
		}		
	}
    
	/**
	 * Set the hotness of this temperature to the given hotness.
	 * 
	 * @param 	standardHotness
	 * 			The new standardHotness for this temperature.
	 * @post    If the given standardHotness is valid, the standardHotness of this temperature is set to the given standardHotness,
	 *          otherwise the standardHotness of this temperature is set to a valid standardHotness (the default).
	 *          | if (isValidValue(standardHotness))
	 *          |      then new.getStandardHotness().equals(standardHotness)
	 *          |      else new.getStandardHotness().equals(getDefaultStandardHotness())
	 */
	private void setStandardHotness(long standardHotness) {
		if(isValidValue(standardHotness)) {
			this.standardHotness = standardHotness;
		}
		else {
			this.standardHotness = this.getDefaultStandardHotness();
		}
		
	}

    /**
     * Return the temperature.
     */
    @Raw @Basic 
    public List<Long> getTemperature() {
    	List<Long> temperature = new ArrayList<Long>();
    	temperature.add(standardColdness);
    	temperature.add(standardHotness);
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
     * @param  	standardColdness
     *			The standardColdness to be checked
     * @param  	standardHotness
     *			The standardHotness to be checked
     * @return	True if the given standardColdness and standardHotness are not both different from 0, and the given standardColdness and standardHotness are between 0 and 10000. 
     */
    public static boolean isValidTemperature(long standardColdness,long standardHotness) {
        if (((standardColdness != 0)  && (standardHotness != 0)) || !(isValidValue(standardColdness)) || !(isValidValue(standardHotness)))  {
        	return false;
        }

        else {
        	return true;
        }
    }
    

    /**
     * Return the standard coldness which is to be used when the given standard coldness is not valid.
     *
     * @return   A valid standard coldness.
     *         	| isValidValue(result)
     */
    @Model
    private static long getDefaultStandardColdness() {
    	return 0;
    }
    
    /**
     * Return the standard hotness which is to be used when the given standard hotness is not valid.
     *
     * @return   A valid standard hotness.
     *         	| isValidValue(result)
     */
    @Model
    private static long getDefaultStandardHotness() {
    	return 20;
    }

    
    /**
	 * Set the temperature to the given temperature (hotness and coldness)
	 * 
	 * @param 	standardColdness
	 * 			The new standardColdness for this temperature.
	 * @param 	standardHotness
	 * 			The new standardHotness for this temperature.
	 * @post    If the given standardColdness and standardHotness are valid, the standardColdness and standardHotness of this temperature is set to the given standardColdness and standardHotness,
	 *          otherwise the standardColdness and standardHotness of this temperature is set to a valid standardColdness and standardHotness (the default).
	 *          | if (isValidTemperature(coldness,standardHotness))
	 *          |      then new.getStandardColdness().equals(standardColdness)
	 *          			new.getStandardHotness().equals(standardHotness)
	 *          |      else new.getStandardColdness().equals(getDefaultStandardColdness())
	 *          			new.getStandardHotness().equals(getDefaultStandardHotness())
	 */
    @Raw @Model 
    private void setTemperature(long standardColdness,long standardHotness) {
        if (isValidTemperature(standardColdness,standardHotness)) {
        		this.standardColdness = standardColdness;
        		this.standardHotness = standardHotness;
        } else {
        		this.standardColdness = this.getDefaultStandardColdness();
        		this.standardHotness = this.getDefaultStandardHotness();
        }
    }
    
	/**
	 * Set the standard hotness of this temperature to the given standard hotness.
	 * 
	 * @param 	standardHotness
	 * 			The new standardHotness for this temperature.
	 * @effect  The standardHotness of this temperature is set to the given standardHotness.
	 *          | setStandardHotness(standardHotness)
	 */
    public void heat(long standardHotness) {
    	setStandardHotness(standardHotness);
    	
    }
    
	/**
	 * Set the standardColdness of this temperature to the given standardColdness.
	 * 
	 * @param 	standardColdness
	 * 			The new standardColdness for this temperature.
	 * @effect  The standardColdness of this temperature is set to the given standardColdness.
	 *          | setStandardColdness(standardColdness)
	 */
    public void cool(long standardColdness) {
    	setStandardColdness(standardColdness);
    }

    

    
    
}

