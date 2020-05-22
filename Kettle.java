import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * 
 * 
 * @author Jérôme D'hulst, Marie Levrau, Art Willems
 *
 */
public class Kettle extends Device{
	
	/**
	 * Initialize a new Kettle in a laboratory.
	 * 
	 * @param 	laboratory
	 * 			The laboratory of this kettle
	 * @effect	The kettle is initialized as a device with the laboratory reference
	 * 			| super(laboratory)
	 * @effect	The type of this kettle is set to "Kettle"
	 * 			| setType("Kettle")
	 */			
	public Kettle(Laboratory laboratory) {
		super(laboratory);
		setType("Kettle");
	}
	
	/**
	 * Add an ingredient from a container to the kettle
	 * 
	 * @post	If the ingredient and the kettle are stored in the same laboratory
	 * 			the ingredient is added to the ingredientList
	 * 			| if (haveSameLaboratory(container.getAlchemicIngredient()))
	 * 			| 	then getIngredientList().contains(container.getAlchemicIngredient())
	 * @effect	If the ingredient is added to the kettle, its container is deleted.
	 * 			| container.delete()
	 * @throws	DifferentLaboratoryException("The kettle and the ingredient have to be stored in the same laboratory",this)
	 * 
	 */
	@Override
	public void addIngredientFrom(IngredientContainer container) throws DifferentLaboratoryException{
		if (haveSameLaboratory(container.getAlchemicIngredient())) {
			this.ingredientList.add(container.getAlchemicIngredient());
			container.delete();
		}
		else {
			throw new DifferentLaboratoryException("The kettle and the ingredient have to be stored in the same laboratory",this);
		}
	}
	
	
	/**********************************************************
     * AlchemicIngredient
     **********************************************************/
	
	/**
	 * Return the ingredients  from which basic characteristics can be taken.
	 * 
	 * @return	The ingredients whose  standardTemperure is closest to [0,20].
	 * 			For every Ingredient i in the ingredientTypeList, the distance between its hotness and coldness is
	 * 			subtracted from 20 which leads to a variable referencing a "distance". This distance is added at the 
	 * 			same index in the temperatureList. If this distance is smaller than the
	 * 			minimum distance a new minimum has been reached.
	 * 			After the iteration every ingredient whose distance is equal to the minimum is a potentialIngredient.
	 * 			| for every ingredient in ingredientList
	 * 			|		coldness = ingredientList.get(i).getStandardTemp().getColdness();
			 	|		hotness  = ingredientList.get(i).getStandardTemp().getHotness();
			 	|		difference = Math.abs(20 - (hotness - coldness));
				|		temperatureList.add(i,difference);
	 * 			|		if (difference < minimum)
	 * 			|			minimum = difference;
	 * 			| for every ingredient i in ingredientList
	 * 			|		if (temperatureList.get(i) == minimum)
	 * 			|			then potentialIngredients.add(ingredientList.get(i));
	 * 			|
	 * 			| result == potentialIngredients
	 */
	public List<AlchemicIngredient> getPotentialIngredientList(){
		List<AlchemicIngredient> ingredientList = getIngredientList();
		List<Long> temperatureList = new ArrayList<Long>();
		long minimum = Long.MAX_VALUE;
		for(int i = 0; i <= ingredientList.size();i++) {
			long coldness = ingredientList.get(i).getStandardTemperature().getColdness();
			long hotness = ingredientList.get(i).getStandardTemperature().getHotness();
			long difference = Math.abs(20 - (hotness - coldness));
			temperatureList.add(i, difference);
			if (difference < minimum) {
				minimum = difference;
			}
		}
		List<AlchemicIngredient> potentialIngredients = new ArrayList<AlchemicIngredient>();
		for (int i = 0; i<= ingredientList.size();i++) {
			if (temperatureList.get(i) == minimum) {
				potentialIngredients.add(ingredientList.get(i));
			}
		}
		return potentialIngredients;
	}
	
	
	
	/**
	 * Returns the state of the mixture.
	 * 
	 * @param 	potentialIngredients
	 * 			The ingredients from which the state characteristic can be determined
	 * @return	the state of the mixture. Because liquid has priority over powder, we set
	 * 			the state to powder and if there is a potentialIngredient with a Liquid state,
	 * 			the result is liquid
	 * 			| result == "Powder"
	 * 			|  for an i in potentialIngredients.size()
	 * 			|	if (potentialIngredients.get(i).getState()== "Liquid")
	 * 			|		result == "Liquid"
	 */
	public String getState(List<AlchemicIngredient> potentialIngredients) {
		String state = "Powder";
		for (int i = 0; i<=potentialIngredients.size();i++) {
			if (potentialIngredients.get(i).getState()== "Liquid") {
				state = "Liquid";
			}
		}
		return state;
	}
	
	
	/**
	 * Return the quantity in spoons of this mixture. Units smaller than spoons can be lost.
	 * 
	 * @return  The sum of quantities in spoons of all ingredients. Afterwards we use the floor function
	 * 			to round the result
	 * 			| for an i in getIngredientList().size()
	 * 			|	quantity = quantity + getIngredientList().get(i).getQuantityInSpoons();
	 * 			|  result == floor(quantity)
	 */
	public int getQuantity() {
		int quantity = 0;
		for (int i = 0; i <= getIngredientList().size();i++) {
			quantity = quantity + getIngredientList().get(i).getQuantityInSpoons();
		}
		return (int) Math.floor(quantity);
	}
	
	
	/**
	 * Return the standard temperature of this mixture
	 * 
	 * @return 	the standard temperature of this mixture which is the standardTemperature
	 * 			of its ingredient which has a standardTemperature closest to [0,20]
	 * 			If there are multiple ingredient with the same distance to [0,20]
	 * 			the hottest one is selected.
	 * 			|  ingredient = potentialIngredients.get(0)
	 * 			| for an i in potentialIngredients.size()
	 * 			|	if (potentialIngredients.get(i).getStandardTemp().getHotness()>20)
	 * 			|		then ingredient = potentialIngredients.get(i)
	 * 			|			break
	 * 			|  result == ingredient.getStandardTemp().getTemperature()
	 */
	public List<Long> getStandardTemperature(List<AlchemicIngredient> potentialIngredients){
		AlchemicIngredient ingredient = potentialIngredients.get(0);
		for (int i = 0; i<=potentialIngredients.size();i++) {
			if (potentialIngredients.get(i).getStandardTemperature().getHotness()>0) {
				ingredient = potentialIngredients.get(i);
				break;
			}
		}
		return ingredient.getStandardTemperature().getTemperature();
		
	}
	
	
	/**
	 * Return the temperature of this mixture
	 * 
	 * @return 	the temperature of this mixture which is formed by added the coldness and hotness of all ingredients together. 
	 * 			The total coldness is then subtracted from the hotness. If the result is negative this is the new coldness of the mixture
	 * 			and the hotness is zero, otherwise the result is the new hotness of the mixture and the coldness is zero. Finally the total 
	 * 			temperature gets divided by the totalQuantity
	 * 			| for an i in ingredientList.size()
	 * 			|	quantity = ingredientList.get(i).getQuantityInSpoons()
				|	hotness = ingredientList.get(i).getTemperature().getHotness() * quantity
				|	coldness = ingredientList.get(i).getTemperature().getColdness() * quantity
				|	totalHotness = totalHotness + hotness
				|	totalColdness = totalColdness + coldness
				|  totalTemperature = totalHotness - totalColdness
				|  if (totalTemperature < 0)
				|  	then totalTemperature = Math.abs(totalTemperature) / totalQuantity
				|		 temperature.add(0,totalTemperature)
				|		 temperature.add(0,(long) 0)
				|  else
				|  		 totalTemperature = totalTemperature / totalQuantity
				|		 temperature.add(0, (long) 0)
				|		 temperature.add(1, totalTemperature)
				|  result == temperature
	 */
	public List<Long> getTemperature(){
		List<AlchemicIngredient> ingredientList = getIngredientList();
		int totalQuantity = getQuantity();
		long totalHotness = 0;
		long totalColdness = 0;
		for (int i = 0; i<ingredientList.size();i++) {
			int quantity = ingredientList.get(i).getQuantityInSpoons();
			long hotness = ingredientList.get(i).getTemperature().getHotness() * quantity;
			long coldness = ingredientList.get(i).getTemperature().getColdness() * quantity;
			totalHotness = totalHotness + hotness;
			totalColdness = totalColdness + coldness;
		}
		long totalTemperature = totalHotness - totalColdness;
		List<Long> temperature = new ArrayList<Long>();
		if (totalTemperature < 0) {
			totalTemperature = Math.abs(totalTemperature) / totalQuantity;
			temperature.add(0,totalTemperature);
			temperature.add(0,(long) 0);
		}
		else {
			totalTemperature = totalTemperature / totalQuantity;
			temperature.add(0, (long) 0);
			temperature.add(1, totalTemperature);
		}
		return temperature;
	}
	
	/**
	 * Return the ingredientTypes of this mixture
	 * 
	 * @return	the ingredientTypes of this mixture which are the ingredientTypes of its AlchemicIngredients
	 * 			without doubles.	
	 * 			| for an i in getIngredientList().size()
	 * 			|	for a j in getIngredientList().get(i).getIngredientTypeList().size()
	 * 			|		if (!ingredientTypeList.contains(getIngredientList().get(i).getIngredientTypeList().get(j)))
	 * 			|			ingredientTypeList.add(getIngredientList().get(i).getIngredientTypeList().get(j)
	 * 			|  result == ingredientTypeList
	 * 
	 */
	private List<IngredientType> getIngredientTypeList(){
		List<IngredientType> ingredientTypeList = new ArrayList<IngredientType>();
		for (int i = 0; i <= getIngredientList().size();i++) {
			for (int j =0;j<getIngredientList().get(i).getIngredientTypeList().size();j++) {
				if (!ingredientTypeList.contains(getIngredientList().get(i).getIngredientTypeList().get(j))){
					ingredientTypeList.add(getIngredientList().get(i).getIngredientTypeList().get(j));
				}
				
			}
		}
		return ingredientTypeList;
	}
	
	
	/**
	 * Execute the operation of the kettle
	 * 
	 * @post	The new ingredient is formed with the correct characteristics
	 * 			newIngredient = new AlchemicIngredient(quantity,"spoon",ingredientTypeList,temperature.get(0),temperature.get(1),state);
	 * @post	After the operation a new ingredient has been formed by combining the other ingredients in to one
	 * 			Therefore the other ingredients don't exist anymore and are terminated. This one ingredient is than added 
	 * 			to the cleared ingredientList of this kettle
	 * 			| for an i in getingredientList().size()
	 * 			|	getIngredientList().get(i).terminate()
	 * 			|  this.ingredientList.clear()
	 * 			|  ingredientList.add(newIngredient)
	 * @throws	NoIngredientInDeviceException("There is no ingredient in this device",this)
	 * 			The kettle does not have an ingredient
	 * 			| this.countIngredient()<1
	 */
	public void executeAlchemicOperation() throws NoIngredientInDeviceException{
		if (this.countIngredients()<1) {
			throw new NoIngredientInDeviceException("There is no ingredient in this device",this);
		}
		else {
			List<AlchemicIngredient> potentialIngredients = getPotentialIngredientList();
			String state = getState(potentialIngredients);
			int quantity = getQuantity();
			List<Long> temperature = getTemperature();
			List<IngredientType> ingredientTypeList = getIngredientTypeList();
			for (int i = 0; i<= getIngredientList().size();i++) {
				getIngredientList().get(i).terminate();
			}
			this.ingredientList.clear();
			AlchemicIngredient newIngredient = new AlchemicIngredient(quantity,"spoon",ingredientTypeList,temperature.get(0),temperature.get(1),state);
			ingredientList.add(newIngredient);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}