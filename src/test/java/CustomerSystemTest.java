import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;


public class CustomerSystemTest {

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
	} 
	
	@After
    public void restoreStreams() {
        System.setOut(systemOut);
    }
	/**
	 * Basic test to check avaliable items
	 */

	@Test
	public void itemAvailableTest1() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
		customerSystem.itemsAvailable();
		String test1 = ("Here are our items available: \n" +
				"\n" +
				"Drinks - \n" +
				"A1 - Water - Quantity: 10 - Price: $2.5\n" +
				"A2 - Soft Drink - Quantity: 10 - Price: $3.0\n" +
				"A3 - Juice - Quantity: 5 - Price: $3.0\n" +
				"\n" +
				"Chocolates - \n" +
				"B1 - M&M - Quantity: 10 - Price: $4.0\n" +
				"B2 - Bounty - Quantity: 10 - Price: $4.0\n" +
				"B3 - Mars - Quantity: 10 - Price: $4.0\n" +
				"B4 - Snickers - Quantity: 10 - Price: $4.0\n" +
				"\n" +
				"Chips - \n" +
				"C1 - Original - Quantity: 10 - Price: $5.0\n" +
				"C2 - Chicken - Quantity: 10 - Price: $5.0\n" +
				"C3 - BBQ - Quantity: 10 - Price: $5.0\n" +
				"C4 - Sweet Chilly - Quantity: 10 - Price: $5.0\n" +
				"\n" +
				"Lollies - \n" +
				"D1 - Sour Worms - Quantity: 10 - Price: $4.5\n" +
				"D2 - Jellybeans - Quantity: 10 - Price: $4.5\n" +
				"D3 - Little Bears - Quantity: 10 - Price: $4.5\n" +
				"D4 - Party Mix - Quantity: 10 - Price: $4.5\n\n"
		);

		assertEquals(test1,testOut.toString() );

	}

	@Test
	public void itemAvailableTest2() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());

		ArrayList<Item> items = i.getItems();
		customerSystem.itemsAvailable();
		String test2 = ("Here are our items available: \n"+"\n"+
				"Drinks - \n"
				+items.get(0).toString()
				+items.get(1).toString()
				+items.get(2).toString()+"\n"+

				"Chocolates - \n"
				+items.get(3).toString()
				+items.get(4).toString()
				+items.get(5).toString()
				+items.get(6).toString()+"\n"+

				"Chips - \n"
				+items.get(7).toString()
				+items.get(8).toString()
				+items.get(9).toString()
				+items.get(10).toString()+"\n"+

				"Lollies - \n"
				+items.get(11).toString()
				+items.get(12).toString()
				+items.get(13).toString()
				+items.get(14).toString()+"\n"
		);

		assertEquals(test2,testOut.toString());

	}

	@Test
	public void enterItemTest1() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
		String test1 = "Invalid input please try again.\n";

		customerSystem.enterItemChecker("Ice-cream");
		assertEquals(test1, testOut.toString());
	}

	@Test
	public void enterItemTest2() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
		String test1 = "Transaction cancelled, have a good day!\n";

		customerSystem.enterItemChecker("cancel");
		assertEquals(test1, testOut.toString());
	}

	@Test
	public void enterQuantityTest1() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
		String test1 = "Quantity must be at least 1 and less than availability. Please try again.\n";

		customerSystem.enterQuantityChecker(0,"Water");
		assertEquals(test1, testOut.toString());
	}

	@Test
	public void enterQuantityTest2() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
		String test1 = "Quantity must be at least 1 and less than availability. Please try again.\n";

		customerSystem.enterQuantityChecker(-10,"Water");
		assertEquals(test1, testOut.toString());
	}

	@Test
	public void enterQuantityTest3() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
		String test1 = "Quantity requested exceeds stock quantity. Please try again.\n";

		customerSystem.enterQuantityChecker(20,"Water");
		assertEquals(test1, testOut.toString());
	}

	@Test
	public void confirmationTest1() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
		String test1 = "Invalid input please try again.\n";

		customerSystem.confirmation(2,"Water",5);
		assertEquals(test1, testOut.toString());
	}

	@Test
	public void confirmationTest2() {
		Inventory i = new Inventory();
		Driver testCustomerSystem = new Driver();
		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
		String test1 = "Transaction cancelled. Have a good day!\n";

		customerSystem.confirmation(2,"Water",3);
		assertEquals(test1, testOut.toString());
	}
//	@Test
//	public void systemExitWithSelectedStatusCode0() {
//		//for system.exit(0) tests
//		exit.expectSystemExitWithStatus(0);
//		Inventory i = new Inventory();
//		CustomerSystem customerSystem = new CustomerSystem(i.getItems());
//		//customerSystem.testExit();
//	}

}