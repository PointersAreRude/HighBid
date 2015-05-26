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

import Model.Auction;

/**
 * A form panel for creating auction
 * 
 * @author Long Nguyen
 * @version 5/22/2015
 */
public class CreatePanel extends JPanel implements ActionListener {

	/**
	 * Default serial.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * label for create panel's form
	 */
	private JLabel _label;
	
	/**
	 * back button
	 */
	private JButton _backBtn;
	
	/**
	 * continue button
	 */
	private JButton _continueBtn;
	
	/**
	 * Form panel (using GridBagLayout)
	 */
	private JPanel _gbag;
	
	/**
	 * Auction class
	 */
	private Auction _auction;
	
	/**
	 * facilitator text field
	 */
	private JTextField _facilitatorTF;
	
	/**
	 * start time text field
	 */
	private JTextField _startTimeTF;
	
	/**
	 * end time text field
	 */
	private JTextField _endTimeTF;
	
	/**
	 * date text field
	 */
	private JTextField _dateTF;
	
	/**
	 * warning label
	 */
	private JLabel _warningLabel;
	
	/**
	 * 
	 */
	private JLabel _informationLabel;
	
	public CreatePanel() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(null);
		setComponents();
		addComponents();
	}

	/**
	 * Setting for each component in this panel.
	 */
	private void setComponents() {
		// Set label for create form
		_label = new JLabel("Auction Form");
		_label.setFont(new Font("Tahoma", 0, 50));
		_label.setBounds((int)(MainFrame.WIDTH / 2.7), 20,300,100);
		_label.setForeground(Color.BLUE);
		
		// Set back button
		_backBtn = new JButton("Back");
		_backBtn.setBounds(20, MainFrame.HEIGHT - 100, 175, 60);
		_backBtn.setFont(MainFrame.BUTTON_FONT);
		_backBtn.addActionListener(this);
		
		// Set continue button
		_continueBtn = new JButton("Continue");
		_continueBtn.setBounds(MainFrame.WIDTH - 200, MainFrame.HEIGHT - 100, 175, 60);
		_continueBtn.setFont(MainFrame.BUTTON_FONT);
		_continueBtn.addActionListener(this);
		
		// creating form
		creatingForm();
		
		// Warning label
		_warningLabel = new JLabel();
		_warningLabel.setForeground(Color.RED);
		_warningLabel.setFont(MainFrame.FORM_LABEL_FONT);
		_warningLabel.setBounds(390 ,630, 430, 35);
		
		// information label
		_informationLabel = new JLabel("All fields are required.");
		_informationLabel.setFont(MainFrame.FORM_LABEL_FONT);
		_informationLabel.setBounds(450, 670, 430, 35);
		
	}

	/**
	 * Creating the form.
	 */
	private void creatingForm() {
		_gbag = new JPanel(new GridBagLayout());
		_gbag.setBorder(BorderFactory.createTitledBorder("Auction's Details"));
		_gbag.setBounds(300, 120, 580, 500);
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		
		JLabel facilitatorLabel = new JLabel("Facilitator name: ");
		facilitatorLabel.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(facilitatorLabel, gc);
		
		gc.gridy++;
		JLabel startTimeLabel = new JLabel("Start time: ");
		startTimeLabel.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(startTimeLabel, gc);
		
		gc.gridy++;
		JLabel endTimeLabel = new JLabel("End time: ");
		endTimeLabel.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(endTimeLabel, gc);
		
		gc.gridy++;
		JLabel dateLabel = new JLabel("Date: ");
		dateLabel.setFont(MainFrame.FORM_LABEL_FONT);
		_gbag.add(dateLabel, gc);
		
		gc.weighty = 1;
				
		gc.gridy = 0;
		gc.gridx = 1;
		_facilitatorTF = new JTextField(20);
		_facilitatorTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_facilitatorTF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_facilitatorTF, gc);
		
		gc.gridy++;
		_startTimeTF = new JTextField(20);
		_startTimeTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_startTimeTF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_startTimeTF, gc);
		
		gc.gridy++;
		_endTimeTF = new JTextField(20);
		_endTimeTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_endTimeTF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_endTimeTF, gc);
		
		gc.gridy++;
		_dateTF = new JTextField(20);
		_dateTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_dateTF.setFont(MainFrame.FORM_TF_FONT);
		_gbag.add(_dateTF, gc);
		
	}

	/**
	 * Add the component to the panel.
	 */
	private void addComponents() {
		this.add(_label);
		this.add(_backBtn);
		this.add(_gbag);
		this.add(_continueBtn);
		this.add(_warningLabel);
		this.add(_informationLabel);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src == _backBtn) {
			int choice = JOptionPane.showConfirmDialog(null, "Your information in this form "
					+ "will not be saved.  Continue back?", "Warning", JOptionPane.OK_CANCEL_OPTION);
			if (choice == JOptionPane.OK_OPTION) {
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StartScreen");
				_warningLabel.setText("");
			}
		} else if (src == _continueBtn) {

			String facilitatorName = _facilitatorTF.getText();
			String startTime = _startTimeTF.getText();
			String endTime = _endTimeTF.getText();
			String date = _dateTF.getText();
			String[] arr = {facilitatorName, startTime, endTime, date};
			
			boolean empty = false;
			for(int i = 0; i < arr.length; i++) {
				if(arr[i].equals("")) {
					empty = true;
					break;
				}
			}
			
			if(empty) {
				_warningLabel.setText("Please enter all required fields.");
			} else {
				MainFrame._auction = new Auction(date, startTime, endTime, facilitatorName);
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
			}
			
		}
	}
	
}
