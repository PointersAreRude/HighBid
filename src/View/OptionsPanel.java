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
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Model.Donor;
import Model.Item;

/**
 * This panel is used to add or remove donor/item
 * 
 * @author Long Nguyen
 * @version 5/29/2015
 */
public class OptionsPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Color _color = Color.WHITE;
	
	private Font _smallFont  = new Font("Tahoma", 0, 28);
	
	private Dimension _smallDimension  = new Dimension(MainFrame.WIDTH / 7, 100);
	
	private JPanel _leftPanel;
	
	private JPanel _removePanel;
	
	private JPanel _mainContainer;
	
	private CardLayout _clayout;
	
	private JButton _addDonorBtn;
	
	private JButton _addItemBtn;
	
	private JButton _removeBtn;
	
	private JButton _backBtn;
	
	/*************** donor panel's components *******************/
	private JPanel _donorPanel;
	
	private JPanel _DContainer;
	
	private JTextField _firstTF;
	
	private JTextField _lastTF;
	
	private JTextField _emailTF;
	
	private JTextField _addressTF;
	
	private JTextField _phoneTF;
	
	private JLabel _infoLabel;
	
	private JButton _donorBtn;
	
	/*************** item panel's components *******************/
	private JPanel _itemPanel;
	
	private JPanel _IContainer;
	
	private JTextField _itemNameTF;
	
	private JTextField _ItemDescriptionTF;
	
	private JTextField _startPriceTF;
	
	private JTextField _minIncrementTF;
	
	private JTextField _qrTF;
	
	private JButton _itemBtn; 
	
	private JComboBox<String> _combo;
	
	private JLabel _IInfo;
	
	private JButton _upload;
	
	private ImageIcon _image;
	
	
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
		_DContainer = new JPanel();
		_DContainer.setLayout(null);
		JLabel donorLabel = new JLabel("Donor Form");
		donorLabel.setFont(new Font("Tahoma", 0, 50));
		donorLabel.setBounds(370,30,500,100);
		donorLabel.setForeground(Color.BLUE);
		
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
		_infoLabel.setForeground(Color.RED);
		_infoLabel.setFont(_smallFont);
		
		_DContainer.add(donorLabel);
		_DContainer.add(_donorPanel);
		_DContainer.add(_infoLabel);
		
	}

	private void setItemPanel() {
		
		JLabel itemLabel = new JLabel("Item Form");
		itemLabel.setFont(new Font("Tahoma", 0, 50));
		itemLabel.setBounds(370,30,500,100);
		itemLabel.setForeground(Color.BLUE);
		
		_itemPanel = new JPanel(new GridBagLayout());
		_IContainer = new JPanel();
		_IContainer.setLayout(null);
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(10,0,10,0);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		
		JLabel donor = new JLabel("Choose a donor (Optional)");
		donor.setFont(MainFrame.FORM_LABEL_FONT);
		_itemPanel.add(donor, gc);
		
		gc.gridy++;
		JLabel name = new JLabel("Item name: ");
		name.setFont(MainFrame.FORM_LABEL_FONT);
		_itemPanel.add(name, gc);
		
		gc.gridy++;
		JLabel des = new JLabel("Description: ");
		des.setFont(MainFrame.FORM_LABEL_FONT);
		_itemPanel.add(des, gc);
		
		gc.gridy++;
		JLabel minIncrement = new JLabel("Minimum increment: ");
		minIncrement.setFont(MainFrame.FORM_LABEL_FONT);
		_itemPanel.add(minIncrement, gc);

		gc.gridy++;
		JLabel startingPrice = new JLabel("Starting price: ");
		startingPrice.setFont(MainFrame.FORM_LABEL_FONT);
		_itemPanel.add(startingPrice, gc);
		
		gc.gridy++;
		JLabel qr = new JLabel("Item's QR: ");
		qr.setFont(MainFrame.FORM_LABEL_FONT);
		_itemPanel.add(qr, gc);
		
		gc.gridy++;
		JLabel upload = new JLabel("Upload Image (Optional)");
		upload.setFont(MainFrame.FORM_LABEL_FONT);
		_itemPanel.add(upload, gc);
				
		gc.gridy = 0;
		gc.gridx = 1;
		
		_combo = new JComboBox<String>();
		_combo.addItem("");
		_combo.setPrototypeDisplayValue("    this controls combobox's width    ");
		_itemPanel.add(_combo,gc);
		
		gc.gridy++;
		_itemNameTF = new JTextField(20);
		_itemNameTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_itemNameTF.setFont(MainFrame.FORM_TF_FONT);
		_itemPanel.add(_itemNameTF, gc);
		
		gc.gridy++;
		_ItemDescriptionTF = new JTextField(20);
		_ItemDescriptionTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_ItemDescriptionTF.setFont(MainFrame.FORM_TF_FONT);
		_itemPanel.add(_ItemDescriptionTF, gc);
		
		gc.gridy++;
		_minIncrementTF = new JTextField(20);
		_minIncrementTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_minIncrementTF.setFont(MainFrame.FORM_TF_FONT);
		_itemPanel.add(_minIncrementTF, gc);
		
		gc.gridy++;
		_startPriceTF = new JTextField(20);
		_startPriceTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_startPriceTF.setFont(MainFrame.FORM_TF_FONT);
		_itemPanel.add(_startPriceTF, gc);
		
		gc.gridy++;
		_qrTF = new JTextField(20);
		_qrTF.setPreferredSize(MainFrame.TF_DIMENSION);
		_qrTF.setFont(MainFrame.FORM_TF_FONT);
		_itemPanel.add(_qrTF, gc);
		
		gc.gridy++;
		_upload = new JButton("Upload Image");
		_upload.addActionListener(this);
		_itemPanel.add(_upload, gc);
		
		gc.gridy++;
		_itemBtn = new JButton("Add");
		_itemBtn.setPreferredSize(new Dimension(100,60));
		_itemBtn.setFont(_smallFont);
		_itemBtn.addActionListener(this);
		_itemPanel.add(_itemBtn, gc);
		
		_itemPanel.setBounds(10,110,900,550);
		
		_IInfo = new JLabel();
		_IInfo.setBounds(350,650,400,65);
		_IInfo.setForeground(Color.RED);
		_IInfo.setFont(_smallFont);
		
		_IContainer.add(_itemPanel);
		_IContainer.add(itemLabel);
		_IContainer.add(_IInfo);
		
		
	}

	private void setLeftPanel() {
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
		_addDonorBtn.setFocusPainted(false);
		_addDonorBtn.setBackground(_color);
		_addDonorBtn.addActionListener(this);
		
		_addItemBtn = new JButton("Add Item");
		_addItemBtn.setPreferredSize(_smallDimension);
		_addItemBtn.setFont(_smallFont);
		_addItemBtn.setFocusPainted(false);
		_addItemBtn.addActionListener(this);
		
		_removeBtn = new JButton("Delete");
		_removeBtn.setPreferredSize(_smallDimension);
		_removeBtn.setFont(_smallFont);
		_removeBtn.setFocusPainted(false);
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
		_mainContainer.add(_IContainer, "AddItem");
		_mainContainer.add(_DContainer, "AddDonor");
		_mainContainer.add(_removePanel, "Remove");
		_mainContainer.add(home, "Home");
		_clayout.show(_mainContainer, "AddDonor");
		
		this.add(_leftPanel, BorderLayout.WEST);
		this.add(_mainContainer, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		
		JTextField[] donorTF = {_firstTF, _lastTF, _emailTF, _addressTF, _phoneTF};
		JTextField[] itemTF = {_itemNameTF, _ItemDescriptionTF, _startPriceTF, _minIncrementTF, _qrTF};
		
		String first = _firstTF.getText();
		String last = _lastTF.getText();
		String email = _emailTF.getText();
		String address = _addressTF.getText();
		String phone = _phoneTF.getText();
		
		String itemName = _itemNameTF.getText();
		String itemDescription = _ItemDescriptionTF.getText();
		int startPrice = 0;
		int minIncrement = 0;
		long qr = 0;
		// parse doesn't work if the string is empty so I gotta check it here
		// the strings are empty by default so it will give an error if not checked.
		if(!checkEmpty(itemTF)) {
			try {
				startPrice = Integer.parseInt(_startPriceTF.getText());
				minIncrement = Integer.parseInt(_minIncrementTF.getText());
				qr = Long.parseLong(_qrTF.getText());
			} catch (Exception ex) {
				_IInfo.setText("Wrong input format!");
			}
		}
		
		JButton src = (JButton) e.getSource();
		if(src == _addDonorBtn) {
			_clayout.show(_mainContainer, "AddDonor");
			setBackGround(_addDonorBtn);
			clearText(itemTF);
		} else if (src == _addItemBtn) {
			_clayout.show(_mainContainer, "AddItem");
			setBackGround(_addItemBtn);
			clearText(donorTF);
			_infoLabel.setText("");
		} else if (src == _removeBtn) {
			_clayout.show(_mainContainer, "Remove");
			setBackGround(_removeBtn);
			clearText(itemTF);
			clearText(donorTF);
			_infoLabel.setText("");
		} else if (src == _backBtn){
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
			clearText(donorTF);
			clearText(itemTF);
			_infoLabel.setText("");
		} else if (src == _donorBtn) {
			if(checkEmpty(donorTF)) {
				_infoLabel.setText("Please enter all required fields.");
			} else {
				try {
					Donor donor = new Donor(first, last, email, address, phone);
					if(donor != null && !checkDonor(donor)) {
						MainFrame._auction.addDonor(donor);
						_combo.addItem(first + " " + last + " - " + email);
						clearText(donorTF);
						_infoLabel.setText(first + " " + last + " has been added.");
					} else {
						_infoLabel.setText("Donor already exists.");
					}
				} catch (Exception err) {
					System.out.println(err);
				}
			}
		} else if (src == _upload) {
			BufferedImage img = uploadImage();
			if(img != null) {
				_image = new ImageIcon(img);
				_IInfo.setText("Image uploaded");
			} else {
				_IInfo.setText("Couldn't upload the image.");
			}
		} else if (src == _itemBtn) {
			if(checkEmpty(itemTF)) {
				_IInfo.setText("Please enter all required fields.");
			} else {
				Item item = null;
				try {
					if(_combo.getSelectedItem().equals("")) {
						item = new Item(itemName, itemDescription, minIncrement, startPrice, qr, _image);
					} else if (_image == null) {
						item = new Item(itemName, itemDescription, minIncrement, startPrice, getDonor(), qr);
					} else if (_image == null && _combo.getSelectedItem().equals("")){
						item = new Item(itemName, itemDescription, minIncrement, startPrice, qr);
					} else {
						item = new Item(itemName, itemDescription, minIncrement, startPrice, getDonor(), qr, _image);
					}
					if(item != null && minIncrement > 0 && !checkItem(item)) {
						MainFrame._auction.addItem(item);
						if(getDonor() != null) {
							getDonor().add(item);
							_IInfo.setText(itemName + " has been added for " + getDonor().getFirstName() 
									+ " " + getDonor().getLastName() + ".");
					    } else {
					    	_IInfo.setText(itemName + " has been added.");
					    }	
						clearText(itemTF);
					} else {
						_IInfo.setText("Item already exists.");
					}
				} catch(Exception err) {
					err.printStackTrace();
				}
			}
		}
	}
	
	private boolean checkItem(Item item) {
		for(Item i : MainFrame._auction.getItems()) {
			if (i.getQr() == item.getQr()) {
				return true;
			}
		}
		return false;
	}

	private Donor getDonor() {
		for(Donor donor: MainFrame._auction.getDonors()) {
			String donorName = donor.getFirstName() + " " + donor.getLastName() + " - " + donor.getEmail();
			if(_combo.getSelectedItem().toString().equals(donorName)) {
				return donor;
			}
		}
		return null;
	}

	private BufferedImage uploadImage() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(OptionsPanel.this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			BufferedImage img = null;
			try {
				img = ImageIO.read(f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return img;
		}
		return null;
	}

	private boolean checkEmpty(JTextField[] tf) {
		for(int i = 0; i < tf.length; i++) {
			if(tf[i].getText().equals("")) {
				return true;
			}
		}
		return false;
	}
	
	private void setBackGround(JButton btn) {
		if(btn == _addItemBtn) {
			_addItemBtn.setBackground(_color);
			_addDonorBtn.setBackground(UIManager.getColor("Button.background"));
			_removeBtn.setBackground(UIManager.getColor("Button.background"));
		} else if (btn == _addDonorBtn) {
			_addDonorBtn.setBackground(_color);
			_addItemBtn.setBackground(UIManager.getColor("Button.background"));
			_removeBtn.setBackground(UIManager.getColor("Button.background"));
		} else if (btn == _removeBtn){
			_removeBtn.setBackground(_color);
			_addDonorBtn.setBackground(UIManager.getColor("Button.background"));
			_addItemBtn.setBackground(UIManager.getColor("Button.background"));
		}
	}

	private void clearText(JTextField[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i].setText("");
		}
	}

	private boolean checkDonor(Donor donor) {
		for(Donor d: MainFrame._auction.getDonors()) {
			String donorToAdd = donor.getFirstName() + donor.getLastName() + donor.getEmail();
			String donorInList = d.getFirstName() + d.getLastName() + d.getEmail();
			if(donorToAdd.equals(donorInList)) {
				return true;
			}
		}
		return false;
	}

}
