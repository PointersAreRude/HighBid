package Testing;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.Donor;
import Model.Item;

/**
 * JUnit test for donor class.
 * 
 * @author Long Nguyen
 * @version 5/21/2015
 */
public class DonorTest {

	@Rule public ExpectedException exceptionRule = ExpectedException.none();
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
		// if successfully removed, size of the list should be decreased.
		donor.add(item);
		donor.add(secondItem);
		donor.delete(secondItem);
		int size =  list.size();
		int sizeExpected = 1;
		assertEquals("Size is not the same", sizeExpected, size);
	}
	
	@Test
	public void testIndexOutOfBOunds() {
		// Should throw an exception if the user
		// query any of the item from an empty list.
		List<Item> temp = new ArrayList<Item>(donor.getItemList());
		Exception outOfBounds = null;
		try {
			temp.get(1);
		} catch (IndexOutOfBoundsException e) {
			outOfBounds = e;
		}
		assertNotNull("No expected exception", outOfBounds);
	}
	
	@Test
	public void testAddNullArgument() {
		// Should throw an exception when adding a null object
		Exception illegalArg = null;
		Item i = null;
		try {
			donor.add(i);
		} catch (IllegalArgumentException e){
			illegalArg = e;
		}
		assertNotNull("No expected exception", illegalArg);
	}
	
	@Test
	public void testDeleteNullArgument() {
		Exception illegalArgument = null;
		Item i = null;
		try {
			donor.delete(i);
		} catch (IllegalArgumentException e){
			illegalArgument = e;
		}
		assertNotNull("No expected exception", illegalArgument);
	}
	
	@Test
	public void testGetList() {
		// Test if two list are equals.
		List<Item> expected = new ArrayList<Item>();
		expected.add(item);
		expected.add(secondItem);
		
		donor.add(item);
		donor.add(secondItem);
		
		assertThat(donor.getItemList(), is(expected));
		//assertThat(donor.getItemList(), is(not(expected)));		
	}
	
	@Test
	public void testToString() {
		// Testing toString() method from Donor class.
		donor.add(item);
		String item = list.get(0).getName() + ":" + list.get(0).getQr();
		String expected = "First Name Last Name, 111-111-1111, Email, 2222 22th Ave S, " + item;
		assertEquals("Two strings are not the same", expected, donor.toString());
	}
	
	@Test
	public void testEquals() {
		Donor donor = new Donor("First", "Last", "Email", "Address", "Phone");
		assertTrue("Should return true", donor.equals(donor));
	}

}
