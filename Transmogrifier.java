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
			newUnit = "vial"; 
		}
		if(unitBfrTransmog == "box") {
			newUnit = "jug"; 
		}
		return newUnit; 
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
		int quantityBeforeTransmog = ingredient.getQuantityInSpoons();
		
		return newQuantity; 
	}
	
	
	
	/**
	 * Transmogrify the ingredient in this laboratory. 
	 * 
	 * @param ingr
	 * 		  the ingredient that has to be transmogrified
	 * @return the transmogrified ingredient 
	 */
	
	public void changeIngredient(AlchemicIngredient ingr) {
		int newQuant = this.changeQuantity(ingr); 
		String newUnit = this.changeUnit(ingr); 
		IngredientType type = ingr.getIngredientType(); 
		long hotness = ingr.getHotness(); 
		long coldness = ingr.getColdness(); 
		String newState = this.changeState(ingr); 
		AlchemicIngredient transmogrifiedIngredient = new AlchemicIngredient(newQuant, newUnit, type, hotness, coldness, newState);
		this.destroyOldIngredient(ingr);
		this.addTransmogrifiedToLabo(transmogrifiedIngredient);
	}
	
	/**
	 * Remove the old ingredient from the list of ingredients in the laboratory 
	 * to which the transmogrifier belongs
	 * NOTE: work in progress
	 * 
	 * @param ingr
	 * 		  The original ingredient that has to be removed. 
	 * 		 
	 */
	private void destroyOldIngredient(AlchemicIngredient ingr) {
		Laboratory labo = this.getLaboratory(); 
		
	}
	
	/**
	 * Add the transmogrified ingredient to a the list of ingredients in the laboratory. 
	 * 
	 * @param transmogrifiedIngredient
	 * 		  The transmogrified ingredient that needs to be added to the ingredients
	 * 		  of the laboratory in which the transmogrifier is placed. 
	 */
	private void addTransmogrifiedToLabo(AlchemicIngredient transmogrifiedIngredient) {
		
	}
	

	
}
