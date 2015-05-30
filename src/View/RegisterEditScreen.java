package View;


import java.awt.event.ActionEvent;

import javax.swing.JButton;

import Model.Bidder;

public class RegisterEditScreen extends RegisterScreen {

	private Bidder _bidder;
	
	
	public RegisterEditScreen(){
		super();
		_bidder = null;
	}
	
	public void setBidder(Bidder theBidder){
		_bidder = theBidder;		
		setForm();
	}
	
	private void detectChanges(){
		if(_bidder != null){
			if(!_fNameF.getText().equals(_bidder.getLastName()))
				_bidder.setFirstName(_fNameF.getText());
					
			if(!_lNameF.getText().equals(_bidder.getLastName()))
				_bidder.setLastName(_lNameF.getText());
			
			if(!_emailF.getText().equals(_bidder.getEmail()))
				_bidder.setEmail(_emailF.getText());
				
			if(!_addressF.getText().equals(_bidder.getAddress()))
				_bidder.setAddress(_addressF.getText());
				
			if(!_nNameF.getText().equals(_bidder.getNickName()))
				_bidder.setNickName(_nNameF.getText());
				
			if (!_phoneF.getText().equals(_bidder.getPhone()))
				_bidder.setPhone(_phoneF.getText());
		}
	}
	
	
	private void setForm(){
		if(_bidder != null){
		_fNameF.setText(_bidder.getFirstName());
		_lNameF.setText(_bidder.getLastName());
		_emailF.setText(_bidder.getEmail());
		_addressF.setText(_bidder.getAddress());
		_nNameF.setText(_bidder.getNickName());
		_phoneF.setText(_bidder.getPhone());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		//from Long's code
	
	
		//currently does NOT have confirmation screens
		JButton src = (JButton) e.getSource();
		if (src == _createBtn) {
			detectChanges();
		}
			//alawys flushes text and returns to prev screen
			_fNameF.setText("");
			_lNameF.setText("");
			_emailF.setText("");
			_addressF.setText("");
			_nNameF.setText("");
			_phoneF.setText("");
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderChooser");

	}
	
}
