package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Model.Auction;
import Model.Bidder;
import Model.Donor;
import Model.Item;

/**
 * @author Mark Ditianquin
 * @version 1.0
 */
public class AuctionTest {

	private Auction auction;
	private Bidder bidder;
	private Item item;
	private Donor donor;

	private final long id = 000;
	private final String itemName = "iPhone 100";
	private final String itemDesc = "This is an iPhone from the feuture";
	private final int startPrice = 1;
	private final int increment = 1;
	private final String personFirstName = "John";
	private final String personLastName = "Doe";
	private final String nickName = "Awesome";
	private final String personEmail = "jogndoe@highbid.org";
	private final String personAddress = "Everywhere there is McDonald";
	private final String personPhoneNumber = "222-2222-222";
	private final String date = "05/23/3000";
	private final String startTime = "25:00";
	private final String endTime = "25:70";
	private final String facilitatorName = "Rich";
	private final int biddersDeviceID = 123;
	
	/**
	 * setup everything first before creating runing test
	 */
	@Before
	public void setUp() {

		donor = new Donor(personFirstName, personLastName, personEmail, personAddress, personPhoneNumber);
		auction = new Auction(date, startTime, endTime, facilitatorName);
		bidder = new Bidder(personFirstName, personLastName, personEmail, personAddress, nickName, personPhoneNumber,
				biddersDeviceID);

		item = new Item(itemName, itemDesc, startPrice, increment, donor, id, null);
		
		auction.addItem(item);
		auction.addBidder(bidder);
		auction.addDonor(donor);

	}
	
	@Test
	public void testPlaceBid(){
		
		auction.placeBid(biddersDeviceID, id);	
		assertEquals(id, item.getQr());
		
	}
	@Test
	public void testGetItem() {
		
		List<Item> list = new ArrayList<Item>();
		
		list = auction.getItems();
		assertTrue("getItem Failed", list.contains(item));
	}
	
	@Test
	public void testGetBidders(){
		List<Bidder> list = new ArrayList<Bidder>();
		
		list = auction.getBidders();
		assertTrue("getBidder failed", list.contains(bidder));
	}

	@Test
	public void testGetDonors(){
		List<Donor> list = new ArrayList<Donor>();
		
		list = auction.getDonors();
		assertTrue("getBidder failed", list.contains(donor));
	}
	
	@Test
	public void testDeleteDonor(){
		List<Donor> list = new ArrayList<Donor>();
		
		list = auction.getDonors();
		auction.deleteDonor(donor);
		assertFalse("addDonor failed", list.contains(donor));
	}
	
	@Test
	public void testDeleteItem(){
		List<Item> list = new ArrayList<Item>();
		
		list = auction.getItems();
		auction.deleteItem(item);
		assertFalse("delete Item Falied", list.contains(item));
	}
	
	@Test
	public void testDeleteBidder(){
		List<Bidder> list = new ArrayList<Bidder>();
		
		list = auction.getBidders();
		auction.deleteBidder(bidder);
		assertFalse("getBidder failed", list.contains(bidder));
	}
}
