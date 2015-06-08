package Testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Model.*;//bad?

/**
 * Item JUnit test class
 * 
 * @author Robert Nichols
 * @version 5/22/15
 */
public class ItemTest {
private Item testItem;
private Bidder testBidder;
private Donor testDonor;

private final long id = 321;
private final String name = "Stringcheese";
private final String desc = "a pseudo-mozzarella string of cheese";
private final int price = 3;
private final int incr = 1;

	/**
	 * Setup before each test -- creates new Bidders, Donors, and Items to be used in testing.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testBidder = new Bidder("Foo", "Bar", "baz@bumble.net", "123 S 1st St, Burrito, WA, 99999", "cheesy1", "123-456-7890");
		testDonor = new Donor("Rod", "Stirling", "host@twilightzone.org", 
				"6174 Bradbury Way, The Twilight Zone, NO, 0", "000-000-0000");
		
		testItem = new Item(name, desc, 1, 3, testDonor, id, null);
	}

	/**
	 * Test method for {@link Model.Item#addBidder(Model.Bidder)}. Tests adding a regular Bidder.
	 * No null or error checking.
	 */
	@Test
	public void testAddBidder() {
		testItem.addBidder(testBidder);
		List<Bidder> bidders = testItem.getBidderList();
		
		assertTrue("Bidder list must contrain the added bidder", bidders.contains(testBidder));
		
	}

	/**
	 * Test method for {@link Model.Item#getBidderList()}.
	 */
	@Test
	public void testGetBidderListEmpty() {
		List<Bidder> bidders = testItem.getBidderList();
		assertEquals("Empty list -- should be size 0", 0,bidders.size());
		
	}
	
	/**
	 * Test method for {@link Model.Item#getBidderList()}. Tests for 100 added bidders.
	 */
	@Test
	public void testGetBidderListMany() {
		
		for(int i = 0; i < 100; i++){
			Bidder temp = new Bidder(new Integer(i).toString(), "Bar", "baz@bumble.net", 
					"123 S 1st St, Burrito, WA, 99999", "cheesy1", "123-456-7890");
			testItem.addBidder(temp);
		}
		List<Bidder>bidders = testItem.getBidderList();
		assertEquals("100 added bidders -- should be size 100", 100,bidders.size());
	}

	/**
	 * Test method for {@link Model.Item#getWinningBidder()}.
	 */
	@Test
	public void testGetWinningBidder() {
		Bidder temp;
		for (int i = 0; i < 100; i++) {
			temp = new Bidder(new Integer(i).toString(), "Bar",
					"baz@bumble.net", "123 S 1st St, Burrito, WA, 99999",
					"cheesy1", "123-456-7890");
			testItem.addBidder(temp);
		}
		testItem.addBidder(testBidder);
		assertSame("Winning bidder is the last added bidder", testBidder, testItem.getWinningBidder());
	}

	/**
	 * Test method for {@link Model.Item#getWinningPrice()}.
	 * What happens if there are no bids? The source code looks like it will just return 0.
	 * Is this a purposeful sentinel value? If so , it should be noted in the comments.
	 */
	@Test
	public void testGetWinningPrice() {
		assertEquals("No bids -- no winning price", 0, testItem.getWinningPrice());
		for (int i = 0; i < 100; i++) {
			Bidder temp = new Bidder(new Integer(i).toString(), "Bar",
					"baz@bumble.net", "123 S 1st St, Burrito, WA, 99999",
					"cheesy1", "123-456-7890");
			testItem.addBidder(temp);
		}
		assertEquals("100 bids of incr 1 + starting price 3 = 103", 100+3, testItem.getWinningPrice());
	}

	/**
	 * Test method for {@link Model.Item#getName()} & {@link Model.Item#setName(java.lang.String)}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Name constant used in the constructor = name of the item", name, testItem.getName());
		String newName = "Velveeta";
		testItem.setName(newName);
		assertEquals("Item name equals new name after calling setName()", newName, testItem.getName());
	}

	/**
	 * Test method for {@link Model.Item#getDescription()} & {@link Model.Item#setDescription(java.lang.String)}.
	 */
	@Test
	public void testGetDescription() {
		assertEquals("Description should equal the string constant used in the constructor.",
				desc, testItem.getDescription());
		
		String newDesc = "A cheese-like product made from petroleum byproducts";
		testItem.setDescription(newDesc);		
		
		assertEquals("Description should equal the new string used in method setDescription().",
				newDesc, testItem.getDescription());
	}


	/**
	 * Test method for {@link Model.Item#getMinIncrement()} & {@link Model.Item#setMinIncrement(int)}.
	 */
	@Test
	public void testGetMinIncrement() {
		assertEquals("MinIncr should equal the int constant used in the constructor.", incr, testItem.getMinIncrement());
		
		int newIncr = 420;
		testItem.setMinIncrement(newIncr);
		assertEquals("MinIncr should equal the int used in setMinIncrement()", newIncr, testItem.getMinIncrement());	
	}


	/**
	 * Test method for {@link Model.Item#getStartingPrice()}.
	 */
	@Test
	public void testGetStartingPrice() {
		assertEquals("Starting price should equal the int constant used in the constructor.", price, testItem.getStartingPrice());
	}

	/**
	 * Test method for {@link Model.Item#getDonor()} & {@link Model.Item#setDonor(Model.Donor)}.
	 */
	@Test
	public void testGetDonor() {
		assertEquals("Donor should equal the Donor constant used in the constructor.", testDonor, testItem.getDonor());
		Donor temp = new Donor("Frodo", "Baggins", "secondsies@greyhavens.eu", 
				"Bag End Bagshot Rowm Hobbiton, The Shire", "000-000-0000");
		testItem.setDonor(temp);
		assertEquals("Donor should equal the Donor used in setDonor()", temp, testItem.getDonor());
	}


	/**
	 * Test method for {@link Model.Item#getQr()} & {@link Model.Item#setQr(long)}.
	 */
	@Test
	public void testGetQr() {
		assertEquals("QR should equal the long constant used in the constructor.", id, testItem.getQr());
		
		long newQR = 12345678;
		testItem.setQr(newQR);
		assertEquals("QR should equal new QR set in setQR method.", newQR, testItem.getQr());	
		}


	/**
	 * Test method for {@link Model.Item#compareTo(Model.Item)}.
	 * 
	 * This only tests for names. What if two items have the same name? Something should take care of that -- ensuring duplicate names cannot exist.
	 */
	@Test
	public void testCompareTo() {
		assertEquals("Item should equal itself.", 0, testItem.compareTo(testItem));
		
		Item temp = new Item("Velveeta", desc, 1, 3, testDonor, id, null);
		
		assertFalse("Item should not be equal to a different object", testItem.compareTo(temp)==0);
		}
}
