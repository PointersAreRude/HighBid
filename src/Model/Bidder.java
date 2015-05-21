package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Bidder class
 * 
 * @author Robert Nichols
 * @version 5/20/2015
 */

public class Bidder extends Person{

	
	private int _id; //added after constructed
	private String _nickName;
	private Set<Item>_itemsBidOn = new TreeSet<Item>();
	private Set<Item> _itemsWon = new TreeSet<Item>();
	
	/**
	 * Default Bidder constructor. id is added later as Registration takes place before id assignment.	
	 * 
	 * @param theName 			- the name of the Bidder
	 * @param theNickName		- the nickname of the Bidder
	 */
	public Bidder (String theFirstName, String theLastName, String theEmail, String theAddress
			, String theNickname){
		super(theFirstName, theLastName, theEmail, theAddress);
		_nickName = theNickname;
	}
	
	/**
	 * Assigns the id of the Bidder.
	 * 
	 * @param theID	The id to assign to the Bidder.
	 */
	public void setid(int theID){
		_id = theID;
	}
	
	/**
	 * Returns the Bidder's id.
	 * 
	 * @return
	 */
	public int getid(){
		return _id;
	}
	
	/**
	 * Assigns the Bidder a nickname.
	 * 
	 * @param theNick The nickname to assign the Bidder.
	 */
	public void setNickName(String theNick){
		_nickName = theNick;
	}
	
	/**
	 * Returns the nickname of the Bidder.
	 * 
	 * @return The nickname of the Bidder.
	 */
	public String getNickName(){
		//test by assigning multiple nicknames.
		return _nickName;
	}
	
	/**
	 * Places a bid on the specified Item.
	 * 
	 * @param theItem The Item bid on.
	 */
	public void placeBid(Item theItem){
		_itemsBidOn.add(theItem);
		theItem.addBidder(this);
		//Test in conjunction with Item class. Place bids on Items and test both the Item and _itemsBidOn.
		//SOMETHING
	}
	
	/**
	 * Adds an Item to the list of Items won.
	 * 
	 * @param theItem The Item won.
	 */
	public void addItemWon(Item theItem){
		//create safe copy?
		_itemsWon.add(theItem);
	}
	
	public String toString() {
		String toReturn = getFirstName() + " " + getLastName() + "," + _nickName + "," + _id + ","
				+ getPhone() + "," + getEmail() + "," + getAddress() + "\n";
		
		Iterator<Item> itr = _itemsBidOn.iterator();
		while (itr.hasNext()) {
			Item item = (Item) itr.next();
			toReturn += "," + item.getName() + ":" + item.getQr();
		}
		
		itr = _itemsWon.iterator();
		while (itr.hasNext()) {
			Item item = (Item) itr.next();
			toReturn += "," + item.getName() + ":" + item.getQr();
		}
		
		return toReturn;
	}
		
}
