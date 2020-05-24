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
 * A JUnit test class for testing the non-private methods of the Device Class.
 * The class is abstract, so we can use either one of the subclasses to test the interface 
 * the superclass.
 * 
 * @author Jérôme D'hulst, Marie Levrau, Art Willems
 * 
 */
public class DeviceTest {

	private static final String expected = null;
	private static Oven terminatedDevice, emptyDevice, constructorTestDevice, deviceWithMilk;
	private static Laboratory laboratoryOfTerminatedDevice, laboratoryOfMilkDevice, validLaboOfConstructorTestDevice;
	private static Laboratory laboratoryOfEmptyDevice, terminatedLaboratory;
	private static IngredientType typeMilk, saltType;
	private static AlchemicIngredient milk, salt, milkEmptyDevice, saltEmptyDevice;
	private static List<IngredientType> typeMilkList, typeSaltList;
	private static List<Long> temperatureSetting;
	private static List<AlchemicIngredient> emptyList, listWithMilk;
	private static IngredientContainer milkContainer, saltContainer, resultMilkDevice;
	
	@BeforeClass
	public static void setUpImmutableFixture() {
		
		temperatureSetting = new ArrayList<Long>();
		temperatureSetting.add((long) 0);
		temperatureSetting.add((long) 20);
		
		terminatedDevice = new Oven(laboratoryOfTerminatedDevice,temperatureSetting);
		terminatedDevice.terminate();
		
		typeMilk = new IngredientType("Milk","Liquid",0,20);
		saltType = new IngredientType("Salt","Powder",0,20);
		typeMilkList = new ArrayList<IngredientType>();
		typeMilkList.add(typeMilk);
		typeSaltList = new ArrayList<IngredientType>();
		typeSaltList.add(saltType);
		
		
		
	}
	
	
	
	
	@Before
	public void setUpFixture() {
		laboratoryOfTerminatedDevice = new Laboratory(10);
		emptyDevice = new Oven(laboratoryOfEmptyDevice,temperatureSetting);
		deviceWithMilk = new Oven(laboratoryOfMilkDevice,temperatureSetting);
		
		
		
		milk = new AlchemicIngredient(20,"spoon",typeMilkList,(long) 0, (long) 30, "Liquid");
		salt = new AlchemicIngredient(30,"spoon",typeSaltList,(long) 0, (long) 40, "Powder");
		
		milkEmptyDevice = new AlchemicIngredient(20,"spoon",typeMilkList,(long) 0, (long) 30, "Liquid");
		saltEmptyDevice = new AlchemicIngredient(30,"spoon",typeSaltList,(long) 0, (long) 30, "Liquid");
		
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		saltContainer = new IngredientContainer(salt,salt.getQuantity(),salt.getUnit(),salt.getState());
		
		laboratoryOfEmptyDevice = new Laboratory(30);
		laboratoryOfMilkDevice = new Laboratory(20);
		laboratoryOfMilkDevice.storeNewIngredient(milkContainer);
		laboratoryOfEmptyDevice.storeNewIngredient(saltContainer);
		
	}
	
	
	
	@Test
	public void testConstructorValidLaboratory() {
		validLaboOfConstructorTestDevice = new Laboratory(40);
		constructorTestDevice = new Oven(validLaboOfConstructorTestDevice,temperatureSetting);
		assertEquals(validLaboOfConstructorTestDevice,constructorTestDevice.getLaboratory());
		assertEquals("Oven",constructorTestDevice.getType());
		assertEquals((int) 0, constructorTestDevice.getIngredientList().size());
		
		
	}
	
	
	
	
	@Test
	public void testCanBeTerminated_Illegal_alreadyTerminated() {
		assertTrue(terminatedDevice.isTerminated());
	}
	
	@Test (expected IllegalStateException("This device cannot be terminated"))
	public void testTerminateTerminatedDevice() {
		terminatedDevice.terminate();
		
	}
	
	public void testTerminateDevice() {
		assertTrue(terminatedDevice.isTerminated());
	}
	
	@Test
	public void testCanHaveAsLaboratoryIllegalLaboratory() {
		assertFalse(emptyDevice.canHaveAsLaboratory(null));
	}
	
	@Test
	public void testCanHaveAsLaboratoryTerminatedDevice() {
		assertFalse(terminatedDevice.canHaveAsLaboratory(laboratoryOfEmptyDevice));
	}
	
	
	

	
	
	
	@Test
	public void testInValidInputNullValue() {
		testList = new ArrayList<AlchemicIngredient>();
		testList.add(null);
		assertFalse(emptyDevice.isValidInput(testList));
	}
	
	@Test
	public void testInvalidInputMultipleIngredients() {
		testList = new ArrayList<AlchemicIngredient>();
		testList.add(milk);
		testList.add(salt);
		assertFalse(emptyDevice.isValidInput(testList));
	}
	
	
	
	@Test 
	public void testValidInput() {
		listWithMilk = new ArrayList<AlchemicIngredient>();
		listWithMilk.add(milk);
		assertTrue(emptyDevice.isValidInput(listWithMilk));
	}
	
	


	
	@Test (expected DifferentLaboratoryException("The device and the ingredient have to be stored in the same laboratory",this).class);
	public void testAdditionIngredientDifferentLab() {
		deviceWithMilk.addIngredientFrom(saltContainer);
	}
	
	@Test
	public void testDifferentLaboratory() {
		assertFalse(deviceWithMilk.haveSameLaboratory(salt));
	}
	
	
	@Test
	public void testSameLaboratory() {
		assertTrue(deviceWithMilk.haveSameLaboratory(milk));
	}
	
	
	
	
	@Test
	public void testValidContainerAddition() {
		deviceWithMilk.addIngredientFrom(milkContainer);
		assertEquals(deviceWithMilk.getIngredientList().get(0),milk);
		assertEquals(deviceWithMilk.countIngredients(),(int) 1);
		}
	
	@Test
	public void testGetIngredientList() {
		assertEquals(deviceWithMilk.getIngredientList(),listWithMilk);
	}
	
	
	@Test (expected IllegalIngredientAdditionException.class)
	public void testInvalidContainerAddition() {
		deviceWithMilk.addIngredientFrom(saltContainer);
		}
	
	
	@Test
	public void testValidRemoveAlchemicResult() {
		resultMilkDevice = deviceWithMilk.removeAlchemicResult();
		assertEquals(resultMilkDevice.getAlchemicIngredient(),milk);
		assertTrue(deviceWithMilk.getIngredientList().isEmpty());
	}
	
	@Test (expected IllegalResultException("There can only be returned one total result from the device"))
	public void testRemoveAlchemicResultFromEmptyDevice() {
		emptyDevice = new Oven(laboratoryOfEmptyDevice,temperatureSetting);
		milkContainer = emptyDevice.removeAlchemicResult();
	}
	
	@Test (expected IllegalStateException("This device has been terminated"))
	public void testRemoveAlchemicResultFromTerminatedDevice() {
		milkContainer = terminatedDevice.removeAlchemicResult();
	}
	
	
	
}