package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * @author Mark Ditianquin
 * @version 5/23/15
 * 
 * This is a class to represent the Donor
 *
 */
public class Donor extends Person {

    private List<Item> _itemList = new ArrayList<Item>();
    private ImageIcon _donrLogo;
    
    public Donor(String firstName, String lastName, String Email, String Address, String phone){
        super(firstName, lastName, Email, Address, phone);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param item to add
     */
    public void add(Item item) {
    	_itemList.add(item);
    }
    
    /**
     * 
     * @param item to delete
     */
    public void delete(Item item){
        _itemList.remove(item);
    }
    
    /*
     * I need a way to get the list of item from this class
     * , so that I could do test on these methods, so I added
     * this method.
     * 
     * Long Nguyen - 5/21/2015
     */
    public List<Item> getItemList() {
    	return _itemList;
    }
    
    public String toString() {
    	String toReturn = getFirstName() + " " + getLastName() + ", " + getPhone() + ", " + getEmail() + ", " + getAddress();
    	
    	for (Item item : _itemList) {
    		toReturn += ", " + item.getName() + ": " + item.getQr();
    	}
    	
    	return toReturn;
    }
    
}
