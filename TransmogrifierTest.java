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
 * A JUnit test class for testing non-private methods of the Transmogrifier Class.
 * Inherited methods are tested in the JUnit test for the superclass.
 *
 * @author Jérôme D'hulst, Marie Levrau, Art Willems
 *
 */
class TransmogrifierTest {
	
	
	private static Laboratory standardTransLab;
	private static Transmogrifier standardTrans;
	private static AlchemicIngredient milk, resultIngredient;
	private static IngredientType typeMilk;
	private static List<IngredientType> typeMilkList;
	private static IngredientContainer milkContainer, resultContainer;
	
	@BeforeClass
	public void setUpImmutableFixture() {
		standardTransLab = new Laboratory(40);
		typeMilk = new IngredientType("Milk","Liquid",0,20);
		typeMilkList = new ArrayList<IngredientType>();
		typeMilkList.add(typeMilk);
		
	}
	
	
	@Before
	public void setUpMutableFixture() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"spoon",typeMilkList,(long) 0, (long) 30, "Liquid");
	}
	
	@Test
	public void TestConstructorLaboratory() {
		assertEquals(standardTrans.getType(),"Transmogrifier");
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationDropLiquid() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"drop",typeMilkList,(long) 0, (long) 30, "Liquid");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Powder");
		assertEquals(resultIngredient.getQuantity(),20*6/8);
		assertEquals(resultIngredient.getUnit(),"pinch");

		
	}
	
	@Test
	public void TestExecuteAlchemicOperationPinchPowder() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"pinch",typeMilkList,(long) 0, (long) 30, "Powder");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Liquid");
		assertEquals(resultIngredient.getQuantity(),20*8/6);
		assertEquals(resultIngredient.getUnit(),"drop");

		
	}
	
	@Test
	public void TestExecuteAlchemicOperationVialLiquid() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"vial",typeMilkList,(long) 0, (long) 30, "Liquid");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Powder");
		assertEquals(resultIngredient.getQuantity(),20*5/7);
		assertEquals(resultIngredient.getUnit(),"sachet");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationSachetPowder() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"sachet",typeMilkList,(long) 0, (long) 30, "Powder");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Liquid");
		assertEquals(resultIngredient.getQuantity(),20*7/5);
		assertEquals(resultIngredient.getUnit(),"storeroom");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationSpoonLiquid() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"spoon",typeMilkList,(long) 0, (long) 30, "Liquid");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Powder");
		assertEquals(resultIngredient.getQuantity(),20);
		assertEquals(resultIngredient.getUnit(),"spoon");
		
		}
	
	
	@Test
	public void TestExecuteAlchemicOperationSpoonPowder() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"spoon",typeMilkList,(long) 0, (long) 30, "Powder");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Liquid");
		assertEquals(resultIngredient.getQuantity(),20);
		assertEquals(resultIngredient.getUnit(),"spoon");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationBottleLiquid() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"bottle",typeMilkList,(long) 0, (long) 30, "Liquid");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Powder");
		assertEquals(resultIngredient.getQuantity(),20*15/42);
		assertEquals(resultIngredient.getUnit(),"box");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationBoxPowder() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"box",typeMilkList,(long) 0, (long) 30, "Powder");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Liquid");
		assertEquals(resultIngredient.getQuantity(),20*42/15);
		assertEquals(resultIngredient.getUnit(),"bottle");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationJugLiquid() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"jug",typeMilkList,(long) 0, (long) 30, "Liquid");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Powder");
		assertEquals(resultIngredient.getQuantity(),20*105/126);
		assertEquals(resultIngredient.getUnit(),"sack");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationSackPowder() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"sack",typeMilkList,(long) 0, (long) 30, "Powder");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Liquid");
		assertEquals(resultIngredient.getQuantity(),20*126/105);
		assertEquals(resultIngredient.getUnit(),"jug");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationBarrelLiquid() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"barrel",typeMilkList,(long) 0, (long) 30, "Liquid");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Powder");
		assertEquals(resultIngredient.getQuantity(),20*1);
		assertEquals(resultIngredient.getUnit(),"chest");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationChestPowder() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"chest",typeMilkList,(long) 0, (long) 30, "Powder");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Liquid");
		assertEquals(resultIngredient.getQuantity(),20*1);
		assertEquals(resultIngredient.getUnit(),"barrel");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationStoreroomLiquid() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"storeroom",typeMilkList,(long) 0, (long) 30, "Liquid");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Powder");
		assertEquals(resultIngredient.getQuantity(),20*1);
		assertEquals(resultIngredient.getUnit(),"storeroom");

		
	}
	
	
	@Test
	public void TestExecuteAlchemicOperationStoreroomPowder() {
		standardTrans = new Transmogrifier(standardTransLab);
		milk = new AlchemicIngredient(20,"storeroom",typeMilkList,(long) 0, (long) 30, "Powder");
		milkContainer = new IngredientContainer(milk,milk.getQuantity(),milk.getUnit(),milk.getState());
		standardTransLab.storeNewIngredient(milkContainer);
		standardTrans.addIngredientFrom(milkContainer);
		standardTrans.executeAlchemicOperation();
		resultContainer = standardTrans.removeAlchemicResult();
		resultIngredient = resultContainer.getAlchemicIngredient();
		
		assertEquals(resultIngredient.getState(),"Liquid");
		assertEquals(resultIngredient.getQuantity(),20*1);
		assertEquals(resultIngredient.getUnit(),"storeroom");

		
	}
	
	
}
