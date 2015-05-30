package View;


import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Model.Bidder;

/**
 * Bidder editing form. Inherited from RegisterScreen.
 * 
 * @author Robbie Nichols
 * @version 5/29/2015
 */
public class RegisterEditScreen extends RegisterScreen {

	/** The bidder to edit.*/
	private Bidder _bidder;
	
	
	public RegisterEditScreen(){
		super();
		_bidder = null;
	}
	
	/**
	 * Sets the Bidder to edit.
	 * 
	 * @param theBidder the Bidder to edit.
	 */
	public void setBidder(Bidder theBidder){
		_bidder = theBidder;		
		setForm();
	}
	
	
	/**
	 * Detects and sets the Bidder's fields to new values
	 * so long as they are not empty. Nickname excluded.
	 */
	private void detectChanges(){
		if(_bidder != null && fieldsNotEmpty()){
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
		JButton src = (JButton) e.getSource();
		if (src == _createBtn) {
			detectChanges();
			if(!fieldsNotEmpty())
				JOptionPane.showMessageDialog(this, "Please enter the required fields.");
			else {
				flushFields();
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderChooser");
			}
		} else { //exiting screen
			//this dialog is verbatim from Abbie's code
			int choice = JOptionPane.showConfirmDialog(null, "Your information in this form "
					+ "will not be saved.  Continue back?", "Warning", JOptionPane.OK_CANCEL_OPTION);
			if (choice == JOptionPane.OK_OPTION) {
				flushFields();
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderChooser");
			}
		}
	}
}
