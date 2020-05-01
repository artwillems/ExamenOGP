/**
 * A class laboratory for doing Alchemic procedures on AlchemicIngredients with a certain Device
 */
import be.kuleuven.cs.som.annotate.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set; 
/**
 * @author willemsart, Jérôme D'hulst en Marie Levrau
 *
 */

public class Laboratory{
	
	/**************************************
	 * Constructors
	 *********************/
	
	/**
	 * 
	 * @param capacity
	 * 		  The capacity of the laboratory. 
	 * @param listOfIngredients
	 * 		  All the ingredients present in the laboratory at time of creation.
	 * @param listOfDevices
	 * 		  All the devices present in the laboratory at time of creation.
	 * 
	 */
	
	public Laboratory(long capacity, ArrayList<AlchemicIngredient> listOfIngredients, ArrayList<Device> listOfDevices) {
		setLabCapacity(capacity); 
		setIngredients(listOfIngredients); 
		setDevices(listOfDevices); 	
	}
	
	
	private void setLabCapacity(long capacity) {
		
	}
	
	private List<AlchemicIngredient> listOfIngredients = null;
	
	/**
	 * Set a collection of ingredients for this laboratory
	 * 
	 * @param listOfIngredients
	 * 
	 * @post the ingredients present at the laboratory become the new list of ingredients
	 */
	private void setIngredients(ArrayList<AlchemicIngredient> listOfIngredients) {
		this.listOfIngredients = listOfIngredients; 
	}
	
	
	private List<Device> listOfDevices = null; 
	
	/**
	 * 
	 * Set the devices to be used in the laboratory 
	 * 
	 * @param listOfDevices
	 * 		  All the devices that are stored in the laboratory. 
	 */
	private void setDevices(ArrayList<Device> listOfDevices) {
		if(areThereNoDoubleDevices(listOfDevices)) {
			this.listOfDevices = listOfDevices; 
		} 
		
	}
	
	
	/**
	 * 
	 * Checks whether there are no duplicate devices present at the laboratory
	 * 
	 * @param listWithDevices
	 * 		
	 * @return
	 */
	
	public static boolean areThereNoDoubleDevices(ArrayList<Device> listWithDevices) {
		Set<Device> setOfDevices = new HashSet<Device>(listWithDevices);
		int setLength = setOfDevices.size(); 
		int listLength = listWithDevices.size(); 
		return (listLength == setLength); 
	}
	
	
	
	

}
