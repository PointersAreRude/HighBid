package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Bidder;

/**
 * RegisterScreen
 * 
 * This class takes user input from text boxes
 * and creates a Bidder object, which is then added
 * to the global _auction field in MainFrame.
 * 
 * @author Robbie Nichols
 * @version 5/26/2015
 */

public class RegisterScreen extends JPanel implements ActionListener  {
	 private static int FWIDTH = 20;
	/**
	 * label for register panel's form
	 */
	private JLabel _label;
	
	/**
	 * back button
	 */
	private JButton _backBtn;
	
	/**
	 * create button
	 */
	private JButton _createBtn;
	
	/**
	 * Form panel (using GridBagLayout)
	 */
	private JPanel _gbag;
	
	/**	First name text box*/
	private JTextField _fNameF;
	
	/**	Last name text box*/
	private JTextField _lNameF;
	
	/**	Email text box*/
	private JTextField _emailF;
	
	/**	Address text box*/
	private JTextField _addressF;
	
	/**	Nick name text box*/
	private JTextField _nNameF;
	
	/**	Phone number text box*/	
	private JTextField _phoneF;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterScreen() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(null);
		setComponents();
		addComponents();
	}
	
	private void addComponents(){
		add(_label);
		add(_backBtn);
		add(_gbag);
		add(_createBtn);

	}
	
	private void setComponents(){
		_label = new JLabel("Bidder Registration Form");
		_label.setFont(new Font("Tahoma", 0, 50));
		_label.setBounds((int)(MainFrame.WIDTH / 2.7), 20,300,100);
		_label.setForeground(Color.BLUE);
		
		// Set back button
		_backBtn = new JButton("Back");
		_backBtn.setBounds(20, MainFrame.HEIGHT - 100, 175, 60);
		_backBtn.setFont(MainFrame.BUTTON_FONT);
		_backBtn.addActionListener(this);
		
		// Set create button
		_createBtn = new JButton("Finish");
		_createBtn.setBounds(MainFrame.WIDTH - 200, MainFrame.HEIGHT - 100, 175, 60);
		_createBtn.setFont(MainFrame.BUTTON_FONT);
		_createBtn.addActionListener(this);
		
		setupForm();
	}
	
	private void setupForm(){
		_gbag = new JPanel(new GridBagLayout());
		_gbag.setBorder(BorderFactory.createTitledBorder("Registration Fields"));
		_gbag.setBounds(300, 120, 580, 500);
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		
		JLabel fNameL = new JLabel("First name: ");
		fNameL.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(fNameL, gc);
		
		gc.gridy++;
		JLabel lNameL = new JLabel("Last name: ");
		lNameL.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(lNameL, gc);
		
		gc.gridy++;
		JLabel emailL = new JLabel("email: ");
		emailL.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(emailL, gc);
		
		gc.gridy++;
		JLabel addressL = new JLabel("Address: ");
		addressL.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(addressL, gc);
		
		gc.gridy++;
		JLabel nNameL = new JLabel("Nick name: ");
		nNameL.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(nNameL, gc);
		
		gc.gridy++;
		JLabel phoneL = new JLabel("Phone number: ");
		phoneL.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(phoneL, gc);
		
		gc.weighty = 1;
				
		gc.gridy = 0;
		gc.gridx = 1;
		_fNameF = new JTextField(FWIDTH);
		_fNameF.setPreferredSize(MainFrame.TF_DIMENSION);
		_fNameF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_fNameF, gc);
		
		gc.gridy++;
		_lNameF = new JTextField(FWIDTH);
		_lNameF.setPreferredSize(MainFrame.TF_DIMENSION);
		_lNameF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_lNameF, gc);
		
		gc.gridy++;
		_emailF = new JTextField(FWIDTH);
		_emailF.setPreferredSize(MainFrame.TF_DIMENSION);
		_emailF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_emailF, gc);
		
		gc.gridy++;
		_addressF = new JTextField(FWIDTH);
		_addressF.setPreferredSize(MainFrame.TF_DIMENSION);
		_addressF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_addressF, gc);
		
		gc.gridy++;
		_nNameF = new JTextField(FWIDTH);
		_nNameF.setPreferredSize(MainFrame.TF_DIMENSION);
		_nNameF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_nNameF, gc);
		
		gc.gridy++;
		_phoneF = new JTextField(FWIDTH);
		_phoneF.setPreferredSize(MainFrame.TF_DIMENSION);
		_phoneF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_phoneF, gc);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		//from Long's code
	
		JButton src = (JButton) e.getSource();
		if (src == _backBtn) {
			//show warning
			
			
			//flush entered text
			_fNameF.setText("");
			_lNameF.setText("");
			_emailF.setText("");
			_addressF.setText("");
			_nNameF.setText("");
			_phoneF.setText("");
			
//			int choice = JOptionPane.showConfirmDialog(null, "Your information in this form "
//					+ "will not be saved.  Continue back?", "Warning", JOptionPane.OK_CANCEL_OPTION);
//			if (choice == JOptionPane.OK_OPTION) {
//				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StartScreen");
//				_warningLabel.setText("");
//			}
		} else if (src == _createBtn) {
			String fname = _fNameF.getText();
			String lname = _lNameF.getText();
			String email = _emailF.getText();
			String address = _addressF.getText();
			String nname = _nNameF.getText();
			String phone = _phoneF.getText();
			
			Bidder aBidder = new Bidder(fname, lname, email, address, nname, phone);
			
			MainFrame._auction.addBidder(aBidder);
		}
	}
}