package View;

import java.awt.event.ActionEvent;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Donor;
import Model.Item;

/**
 * Remove tabbed Pane. Containing components that are used
 * to remove an item or a donor.
 * 
 * @author Long Nguyen
 * @version 6/3/2015
 */
@SuppressWarnings("serial")
public class OptionsRemovePanel extends OptionsContext {
	
	/**
	 * remove donor's panel
	 */
	private JPanel _removeDonorPanel;
	
	public OptionsRemovePanel() {
		setRemovePanel();
	}
	
	/**
	 * Set up remove tab for removing item/donor
	 */
	private void setRemovePanel() {
		setRemoveDonorPanel();
		setRemoveItemPanel();
		this.add("Remove Donor", _removeDonorPanel);
		this.add("Remove Item", _removeItemPanel);		
	}
	
	/**
	 * Set up remove donor tab
	 */
	private void setRemoveDonorPanel() {
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
		String[] colNamess = {"First Name", "Last Name", "Email", "Address", "Phone"};
		for(int i = 0; i < colNamess.length; i++) {
			_donorModel.addColumn(colNamess[i]);
		}
		OptionsMain._donorTable = new JTable(_donorModel);
		OptionsMain._donorTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(OptionsMain._donorTable);
		scrollPane.setBounds(120, 100, 800, 500);
		
		_removeDonorBtn = new JButton("Remove");
		_removeDonorBtn.setFont(OptionsMain._smallFont);
		_removeDonorBtn.setBounds(770, 630, 150, 50);
		_removeDonorBtn.addActionListener(this);
		
		JLabel info = new JLabel("Select a donor from the table below, and click remove.");
		info.setBounds(120,30,800,50);
		info.setFont(OptionsMain._smallFont);
		
		OptionsMain._removeDonorWarning = new JLabel();
		OptionsMain._removeDonorWarning.setFont(OptionsMain._smallFont);
		OptionsMain._removeDonorWarning.setForeground(OptionsMain._labelColor);
		OptionsMain._removeDonorWarning.setBounds(120,630,700,50);
		
		_removeDonorPanel.add(scrollPane);
		_removeDonorPanel.add(_removeDonorBtn);
		_removeDonorPanel.add(info);
		_removeDonorPanel.add(OptionsMain._removeDonorWarning);
	}
	
	/**
	 * Set up remove item tab
	 */
	private void setRemoveItemPanel() {
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
		
		OptionsMain._itemTable = new JTable(_itemModel);
		OptionsMain._itemTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(OptionsMain._itemTable);
		scrollPane.setBounds(120, 100, 800, 500);
		
		_removeItemBtn = new JButton("Remove");
		_removeItemBtn.setFont(OptionsMain._smallFont);
		_removeItemBtn.setBounds(770, 630, 150, 50);
		_removeItemBtn.addActionListener(this);
		
		JLabel info = new JLabel("Select an item from the table below, and click remove.");
		info.setBounds(120,30,800,50);
		info.setFont(OptionsMain._smallFont);
		
		OptionsMain._removeItemWarning = new JLabel();
		OptionsMain._removeItemWarning.setFont(OptionsMain._smallFont);
		OptionsMain._removeItemWarning.setForeground(OptionsMain._labelColor);
		OptionsMain._removeItemWarning.setBounds(120,630,700,50);
		
		_removeItemPanel.add(scrollPane);
		_removeItemPanel.add(_removeItemBtn);
		_removeItemPanel.add(info);
		_removeItemPanel.add(OptionsMain._removeItemWarning);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		JButton src = (JButton) e.getSource();
		
		if (src == _removeDonorBtn) {
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
		int index = OptionsMain._itemTable.getSelectedRow();
		if(index == -1) {
			OptionsMain._removeItemWarning.setText("Please select an item first.");
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
			OptionsMain._removeItemWarning.setText(item.getName() + " has been removed.");
		}
	}

	/**
	 * Removing donor operation. This method is called
	 * when remove button in remove donor tab is clicked.
	 */
	private void removeDonorAction() {
		int index = OptionsMain._donorTable.getSelectedRow();
		if(index == -1) {
			OptionsMain._removeDonorWarning.setText("Please select a donor first.");
		} else {
			Donor donor = MainFrame._auction.getDonors().get(index);
			int comboIndex = OptionsMain._helper.getDonorIndex(donor)+1;
			MainFrame._auction.deleteDonor(donor);
			OptionsMain._comboModel.removeElementAt(comboIndex);
			_donorModel.removeRow(index);
			OptionsMain._removeDonorWarning.setText(donor.getFirstName() + " " +donor.getLastName() + " has been removed.");
		}
	}
}
