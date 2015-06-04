package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * The backbone for all related classes (OptionsRemovePanel, 
 * OptionsAddPanel, and the sidebar in this class)
 * 
 * @author Long Nguyen
 * @autor 6/3/2015
 */
@SuppressWarnings("serial")
public class OptionsMain extends JPanel implements ActionListener {
	
	/**
	 * Color for warning/info labels
	 */
	protected final static Color _labelColor = Color.RED;
	
	/**
	 * default font used in this panel
	 */
	protected final static Font _smallFont  = new Font("Tahoma", 0, 23);
	
	/**
	 * Array of text field for donor's form
	 */
	protected static JTextField[] donorTF = null;
	
	/**
	 * Array of text field for item's form
	 */
	protected static JTextField[] itemTF = null;
	
	/**
	 * helper class for item/donor's form (filling, clearing text, etc...)
	 */
	protected static final FormHelper _helper = new FormHelper();
	
	/**
	 * Jlabel to display warning/info to users.
	 */
	protected static JLabel _IInfo;
	
	/**
	 * Label to display warning/details to user
	 */
	protected static JLabel _infoLabel;
	
	/**
	 * JLabel to display warning/info to users
	 */
	protected static JLabel _removeItemWarning;
	
	/**
	 * Jlabel to display warning/info to users.
	 */
	protected static JLabel _removeDonorWarning;
	
	/**
	 * Combo box's model
	 */
	public static final DefaultComboBoxModel<String> _comboModel = new DefaultComboBoxModel<String>();
	
	/**
	 * Table to hold donors' data
	 */
	protected static JTable _donorTable;
	
	/**
	 * Table to hold items' data
	 */
	protected static JTable _itemTable;
	
	/**
	 * default dimension used in this panel
	 */
	private final Dimension _smallDimension  = new Dimension(MainFrame.WIDTH / 7, 100);
	
	/**
	 * Sidebar's button color
	 */
	private final Color _btnColor = Color.WHITE;
	
	/**
	 * Sidebar
	 */
	private JPanel _sideBar;
	
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
	
	/**
	 * Add button
	 */
	private JButton _addBtn;
	
	/**
	 * Remove button
	 */
	private JButton _removeBtn;
	
	
	public OptionsMain() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(new BorderLayout());
		setSideBar();
		addComponents();
	}

	/**
	 * Set up the right sidebar
	 */
	private void setSideBar() {
		Border border = BorderFactory.createLineBorder(Color.black);
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		
		_sideBar = new JPanel(new GridBagLayout());
		_sideBar.setBorder(border);
		_sideBar.setSize(MainFrame.WIDTH / 8, MainFrame.HEIGHT);
		
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
		
		_sideBar.add(_addBtn, gc);
		gc.gridy++;
		_sideBar.add(_removeBtn, gc);
		gc.weighty = 1;
		gc.gridy++;
		_sideBar.add(_backBtn, gc);
      
	}
	
	/**
	 * Add components to card layout container
	 * then add that cardlayout and other components
	 * to the main panel
	 */
	private void addComponents() {
		HomeScreen home = new HomeScreen();
		OptionsAddPanel add = new OptionsAddPanel();
		OptionsRemovePanel remove = new OptionsRemovePanel();
		
		_clayout = new CardLayout();
		_mainContainer = new JPanel();
		_mainContainer.setLayout(_clayout);
		_mainContainer.add(add, "Add");
		_mainContainer.add(remove, "Remove");
		_mainContainer.add(home, "Home");
		_clayout.show(_mainContainer, "Add");
		
		this.add(_sideBar, BorderLayout.WEST);
		this.add(_mainContainer, BorderLayout.CENTER);
		
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if(src == _addBtn) {
			_clayout.show(_mainContainer, "Add");
			setBackGround(_addBtn);
			_helper.clearText(itemTF);
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
}
