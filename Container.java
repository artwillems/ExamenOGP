import java.util.HashMap;
import java.util.Map;

public class Container {

	public Container(AlchemicIngredient ingredient, int capAmount, String capUnit, String capState){
		setIngredient(ingredient);
		setCapAmount(capAmount);
		setCapUnit(capUnit);
		setCapState(capState);
	}
	
	private AlchemicIngredient ingredient = null;
	
		
  
	@Basic @Raw
	public AlchemicIngredient getAlchemicIngredient() {
		return ingredient;
	}

	private int capAmount = 0;
	
	public int getCapAmount() {
		return this.capAmount;
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
	/*indien geen validstate throw error bij setAlchemicIngredient*/
	public boolean isValidState() {
		return (this.getAlchemicIngredient().getState() == this.getCapState());
	}
	
	/*legale containers voor liquid*/
	private static Map<String,Integer> legalCapacityLiquid = new HashMap<String,Integer>(){
		{
			put("spoon",1);
			put("vial",1);
			put("bottle",1);
			put("jug",1);
			put("barrel",1);
		}
	};
	
	/*legale containers voor powders*/
	private static Map<String,Integer> legalCapacityPowder = new HashMap<String,Integer>(){
		{
			put("spoon",1);
			put("sachet",1);
			put("box",1);
			put("sack",1);
			put("chest",1);
		}
	};
	
	/*controleren of het een geldig container is, dus bijvoorbeel voor liquid dat het enkel [1 spoon, 1 vial, 1 bottle, 1 jug, 1 barrel] kan zijn*/
	
	public boolean isValidCapacity() {
		if (this.getCapState() == "Liquid") {
			/*kijk of je capaciteit in de legale lijst van legalCapacityLiquid zit*/
			for (Map.Entry<String,Integer> entry: legalCapacityLiquid.entrySet()) {
				if (entry.getKey() == this.getCapUnit()) {
					if(entry.getValue() == this.getCapAmount()) {
						return true;
					}
				}
			}
		}
		else {
			for (Map.Entry<String,Integer> entry: legalCapacityPowder.entrySet()) {
				if (entry.getKey() == this.getCapUnit()) {
					if(entry.getValue() == this.getCapAmount()) {
						return true;
					}
				}
			}
		}
	}
	
	
	/*controleren of de hoeveelheid van alchemicIngredient volledig in hoeveelheid van capacity kan, eerst alles omzetten in spoons om zo te controleren*/
	
	public boolean isValidAmount() {
		return (this.getAlchemicIngredient().getQuantityInSpoons() <= this.getQuantityCapInSpoons()); 
	}
	
	/*zet de amount van je capacity van constructor om in spoons*/
	public int getQuantityCapInSpoons() {
		int result = this.getCapAmount();
		if (getCapState()=="Liquid") {
			for (Map.Entry<String,Integer> entry: liquidCapInSpoons.entrySet()) {
				if (entry.getKey() == this.getCapUnit()) {
					result = result * entry.getValue();
				}
			}
		}
		else {
			for(Map.Entry<String, Integer> entry: powderCapInSpoons.entrySet()) {
				if (entry.getKey() == this.getCapUnit()) {
					result = result * entry.getValue();
				}
			}
		}
		return result;
	}
	


	/*zet de mogelijke capaciteiten van liquid in spoons*/
	private static Map<String,Integer> liquidCapInSpoons = new HashMap<String,Integer>(){
		{
			put("vial",5);
			put("bottle",15);
			put("spoon",1);
			put("jug",105);
			put("barrel",1260);
		}
	};
	
	/*zet de mogelijke capaciteiten van powder in spoons*/
	private static Map<String,Integer> powderCapInSpoons = new HashMap<String,Integer>(){
		{
	
			put("sachet",7);
			put("box",42);
			put("sack",126);
			put("chest",1260);
			put("spoon",1);
		}
	};
	
	
	private void setCapState(String capState) {
			this.capState = capState;
		}
	

	private void setCapUnit(String capUnit) throws IllegalCapacityException {
		if (!isValidCapacity()){
			throw new IllegalCapacityException("The capacity is illegal");
		}
		else {
			this.capUnit = capUnit;
		}
	}
	
	private void setCapAmount(int capAmount) throws IllegalCapacityException {
		if (!isValidCapacity()){
			throw new IllegalCapacityException("The capacity is illegal");
		}
		else {
			this.capAmount = capAmount;
		}
	}
	
	private void setIngredient(AlchemicIngredient ingredient) throws IllegalStateException, IllegalAmountException{
		if (!isValidState()){
			throw new IllegalStateException("The state of the alchemic ingredient is not the same state as the capacity");
		}
		else if (!isValidAmount()){
			throw new IllegalAmountException("The amount of alchemic ingredient is too big for this container");
		}
		else {
			 this.ingredient = ingredient; 
		}
		 
		}
	
	
	
}