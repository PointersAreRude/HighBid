package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Donor extends Person{

    private List<Item> _itemList = new ArrayList<Item>();
    private ImageIcon _donrLogo;
    
    public Donor(String firstName, String lastName, String Email, String Address){
        super(firstName, lastName, Email, Address);
        // TODO Auto-generated constructor stub
    }

    public void add(Item item){
        _itemList.add(item);
    }
    
    public void delete(Item item){
        _itemList.remove(item);
    }
    
}
