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
 * 			| isValidStandardTemperature(getStandardTemperature())
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
	 * @param	standardColdness
	 * 			The standard coldness of the new ingredientType.
	 * @param	standardHotness
	 * 			The standard hotness of the new ingredientType.
	 * 
	 * @effect  The name of the ingredient type is set to the given name.
	 * 			If the given name is not valid, a default name is set.
	 *          | setName(name) 
	 * @effect  The state of the ingredient type is set to the given state.
	 * 			If the given state is not valid, a default state is set.
	 *          | setState(state) 
	 * @effect  The standard temperature of the ingredient type is set to the given standard temperature.
	 * 			If the given standard temperature is not valid, a default standard temperature is set.
	 *          | setStandardTemperature(standardTemperature) 	 
	 */
	public IngredientType(String name, String state, int standardColdness,int standardHotness) {
		setName(name);
		setState(state);
		setStandardTemperature(standardColdness,standardHotness);
	}
	
	/**
	 * Initialize a new ingredientType with given name and standard temperature.
	 * @param 	name
	 * 			The name of the new ingredientType.
	 * @param	standardColdness
	 * 			The standard coldness of the new ingredientType.
	 * @param	standardHotness
	 * 			The standard hotness of the new ingredientType.
	 * 
	 * @effect  The name of the ingredient type is set to the given name.
	 * 			If the given name is not valid, a default name is set.
	 *          | setName(name) 
	 * @effect  The standard temperature of the ingredient type is set to the given standard temperature.
	 * 			If the given standard temperature is not valid, a default standard temperature is set.
	 *          | setStandardTemperature(standardTemperature) 	 
	 */
	public IngredientType(String name, int standardColdness,int standardHotness) {
		setName(name);
		setStandardTemperature(standardColdness,standardHotness);
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
     */
    public static boolean isValidName(String name) {
    	String[] splitName = name.split(" ");
    	boolean result = true;
    	if (splitName.length > 1) {
    		for (int i = 0; i < splitName.length ; i++) {
    			  if  ((splitName[i] == null) || (splitName[i].length() < 2) || (name.matches("[a-zA-Z^()]+") == false)) {
    				  result = false;
    				  break;
    			  }
    			}
    		return result;
    	}
    	else {
    		return (name != null && name.matches("[a-zA-Z^()]+") && (name.length() >= 3)  );
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
        return "new_state";
    }
    
	/**********************************************************
     * standardTemperature
     **********************************************************/
    
    /**
	 * Variable referencing the standard hotness of this ingredientType.
	 */
	private long standardHotness = 0;
	
    /**
	 * Variable referencing the standard coldness of this ingredientType.
	 */
	private long standardColdness = 0;
	
	

	
    /**
     * Return the standard temperature of this ingredientType.
     */
    @Raw @Basic 
    public List<Long> getStandardTemperature() {
    	List<Long> standardTemperature = new ArrayList<Long>();
    	standardTemperature.add(standardColdness);
    	standardTemperature.add(standardHotness);
        return standardTemperature;
    }
    

    /**
	 * Variable referencing the standard temperature of this ingredientType.
	 */

    @Raw @Model 
    private void setStandardTemperature(long standardColdness,long standardHotness) {
        if (isValidStandardTemperature(standardColdness,standardHotness)) {
        		this.standardColdness = standardColdness;
        		this.standardHotness = standardHotness;
        } else {
        		this.standardColdness = this.getDefaultStandardTemperature().get(0);
        		this.standardHotness = this.getDefaultStandardTemperature().get(1);
        }
    }
    
    /**
     * Check whether the given standard temperature is a legal standard temperature for a ingredientType.
     * 
     * @param  	standardTemperature
     *			The standard temperature to be checked
     * @return	True if the given standard temperature is strictly higher than [0,0] and 
     * 			the coldness and hotness are not both different from 0. 
     */
    public static boolean isValidStandardTemperature(long standardColdness,long standardHotness) {
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

