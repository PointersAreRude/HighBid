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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
	
	private CardLayout _clayout;
	
	private JButton _addDonorBtn;
	
	private JButton _addItemBtn;
	
	private JButton _removeBtn;
	
	private JButton _backBtn;
	
	
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
		// TODO Auto-generated method stub
		
	}

	private void setDonorPanel() {
		// TODO Auto-generated method stub
		
	}

	private void setItemPanel() {
		// TODO Auto-generated method stub
		
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
		gc.gridy += 3;
		_leftPanel.add(_backBtn, gc);
      
	}

	private void addComponents() {
		// TODO Auto-generated method stub		
		_itemPanel = new JPanel();
		_itemPanel.setBackground(Color.black);
		_donorPanel = new JPanel();
		_donorPanel.setBackground(Color.CYAN);
		_removePanel = new JPanel();
		_removePanel.setBackground(Color.RED);
		HomeScreen home = new HomeScreen();
		
		_clayout = new CardLayout();
		_mainContainer = new JPanel();
		_mainContainer.setLayout(_clayout);
		_mainContainer.add(_itemPanel, "AddItem");
		_mainContainer.add(_donorPanel, "AddDonor");
		_mainContainer.add(_removePanel, "Remove");
		_mainContainer.add(home, "Home");
		_clayout.show(_mainContainer, "AddItem");
		
		this.add(_leftPanel, BorderLayout.WEST);
		this.add(_mainContainer, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton src = (JButton) e.getSource();
		if(src == _addDonorBtn) {
			_clayout.show(_mainContainer, "AddDonor");
		} else if (src == _addItemBtn) {
			_clayout.show(_mainContainer, "AddItem");
		} else if (src == _removeBtn) {
			_clayout.show(_mainContainer, "Remove");
		} else {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
		}
	}

}
