package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Item class
 * 
 * @author Long Nguyen
 * @version 5/21/2015
 */
public class Item implements Comparable<Item> {

	private String _itemName;
	private String _description;
	private int _minIncrement;
	private int _startingPrice;
	private Donor _donor;
	private long _qr;
	private ImageIcon _image;
	private List<Bidder> _bidderList = new ArrayList<Bidder>();
	
	/**
	 * Default constructor if image, or donor is optional.
	 * 
	 * @param name               - Name of item
	 * @param description        - Item's description
	 * @param minIncrement       - minimum increment bid
	 * @param startingPrice      - the starting price
	 * @param qr                 - the item's QR code
	 */
	public Item(String name, String description
			, int minIncrement, int startingPrice,
			long qr) {
		this(name, description, minIncrement, startingPrice, null, qr, null);
	}
	
	/**
	 * Main constructor
	 * 
	 * @param name              - Name of item
	 * @param description       - Item's description
	 * @param minIncrement      - minimum increment bid
	 * @param startingPrice     - the starting price
	 * @param donor             - the donor of the item (optional ???)
	 * @param qr                - the item's QR code
	 * @param image             - the image of the item (optional)
	 */
	public Item(String name, String description
			, int minIncrement, int startingPrice
			, Donor donor, long qr, ImageIcon image) {
		
		_itemName = name;
		_description = description;
		_minIncrement = minIncrement;
		_startingPrice = startingPrice;
		_donor = donor;
		_qr = qr;
		_image = image;
	}

	/**
	 * Adding a bidder to the item.
	 * 
	 * @param bidder - the bidder to be added to the list.
	 */
	public void addBidder(Bidder bidder) {
		if(bidder == null) {
			throw new IllegalArgumentException("not a bidder.");
		}
		_bidderList.add(bidder);
	}
	
	/**
	 * Returning a list of bidders.
	 * 
	 * @return list of bidders.
	 */
	public List<Bidder> getBidderList() {
		return _bidderList;
	}
	
	/**
	 * get the bidder who won.
	 * 
	 * @return a bidder
	 */
	public Bidder getWinningBidder() {
		if(!_bidderList.isEmpty()) {
			return _bidderList.get(_bidderList.size()-1);
		}
		return null;
	}
	
	/**
	 * Get the winning price
	 * 
	 * @return winning price.
	 */
	public int getWinningPrice() {
		int price = 0;
		if(!_bidderList.isEmpty() && _minIncrement >= 0 && _startingPrice > 0) {
			price = (_bidderList.size() * _minIncrement) + _startingPrice;
		}
		return price;
	}

	public String getName() {
		return _itemName;
	}

	public void setName(String name) {
		this._itemName = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}

	public int getMinIncrement() {
		return _minIncrement;
	}

	public void setMinIncrement(int minIncrement) {
		this._minIncrement = minIncrement;
	}

	public int getStartingPrice() {
		return _startingPrice;
	}

	public void setStartingPrice(int startingPrice) {
		this._startingPrice = startingPrice;
	}
	
	public Donor getDonor() {
		return _donor;
	}
	
	public void setDonor(Donor donor) {
		this._donor = donor;
	}
	
	public long getQr() {
		return _qr;
	}

	public void setQr(long qr) {
		this._qr = qr;
	}
	
	public ImageIcon getImage() {
		return _image;
	}

	public void setImage(ImageIcon image) {
		this._image = image;
	}
	
	public String toString() {
		String toReturn = _itemName + "," + _description + "," + _minIncrement
				+ "," + _startingPrice + "," + _donor.getFirstName() + "," + _donor.getLastName() + "," + _qr;
		
		for (Bidder bidder : _bidderList) {
			toReturn += "," + bidder.getFirstName() + " " + bidder.getLastName() + ":" + bidder.getid();
		}
		return toReturn;
	}

	/**
	 * Comparing item base on item's name
	 */
	public int compareTo(Item o) {
		return this._itemName.compareTo(o._itemName);
	}

}
