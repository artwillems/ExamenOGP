import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


public class Kettle extends Device{
	
	public Kettle(Laboratory laboratory) {
		super(laboratory);
		setType("Kettle");
	}
	
	public void addIngredientFrom(IngredientContainer container) throws DifferentLaboratoryException{
		if (haveSameLaboratory(container.getAlchemicIngredient())) {
			this.ingredientList.add(container.getAlchemicIngredient());
		}
		else {
			throw new DifferentLaboratoryException("The device and the ingredient have to be stored in the same laboratory",this);
		}
	}
	
	
	/**********************************************************
     * AlchemicIngredient
     **********************************************************/
	
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
	
	public String getState(List<AlchemicIngredient> potentialIngredients) {
		String state = "Powder";
		for (int i = 0; i<=potentialIngredients.size();i++) {
			if (potentialIngredients.get(i).getState()== "Liquid") {
				state = "Liquid";
			}
		}
		return state;
	}
	
	
	public int getQuantity() {
		int quantity = 0;
		for (int i = 0; i <= getIngredientList().size();i++) {
			quantity = quantity + getIngredientList().get(i).getQuantityInSpoons();
		}
		return quantity;
	}
	
	public List<Long> getStandardTemperature(List<AlchemicIngredient> potentialIngredients){
		AlchemicIngredient ingredient = potentialIngredients.get(0);
		return ingredient.getStandardTemperature().getTemperature();
		
	}
	
	
	
	
	public void executeAlchemicOperation() throws NoIngredientInDeviceException{
		if (this.countIngredients()<1) {
			throw new NoIngredientInDeviceException("There is no ingredient in this device",this);
		}
		else {
			List<AlchemicIngredient> potentialIngredients = getPotentialIngredientList();
			String state = getState(potentialIngredients);
			int quantity = getQuantity();
			List<Long> standardTemperature = getStandardTemperature(potentialIngredients);
			this.ingredientList.clear();
			/*nieuw ingredient toevoegen*/
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}