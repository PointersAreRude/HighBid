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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

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
	
	private Color _labelColor = Color.RED;
	
	private Font _smallFont  = new Font("Tahoma", 0, 20);
	
	private Dimension _smallDimension  = new Dimension(MainFrame.WIDTH / 7, 100);
	
	private JPanel _leftPanel;
	
	private JPanel _mainContainer;
	
	private CardLayout _clayout;
	
	private JButton _backBtn;
	
	/*************** add panel's component *********************/
	private JTabbedPane _addPanel;
	
	private JButton _addBtn;
	
	/*************** add donor panel's components **************/
	private JPanel _donorPanel;
	
	private JPanel _DContainer;
	
	private JTextField _firstTF;
	
	private JTextField _lastTF;
	
	private JTextField _emailTF;
	
	private JTextField _addressTF;
	
	private JTextField _phoneTF;
	
	private JLabel _infoLabel;
	
	private JButton _donorBtn;
	
	/*************** add item panel's components ***************/
	private JPanel _itemPanel;
	
	private JPanel _IContainer;
	
	private JTextField _itemNameTF;
	
	private JTextField _ItemDescriptionTF;
	
	private JTextField _startPriceTF;
	
	private JTextField _minIncrementTF;
	
	private JTextField _qrTF;
	
	private JButton _itemBtn; 
	
	private JComboBox<String> _combo;
	
	private DefaultComboBoxModel<String> _comboModel;
	
	private JLabel _IInfo;
	
	private JButton _upload;
	
	private ImageIcon _image;
	
	/************* remove panel's components ******************/
	private JTabbedPane _removePanel;
	
	private JButton _removeBtn;
	
	/************ remove donor panel's components ************/	
	private JPanel _removeDonorPanel;
	
	private JTable _donorTable;
	
	private DefaultTableModel _model;
	
	private JButton _removeDonorBtn;
	
	private JLabel _removeDonorWarning;
	
	/************ remove item panel's components *************/
	private JPanel _removeItemPanel;
	
	
	public OptionsPanel() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(new BorderLayout());
		setLeftPanel();
		setRemovePanel();
		setAddPanel();
		addComponents();
	}

	private void setRemovePanel() {
		_removePanel = new JTabbedPane();
		setRemoveDonorPanel();
		setRemoveItemPanel();
		_removePanel.add("Remove Donor", _removeDonorPanel);
		_removePanel.add("Remove Item", _removeItemPanel);
		
	}
	
	private void setRemoveDonorPanel() {
		_removeItemPanel = new JPanel();
		
	}
	
	private void setRemoveItemPanel() {
		_removeDonorPanel = new JPanel(null);	
		_model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}			
		};
		String[] colNames = {"First Name", "Last Name", "Email", "Address", "Phone"};
		for(int i = 0; i < colNames.length; i++) {
			_model.addColumn(colNames[i]);
		}
		_donorTable = new JTable(_model);
		_donorTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(_donorTable);
		scrollPane.setBounds(120, 100, 800, 500);
		
		_removeDonorBtn = new JButton("Remove");
	    //_removeDonorBtn.setPreferredSize(new Dimension(150,60));
		_removeDonorBtn.setFont(_smallFont);
		_removeDonorBtn.setBounds(770, 630, 150, 50);
		_removeDonorBtn.addActionListener(this);
		
		JLabel info = new JLabel("Select a donor from the table below, and click remove.");
		info.setBounds(120,30,800,50);
		info.setFont(_smallFont);
		
		_removeDonorWarning = new JLabel();
		_removeDonorWarning.setFont(_smallFont);
		_removeDonorWarning.setForeground(_labelColor);
		_removeDonorWarning.setBounds(120,630,700,50);
		
		_removeDonorPanel.add(scrollPane);
		_removeDonorPanel.add(_removeDonorBtn);
		_removeDonorPanel.add(info);
		_removeDonorPanel.add(_removeDonorWarning);
		
	}

	private void setAddPanel() {
		_addPanel = new JTabbedPane();
		setAddDonorPanel();
		setAddItemPanel();
		_addPanel.add("Add Donor", _DContainer);
		_addPanel.add("Add Item", _IContainer);
	}

	private void setAddDonorPanel() {
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
		_infoLabel.setBounds(350,600,800,100);
		_infoLabel.setForeground(_labelColor);
		_infoLabel.setFont(_smallFont);
		
		_DContainer.add(donorLabel);
		_DContainer.add(_donorPanel);
		_DContainer.add(_infoLabel);
		
	}

	private void setAddItemPanel() {
		
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
		
		_comboModel = new DefaultComboBoxModel<String>();
		_combo = new JComboBox<String>();
		_combo.setModel(_comboModel);
		_comboModel.insertElementAt("", 0);
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
		_IInfo.setBounds(220,650,800,65);
		_IInfo.setForeground(_labelColor);
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
		
		_addBtn = new JButton("Add");
		_addBtn.setPreferredSize(_smallDimension);
		_addBtn.setFont(_smallFont);
		_addBtn.setFocusPainted(false);
		_addBtn.setBackground(_color);
		_addBtn.addActionListener(this);

		_removeBtn = new JButton("Remove");
		_removeBtn.setPreferredSize(_smallDimension);
		_removeBtn.setFont(_smallFont);
		_removeBtn.setFocusPainted(false);
		_removeBtn.addActionListener(this);
		
		_backBtn = new JButton("Back");
		_backBtn.setPreferredSize(_smallDimension);
		_backBtn.setFont(_smallFont);;
		_backBtn.addActionListener(this);
		
		_leftPanel.add(_addBtn, gc);
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
		_mainContainer.add(_addPanel, "Add");
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
		if(src == _addBtn) {
			_clayout.show(_mainContainer, "Add");
			setBackGround(_addBtn);
			clearText(itemTF);
			_IInfo.setText("");
			_removeDonorWarning.setText("");
		} else if (src == _removeBtn) {
			_clayout.show(_mainContainer, "Remove");
			setBackGround(_removeBtn);
			clearText(itemTF);
			clearText(donorTF);
			_IInfo.setText("");
			_infoLabel.setText("");
		} else if (src == _backBtn){
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
			clearText(donorTF);
			clearText(itemTF);
			_infoLabel.setText("");
			_IInfo.setText("");
			_removeDonorWarning.setText("");
		} else if (src == _donorBtn) {
			if(checkEmpty(donorTF)) {
				_infoLabel.setText("Please enter all required fields.");
			} else {
				try {
					Donor donor = new Donor(first, last, email, address, phone);
					DefaultTableModel model = (DefaultTableModel) _donorTable.getModel();
					model.addRow(new Object[]{first, last, email, address, phone});
					if(donor != null && !checkDonor(donor)) {
						MainFrame._auction.addDonor(donor);
						_comboModel.addElement(first + " " + last + " - " + email);
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
		} else if (src == _removeDonorBtn) {
			int index = _donorTable.getSelectedRow();
			if(index == -1) {
				_removeDonorWarning.setText("Please select a donor first.");
			} else {
				Donor donor = MainFrame._auction.getDonors().get(index);
				int comboIndex = getDonorIndex(donor)+1;
				MainFrame._auction.deleteDonor(donor);
				_comboModel.removeElementAt(comboIndex);
				_model.removeRow(index);
				_removeDonorWarning.setText(donor.getFirstName() + " " +donor.getLastName() + " has been removed.");
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
	
	private int getDonorIndex(Donor donor) {
		final List<Donor> list = MainFrame._auction.getDonors();
		for(Donor d : list) {
			if(d.equals(donor)) {
				return list.indexOf(donor);
			}
		}
		return -1;
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
		if (btn == _addBtn) {
			_addBtn.setBackground(_color);
			_removeBtn.setBackground(UIManager.getColor("Button.background"));
		} else if (btn == _removeBtn){
			_removeBtn.setBackground(_color);
			_addBtn.setBackground(UIManager.getColor("Button.background"));
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
