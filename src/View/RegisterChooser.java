package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Bidder;

/**
 * Displays a list of Bidders to edit or delete.
 * 
 * @author Robbie Nichols, Mark Ditianquin (borrowed his list scroller)
 * @version 5/29/2015
 */

@SuppressWarnings("serial")
public class RegisterChooser extends JPanel implements ActionListener {
	
	/** Button width*/
	private static int btnX = 150;
	
	/** Button height */
	private static int btnY = 85;
	
	/** Reference to the edit screen. Used for passing the selected Bidder. */
	private RegisterEditScreen _editScrn;
	
	/** The scrollable list display. */
	private JScrollPane _scroller;
	
	/** The list of Bidders. */
	private JList<Bidder> _list;
	
	/** The array of Bidders to pass to the list*/
	private Bidder[] _array;
	
	/** The currently selected Bidder. */
	private Bidder _selection;
	
	/** Indicates if the list is empty (ie no Bidders in the Auction). */
	private boolean _isEmpty;
	
	/** The back button. */
	private JButton _backBtn;
	
	/** The edit Bidder button. */
	private JButton _editBtn;
	
	/** The remove Bidder button. */
	private JButton _removeBtn;
	
	public RegisterChooser(RegisterEditScreen theEditScrn){
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(null);
		_editScrn = theEditScrn;
		_selection = null;
		_isEmpty = true;
		setComponents();
	}
	
	public void createList(){
		if(MainFrame._auction != null){
			ArrayList<Bidder> theList = MainFrame._auction.getBidders();
			int size = theList.size();
			
			if(size > 0)
				_isEmpty = false;
			
			_array = (Bidder[]) new Bidder[size];
			
			for (int i = 0; i < size; i++){
				_array[i] = theList.get(i);
			}
		}
		update();
	}
	
	public void setComponents(){
		JLabel title = new JLabel("Bidder Edit");
		title.setFont(new Font("Tahoma", 0, 70));
		title.setForeground(Color.BLUE);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(title);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//list
		if (_array != null) {
			_list = new JList<Bidder>(_array);
		} else {
			_list = new JList<Bidder>();
		}

		_list.setCellRenderer(new MyCellRenderer());
		_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		_list.setVisibleRowCount(-1);
		_scroller = new JScrollPane(_list);
		if (!_isEmpty) {
			_scroller.setBorder(BorderFactory.createTitledBorder("Select a Bidder"));
		} else {
			_scroller.setBorder(BorderFactory.createTitledBorder("No Bidders Available"));
		}
		
		_list.addListSelectionListener(new MyListSelectionListener(_list));
		_scroller.setPreferredSize(new Dimension(450, 110));
		add(_scroller, BorderLayout.CENTER);
		
		//buttons
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.LINE_AXIS));
		
		_backBtn = new JButton("Back");
		_backBtn.setSize(btnX, btnY);
		_backBtn.setFont(MainFrame.BUTTON_FONT);
		_backBtn.addActionListener(this);
		
		_editBtn = new JButton("Edit");
		_editBtn.setSize(btnX, btnY);
		_editBtn.setFont(MainFrame.BUTTON_FONT);
		_editBtn.addActionListener(this);
		
		_removeBtn = new JButton("<html>Remove</html>");
		_removeBtn.setSize(btnX, btnY);
		_removeBtn.setFont(MainFrame.BUTTON_FONT);
		_removeBtn.addActionListener(this);
		
 		btnPane.add(Box.createHorizontalGlue());
		btnPane.add(_backBtn);
		btnPane.add(Box.createRigidArea(new Dimension(MainFrame.WIDTH - 500, 0)));
		btnPane.add(_editBtn);
		btnPane.add(Box.createRigidArea(new Dimension(100, 0)));
		btnPane.add(_removeBtn);
		btnPane.add(Box.createRigidArea(new Dimension(100, 0)));

		this.add(btnPane, BorderLayout.PAGE_END);
	}
	
	/**
	 * Updates the list.
	 */
	private void update(){
		if (_array != null) {
			_list.setListData(_array);
		} else {
			_list = new JList<Bidder>();
		}
		
		if( _array.length < 1)
			_isEmpty = true;
		
		if (!_isEmpty) {
			_scroller.setBorder(BorderFactory.createTitledBorder("Select a Bidder"));
		} else {
			_scroller.setBorder(BorderFactory.createTitledBorder("No Bidders Available"));
		}
		
		this.repaint();
	}
	
	private class MyListSelectionListener implements ListSelectionListener {
		private JList<Bidder> selList;
		
		public MyListSelectionListener(JList<Bidder> aList){
			selList = aList;
		}
		public void valueChanged(ListSelectionEvent e) {
			_selection = selList.getSelectedValue();
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src == _backBtn) {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "RegPortal");
			
		} else if (src == _editBtn) {
			if(_selection != null){
				_editScrn.setBidder(_selection);
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderEdit");
			} else {
				JOptionPane.showMessageDialog(this, "Please select a Bidder.");
			}
			
		} else if (src == _removeBtn) {
			if(_selection != null){
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this bidder?", "Warning", JOptionPane.OK_CANCEL_OPTION);
				if (choice == JOptionPane.OK_OPTION) {
				MainFrame._auction.deleteBidder(_selection);
				createList();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please select a Bidder.");
			}
		}
	}

	private class MyCellRenderer extends JLabel implements ListCellRenderer<Bidder> {
		
		public Component getListCellRendererComponent(JList<? extends Bidder> list, 
				Bidder value, int index, boolean isSelected, boolean cellHasFocus) {
			
			String toDisplay = value.getFirstName() + " " + value.getLastName() + " : " + value.getid();
			
			setText(toDisplay);
			if (isSelected) {
	             setBackground(list.getSelectionBackground());
	             setForeground(list.getSelectionForeground());
	         } else {
	             setBackground(list.getBackground());
	             setForeground(list.getForeground());
	         }
	         setEnabled(list.isEnabled());
	         setFont(new Font(list.getFont().getName(), list.getFont().getStyle(), 20));
	         setOpaque(true);
	         return this;
		}
	}
	
}
