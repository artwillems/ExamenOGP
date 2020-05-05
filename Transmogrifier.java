import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class to transform the state of an AlchemicIngredient and with it its
 * quantity and IngredientContainer
 */

/**
 * @author willemsart, Jérôme D'hulst, Marie Levrau
 * @version 1.8 
 */
public class Transmogrifier extends Device{
	
	/*****************************
	 * Constructor
	 *****************/
	
	/**
	 * Initializes a transmogrifier that changes the state and by extent the quantity of an
	 * AlchemicIngredient. 
	 *
	 * @param labo
	 * 		  the laboratory in which the transmogrifier is set. 
	 */
	
	public Transmogrifier(Laboratory labo) {
		super(labo); 
	}
	
	/*******************************
	 * Methods
	 *********************/
	
	/**
	 * Change the state of the AlchemicIngredient
	 * 
	 * @param ingredient
	 * 		  The ingredient of which the state has to be changed.
	 * @return The new state of the AlchemicIngredient
	 * 		   If the ingredient were a liquid, it will now be a powder and vice versa. 
	 */
	
	private String changeState(AlchemicIngredient ingredient) {
		if(ingredient.getState() == "Liquid") {
			return "Powder"; 
		}
		else return "Liquid"; 
		
	}
	
	
	/**
	 * Change the unit when transmogrifying by going from liquid units to powder units and 
	 * vice versa. 
	 * 
	 * @param ingredient
	 * 		  The ingredient that needs to be transmogrified. 
	 * @return The new unit of the transmogrified ingredient. 
	 */
	
	private String changeUnit(AlchemicIngredient ingredient) {
		String newUnit = null; 
		String unitBfrTransmog = ingredient.getUnit(); 
		if(this.changeState(ingredient)=="Liquid") {
			if(unitBfrTransmog == "drop") {
				newUnit = "pinch"; 
			}
			if(unitBfrTransmog == "vial") {
				newUnit = "sachet"; 
			}
			if(unitBfrTransmog == "bottle") {
				newUnit = "box"; 
			}
			if(unitBfrTransmog == "jug") {
				newUnit = "sack";
			}
			if(unitBfrTransmog == "barrel") {
				newUnit = "chest"; 
			}
			if(unitBfrTransmog == "storeroom") {
				newUnit = "storeroom"; 
			}
		}
		if(unitBfrTransmog == "pinch") {
			newUnit = "drop";
		}
		if(unitBfrTransmog == "sachet") {
			newUnit = "bottle"; 
		}
		if(unitBfrTransmog == "box") {
			newUnit = "jug"; 
		}
		if(unitBfrTransmog == "sack") {
			newUnit = "barrel";
		}
		if(unitBfrTransmog == "chest") {
			newUnit = "barrel";
		}
		if(unitBfrTransmog == "storeroom") {
			newUnit = unitBfrTransmog; 
		}
		return newUnit; 
	}
	
	/**
	 * Makes a map of all the units for liquids and their conversion numbers when going from liquid to powder.
	 * 
	 * @return The map with the liquid units as keys and the conversion numbers as values. 
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
	 * @param ingredient
	 * 		  The ingredient that has to be transmogrified. 
	 * @return The new quantity of the transmogrified ingredient. 
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
		Map<String, Integer> powderConversion = powderConversion();
		for(Map.Entry<String, Integer> entry : powderConversion.entrySet()) {
			if(ingredient.getUnit() == entry.getKey()) {
				newQuantity = quantityBfrTransmog * entry.getValue(); 
			}
		}
		return newQuantity; 
	}
	
	/**
	 * Initialize the transmogrified element as a new AlchemicIngredient
	 * 
	 * @param ingredient
	 * 		  The AlchemicIngredient that has to be transmogrified
	 * @return the transmogrified of the original AlchemicIngredient
	 */

	private AlchemicIngredient setIngredient(AlchemicIngredient ingredient) {
		int newQuant = changeQuantity(ingredient);
		String newUnit = changeUnit(ingredient); 
		String newState = changeState(ingredient); 
		List<IngredientType> type = ingredient.getIngredientTypeList(); 
		long hotness = ingredient.getHotness();
		long coldness = ingredient.getColdness(); 
		AlchemicIngredient transmogrified = new AlchemicIngredient(newQuant, newUnit, type, hotness, coldness, newState); 
		return transmogrified; 
	}
	
	/**
	 * Transmogrify the ingredient in this laboratory. 
	 * 
	 * @param ingr
	 * 		  the ingredient that has to be transmogrified
	 * @return the transmogrified ingredient 
	 */
	
	public void changeIngredient(AlchemicIngredient ingr) {
		List<AlchemicIngredient> ingredientsInLab = this.getLaboratory().getIngredients(); 
		if(ingredientsInLab.contains(ingr)) {
			ingredientsInLab.remove(ingr); 
			AlchemicIngredient transmogrified = setIngredient(ingr); 
			addTransmogrifiedToLabo(ingredientsInLab, transmogrified); 
			
		}
		/**throw exception*/
		
	}
	

	/**
	 * Add the transmogrified ingredient to a the list of ingredients in the laboratory. 
	 * 
	 * @param transmogrifiedIngredient
	 * 		  The transmogrified ingredient that needs to be added to the ingredients
	 * 		  of the laboratory in which the transmogrifier is placed. 
	 */
	
	private void addTransmogrifiedToLabo(List<AlchemicIngredient> oldIngredients, AlchemicIngredient transmogrifiedIngredient) {
		oldIngredients.add(transmogrifiedIngredient);
		Laboratory lab = getLaboratory(); 
		
	}
	

	
}
