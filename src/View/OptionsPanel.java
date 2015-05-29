package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Model.Donor;

public class OptionsPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Font _smallFont  = new Font("Tahoma", 0, 28);
	
	private Dimension _smallDimension  = new Dimension(MainFrame.WIDTH / 7, 100);
	
	private JPanel _leftPanel;
	
	private JPanel _donorPanel;
	
	private JPanel _itemPanel;
	
	private JPanel _removePanel;
	
	private JPanel _mainContainer;
	
	private JPanel container;
	
	private CardLayout _clayout;
	
	private JButton _addDonorBtn;
	
	private JButton _addItemBtn;
	
	private JButton _removeBtn;
	
	private JButton _backBtn;
	
	private JButton _donorBtn;
	
	private JTextField _firstTF;
	
	private JTextField _lastTF;
	
	private JTextField _emailTF;
	
	private JTextField _addressTF;
	
	private JTextField _phoneTF;
	
	private JLabel _infoLabel;
	
	public OptionsPanel() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(new BorderLayout());
		setLeftPanel();
		setItemPanel();
		setDonorPanel();
		setRemovePanel();
		addComponents();
	}

	private void setRemovePanel() {
		_removePanel = new JPanel();
		_removePanel.setBackground(Color.RED);
		
	}

	private void setDonorPanel() {
		container = new JPanel();
		container.setLayout(null);
		_donorPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(20,0,20,0);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		
		JLabel first = new JLabel("First name: ");
		first.setFont(MainFrame.FORM_LABEL_FONT);
		_donorPanel.add(first, gc);
		
		gc.gridy++;
		JLabel last = new JLabel("Last name: ");
		last.setFont(MainFrame.FORM_LABEL_FONT);
		_donorPanel.add(last, gc);
		
		gc.gridy++;
		JLabel email = new JLabel("Email: ");
		email.setFont(MainFrame.FORM_LABEL_FONT);
		_donorPanel.add(email, gc);

		gc.gridy++;
		JLabel address = new JLabel("Address: ");
		address.setFont(MainFrame.FORM_LABEL_FONT);
		_donorPanel.add(address, gc);
		
		gc.gridy++;
		JLabel phone = new JLabel("Phone: ");
		phone.setFont(MainFrame.FORM_LABEL_FONT);
		_donorPanel.add(phone, gc);
				
		gc.gridy = 0;
		gc.gridx = 1;
		_firstTF = new JTextField(20);
		_firstTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_firstTF.setFont(MainFrame.FORM_TF_FONT);
		_donorPanel.add(_firstTF, gc);
		
		gc.gridy++;
		_lastTF = new JTextField(20);
		_lastTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_lastTF.setFont(MainFrame.FORM_TF_FONT);
		_donorPanel.add(_lastTF, gc);
		
		gc.gridy++;
		_emailTF = new JTextField(20);
		_emailTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_emailTF.setFont(MainFrame.FORM_TF_FONT);
		_donorPanel.add(_emailTF, gc);
		
		gc.gridy++;
		_addressTF = new JTextField(20);
		_addressTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_addressTF.setFont(MainFrame.FORM_TF_FONT);
		_donorPanel.add(_addressTF, gc);
		
		gc.gridy++;
		_phoneTF = new JTextField(20);
		_phoneTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_phoneTF.setFont(MainFrame.FORM_TF_FONT);
		_donorPanel.add(_phoneTF, gc);
		
		gc.gridy++;
		_donorBtn = new JButton("Add");
		_donorBtn.setPreferredSize(new Dimension(100,60));
		_donorBtn.setFont(_smallFont);
		_donorBtn.addActionListener(this);
		_donorPanel.add(_donorBtn, gc);
		
		_donorPanel.setBounds(250,120,500,500);
		
		_infoLabel = new JLabel();
		_infoLabel.setBounds(350,600,400,100);
		_infoLabel.setFont(_smallFont);
		
		container.add(_donorPanel);
		container.add(_infoLabel);
		
	}

	private void setItemPanel() {
		_itemPanel = new JPanel();
		_itemPanel.setBackground(Color.black);
		
	}

	private void setLeftPanel() {
		// TODO Auto-generated method stub
		Border border = BorderFactory.createLineBorder(Color.black);
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		
		_leftPanel = new JPanel(new GridBagLayout());
		_leftPanel.setBorder(border);
		_leftPanel.setSize(MainFrame.WIDTH / 8, MainFrame.HEIGHT);
		
		_addDonorBtn = new JButton("Add Donor");
		_addDonorBtn.setPreferredSize(_smallDimension);
		_addDonorBtn.setFont(_smallFont);
		_addDonorBtn.addActionListener(this);
		
		_addItemBtn = new JButton("Add Item");
		_addItemBtn.setPreferredSize(_smallDimension);
		_addItemBtn.setFont(_smallFont);
		_addItemBtn.addActionListener(this);
		
		_removeBtn = new JButton("Delete");
		_removeBtn.setPreferredSize(_smallDimension);
		_removeBtn.setFont(_smallFont);
		_removeBtn.addActionListener(this);
		
		_backBtn = new JButton("Back");
		_backBtn.setPreferredSize(_smallDimension);
		_backBtn.setFont(_smallFont);;
		_backBtn.addActionListener(this);
		
		_leftPanel.add(_addDonorBtn, gc);
		gc.gridy++;
		_leftPanel.add(_addItemBtn, gc);
		gc.gridy++;
		_leftPanel.add(_removeBtn, gc);
		gc.gridy++;
		gc.weighty = 1;
		_leftPanel.add(_backBtn, gc);
      
	}

	private void addComponents() {
		HomeScreen home = new HomeScreen();
		
		_clayout = new CardLayout();
		_mainContainer = new JPanel();
		_mainContainer.setLayout(_clayout);
		_mainContainer.add(_itemPanel, "AddItem");
		_mainContainer.add(container, "AddDonor");
		_mainContainer.add(_removePanel, "Remove");
		_mainContainer.add(home, "Home");
		_clayout.show(_mainContainer, "AddDonor");
		
		this.add(_leftPanel, BorderLayout.WEST);
		this.add(_mainContainer, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		
		String first = _firstTF.getText();
		String last = _lastTF.getText();
		String email = _emailTF.getText();
		String address = _addressTF.getText();
		String phone = _phoneTF.getText();
		
	    String[] arr = {first, last, email, address, phone};
	    boolean isEmpty = false;
		
		JButton src = (JButton) e.getSource();
		if(src == _addDonorBtn) {
			_clayout.show(_mainContainer, "AddDonor");
		} else if (src == _addItemBtn) {
			_clayout.show(_mainContainer, "AddItem");
			_infoLabel.setText("");
		} else if (src == _removeBtn) {
			_clayout.show(_mainContainer, "Remove");
			_infoLabel.setText("");
		} else if (src == _backBtn){
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
			_infoLabel.setText("");
		} else if (src == _donorBtn) {
			for(int i = 0; i < arr.length; i++) {
				if(arr[i].equals("")) {
					isEmpty = true;
					break;
				}
			}
			if(isEmpty) {
				_infoLabel.setText("Please enter all required fields.");
			} else {
				try {
					Donor donor = new Donor(first, last, email, address, phone);
					if(donor != null) {
						MainFrame._auction.addDonor(donor);
					}
				} catch (Exception err) {
					System.out.print(err);
				}
			}
		}
	}

}
