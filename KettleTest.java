import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;




/**
 * A JUnit test class for testing non-private methods of the Kettle Class.
 * Inherited methods are tested in the JUnit test for the superclass.
 *
 * @author Jérôme D'hulst, Marie Levrau, Art Willems
 *
 */
class KettleTest {
	
	private static final String expected = null;
	private static Kettle standardKettle;
	private static Laboratory standardKettleLabo, differentKettleLabo;
	private static IngredientType typeMilk, typeCola, typeCoffee;
	private static AlchemicIngredient milk, cola, coffee, ingredientFromKettle,resultIngredient;
	private static List<IngredientType> typeMilkList, typeColaList, typeCoffeeList, ingredientTypeList;
	private static List<AlchemicIngredient> ingredientList, resultIngredient;
	private static IngredientContainer milkContainer, colaContainer, coffeeContainer,resultContainer;
	private static List<Long> temperatureList;
	
	
	@BeforeClass
	public void setImmutableFixture() {
		standardKettleLabo = new Laboratory(30);
		differentKettleLabo = new Laboratory(30);
		typeMilk = new IngredientType("Milk","Liquid",0,30);
		typeMilkList = new ArrayList<IngredientType>();
		typeMilkList.add(typeMilk);
		typeCola = new IngredientType("Cola","Liquid",0,10);
		typeColaList = new ArrayList<IngredientType>();
		typeColaList.add(typeCola);
		typeCoffee = new IngredientType("Coffee","Liquid",0,50);
		typeCoffeeList = new ArrayList<IngredientType>();
		typeCoffeeList.add(typeCoffee);
		
		
	}
	
	@Before
	public void setMutableFixture() {
		standardKettle = new Kettle(standardKettleLabo);
		milk = new AlchemicIngredient(20,"spoon",typeMilkList, (long) 0,(long) 30,"Powder");
		cola = new AlchemicIngredient(30,"spoon",typeColaList,(long) 0, (long) 10,"Liquid");
		coffee = new AlchemicIngredient(30,"spoon",typeColaList,(long) 0, (long) 40,"Liquid");
		standardKettleLabo.addKettle(standardKettle);
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		differentKettleLabo.storeNewIngredient(milkContainer);
		
	}
	
	
	@Test
	public void TestConstructorLaboratory(){
		assertEquals(standardKettle.getType(),"Kettle");
	}
	
	@Test (expected DifferentLaboratoryException("The kettle and the ingredient have to be stored in the same laboratory",this).class);
	public void TestAdditionOfIngredientFromDifferentLab() {
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardKettle.addIngredientFrom(milkContainer);
	}
	
	@Test
	public void TestAdditionOfIngredientsFromSameLab() {
		milk = new AlchemicIngredient(20,"spoon",typeMilkList,(long) 0, (long) 30, "Liquid");
		standardKettleLabo.addIngredient(milk);
		standardKettleLabo.addIngredient(cola);
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		colaContainer = new IngredientContainer(cola,cola.getQuantity(),cola.getUnit(),cola.getState());
		standardKettleLabo.storeNewIngredient(milkContainer);
		standardKettleLabo.storeNewIngredient(colaContainer);
		standardKettle.addIngredientFrom(milkContainer);
		standardKettle.addIngredientFrom(colaContainer);
		ingredientList = new ArrayList<AlchemicIngrediet>();
		ingredientList.add(milk);
		ingredientList.add(cola);
		assertEquals(standardKettle.getIngredientList(),ingredientList);
	}
	
	
	
	@Test
	public void TestPotentialIngredients() {
		standardKettle = new Kettle(standardKettleLabo);
		coffeeContainer = new IngredientContainer(coffee,coffee.getQuantity(),coffee.getUnit(),coffee.getState());
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		colaContainer = new IngredientContainer(cola,cola.getQuantity(),cola.getUnit(),cola.getState());
		standardKettle.addIngredientFrom(coffeeContainer);
		standardKettle.addIngredientFrom(milkContainer);
		standardKettle.addIngredientFrom(colaContainer);
		ingredientList.clear();
		ingredientList.add(milk);
		ingredientList.add(cola);
		assertEquals(standardKettle.getPotentialIngredientList(),ingredientList);
	}
	
	
	@Test
	public void TestGetState() {
		standardKettle = new Kettle(standardKettleLabo);
		coffeeContainer = new IngredientContainer(coffee,coffee.getQuantity(),coffee.getUnit(),coffee.getState());
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		colaContainer = new IngredientContainer(cola,cola.getQuantity(),cola.getUnit(),cola.getState());
		standardKettle.addIngredientFrom(coffeeContainer);
		standardKettle.addIngredientFrom(milkContainer);
		standardKettle.addIngredientFrom(colaContainer);
		ingredientList = standardKettle.getPotentialIngredientList();
		assertEquals(standardKettle.getState(ingredientList),"Liquid");
		
	}
	
	
	@Test
	public void TestGetQuantity() {
		standardKettle = new Kettle(standardKettleLabo);
		coffeeContainer = new IngredientContainer(coffee,coffee.getQuantity(),coffee.getUnit(),coffee.getState());
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		colaContainer = new IngredientContainer(cola,cola.getQuantity(),cola.getUnit(),cola.getState());
		standardKettle.addIngredientFrom(coffeeContainer);
		standardKettle.addIngredientFrom(milkContainer);
		standardKettle.addIngredientFrom(colaContainer);
		assertEquals(standardKettle.getQuantity(),80);
	}
	
	
	@Test
	public void TestStandardTemperature() {
		standardKettle = new Kettle(standardKettleLabo);
		coffeeContainer = new IngredientContainer(coffee,coffee.getQuantity(),coffee.getUnit(),coffee.getState());
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		colaContainer = new IngredientContainer(cola,cola.getQuantity(),cola.getUnit(),cola.getState());
		standardKettle.addIngredientFrom(coffeeContainer);
		standardKettle.addIngredientFrom(milkContainer);
		standardKettle.addIngredientFrom(colaContainer);
		ingredientList = standardKettle.getPotentialIngredientList();
		temperatureList = new ArrayList<Long>();
		temperatureList.add((long)0);
		temperatureList.add((long)30);
		assertEquals(standardKettle.getStandardTemperature(ingredientList).get(0),temperatureList.get(0));
		assertEquals(standardKettle.getStandardTemperature(ingredientList).get(1),temperatureList.get(1));
	}
	
	
	
	@Test
	public void TestTotalTemperature() {
		standardKettle = new Kettle(standardKettleLabo);
		coffeeContainer = new IngredientContainer(coffee,coffee.getQuantity(),coffee.getUnit(),coffee.getState());
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		colaContainer = new IngredientContainer(cola,cola.getQuantity(),cola.getUnit(),cola.getState());
		standardKettle.addIngredientFrom(coffeeContainer);
		standardKettle.addIngredientFrom(milkContainer);
		standardKettle.addIngredientFrom(colaContainer);
		
		temperatureList = new ArrayList<Long>();
		temperatureList.add((long)0);
		temperatureList.add((long) 210/80);
		
		assertEquals(standardKettle.getTemperature().get(0),temperatureList.get(0)));
		assertEquals(standardKettle.getTemperature().get(1),temperatureList.get(1));
		
		
	}
	
	@Test
	public void TestAddIngredientToDevice() {
		standardKettle = new Kettle(standardKettleLabo);
		coffeeContainer = new IngredientContainer(coffee,coffee.getQuantity(),coffee.getUnit(),coffee.getState());
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		colaContainer = new IngredientContainer(cola,cola.getQuantity(),cola.getUnit(),cola.getState());
		standardKettle.addIngredientFrom(coffeeContainer);
		standardKettle.addIngredientFrom(milkContainer);
		standardKettle.addIngredientFrom(colaContainer);
		
		ingredientTypeList = new ArrayList<IngredientType>();
		ingredientTypeList.add(typeCola);
		ingredientTypeList.add(typeMilk);
		ingredientTypeList.add(typeCoffee);
		
		assertEquals(standardKettle.getIngredientTypeList(),ingredientTypeList);
		
	}
	
	@Test
	public void TestExecuteKettle() {
		standardKettle = new Kettle(standardKettleLabo);
		coffeeContainer = new IngredientContainer(coffee,coffee.getQuantity(),coffee.getUnit(),coffee.getState());
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		colaContainer = new IngredientContainer(cola,cola.getQuantity(),cola.getUnit(),cola.getState());
		standardKettle.addIngredientFrom(coffeeContainer);
		standardKettle.addIngredientFrom(milkContainer);
		standardKettle.addIngredientFrom(colaContainer);
		
		ingredientTypeList = new ArrayList<IngredientType>();
		ingredientTypeList.add(typeCola);
		ingredientTypeList.add(typeMilk);
		ingredientTypeList.add(typeCoffee);
		
		temperatureList = new ArrayList<Long>();
		temperatureList.add((long)0);
		temperatureList.add((long) 210/80);
		
		resultIngredient = new AlchemicIngredient(80,"spoon",ingredientTypeList,temperatureList.get(0),temperatureList.get(1),"Liquid");
		
		standardKettle.executeAlchemicOperation();
		resultContainer = standardKettle.removeAlchemicResult();
		ingredientFromKettle = resultContainer.getAlchemicIngredient();
		
		assertEquals(ingredientFromKettle.getCompleteName(),resultIngredient.getCompleteName());
		assertEquals(ingredientFromKettle.getQuantityInSpoons(),resultIngredient.getQuantityInSpoons());
		assertEquals(ingredientFromKettle.getState(),resultIngredient.getState());
		assertEquals(ingredientFromKettle.getTemperature().getColdness(),resultIngredient.getTemperature().getColdness());
		assertEquals(ingredientFromKettle.getTemperature().getHotness(),resultIngredient.getTemperature().getHotness());
		assertEquals(ingredientFromKettle.getStandardTemperature().getColdness(),resultIngredient.getStandardTemperature().getColdness());
		assertEquals(ingredientFromKettle.getStandardTemperature().getHotness(),resultIngredient.getStandardTemperature().getHotness());
		
		
		}
	
	@Test (expected NoIngredientInDeviceException("There is no ingredient in this device",this).class)
	public void TestExecuteAlchemicOperation_illegalCount() {
		standardKettle = new Kettle(standardKettleLabo);
		standardKettle.executeAlchemicOperation();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}