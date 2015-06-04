package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;


/**
 * Hold components that are used only in remove and add JTabbedPane
 * but not in the whole options panel.
 * 
 * @author Long Nguyen
 * @version 6/3/2015
 */
@SuppressWarnings("serial")
public class OptionsContext extends JTabbedPane implements ActionListener {

	/**
	 * remove donor button
	 */
	protected JButton _removeDonorBtn;
	
	/**
	 * Remove item's panel
	 */
	protected JPanel _removeItemPanel;
	
	/**
	 * Remove item button
	 */
	protected JButton _removeItemBtn;
	
/************ remove item panel's components *************/
	
	/**
	 * Item table's model
	 */
	protected DefaultTableModel _itemModel;
	
	/**
	 * Donor table's model
	 */
	protected DefaultTableModel _donorModel;
	
	public OptionsContext() {}


	public void actionPerformed(ActionEvent e) {}

}
