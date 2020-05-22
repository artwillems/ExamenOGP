import java.util.HashMap;
import java.util.List;
import java.util.Map;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class to transform the state of an AlchemicIngredient and with it its
 * quantity and IngredientContainer
 */

/**
 * @author	willemsart, Jérôme D'hulst, Marie Levrau
 * @version	1.8 
 */
public class Transmogrifier extends Device{
	
	/*****************************
	 * Constructor
	 *****************/
	
	/**
	 * Initializes a transmogrifier that changes the state and by extent the quantity of an
	 * AlchemicIngredient. 
	 *
	 * @param	labo
	 * 			The laboratory of this transmogrifier. 
	 * @effect	The new transmogrifier is initialized as a device with a given laboratory.
	 * 			| super(labo)
	 * @effect	The type is set to transmogrifier
	 * 			| setType("Transmogrifier")
	 */
	
	public Transmogrifier(Laboratory labo) {
		super(labo); 
		setType("Transmogrifier");
	}
	
	/*******************************
	 * State
	 ************************************/
	
	/**
	 * Change the state of the AlchemicIngredient
	 * 
	 * @param	ingredient
	 * 			The ingredient of which the state has to be changed.
	 * @return	The new state of the AlchemicIngredient
	 * 			If the ingredient were a liquid, it will now be a powder and vice versa. 
	 */
	
	private String changeState(AlchemicIngredient ingredient) {
		if(ingredient.getState() == "Liquid") {
			return "Powder"; 
		}
		else return "Liquid"; 
		
	}
	
	
	/**********************************************************
     * unit (conversion)
     **********************************************************/
	/**
	 * Creates a map with liquid units as keys and their corresponding powder units as values. 
	 * 
	 * @return A map with liquid units as keys and powder units as values. 
	 */
	
	private static Map<String, String> liquidToPowder(){
		Map<String, String> liquidToPowderUnit = new HashMap<String, String>(); 
		liquidToPowderUnit.put("drop", "pinch");
		liquidToPowderUnit.put("vial", "sachet"); 
		liquidToPowderUnit.put("bottle", "box"); 
		liquidToPowderUnit.put("jug", "sack"); 
		liquidToPowderUnit.put("barrel", "chest"); 
		liquidToPowderUnit.put("storeroom", "storeroom"); 
		return liquidToPowderUnit; 
	}
	
	/**
	 * Creates a map with powder units as keys and their corresponding liquid units as values. 
	 * 
	 * @return A map with powder units as keys and liquid units as values. 
	 */
	
	private static Map<String, String> powderToLiquid(){
		Map<String, String> powderToLiquidUnit = new HashMap<String, String>(); 
		powderToLiquidUnit.put("pinch", "drop");
		powderToLiquidUnit.put("sachet", "bottle");
		powderToLiquidUnit.put("box", "jug"); 
		powderToLiquidUnit.put("sack", "jug"); 
		powderToLiquidUnit.put("chest", "barrel"); 
		powderToLiquidUnit.put("storeroom", "storeroom");
		return powderToLiquidUnit; 
	}
	
	
	/**
	 * Change the unit when transmogrifying by going from liquid units to powder units and 
	 * vice versa. 
	 * 
	 * @param	ingredient
	 * 			The ingredient that needs to be transmogrified. 
	 * @return	The new unit of the transmogrified ingredient. If the ingredient is Liquid 
	 * 			unit conversion is found by looking in the liquid to powder map
	 * 			Otherwise the unit conversion is found by looking in the powder to liquid map
	 * 			| if (unitBfrTransmog == "Liquid")
	 * 			|	then for an entry in liquidToPowder.entrySet()
	 * 			|		if (ingredient.getUnit().equals(entry.getKey())
	 * 			|			then newUnit = entry.getValue
	 * 			|  else
	 * 			|	for an entry in powderToLiquid.entrySet()
	 * 			|		if (ingredient.getUnit().equals(entry.getKey())
	 * 			|			then newUnit = entry.getValue()
	 * 			| result == newUnit
	 */
	
	private String changeUnit(AlchemicIngredient ingredient) {
		String newUnit = null; 
		String unitBfrTransmog = ingredient.getState(); 
		if(unitBfrTransmog == "Liquid") {
			Map<String, String> liquidToPowder = liquidToPowder(); 
			for(Map.Entry<String, String> entry : liquidToPowder.entrySet()) {
				if(ingredient.getUnit().equals(entry.getKey())) {
					newUnit = entry.getValue(); 
				}
			}
		}
		else {
			Map<String, String> powderToLiquid = powderToLiquid(); 
			for(Map.Entry<String, String> entry : powderToLiquid.entrySet()) {
				if(ingredient.getUnit().equals(entry.getKey())) {
					newUnit = entry.getValue(); 
				}
			}
		}
		
		return newUnit; 
	}
	
	/**
	 * Makes a map of all the units for liquids and their conversion numbers when going from liquid to powder.
	 * 
	 * @return	The map with the liquid units as keys and the conversion numbers as values. 
	 */
	
	private Map<String, Integer> liquidConversion(){
		Map<String, Integer> liquid = new HashMap<String, Integer>();
		liquid.put("drop", 6/8); 
		liquid.put("vial", 5/7); 
		liquid.put("bottle", 15/42); 
		liquid.put("jug", 105/126); 
		liquid.put("barrel", 1);
		liquid.put("storeroom", 1); 
		return liquid; 
	}
	
	/**
	 * Makes a map with all the units for powders as keys and conversion numbers when going from powder to liquid, as keys
	 * 
	 * @return The map with the powder units as keys and the conversion numbers as values. 
	 */
	
	private Map<String, Integer> powderConversion(){
		Map<String, Integer> powder = new HashMap<String, Integer>();
		powder.put("pinch", 8/6); 
		powder.put("sachet", 7/5); 
		powder.put("box", 42/15); 
		powder.put("sack", 126/105); 
		powder.put("chest", 1);
		powder.put("storeroom", 1); 
		return powder; 
	}
	
	/**
	 * Change the quantity of the the transmogrified ingredient based on its new state and its new 
	 * unit. 
	 * 
	 * @param	ingredient
	 * 			The ingredient that has to be transmogrified. 
	 * @return	The new quantity of the transmogrified ingredient is formed depending on the state of the ingredient
	 * 			If the state is liquid than the liquidConversion map is used otherwise the powderConversion map is used
	 * 			| if (ingredient.getState() == "Liquid")
	 * 			|	then for an entry of liquidConversion.entrySet()
	 * 			|		if (ingredient.getUnit() == entry.getKey())
	 * 			|			then newQuantity = quantityBfrTransmog * entry.getValue()
	 * 			|  else
	 * 			|	for an entry of powderConversion.entrySet()
	 * 			|		if (ingredient.getUnit() == entry.getKey())
	 * 			|			then newQuantity = quantityBfrTransmpg * entry.getValue()
	 * 			| result == newQuantity 
	 * 
	 */
	
	private int changeQuantity(AlchemicIngredient ingredient) {
		int newQuantity = 0; 
		int quantityBfrTransmog = ingredient.getQuantity(); 
		if(ingredient.getState() == "Liquid") {
			Map<String, Integer> liquidConversion = liquidConversion(); 
			for(Map.Entry<String, Integer> entry : liquidConversion.entrySet()) {
				if(ingredient.getUnit() == entry.getKey()) {
					newQuantity = quantityBfrTransmog * entry.getValue(); 
				}
				
			}
		}
		else {
			Map<String, Integer> powderConversion = powderConversion();
			for(Map.Entry<String, Integer> entry : powderConversion.entrySet()) {
				if(ingredient.getUnit() == entry.getKey()) {
					newQuantity = quantityBfrTransmog * entry.getValue(); 
					}
				}
			}
		return newQuantity; 
	}
	
	/**
	 * Initialize the transmogrified element as a new AlchemicIngredient
	 * 
	 * @param	ingredient
	 * 			The AlchemicIngredient that has to be transmogrified
	 * @return	the transmogrified of the original AlchemicIngredient
	 *			| newQuant = changeQuantity(ingredient)
	 *			| newUnit = changeUnit(ingredient)
	 *			| newState = changeState(ingredient)
	 *			| if (newQuant != ingredient.getQuantity())
	 *			|   result == new AlchemicIngredient(newQuant,newUnit,type,hotness,coldness,newState)
	 * @effect 	If the quantity does not change, the characteristics of the ingredient are directly changed without forming 
	 * 			a new ingredient
	 * 			| if (newQuant == ingredient.getQuantity())
	 * 			|	then ingredient.changeState()
	 * 			|		 ingredient.changeUnit(newUnit)
	 * @return	If the quantity does not change, the ingredient itself is returned with changed characteristics
	 * 			| result == ingredient
	 * 					
	 */

	private AlchemicIngredient setTransmogrifiedIngredient(AlchemicIngredient ingredient) {
		int newQuant = changeQuantity(ingredient);
		String newUnit = changeUnit(ingredient); 
		String newState = changeState(ingredient); 
		if (newQuant == ingredient.getQuantity()) {
			ingredient.changeState();
			ingredient.changeUnit(newUnit);
			return ingredient;
		}
		else {
			List<IngredientType> type = ingredient.getIngredientTypeList(); 
			long hotness = ingredient.getTemperature().getHotness();
			long coldness = ingredient.getTemperature().getColdness(); 
			AlchemicIngredient transmogrified = new AlchemicIngredient(newQuant, newUnit, type, hotness, coldness, newState); 
			return transmogrified; 
		}
	}
	
	
	
	/**
	 * Execute the AlchemicOperation of this transmogrifier
	 * 
	 * @effect	the ingredient is adapted 
	 * 			result = setTransmogrifiedIngreient(getIngredientList().get(0)
	 * @post	The oldIngredient is terminated and the changed ingredient is added to the cleared list
	 * 			| getIngredientList().get(0).terminate()
	 * 			| this.ingredientList.clear()
	 * 			| this.ingredientList.add(result)
	 */
	@Override
	public void executeAlchemicOperation() {
		AlchemicIngredient result = setTransmogrifiedIngredient(getIngredientList().get(0));
		getIngredientList().get(0).terminate();
		this.ingredientList.clear();
		this.ingredientList.add(result);
		
		/**
		 * ingredientsInLaboratory.remove(AlchemicIngredient ingredient); 
		 * ingredientsInLaboratory.add(setTransmogrifiedIngredient);
		 */ 
		
	}
	
}