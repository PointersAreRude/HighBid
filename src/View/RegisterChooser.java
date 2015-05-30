package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Bidder;

@SuppressWarnings("serial")
public class RegisterChooser extends JPanel implements ActionListener {
	
	private static int btnX = 150;
	
	private static int btnY = 85;
	
	private RegisterEditScreen _editScrn;
	
//copied from Mark
	private JScrollPane _scroller;
	
	private JList<Bidder> _list;
	
	private Bidder[] _array;
	
	private Bidder _selection;
	
	private JLabel _label;
	
	private boolean _isEmpty;
	
	private JButton _backBtn;
	
	private JButton _editBtn;
	
	private JButton _removeBtn;
	
	
	public RegisterChooser(RegisterEditScreen theEditScrn){
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(null);
		_editScrn = theEditScrn;
		_selection = null;
		_label = new JLabel();
		_isEmpty = true;
//		createList();
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
		add(title);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		
		//list
		if (!_isEmpty) {
				_label.setText("Select a Bidder:");
		} else {
			_label.setText("No Bidders Available");
		}
		

		if (_array != null) {
			_list = new JList<Bidder>(_array);
		} else {
			_list = new JList<Bidder>();
		}

		listPane.add(_label, BorderLayout.NORTH);
		_list.setCellRenderer(new MyCellRenderer());
		_scroller = new JScrollPane(_list);
		_scroller.setPreferredSize(new Dimension(MainFrame.WIDTH -200, MainFrame.HEIGHT - 400));
		listPane.add(_scroller);		
		_list.addListSelectionListener(new MyListSelectionListener(_list));
		

		
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

		this.add(listPane, BorderLayout.CENTER);
		this.add(btnPane, BorderLayout.PAGE_END);
	}
	
	private void update(){
		if (_array != null) {
			_list.setListData(_array);
		} else {
			_list = new JList<Bidder>();
		}
		
		if (!_isEmpty) {
			_label.setText("Select a Bidder:");
		} else {
			_label.setText("No Bidders Available");
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
				MainFrame._auction.deleteBidder(_selection);
				createList();
			} else {
				JOptionPane.showMessageDialog(this, "Please select a Bidder.");
			}
		}
	}

	@SuppressWarnings("serial")
	private class MyCellRenderer extends JLabel implements ListCellRenderer<Bidder> {

		public Component getListCellRendererComponent(JList<? extends Bidder> list, Bidder value, int index, 
														boolean isSelected, boolean cellHasFocus) {
			String toDisplay = value.getFirstName() + " " + value.getLastName() + ":" + value.getid();
			
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
