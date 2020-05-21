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
 * A JUnit test class for testing non-private methods of the CoolingBox Class.
 * Inherited methods are tested in the JUnit test for the superclass.
 *
 * @author Jérôme D'hulst, Marie Levrau, Art Willems
 *
 */
class CoolingBoxTest {
	
	private static Laboratory laboOfMilkCoolingBox, laboOfLegalConstructorTestBox, laboOfIllegalConstructorTestBox;
	private static IngredientType typeMilk;
	private static List<IngredientType> typeMilkList, typeSaltList;
	private static AlchemicIngredient milk;
	private static CoolingBox boxAtStandardTemp, legalConstructorTestBox, illegalConstructorTestBox;
	private static List<Long> temperatureSetting, invalidTemperatureSetting;
	
	
	@BeforeClass
	public void setUpImmutableFixture() {
		laboOfMilkCoolingBox = new Laboratory(30);
		laboOfLegalConstructorTestBox = new Laboratory(40);
		laboOfIllegalConstructorTestBox = new Laboratory(50);
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
		boxAtStandardTemp = new CoolingBox(laboOfMilkCoolingBox,temperatureSetting);
		legalConstructorTestBox = new CoolingBox(laboOfLegalConstructorTestBox,temperatureSetting);
		
	}
	
	
	
	@Test
	public void testConstructorLaboratoryTemperatureSetting_Legal() {
		assertEquals(legalConstructorTestBox.getType(),"CoolingBox");
		assertEquals((long) legalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long) 0);
		assertEquals((long) legalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 20);
	
	}	
	
	@Test 
	public void testConstructorLaboratoryTemperatureSetting_IllegalTemperature() {
		illegalConstructorTestBox = new CoolingBox(laboOfIllegalConstructorTestBox,invalidTemperatureSetting);
		assertFalse(illegalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long) 30);
		assertFalse(illegalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 30);
		assertTrue(illegalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long) 0);
		assertTrue(illegalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 20);
	}
	
	@Test 
	public void testChangeCoolingBoxTemperature_InvalidTemperature() {
		legalConstructorTestBox.changeCoolingBoxTemperature(30, 30);
		assertFalse(legalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long) 30);
		assertFalse(legalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 30);
		assertTrue(legalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long) 0);
		assertTrue(legalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 20);
	}
	
	@Test
	public void testChangeCoolingBoxTemperature_ValidTemperature() {
		legalConstructorTestBox.changeCoolingBoxTemperature(20, 0);
		assertTrue(legalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long)20);
		assertTrue(legalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 0);
		
	}
	@Test (expected NoIngredientInDeviceException("There is no ingredient in this device",this).class)
	public void TestEmptyDeviceExcecution() {
		boxAtStandardTemp.executeAlchemicOperation();
	}
	
	public void TestCoolHotIngredient{
		boxAtStandard
	}
}
