package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Donor extends Person{

    private List<Item> _itemList = new ArrayList<Item>();
    private ImageIcon _donrLogo;
    
    public Donor(String firstName, String lastName, String Email, String Address, String phone){
        super(firstName, lastName, Email, Address, phone);
        // TODO Auto-generated constructor stub
    }

    public void add(Item item){
        _itemList.add(item);
    }
    
    public void delete(Item item){
        _itemList.remove(item);
    }
    
    public String toString() {
    	String toReturn = getFirstName() + " " + getLastName() + "," + getPhone() + "," + getEmail() + "," + getAddress();
    	
    	for (Item item : _itemList) {
    		toReturn += "," + item.getName() + ":" + item.getQr();
    	}
    	
    	return toReturn;
    }
    
}
