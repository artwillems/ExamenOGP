import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


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
	 * @throws	DifferentLaboratoryException("The kettle and the ingredient have to be stored in the same laboratory",this)
	 * 
	 */
	@Override
	public void addIngredientFrom(IngredientContainer container) throws DifferentLaboratoryException{
		if (haveSameLaboratory(container.getAlchemicIngredient())) {
			this.ingredientList.add(container.getAlchemicIngredient());
		}
		else {
			throw new DifferentLaboratoryException("The kettle and the ingredient have to be stored in the same laboratory",this);
		}
	}
	
	
	/**********************************************************
     * AlchemicIngredient
     **********************************************************/
	
	/**
	 * 
	 * @return
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
		for (int i = 0; i<=potentialIngredients.size();i++) {
			if (potentialIngredients.get(i).getStandardTemperature().getHotness()>0) {
				ingredient = potentialIngredients.get(i);
				break;
			}
		}
		return ingredient.getStandardTemperature().getTemperature();
		
	}
	
	
	public List<Long> getTemperature(){
		List<AlchemicIngredient> ingredientList = getIngredientList();
		int totalQuantity = 0;
		long totalHotness = 0;
		long totalColdness = 0;
		for (int i = 0; i<ingredientList.size();i++) {
			int quantity = ingredientList.get(i).getQuantityInSpoons();
			long hotness = ingredientList.get(i).getTemperature().getHotness() * quantity;
			long coldness = ingredientList.get(i).getTemperature().getColdness() * quantity;
			totalHotness = totalHotness + hotness;
			totalColdness = totalColdness + coldness;
			totalQuantity = totalQuantity + quantity;
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
	
	public List<IngredientType> getIngredientTypeList(){
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