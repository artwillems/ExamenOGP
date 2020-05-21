import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * A JUnit test class for testing the non-private methods of the Oven Class.
 * Inherited methods are tested in the JUnit test for the superclass
 * @author Jérôme D'hulst, Marie Levrau, Art Willems
 *
 */
public class OvenTest{
	
	private static Laboratory laboOfMilkOven, laboOfLegalConstructorTestOven, laboOfIllegalConstructorTestOven;
	private static IngredientType typeMilk;
	private static List<IngredientType> typeMilkList;
	private static AlchemicIngredient milk, hotMilk, coldMilk, heatedMilk;
	private static Oven ovenAtStandardTemp, legalConstructorTestOven, illegalConstructorTestOven;
	private static List<Long> temperatureSetting, invalidTemperatureSetting;
	private static IngredientContainer standardTempIngrContainer, coldMilkContainer, hotMilkContainer, heatedMilkContainer;
	
	
	@BeforeClass
	public void setUpImmutableFixture() {
		laboOfMilkOven = new Laboratory(30);
		laboOfLegalConstructorTestOven= new Laboratory(40);
		laboOfIllegalConstructorTestOven = new Laboratory(50);
		typeMilk = new IngredientType("Milk","Liquid",0,20);
		typeMilkList = new ArrayList<IngredientType>();
		typeMilkList.add(typeMilk);
		temperatureSetting = new ArrayList<Long>();
		temperatureSetting.add((long)0);
		temperatureSetting.add((long)20);
		invalidTemperatureSetting = new ArrayList<Long>();
		invalidTemperatureSetting.add((long)30);
		invalidTemperatureSetting.add((long)30);
		
	}
	
	@Before
	public void setUpMuttableFixture() {
		milk = new AlchemicIngredient(20,"spoon",typeMilkList,(long) 0, (long) 30, "Liquid");
		coldMilk = new AlchemicIngredient(20,"spoon",typeMilkList,(long) 30, (long) 0,"Liquid");
		ovenAtStandardTemp = new Oven(laboOfMilkOven,temperatureSetting);
		legalConstructorTestOven = new Oven(laboOfLegalConstructorTestOven,temperatureSetting);
		standardTempIngrContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		coldMilkContainer = new IngredientContainer(coldMilk,coldMilk.getQuantity(),coldMilk.getUnit(),coldMilk.getState());
		
		
	}
	
	public void testConstructorLaboratoryTemperatureSetting_Legal() {
		assertEquals(legalConstructorTestOven.getType(),"Oven");
		assertEquals((long) legalConstructorTestOven.getOvenTemperature().getColdness(),(long) 0);
		assertEquals((long) legalConstructorTestOven.getOvenTemperature().getHotness(),(long) 20);
	}
	
	@Test 
	public void testConstructorLaboratoryTemperatureSetting_IllegalTemperature() {
		illegalConstructorTestOven = new Oven(laboOfIllegalConstructorTestOven,invalidTemperatureSetting);
		assertFalse(illegalConstructorTestOven.getOvenTemperature().getColdness(),(long) 30);
		assertFalse(illegalConstructorTestOven.getOvenTemperature().getHotness(),(long) 30);
		assertTrue(illegalConstructorTestOven.getOvenTemperature().getColdness(),(long) 0);
		assertTrue(illegalConstructorTestOven.getOvenTemperature().getHotness(),(long) 20);
	}
	
	@Test 
	public void testChangeOvenTemperature_InvalidTemperature() {
		legalConstructorTestOven.changeOvenTemperature(30, 30);
		assertFalse(legalConstructorTestOven.getOvenTemperature().getColdness(),(long) 30);
		assertFalse(legalConstructorTestOven.getOvenTemperature().getHotness(),(long) 30);
		assertTrue(legalConstructorTestOven.getOvenTemperature().getColdness(),(long) 0);
		assertTrue(legalConstructorTestOven.getOvenTemperature().getHotness(),(long) 20);
	}
	
	
	
	@Test
	public void testChangeOvenTemperature_ValidTemperature() {
		legalConstructorTestOven.changeOvenTemperature(20, 0);
		assertTrue(legalConstructorTestOven.getOvenTemperature().getColdness(),(long)20);
		assertTrue(legalConstructorTestOven.getOvenTemperature().getHotness(),(long) 0);
		
	}
	
	@Test (expected NoIngredientInDeviceException("There is no ingredient in this device",this).class);
	public void TestEmptyDeviceExcecution() {
		ovenAtStandardTemp.executeAlchemicOperation();
	}
	
	@Test
	public void TestHeatColdIngredient() {
		ovenAtStandardTemp.addIngredientFrom(coldMilkContainer);
		ovenAtStandardTemp.executeAlchemicOperation();
		heatedMilkContainer = ovenAtStandardTemp.removeAlchemicResult();
		heatedMilk = heatedMilkContainer.getAlchemicIngredient();
		heatedMilkContainer.delete();
		assertEquals(heatedMilk.getTemperature().getColdness(),ovenAtStandardTemp.getOvenTemperature().getColdness());
		assertTrue(heatedMilk.getTemperature().getHotness()<=ovenAtStandardTemp.getOvenTemperature().getHotness()*1.05);
		assertTrue(heatedMilk.getTemperature().getHotness()>=ovenAtStandardTemp.getOvenTemperature().getHotness()*0.95);
	}
	
	@Test
	public void TestHeatHotIngredient() {
		ovenAtStandardTemp.addIngredientFrom(standardTempIngrContainer);
		ovenAtStandardTemp.executeAlchemicOperation();
		hotMilkContainer = ovenAtStandardTemp.removeAlchemicResult();
		hotMilk = hotMilkContainer.getAlchemicIngredient();
		hotMilkContainer.delete();
		assertEquals(hotMilk.getTemperature().getColdness(),(long) 0);
		assertEquals(hotMilk.getTemperature().getHotness(),(long) 30);
		
	}
}