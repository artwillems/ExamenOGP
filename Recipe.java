import java.util.List;

/**
 * A class of recipes involving a list of operations that needs to be executed on a list of quantities of ingredients, and a laboratory where the recipe is executed.
 * 
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/
public class Recipe {
	
	public Recipe(List<String> operationList, List<String> ingredientList, Laboratory labo) {
		setOperationList(operationList);
		setIngredientList(ingredientList);
		setLaboratory(labo);
	}
	
	/*positie in ingredientList*/
	private int ingredientInUse = 0;
	
	private Laboratory labo = null;
	
	private List<> mixList = null;
	
	private List<IngredientContainer> recipeList = null;
	
	private List<String> operationList = null;
	
	private List<String> ingredientList = null;
	
	public List<String> getOperationList(){
		return operationList;
	}
	
	public List<String> getIngredientList(){
		return ingredientList;
	}
	
	public Laboratory getLaboratory() {
		return labo;
	}
	
	private void setOperationList(List<String> operationList) {
		
	}
	
	private void setIngredientList(List<String> ingredientList) {
		
	}
	
	private void setLaboratory(Laboratory labo) {
	
	}
	
	
	public void add(int amount, String unit, String ingredientName) {
		IngredientContainer container = this.getLaboratory().getAmountFromLabo(ingredientName, amount, unit);
		recipeList.add(container);
	}
	
	public void heat(IngredientContainer container) {
		Oven oven = this.getLaboratory().getDevices() /*nog zorgen dat je hier oven krijgt*/
		oven.changeOvenTemperature(0,50);
		oven.addIngredientFrom(container);
		oven.executeAlchemicOperation();
	}
	
	public void cool(IngredientContainer container) {
		CoolingBox coolingBox = this.getLaboratory().getDevices() /*nog zorgen dat je hier coolingbox krijgt*/
		coolingBox.changeCoolingBoxTemperature(50,0);
		coolingBox.addIngredientFrom(container);
		coolingBox.executeAlchemicOperation();
	}
	
	public void mix() {
		
	}
	
	public void executeRecipe() {
		for (int i = 0; i < operationList.size(); i++) {
			  if (this.getOperationList().get(i) == "Add") {
				  /*neem string uit ingredientList die op dat moment in use is, splits de string om, zet string getal om naar int*/
				  String ingredientString = this.getIngredientList().get(ingredientInUse); /*bv "3 drops Mercurial Acid" */
				  String[] splitIngredient = ingredientString.split(" ",3);
				  int amount = Integer.parseInt(splitIngredient[0]); /*bv 3*/
				  String unit = splitIngredient[1]; /*bv "drops" */
				  String ingredientName = splitIngredient[2]; /*bv "Mercurial Acid" */
				  
				  this.add(amount, unit, ingredientName);
			  }
			  else if(this.getOperationList().get(i) == "heat") {
				  this.heat(); /*container werd gestopt in recipeList, dus haal die eruit maar aan de hand van LastUsed*/
				  
			  }
			  else if(this.getOperationList().get(i) == "cool") {
				  this.cool(); /*container werd gestopt in recipeList, dus haal die eruit maar aan de hand van LastUsed*/
			  }
			  else {
				  
			  }
			}
	}
	
}
