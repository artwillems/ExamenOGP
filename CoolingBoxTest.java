import static org.junit.jupiter.api.Assertions.*;
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
	private static IngredientType typeMilk, saltType;
	private static List<IngredientType> typeMilkList, typeSaltList;
	private static AlchemicIngredient milk, salt;
	private static CoolingBox boxAtStandardTemp, legalConstructorTestBox, illegalConstructorTestBox;
	private static List<Long> temperatureSetting, invalidTemperatureSetting;
	
	
	@BeforeClass
	public void setUpImmutableFixture() {
		laboOfMilkCoolingBox = new Laboratory(30);
		laboOfLegalConstructorTestBox = new Laboratory(40);
		laboOfIllegalConstructorTestBox = new Laboratory(50);
		typeMilk = new IngredientType("Milk","Liquid",0,20);
		saltType = new IngredientType("Salt","Powder",0,20);
		typeMilkList = new ArrayList<IngredientType>();
		typeMilkList.add(typeMilk);
		typeSaltList = new ArrayList<IngredientType>();
		typeSaltList.add(saltType);
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
		salt = new AlchemicIngredient(30,"spoon",typeSaltList,(long) 0, (long) 10, "Powder");
		boxAtStandardTemp = new CoolingBox(laboOfMilkCoolingBox,temperatureSetting);
		
	}
	
	
	
	@Test
	public void testConstructorLaboratoryTemperatureSetting_Legal() {
		legalConstructorTestBox = new CoolingBox(laboOfLegalConstructorTestBox,temperatureSetting);
		assertEquals(legalConstructorTestBox.getType(),"CoolingBox");
		assertEquals((long) legalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long) 0);
		assertEquals((long) legalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 20);
		assertEquals(legalConstructorTestBox.getLaboratory(),laboOfLegalConstructorTestBox);
	}	
	
	@Test 
	public void testConstructorLaboratoryTemperatureSetting_IllegalTemperature() {
		illegalConstructorTestBox = new CoolingBox(laboOfIllegalConstructorTestBox,invalidTemperatureSetting);
		assertFalse(illegalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long) 30);
		assertFalse(illegalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 30);
		assertTrue(illegalConstructorTestBox.getCoolingBoxTemperature().getColdness(),(long) 0);
		assertTrue(illegalConstructorTestBox.getCoolingBoxTemperature().getHotness(),(long) 20);
	}
	
	
	
	
	
}
