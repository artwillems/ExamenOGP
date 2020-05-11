import java.util.HashMap;
import java.util.Map;

public class Container {

	public Container(AlchemicIngredient ingredient, int capQuantity, String capUnit, String capState){
		setIngredient(ingredient);
		setCapQuantity(capQuantity);
		setCapUnit(capUnit);
		setCapState(capState);
	}
	
	private AlchemicIngredient ingredient = null;
	
	private void setIngredient(AlchemicIngredient ingredient) {
	  this.ingredient = ingredient; 
	}	
  
	@Basic @Raw
	public AlchemicIngredient getAlchemicIngredient() {
		return ingredient;
	}

	private int capQuantity = 0;
	
	public int getCapQuantity() {
		return this.capQuantity;
	}
	
	private String capUnit = null;
	
	public String getCapUnit() {
		return this.capUnit;
	}
	
	private String capState = null;
	
	public String getCapState(){
		return this.capState;
	}
	
	/*Controleer of zowel de state van ingredient als state van capacity gelijk zijn, want het kan niet zijn dat je een liquid wil stockeren in een capacity voor powders*/
	
	public boolean isValidState() {
		return (this.getAlchemicIngredient().getState() == this.getCapState());
	}
	
	private void setCapState(String capState) throws illegalStateException{
		if (isValidState() == false){
			throw new illegalStateException("The state of the ingredient is not the same as the state of this container");
		}
		else {
			this.capState = capState;
		}
	}
	
	/**
	 * Returns the quantity of this ingredient measured in the unit of spoons.
	 */
	public int getQuantityInSpoons() {
		int result = getCapQuantity();
		if (getCapState()=="Liquid") {
			for (Map.Entry<String,Integer> entry: liquidLibrary.entrySet()) {
				if (entry.getKey() == this.capUnit) {
					result = result * entry.getValue();
				}
			}
		}
		else {
			for(Map.Entry<String, Integer> entry: powderLibrary.entrySet()) {
				if (entry.getKey() == this.capUnit) {
					result = result * entry.getValue();
				}
			}
		}
	}
	
	private static Map<String,Integer> liquidLibrary = new HashMap<String,Integer>(){
		{
			put("drop",1/8);
			put("vial",5);
			put("bottle",15);
			put("spoon",1);
			put("jug",105);
			put("barrel",1260);
			put("storeroom",6300);
		}
	};
	
	private static Map<String,Integer> powderLibrary = new HashMap<String,Integer>(){
		{
			put("pinch",1/6);
			put("sachet",7);
			put("box",42);
			put("sack",126);
			put("chest",1260);
			put("storeroom",6300);
			put("spoon",1);
		}
	};
	
	public boolean isValidQuantity() {
		if (this.getAlchemicIngredient().getQuantityInSpoons() > this.getQuantityInSpoons()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	
}
