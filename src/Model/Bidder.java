package Model;

import java.util.List;

public class Bidder {

	
	private int _id; //added after constructed
	private String _nickName;
//	private Map<Item, Integer>_itemsBidOn;
	private List<Item> _itemsWon;
	
		
	public Bidder (String theName /*,contact info*/, String theNickName){
//		super(/*name, contact info*/);
//		_name = theName;
		_nickName = theName;
	}
	public void setid(int theID){
		_id = theID;
	}
	
	public int getid(){
		return _id;
	}
	
	public void setNickName(String theNick){
		_nickName = theNick;
	}
	
	public String getNickName(){
		return _nickName;
	}
	
	public void placeBid(Item theItem){
		//SOMETHING
	}
	
	public void buyOut(Item theItem){
		//SOMETHING
	}
	
}
