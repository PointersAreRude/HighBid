package Testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Model.Donor;
import Model.Item;

/**
 * JUnit test for donor class.
 * 
 * @author Long Nguyen
 * @version 5/21/2015
 */
public class DonorTest {

	private Donor donor;
	private List<Item> list;
	private Item item;
	private Item secondItem;
	
	@Before
	public void setUp() {
		 donor = new Donor("First Name", "Last Name", "Email", "2222 22th Ave S", "111-111-1111");
		 list = donor.getItemList();
		 item = new Item("Diamond", "This is a diamond from 1799", 5, 15, 128258257);
		 secondItem = new Item("Necklace", "This is a necklace from the future", 10, 15, 027327522);
	}
	
	@Test
	public void testAdd() {
		// testing add method in Donor class
		// if succesfully added, size of the list should be increased.
		donor.add(item);
		donor.add(secondItem);
		int size =  list.size();
		int sizeExpected = 2;
		assertEquals("Size is not the same", sizeExpected, size);		
	}
	
	@Test
	public void testDelete() {
		// testing delete method in Donor class
		// if sucessfully removed, size of the list should be decreased.
		donor.add(item);
		donor.add(secondItem);
		donor.delete(secondItem);
		int size =  list.size();
		int sizeExpected = 1;
		assertEquals("Size is not the same", sizeExpected, size);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBOunds() {
		// Should throw an exception if the user
		// query any of the item from an empty list.
		list.removeAll(donor.getItemList());
		@SuppressWarnings("unused")
		Object o = donor.getItemList().get(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullArgument() {
		// Should throw an exception when adding/deleting a null object
		list.add(null);
		list.remove(null);
	}
	
	@Test
	public void testToString() {
		// Testing toString() method from Donor class.
		donor.add(item);
		String item = list.get(0).getName() + ": " + list.get(0).getQr();
		String expected = "First Name Last Name, 111-111-1111, Email, 2222 22th Ave S, " + item;
		assertEquals("Two strings are not the same", expected, donor.toString());
	}

}
