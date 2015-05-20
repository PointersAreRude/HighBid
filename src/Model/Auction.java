package Model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Auction class; 
 * Precondition: myDate must be of "mm/dd/yyyy" format, and myStartTime and myEndTime must be of "hh:mm" format.
 * 
 * @author Abigail Smith
 * @version 5/20/15
 * 
 */
public class Auction {

	/**
	 * A list of the Auction's Donors.
	 */
	private ArrayList<Donor> myDonors;
	
	/**
	 * A list of the Auction's Items.
	 */
	private ArrayList<Item> myItems;
	
	/**
	 * A list of the Auction's Bidders.
	 */
	private ArrayList<Bidder> myBidders;
	
	/**
	 * The date of the Auction.
	 */
	private String myDate;
	
	/**
	 * The time the Auction opens.
	 */
	private String myStartTime;
	
	/**
	 * The time the Auction closes.
	 */
	private String myEndTime;
	
	/**
	 * Name of the facilitator of the Auction.
	 */
	private String myFacilitator;
	
	/**
	 * The file path to save all the Auction stats to.
	 */
	private String myFileName;
	
	/**
	 * Auction constructor
	 * 
	 * @param aDate The date of the Auction; must be of "mm/dd/yyyy" format.
	 * @param aStartTime The Auction's start time; must be of "hh:mm" format.
	 * @param anEndTime The Auction's closing time; must be of "hh:mm" format.
	 * @param aFacilitator The Auction's facilitator.
	 */
	public Auction(String aDate, String aStartTime, String anEndTime, String aFacilitator) {
		myDate = aDate;
		myStartTime = aStartTime;
		myEndTime = anEndTime;
		myFacilitator = aFacilitator;
		
		myDonors = new ArrayList<Donor>();
		myItems = new ArrayList<Item>();
		myBidders = new ArrayList<Bidder>();
		
		String fileName = "tempOutput/" + myFacilitator + ":" + myDate + ".csv";
	}
	
	/**
	 * Returns a list of Donors in this Auction.
	 * 
	 * @return myDonors
	 */
	public ArrayList<Donor> getDonors() {
		return myDonors;
	}
	
	/**
	 * Returns a list of Items in this Auction.
	 * 
	 * @return myItems
	 */
	public ArrayList<Item> getItems() {
		return myItems;
	}
	
	/**
	 * Returns a list of Bidders in this Auction.
	 * 
	 * @return myBidders
	 */
	public ArrayList<Bidder> getBidders() {
		return myBidders;
	}
	
	/**
	 * Adds a Donor to the myDonors list.
	 * 
	 * @param aDonor a Donor to be added.
	 */
	public void addDonor(Donor aDonor) {
		myDonors.add(aDonor);
	}
	
	/**
	 * Adds an Item to the myItems list.
	 * 
	 * @param an Item an Item to be added.
	 */
	public void addItem(Item anItem) {
		myItems.add(anItem);
	}
	
	/**
	 * Adds a Bidder to the myBidder's list.
	 * 
	 * @param aBidder a Bidder to be added.
	 */
	public void addBidder(Bidder aBidder) {
		myBidders.add(aBidder);
	}
	
	/**
	 * Deletes a Donor from the myDonors list.
	 * 
	 * @param aDonor a Donor to be deleted.
	 */
	public void deleteDonor(Donor aDonor) {
		myDonors.remove(aDonor);
	}
	
	/**
	 * Deletes an Item from the myItems list.
	 * 
	 * @param anItem an Item to be deleted.
	 */
	public void deleteItem(Item anItem) {
		myItems.remove(anItem);
	}
	
	/**
	 * Deletes a Bidder from the myBidders list.
	 * 
	 * @param aBidder a Bidder to be deleted.
	 */
	public void deleteBidder(Bidder aBidder) {
		myBidders.remove(aBidder);
	}
	
	/**
	 * Handles the event of a bid being placed.
	 * 
	 * @param aBidderID The ID of the bidder's device.
	 * @param anItemQR The QR code of the item being bid on.
	 * @throws IllegalArgumentException if the bidder's ID passe does not belong to any Bidder, or if the item QR code
	 * passed does not belong to any Item.
	 */
	public void placeBid(int aBidderID, long anItemQR) {
		Bidder bidder = null;
		Item itemBidOn = null;
		for (Bidder aBidder : myBidders) {
			if (aBidder.getid() == aBidderID) {
				bidder = aBidder;
				break;
			}
		}
		
		for (Item anItem : myItems) {
			if (anItem.getQr() == anItemQR) {
				itemBidOn = anItem;
				break;
			}
		}
		
		if (bidder != null && itemBidOn != null) {
			bidder.placeBid(itemBidOn);
		} else {
			throw new IllegalArgumentException("One of the parameters is invalid.");
		}
		
	}
	
	/**
	 * Creates a new file for this Auction, placing all it's info into a csv file titled with  
	 * the facilitator's name and the date of the Auction; 
	 * currently the file is saved in a folder under this project called "tempOutput".
	 */
	public void exportFile() {
		PrintWriter writeFile = new PrintWriter(new FileWriter(myFileName, true));
		writeFile.println("#,Auction Info");
		writeFile.println("+," + myFacilitator + "," + myDate + "," + myStartTime + "," + myEndTime);
		
		//write out all items.
		writeFile.println("#,Items");
		for (Item item : myItems) {
			writeFile.print("+," + item.getName() + "," + item.getDescription() + "," + item.getMinIncrement()
					+ "," + item.getStartingPrice() + "," + item.getDonor().getName() + "," + item.getQr()
					/*+ "," + item.getImage()*/);
			
			//this needs to change to Map<Bidder, Integer>
			Map<Item, Integer> itemMap = item.getBids();
			Iterator<Entry<Item, Integer>> itr = itemMap.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry<Item, Integer> entry = (Entry<Item, Integer>) itr.next();
				//change "getQr()" to "getid()" when it changes to Bidder instead of Item
				writeFile.print("," + entry.getKey()  + ":" + entry.getKey().getQr() + "," + entry.getValue());
			}
			writeFile.println();
		}
		
		//write out Donors
		writeFile.println("#,Donors");
		for (Donor donor : myDonors) {
			writeFile.print("+," + donor.getName() + "," + donor.getPhone() + "," + donor.getEmail()
					+ "," + donor.getAddress() /*+ ",* + donor.getImage()*/);
			
			ArrayList<Item> itemsDonated = donor.getItems();
			for (Item item : itemsDonated) {
				writeFile.print("," + item.getName() + ":" + item.getQr());
			}
			writeFile.println();
		}
		
		//write out Bidders
		writeFile.println("#,Bidders");
		for (Bidder bidder : myBidders) {
			writeFile.println("+," + bidder.getName() + "," + bidder.getNickName() + "," + bidder.getid()
					+ "," + bidder.getPhone() + "," + bidder.getEmail() + "," + bidder.getAddress());
			
			writeFile.print("-,");
			ArrayList<Item> itemsWon = bidder.getItemsWon();
			for (Item item : itemsWon) {
				writeFile.print("," + item.getName() + ":" + item.getQr());
			}
			writeFile.println();
			
			writeFile.print("-,");
			Map<Item, Integer> bidderMap = bidder.getBids();
			Iterator<Entry<Item, Integer>> itr = bidderMap.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry<Item, Integer> entry = (Entry<Item, Integer>) itr.next();
				writeFile.print("," + entry.getKey() + ":" + entry.getKey().getQr() + "," + entry.getValue());
			}
			writeFile.println();
		}
		
		writeFile.close();
	}
	
	/**
	 * This would read in an Auction file and populate the software with the data in the file.
	 */
	public void importFile() {
		
	}
	
}
