package Testing;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import Model.Bidder;
import Model.Item;

public class BidderTest {

	private Bidder myBidder;
	
	private Item item1;
	
	private Item item2;
	
	public static final double TOLERANCE = 0.00001;
	
	@Before
	public void setUp() throws Exception {
		myBidder = new Bidder("Sally", "Seashore", "seashellSeller@oceans.com", "879 Beaches Ave, Portland, OR", 
				"byTheShore", "123-456-Shells");
		
		item1 = new Item("Headphones", "A sweet set of mind blowing headphones with bass!", 5, 20, 1111111111);
		item2 = new Item("Pillow", "A cozy pillow to rest your head on.", 2, 10, 222222222);
	}

	@Test
	public void testBidder() {
		assertEquals("Sally", myBidder.getFirstName());
		assertEquals("Seashore", myBidder.getLastName());
		assertEquals("seashellSeller@oceans.com", myBidder.getEmail());
		assertEquals("879 Beaches Ave, Portland, OR", myBidder.getAddress());
		assertEquals("byTheShore", myBidder.getNickName());
		assertEquals("123-456-Shells", myBidder.getPhone());
	}

	@Test
	public void testSetid() {
		assertEquals(-1, myBidder.getid());
		myBidder.setid(9898989);
		assertEquals(9898989, myBidder.getid());
	}

	@Test
	public void testSetNickName() {
		myBidder.setNickName("sellsSeashells");
		assertEquals("sellsSeashells", myBidder.getNickName());
	}


	@Test
	public void testGetItemsBidOn() {
		Set<Item> set = myBidder.getItemsBidOn();
		assertNotNull(set);
	}

	@Test
	public void testPlaceBid() {
		
		myBidder.placeBid(item1);
		Set<Item> bids = myBidder.getItemsBidOn();
		assertTrue(bids.contains(item1));
		
		List<Bidder> bidders = item1.getBidderList();
		assertTrue(bidders.contains(myBidder));
		
		myBidder.placeBid(item2);
		bids = myBidder.getItemsBidOn();
		assertTrue(bids.contains(item2));
		assertTrue(bids.contains(item1));
		
		Bidder aBidder = new Bidder("Peter", "Piper", "pepperPicker@pickles.com", "7777 Pickle st, Pepper Fields, TX", 
				"pickledPeppers", "573-990-Peck");
		aBidder.placeBid(item1);
		bidders = item1.getBidderList();
		assertEquals(1, bidders.indexOf(aBidder));
		
		myBidder.placeBid(item1);
		bidders = item1.getBidderList();
		assertEquals(myBidder, bidders.get(bidders.size() - 1));
	}

	@Test
	public void testGetItemsWon() {
		Set<Item> itemsWon = myBidder.getItemsWon();
		assertNotNull(itemsWon);
	}
	
	@Test
	public void testAddItemWon() {
		Set<Item> items = myBidder.getItemsWon();
		assertFalse(items.contains(item1));
		assertFalse(items.contains(item2));
		
		myBidder.addItemWon(item1);
		items = myBidder.getItemsWon();
		assertTrue(items.contains(item1));
		
		myBidder.addItemWon(item2);
		items = myBidder.getItemsWon();
		assertTrue(items.contains(item2));
		assertTrue(items.contains(item1));
	}

	@Test
	public void testEqualsBidder() {
		Bidder aBidder = new Bidder("Sally", "Seashore", "seashellSeller@oceans.com", "879 Beaches Ave, Portland, OR", 
				"byTheShore", "123-456-Shells");
		assertTrue(myBidder.equals(aBidder));
		
		Bidder aBidder2 = new Bidder("Sally2", "Seashore", "seashellSeller@oceans.com", "879 Beaches Ave, Portland, OR", 
				"byTheShore", "123-456-Shells");
		assertFalse(myBidder.equals(aBidder2));
		
		Bidder aBidder3 = new Bidder("Sally", "Seashore2", "seashellSeller@oceans.com", "879 Beaches Ave, Portland, OR", 
				"byTheShore", "123-456-Shells");
		assertFalse(myBidder.equals(aBidder3));
		
		myBidder.setid(9898989);
		Bidder aBidder4 = new Bidder("Sally", "Seashore", "seashellSeller@oceans.com", "879 Beaches Ave, Portland, OR", 
				"byTheShore", "123-456-Shells");
		aBidder.setid(9898989);
		assertTrue(myBidder.equals(aBidder4));
		
		aBidder4.setid(0111010001);
		assertFalse(myBidder.equals(aBidder4));
	}

	@Test
	public void testSetFirstName() {
		myBidder.setFirstName("Peter");
		assertEquals("Peter", myBidder.getFirstName());
	}

	@Test
	public void testSetLastName() {
		myBidder.setLastName("Piper");
		assertEquals("Piper", myBidder.getLastName());
	}

	@Test
	public void testSetEmail() {
		myBidder.setEmail("pepperPicker@pickles.com");
		assertEquals("pepperPicker@pickles.com", myBidder.getEmail());
	}

	@Test
	public void testSetAddress() {
		myBidder.setAddress("7777 Pickle st, Pepper Fields, TX");
		assertEquals("7777 Pickle st, Pepper Fields, TX", myBidder.getAddress());
	}

	@Test
	public void testSetPhone() {
		myBidder.setPhone("573-990-Peck");
		assertEquals("573-990-Peck", myBidder.getPhone());
	}

	/*
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}
*/
}
