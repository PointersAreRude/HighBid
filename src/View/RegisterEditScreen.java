package View;


import Model.Bidder;

public class RegisterEditScreen extends RegisterScreen {

	private Bidder _bidder;
	
	
	
	
	public RegisterEditScreen(){
		super();
		_bidder = null;
	}
	
	public void setBidder(Bidder theBidder){
		_bidder = theBidder;		
		
	}
	
	private void detectChanges(){
		
	}
	
	
	private void setForm(Bidder theBidder){
		_fNameF.setText(theBidder.getFirstName());
		_lNameF.setText(theBidder.getLastName());
		_emailF.setText(theBidder.getEmail());
		_addressF.setText(theBidder.getAddress());
		_nNameF.setText(theBidder.getNickName());
		_phoneF.setText(theBidder.getPhone());
	}
	
}
