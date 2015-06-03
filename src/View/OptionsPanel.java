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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Model.Donor;
import Model.Item;

/**
 * This panel is used to add or remove donor/item
 * 
 * @author Long Nguyen
 * @version 6/1/2015
 */
public class OptionsPanel extends JPanel implements ActionListener {

	/**
	 * gererated serial version uid
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Sidebar's button color
	 */
	private Color _btnColor = Color.WHITE;
	
	/**
	 * Color for warning/info labels
	 */
	private Color _labelColor = Color.RED;
	
	/**
	 * default font used in this panel
	 */
	private Font _smallFont  = new Font("Tahoma", 0, 23);
	
	/**
	 * default dimension used in this panel
	 */
	private Dimension _smallDimension  = new Dimension(MainFrame.WIDTH / 7, 100);
	
	/**
	 * Sidebar
	 */
	private JPanel _leftPanel;
	
	/**
	 * container panel for CardLayout
	 */
	private JPanel _mainContainer;
	
	/**
	 * Card layout
	 */
	private CardLayout _clayout;
	
	/**
	 * Back button
	 */
	private JButton _backBtn;
	
	/*************** add panel's component *********************/
	/**
	 * Add tabbbed pane
	 */
	private JTabbedPane _addPanel;
	
	/**
	 * Add button
	 */
	private JButton _addBtn;
	
	/*************** add donor panel's components **************/
	/**
	 * Add donor's panel
	 */
	private JPanel _donorPanel;
	
	/**
	 * Add donor's container (null layout)
	 */
	private JPanel _DContainer;
	
	/**
	 * first name text field
	 */
	private JTextField _firstTF;
	
	/**
	 * Last name text field
	 */
	private JTextField _lastTF;
	
	/**
	 * email text field
	 */
	private JTextField _emailTF;
	
	/**
	 * address text field
	 */
	private JTextField _addressTF;
	
	/**
	 * phone text field
	 */
	private JTextField _phoneTF;
	
	/**
	 * Label to display warning/details to user
	 */
	private JLabel _infoLabel;
	
	/**
	 * Add donor button
	 */
	private JButton _donorBtn;
	
	
	/*************** add item panel's components ***************/
	/**
	 * add item's panel
	 */
	private JPanel _itemPanel;
	
	/**
	 * add item's container (null layout)
	 */
	private JPanel _IContainer;
	
	/**
	 * Item name text field
	 */
	private JTextField _itemNameTF;
	
	/**
	 * item description text field
	 */
	private JTextField _ItemDescriptionTF;
	
	/**
	 * Starting price text field
	 */
	private JTextField _startPriceTF;
	
	/**
	 * Minimum increment text field
	 */
	private JTextField _minIncrementTF;
	
	/**
	 * QR text field
	 */
	private JTextField _qrTF;
	
	/**
	 * Add item button
	 */
	private JButton _itemBtn; 
	
	/**
	 * A combo box for donors
	 */
	private JComboBox<String> _combo;
	
	/**
	 * Combo box's model
	 */
	private DefaultComboBoxModel<String> _comboModel;
	
	/**
	 * Jlabel to display warning/info to users.
	 */
	private JLabel _IInfo;
	
	/**
	 * Upload button
	 */
	private JButton _upload;
	
	/**
	 * image for item
	 */
	private ImageIcon _image;

	
	/************* remove panel's components ******************/
	/**
	 * Remove tabbed pane
	 */
	private JTabbedPane _removePanel;
	
	/**
	 * Remove button
	 */
	private JButton _removeBtn;
	
	/************ remove donor panel's components ************/	
	/**
	 * Remove donor's panel
	 */
	private JPanel _removeDonorPanel;
	
	/**
	 * Table to hold donors' data
	 */
	private JTable _donorTable;
	
	/**
	 * Donor table's model
	 */
	private DefaultTableModel _donorModel;
	
	/**
	 * remove donor button
	 */
	private JButton _removeDonorBtn;
	
	/**
	 * Jlabel to display warning/info to users.
	 */
	private JLabel _removeDonorWarning;
	
	/************ remove item panel's components *************/
	/**
	 * Remove item's panel
	 */
	private JPanel _removeItemPanel;
	
	/**
	 * Table to hold items' data
	 */
	private JTable _itemTable;
	
	/**
	 * Item table's model
	 */
	private DefaultTableModel _itemModel;
	
	/**
	 * Remove item button
	 */
	private JButton _removeItemBtn;
	
	/**
	 * JLabel to display warning/info to users
	 */
	private JLabel _removeItemWarning;

	private FormHelper _helper = new FormHelper();
	
	JTextField[] donorTF = null;
	JTextField[] itemTF = null;
	
	
	public OptionsPanel() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(new BorderLayout());
		setLeftPanel();
		setRemovePanel();
		setAddPanel();
		addComponents();
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "F key");
        //getActionMap().put("F key", keyAction);
	}

	/**
	 * Set up remove tab for removing item/donor
	 */
	private void setRemovePanel() {
		_removePanel = new JTabbedPane();
		setRemoveDonorPanel();
		setRemoveItemPanel();
		_removePanel.add("Remove Donor", _removeDonorPanel);
		_removePanel.add("Remove Item", _removeItemPanel);		
	}
	
	/**
	 * Set up remove donor tab
	 */
	private void setRemoveDonorPanel() {
		_removeItemPanel = new JPanel(null);
		_itemModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}			
		};
		String[] colNames = {"Item Name", "Item QR", "Starting Price", "Minimum Increment"};
		for(int i = 0; i < colNames.length; i++) {
			_itemModel.addColumn(colNames[i]);
		}
		_itemTable = new JTable(_itemModel);
		_itemTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(_itemTable);
		scrollPane.setBounds(120, 100, 800, 500);
		
		_removeItemBtn = new JButton("Remove");
		_removeItemBtn.setFont(_smallFont);
		_removeItemBtn.setBounds(770, 630, 150, 50);
		_removeItemBtn.addActionListener(this);
		
		JLabel info = new JLabel("Select an item from the table below, and click remove.");
		info.setBounds(120,30,800,50);
		info.setFont(_smallFont);
		
		_removeItemWarning = new JLabel();
		_removeItemWarning.setFont(_smallFont);
		_removeItemWarning.setForeground(_labelColor);
		_removeItemWarning.setBounds(120,630,700,50);
		
		_removeItemPanel.add(scrollPane);
		_removeItemPanel.add(_removeItemBtn);
		_removeItemPanel.add(info);
		_removeItemPanel.add(_removeItemWarning);
	}
	
	/**
	 * Set up remove item tab
	 */
	private void setRemoveItemPanel() {
		_removeDonorPanel = new JPanel(null);	
		_donorModel = new DefaultTableModel() {
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
			_donorModel.addColumn(colNames[i]);
		}
		_donorTable = new JTable(_donorModel);
		_donorTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(_donorTable);
		scrollPane.setBounds(120, 100, 800, 500);
		
		_removeDonorBtn = new JButton("Remove");
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

	/**
	 * Setting up add tab for adding donor/item
	 */
	private void setAddPanel() {
		_addPanel = new JTabbedPane();
		setAddDonorPanel();
		setAddItemPanel();
		_addPanel.add("Add Donor", _DContainer);
		_addPanel.add("Add Item", _IContainer);
		
		_addPanel.addChangeListener(new myChangeListener());
	}

	/**
	 * Set up add donor tab's form
	 */
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
		
		String[] labelName = {"First Name: ", "Last Name: "
				, "Email: ", "Address: ", "Phone: "};
		
		for(int i = 0; i < labelName.length; i++) {
			JLabel temp = new JLabel(labelName[i]);
			temp.setFont(MainFrame.FORM_LABEL_FONT);
			_donorPanel.add(temp, gc);
			gc.gridy++;
		}

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

	/**
	 * Set up add item tab's form
	 */
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
		
		String[] labelName = {"Choose a donor (Optional)", "Item Name: "
				, "Description: ", "Minimum Increment: "
				, "Starting Price: ", "Item's QR: ", "Upload Image (Optional)"};
		
		for(int i = 0; i < labelName.length; i++) {
			JLabel temp = new JLabel(labelName[i]);
			temp.setFont(MainFrame.FORM_LABEL_FONT);
			_itemPanel.add(temp, gc);
			gc.gridy++;
		}
				
		gc.gridy = 0;
		gc.gridx = 1;
		
		_comboModel = new DefaultComboBoxModel<String>();
		_combo = new JComboBox<String>();
		_combo.setModel(_comboModel);
		_comboModel.addElement("");
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

	/**
	 * Set up the right sidebar
	 */
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
		_addBtn.setBackground(_btnColor);
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
		gc.weighty = 1;
		gc.gridy++;
		_leftPanel.add(_backBtn, gc);
      
	}

	/**
	 * Add components to card layout container
	 * then add that cardlayout and other components
	 * to the main panel
	 */
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
		
		donorTF = new JTextField[]{_firstTF, _lastTF, _emailTF, _addressTF, _phoneTF};
		itemTF = new JTextField[]{_itemNameTF, _ItemDescriptionTF, _startPriceTF, _minIncrementTF, _qrTF};
		
	}

	public void actionPerformed(ActionEvent e) {
			
		JButton src = (JButton) e.getSource();
		if(src == _addBtn) {
			_clayout.show(_mainContainer, "Add");
			setBackGround(_addBtn);
			_helper.clearText(itemTF);
			_IInfo.setText("");
			_removeDonorWarning.setText("");
			_removeItemWarning.setText("");
		} else if (src == _removeBtn) {
			_clayout.show(_mainContainer, "Remove");
			setBackGround(_removeBtn);
			_helper.clearText(itemTF);
			_helper.clearText(donorTF);
			_IInfo.setText("");
			_infoLabel.setText("");
		} else if (src == _backBtn){
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
			_helper.clearText(donorTF);
			_helper.clearText(itemTF);
			_infoLabel.setText("");
			_IInfo.setText("");
			_removeDonorWarning.setText("");
			_removeItemWarning.setText("");
		} else if (src == _donorBtn) {
			if(_helper.checkEmpty(donorTF)) {
				_infoLabel.setText("Please enter all required fields.");
			} else {
				addDonorAction(donorTF);
			}
		} else if (src == _upload) {
			BufferedImage img = _helper.uploadImage();
			if(img != null) {
				_image = new ImageIcon(img);
				_IInfo.setText("Image uploaded");
			} else {
				_IInfo.setText("Couldn't upload the image.");
			}
		} else if (src == _itemBtn) {
			if(_helper.checkEmpty(itemTF)) {
				_IInfo.setText("Please enter all required fields.");
			} else {
				addItemAction(itemTF);
			}
		} else if (src == _removeDonorBtn) {
			removeDonorAction();
		} else if (src == _removeItemBtn) {
			removeItemAction();
		}
	}
	
	/**
	 * Removing item operation. This method is called
	 * when remove button in remove item tab is clicked.
	 * 
	 * @throws ConcurrentModificationException
	 */
	private void removeItemAction() throws ConcurrentModificationException {
		int index = _itemTable.getSelectedRow();
		if(index == -1) {
			_removeItemWarning.setText("Please select an item first.");
		} else {
			Item item = MainFrame._auction.getItems().get(index);
			MainFrame._auction.deleteItem(item);
			_itemModel.removeRow(index);
			Iterator<Item> itr = null;
			for(Donor d : MainFrame._auction.getDonors()) {
				itr = d.getItemList().iterator();
				while(itr.hasNext()) {
					Item i = itr.next();
					if(item.equals(i)) {
						itr.remove();
					}
				}				
			}
			_removeItemWarning.setText(item.getName() + "has been removed.");
		}
	}

	/**
	 * Removing donor operation. This method is called
	 * when remove button in remove donor tab is clicked.
	 */
	private void removeDonorAction() {
		int index = _donorTable.getSelectedRow();
		if(index == -1) {
			_removeDonorWarning.setText("Please select a donor first.");
		} else {
			Donor donor = MainFrame._auction.getDonors().get(index);
			int comboIndex = _helper.getDonorIndex(donor)+1;
			MainFrame._auction.deleteDonor(donor);
			_comboModel.removeElementAt(comboIndex);
			_donorModel.removeRow(index);
			_removeDonorWarning.setText(donor.getFirstName() + " " +donor.getLastName() + " has been removed.");
		}
	}
	
	/**
	 * Adding donor operation.  This method is called
	 * when add button in add donor tab is clicked.
	 * 
	 * @param donorTF An array of JTextField in add donor tab
	 */
	private void addDonorAction(JTextField[] donorTF) {
		String first = _firstTF.getText();
		String last = _lastTF.getText();
		String email = _emailTF.getText();
		String address = _addressTF.getText();
		String phone = _phoneTF.getText();
		
		try {
			Donor donor = new Donor(first, last, email, address, phone);
			// Add info to donor table
			DefaultTableModel model = (DefaultTableModel) _donorTable.getModel();
			model.addRow(new Object[]{first, last, email, address, phone});
			if(donor != null && !_helper.checkDonor(donor)) {
				// Add info to the list in auction
				MainFrame._auction.addDonor(donor);
				// Add info to the combo box in item panel
				_comboModel.addElement(first + " " + last + " - " + email);
				_helper.clearText(donorTF);
				_infoLabel.setText(first + " " + last + " has been added.");
			} else {
				_infoLabel.setText("Donor already exists.");
			}
			
			String fileWriteTo = "+," + first + "," + last + "," + phone + "," + email + "," + address;
			MainFrame._auction.writeToFile("Donors", fileWriteTo);

		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Adding item operation. This method is called
	 * when add button in add item tab is clicked.
	 * 
	 * @param itemTF An array of JTextField in add item tab
	 */
	private void addItemAction(JTextField[] itemTF) {
		
		String itemName = _itemNameTF.getText();
		String itemDescription = _ItemDescriptionTF.getText();
		int startPrice = 0;
		int minIncrement = 0;
		long qr = 0;
		// parse doesn't work if the string is empty so I gotta check it here
		// the strings are empty by default so it will give an error if not checked.
		if(!_helper.checkEmpty(itemTF)) {
			try {
				startPrice = Integer.parseInt(_startPriceTF.getText());
				minIncrement = Integer.parseInt(_minIncrementTF.getText());
				qr = Long.parseLong(_qrTF.getText());
			} catch (Exception ex) {
				_IInfo.setText("Wrong input format!");
			}
		}
		
		Item item = null;
		Donor donor = _helper.getDonor(_combo);
		try {
			// if no donor is selected
			if(_combo.getSelectedItem().equals("")) {
				item = new Item(itemName, itemDescription, minIncrement, startPrice, qr, _image);
			// if  no image is uploaded	
			} else if (_image == null) {
				item = new Item(itemName, itemDescription, minIncrement, startPrice, donor, qr);
			// if no image is uploaded or no donor is selected	
			} else if (_image == null && _combo.getSelectedItem().equals("")){
				item = new Item(itemName, itemDescription, minIncrement, startPrice, qr);
			// If everything is filled out, selected, or uploaded	
			} else {
				item = new Item(itemName, itemDescription, minIncrement, startPrice, donor, qr, _image);
			}
			
			if(item != null && minIncrement > 0 && !_helper.checkItem(item)) {
				// Add to the list of item in auction class
				MainFrame._auction.addItem(item);
				if(donor != null) {
					// Add the item to a particular donor
					donor.add(item);
					_IInfo.setText(itemName + " has been added for " + donor.getFirstName() 
							+ " " + donor.getLastName() + ".");
			    } else {
			    	_IInfo.setText(itemName + " has been added.");
			    }	
				_helper.clearText(itemTF);
			} else {
				_IInfo.setText("Item already exists.");
			}
			// Add the item the the item table
			DefaultTableModel model = (DefaultTableModel) _itemTable.getModel();
			model.addRow(new Object[]{itemName, qr, "$" + startPrice, "$" + minIncrement});
		} catch(Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Set the background of the sidebar's buttons
	 * So user know which panel they are on.
	 * 
	 * @param btn A button object
	 */
	private void setBackGround(JButton btn) {
		if (btn == _addBtn) {
			_addBtn.setBackground(_btnColor);
			_removeBtn.setBackground(UIManager.getColor("Button.background"));
		} else if (btn == _removeBtn){
			_removeBtn.setBackground(_btnColor);
			_addBtn.setBackground(UIManager.getColor("Button.background"));
		}
	}
	
//	@SuppressWarnings("serial")
//	private Action keyAction = new AbstractAction() {
//        public void actionPerformed(ActionEvent a) {
//			if(_addPanel.getSelectedComponent() == _DContainer) {
//				_helper.whichForm("Donor",donorTF);
//			} else if (_addPanel.getSelectedComponent() == _IContainer) {
//				_helper.whichForm("Item", itemTF);
//			}
//        }
//    };
    
    private class myChangeListener implements ChangeListener {
    	
    	public myChangeListener() {
    	}
    	
		public void stateChanged(ChangeEvent e) {
			JTabbedPane tabP = (JTabbedPane) e.getSource();
			if (tabP.getSelectedIndex() == 0) {
				tabP.setSelectedComponent(_DContainer);
			} else if (tabP.getSelectedIndex() == 1) {
				tabP.setSelectedComponent(_IContainer);
			}
			
		}
    	
    }
}
