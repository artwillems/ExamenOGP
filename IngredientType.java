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
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/

public class IngredientType {
	
	/**********************************************************
     * constructor
     **********************************************************/
	
	/**
	 * Initialize a new ingredientType with given name, state, coldness and hotness.
	 * @param 	name
	 * 			The name of the new ingredientType.
	 * @param 	state
	 * 			The state of the new ingredientType.
	 * @param	coldness
	 * 			The standard coldness of the new ingredientType.
	 * @param	hotness
	 * 			The standard hotness of the new ingredientType.
	 * @effect  The name of the ingredient type is set to the given name.
	 * 			If the given name is not valid, a default name is set.
	 *          | setName(name) 
	 * @effect  The state of the ingredient type is set to the given state.
	 * 			If the given state is not valid, a default state is set.
	 *          | setState(state) 
	 * @effect  The standard temperature of the ingredient type is set to the given coldness and hotness.
	 * 			If the given standard temperature is not valid, a default standard temperature is set.
	 *          | setStandardTemp(standardTemp) 	 
	 */
	public IngredientType(String name, String state, long coldness, long hotness) {
		setName(name);
		setState(state);
		setStandardTemp(coldness, hotness);
	}
	
	/**
	 * Initialize a new ingredientType with given name and standard temperature.
	 * @param 	name
	 * 			The name of the new ingredientType.
	 * @param	standardTemp
	 * 			The standard temperature of the new ingredientType.
	 * @effect	The new ingredient type is initialized with the given name, coldness and hotness.
	 * 			| this(name,null,standardTemp)
	 *  
	 */
	public IngredientType(String name, long coldness, long hotness) {
		this(name,null,coldness,hotness);
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
     * @return	True if the name contains out of one word, then the word exists out of at least three letters. 
     * 			If the name contains more than one word, then each word exists out of at least two letters. Each word starts with
     * 			a capital letter or special allowed character. The rest is written in lower cases. 
     * 			|String[] splitName = name.split(" ");
     * 			|boolean result = true;
     * 			|if (splitName.length > 1)
     * 			|	for i in splitName
     * 			|		if ((splitName[i] == null) || (splitName[i].length() < 2) || (String.valueOf(splitName[i].charAt(0)).matches("[A-Z^()]+")) == false || (splitName[i].substring(1).matches("[a-z]+") == false))
     * 			|			result == false
     * 			|else	
     * 			|	result == (name != null && String.valueOf(name.charAt(0)).matches("[A-Z^()]+") && name.substring(1).matches("[a-z]+") && (name.length() >= 3)  )
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
	 * Variable referencing the state of this ingredientType.
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
	 * Variable referencing the standard temperature of this ingredientType.
	 */
	private Temperature standardTemp = null;
	
	
	
	 /**
     * Return the standard temperature of this ingredientType.
     */
    @Raw @Basic 
    public Temperature getStandardTemp() {
        return standardTemp;
    }
    
	 /**
     * Set the standard temperature of this ingredientType to the given standard temperature.
     * 
     * @param standardTemp
     * 		  The new standard temperature for this ingredientType
     */
    @Model 
    public void setStandardTemp(long coldness, long hotness) {
    	if (isValidStandardTemp(coldness,hotness)) {
    		this.standardTemp = new Temperature(coldness,hotness);
    	}
    	else {
    		
    		this.standardTemp = new Temperature(getDefaultStandardTemp().getColdness(),getDefaultStandardTemp().getHotness());
    	}
    }
    
    /**
     * Check whether the given standard temperature is legal for this ingredientType.
     * 
     * @param  	standardTemp
     *			The standard temperature to be checked
     * @return	True if the given standard temperature is strictly higher than [0,0] and either the coldness is strictly higher than zero or the hotness is strictly higher than zero.
     *			|if (((coldness == 0)  && (hotness == 0)) || ((coldness >0) && (hotness > 0)) || (coldness < 0) || (hotness <0))
     *			|	result == false
     *			|else
     *			|	result == true
     */
    public boolean isValidStandardTemp(long coldness, long hotness) {
        if (((coldness == 0)  && (hotness == 0)) || ((coldness >0) && (hotness > 0)) || (coldness < 0) || (hotness <0)) {
        		return false;
        }
        else {
        	return true;
        }
    	
    }

    /**
	 * Variable referencing the default standard temperature of this ingredientType.
	 */
    private static final Temperature defaultStandardTemp = new Temperature(0,20);
    

    /**
     * Return the default standard temperature for a new ingredientType which is to be used when the
     * given default standard temperature is not valid.
     *
     */
    @Model
    private static Temperature getDefaultStandardTemp() {
    	return defaultStandardTemp;
    }

    
    
}


