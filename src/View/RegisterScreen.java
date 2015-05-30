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
import javax.swing.JOptionPane;
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
 * @version 5/29/2015
 */

public class RegisterScreen extends JPanel implements ActionListener  {
	
	/** Text field width */
	 private static int FWIDTH = 20;
	 
	/** label for register panel's form */
	private JLabel _label;
	
	/** back button */
	protected JButton _backBtn;
	
	/** create button */
	protected JButton _createBtn;
	
	/** Form panel (using GridBagLayout) */
	private JPanel _gbag;
	
	/**	First name text box*/
	protected JTextField _fNameF;
	
	/**	Last name text box*/
	protected JTextField _lNameF;
	
	/**	Email text box*/
	protected JTextField _emailF;
	
	/**	Address text box*/
	protected JTextField _addressF;
	
	/**	Nick name text box*/
	protected JTextField _nNameF;
	
	/**	Phone number text box*/	
	protected JTextField _phoneF;

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
		_label = new JLabel("<html>Registration Form</html>");
		_label.setFont(new Font("Tahoma", 0, 50));
		_label.setBounds((int)(MainFrame.WIDTH / 2.7), 20,300,100);
		_label.setForeground(Color.BLUE);
		
		_backBtn = new JButton("Back");
		_backBtn.setBounds(20, MainFrame.HEIGHT - 100, 175, 60);
		_backBtn.setFont(MainFrame.BUTTON_FONT);
		_backBtn.addActionListener(this);
		
		_createBtn = new JButton("Finish");
		_createBtn.setBounds(MainFrame.WIDTH - 200, MainFrame.HEIGHT - 100, 175, 60);
		_createBtn.setFont(MainFrame.BUTTON_FONT);
		_createBtn.addActionListener(this);
		
		setupForm();
		
		JLabel reqd = new JLabel("* required fields");
		reqd.setFont(MainFrame.FORM_LABEL_FONT);
		reqd.setBounds((int)(MainFrame.WIDTH / 2.7), MainFrame.HEIGHT - 200,300,100);

		add(reqd);
	}
	
	private void setupForm(){
		_gbag = new JPanel(new GridBagLayout());
		_gbag.setBorder(BorderFactory.createTitledBorder("<html>Registration Fields</html>"));
		_gbag.setBounds(300, 120, 580, 500);
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		
		//labels
		String[] fields = {"First name:* ", "Last name:* ", "Email:* ", "Address:* ", "Nick name:  ", "Phone number:* "};
		for(int i = 0; i < 6; i++){
			JLabel tmp = new JLabel(fields[i]);
			tmp.setFont(MainFrame.FORM_LABEL_FONT);
			_gbag.add(tmp, gc);
			gc.gridy++;
		}
			
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

	/**
	 * Returns true if required fields are not empty.
	 * 
	 * @return true if required fields not empty.
	 */
	protected boolean fieldsNotEmpty(){
		if(_fNameF.getText().equals(""))
			return false;
				
		if(_lNameF.getText().equals(""))
			return false;
		
		if(_emailF.getText().equals(""))
			return false;
			
		if(_addressF.getText().equals(""))
			return false;
			
		if(_phoneF.getText().equals(""))
			return false;
		
		return true;
	}
	
	
	/**
	 * Flushes data from text fields.
	 */
	protected void flushFields(){
		_fNameF.setText("");
		_lNameF.setText("");
		_emailF.setText("");
		_addressF.setText("");
		_nNameF.setText("");
		_phoneF.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src == _createBtn) {
			if(fieldsNotEmpty()){
				Bidder aBidder = new Bidder(_fNameF.getText(), _lNameF.getText(),
						_emailF.getText(),_addressF.getText(), _nNameF.getText(),
						_phoneF.getText(),	MainFrame._auction.assignID());
				
				MainFrame._auction.addBidder(aBidder);
				flushFields();
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "RegPortal");
			} else {
				JOptionPane.showMessageDialog(this, "Please enter the required fields.");
				
			}
		} else if (src ==_backBtn){
			int choice = JOptionPane.showConfirmDialog(null, "Your information in this form "
					+ "will not be saved.  Continue back?", "Warning", JOptionPane.OK_CANCEL_OPTION);
			if (choice == JOptionPane.OK_OPTION) {
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "RegPortal");
				flushFields();
			}
		}
	}
}
