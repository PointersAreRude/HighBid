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
 * @version 5/21/2015
 */

public class Bidder extends Person implements Comparable<Bidder>{
	
	/** The Bidder's unique ID number. Initialized to -1	 */
	private int _id;
	
	/** The Bidder's nickname. Possible use for name tags. */
	private String _nickName;
	
	/**	A set of the Items this Bidder has bid on. */
	private Set<Item>_itemsBidOn = new TreeSet<Item>();
	
	/**	A set of the Items the Bidder has won. */
	private Set<Item> _itemsWon = new TreeSet<Item>();
	
	/**
	 * Default Bidder constructor. id is added later as Registration takes place before id assignment.	
	 * 
	 * @param theName 			- the name of the Bidder
	 * @param theNickName		- the nickname of the Bidder
	 */
	public Bidder (String theFirstName, String theLastName, String theEmail, String theAddress
			, String theNickname, String thePhone, int theID){
		super(theFirstName, theLastName, theEmail, theAddress, thePhone);
		_nickName = theNickname;
		_id = theID;
	}
	
	//constructor w/o ID
	public Bidder (String theFirstName, String theLastName, String theEmail, String theAddress
			, String theNickname, String thePhone){
		super(theFirstName, theLastName, theEmail, theAddress, thePhone);
		_nickName = theNickname;
		_id = -1;
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
		_itemsWon.add(theItem);
	}
	
	/**
	 * Creates and returns a shallow copy of _itemsWon.
	 * 
	 * @return A shallow copy of _itemsWon.
	 */
	public Set<Item> getItemsWon(){
		Set<Item> set = new TreeSet<Item>();
		for (Item it : _itemsWon)
			set.add(it);
		return set;
	}
	
	/**
	 * Creates and returns a shallow copy of _itemsBidOn.
	 * 
	 * @return A shallow copy of _itemsBidOn.
	 */
	public Set<Item> getItemsBidOn(){
		Set<Item> set = new TreeSet<Item>();
		for (Item it : _itemsBidOn)
			set.add(it);
		return set;
	}
	
	public String toString() {
		String toReturn = getFirstName() + " " + getLastName() + "," + _nickName + "," + _id + ","
				+ getPhone() + "," + getEmail() + "," + getAddress() + "\n";
		
		Iterator<Item> itr = _itemsBidOn.iterator();
		toReturn += "-Items bid on:";
		while (itr.hasNext()) {
			Item item = (Item) itr.next();
			toReturn += "," + item.getName() + ":" + item.getQr();
		}
		
		itr = _itemsWon.iterator();
		toReturn += "\n-Items won:";
		while (itr.hasNext()) {
			Item item = (Item) itr.next();
			toReturn += "," + item.getName() + ":" + item.getQr();
		}
		
		return toReturn;
	}
		

	/**
	 * Compares two bidders.
	 * 
	 * @param theBidder The bidder to compare this bidder with.
	 * @return 0 if the bidders are equal to each other. 
	 */
	public int compareTo(Bidder theBidder) {
		if (_id == -1 || theBidder.getid() == -1){
			
			return this.getFirstName().compareTo(theBidder.getFirstName()) + 
					this.getLastName().compareTo(theBidder.getLastName());
		} else {
			if(_id == theBidder.getid())
				return 0;
			else
				return -1;
		}
	}
	
	//should this and compareTo be modified to test the object ids/mem addresses instead of field data?
	/**
	 * Returns true if the two bidders being compared are the same.
	 * 
	 * @param theBidder the bidder to compare equality against.
	 * @return True if the two bidders are equal, false otherwise.
	 */
	public boolean equals(Bidder theBidder){
		if( this.compareTo(theBidder) == 0)
			return true;
		else 
			return false;
	}
}
