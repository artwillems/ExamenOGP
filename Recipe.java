import java.util.List;

/**
 * A class of recipes involving a list of operations that needs to be executed on a list of quantities of ingredients, and a laboratory where the recipe is executed.
 * 
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/
public class Recipe {
	
	/**********************************************************
     * constructor
     **********************************************************/
	
	public Recipe(List<String> operationList, List<String> ingredientList, Laboratory labo) {
		setOperationList(operationList);
		setIngredientList(ingredientList);
		setLaboratory(labo);
	}
	
	/*we mogen geen unidirectionele relatie vergeten met recipebook!!!*/
	
	private List<String> operationList = null;
	
	public List<String> getOperationList(){
		return operationList;
	}
	
	private void setOperationList(List<String> operationList) {
		this.operationList = operationList;
	}
	
	private List<String> ingredientList = null;
	
	public List<String> getIngredientList(){
		return ingredientList;
	}
	
	private void setIngredientList(List<String> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	
	private List<IngredientContainer> recipeList = null;
	
	public List<IngredientContainer> getRecipeList(){
		return recipeList;
	}
	
	private void setRecipeList(List<IngredientContainer> recipeList) {
		this.recipeList = recipeList;
	}
	
	private Laboratory labo = null;
	
	public Laboratory getLaboratory() {
		return labo;
	}
	
	private void setLaboratory(Laboratory labo) {
		this.labo = labo;
	}
	
	private int lastUsed = 0;
	
	public int getLastUsed() {
		return lastUsed;
	}
	
	private void setLastUsed(int lastUsed) {
		this.lastUsed = lastUsed;
	}
	
	private int posIngredientList = 0;
	
	public int getPosIngredientList() {
		return posIngredientList;
	}
	
	private void setPosIngredientList(int posIngredientList) {
		this.posIngredientList = posIngredientList;
	}

	
	
	public void add(int amount, String unit, String ingredientName) {
		IngredientContainer container = this.getLaboratory().getAmountFromLabo(ingredientName, amount, unit);
		this.getRecipeList().add(container);
		lastUsed = this.getRecipeList().size() - 1;
	}
	
	public IngredientContainer heat(IngredientContainer container) {
		Oven oven = this.getLaboratory().getDevices() /*nog zorgen dat je hier oven krijgt*/
		oven.changeOvenTemperature(0,50);
		oven.addIngredientFrom(container);
		oven.executeAlchemicOperation();
		IngredientContainer result = oven.removeAlchemicResult();
		return result;

	}
	
	public IngredientContainer cool(IngredientContainer container) {
		CoolingBox coolingBox = this.getLaboratory().getDevices() /*nog zorgen dat je hier coolingbox krijgt*/
		coolingBox.changeCoolingBoxTemperature(50,0);
		coolingBox.addIngredientFrom(container);
		coolingBox.executeAlchemicOperation();
		IngredientContainer result = coolingBox.removeAlchemicResult();
		return result;
	}
	
	/*wss rekening houden met verschillende toestanden van ingredient, want als je ze mixt, moet dat wss zelfde toestand zijn??*/
	public IngredientContainer mix(List<IngredientContainer> listContainers) {
		Kettle kettle = this.getLaboratory().getDevices() /*nog zorgen dat je hier kettle krijgt*/
		for (int i = 0; i < recipeList.size(); i++) {
			kettle.addIngredientFrom(listContainers.get(i));
		}
		kettle.executeAlchemicOperation();
		IngredientContainer result = kettle.removeAlchemicResult();
		lastUsed = 0;
		return result;	
	}
	
	public static boolean isValidOperation(String operation) {
		return ((operation == "Add") || (operation == "heat") || (operation == "cool") || (operation == "mix"));
	}
	
	public IngredientContainer executeRecipe() throws IllegalOperationException{
		
		if (this.getOperationList().get(operationList.size() -1) != "mix" ){
			this.getOperationList().add("mix");
		}
		
		for (int i = 0; i < operationList.size(); i++) {
			  if (!isValidOperation(this.getOperationList().get(i))){
				  throw new IllegalOperationException("This is an illegal operation");
			  }
			  else if (this.getOperationList().get(i) == "Add") {
				  /*neem string uit ingredientList die op dat moment in use is, splits de string om, zet string getal om naar int*/
				  String ingredientString = this.getIngredientList().get(posIngredientList); /*bv "3 drops Mercurial Acid" */
				  String[] splitIngredient = ingredientString.split(" ",3);
				  int amount = Integer.parseInt(splitIngredient[0]); /*bv 3*/
				  String unit = splitIngredient[1]; /*bv "drops" */
				  String ingredientName = splitIngredient[2]; /*bv "Mercurial Acid" */
				
				  this.add(amount, unit, ingredientName);
				  posIngredientList = posIngredientList + 1;
			  }
			  else if(this.getOperationList().get(i) == "heat") {
				  IngredientContainer result = this.heat(this.getRecipeList().get(lastUsed)); /*container werd gestopt in recipeList, dus haal die eruit maar aan de hand van LastUsed*/
				  this.getRecipeList().remove(lastUsed);
				  this.getRecipeList().add(result);
			  }
			  else if(this.getOperationList().get(i) == "cool") {
				  IngredientContainer result = this.cool(this.getRecipeList().get(lastUsed)); /*container werd gestopt in recipeList, dus haal die eruit maar aan de hand van LastUsed*/
				  this.getRecipeList().remove(lastUsed);
				  this.getRecipeList().add(result);
			  }
			  
			  else {
				  IngredientContainer result = this.mix(this.getRecipeList());
				  this.getRecipeList().clear();
				  this.getRecipeList().add(result); 
			  }
			}
		return this.getRecipeList().get(0);
	}
	
}
