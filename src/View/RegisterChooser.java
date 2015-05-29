package View;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
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
	
	private JTextArea _text;
	
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
		setUp();
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
	}
	
	public void setUp(){
		GridBagLayout bag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(bag);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.1;
		c.gridheight = 1;
		c.gridwidth = 5;
		
		if (_isEmpty) {
				_label.setText("Bidders");
		} else {
			_label.setText("No Bidders Available");
		}
		
		_label.setFont(MainFrame.FORM_LABEL_FONT);
		bag.setConstraints(_label, c);
		add(_label);
		
		_backBtn = new JButton("Back");
		_backBtn.setLocation((MainFrame.WIDTH / 2) - 550, 620);
		_backBtn.setSize(btnX, btnY);
		_backBtn.setFont(MainFrame.BUTTON_FONT);
		_backBtn.addActionListener(this);
		add(_backBtn);
		
		_editBtn = new JButton("Edit");
		_editBtn.setLocation((MainFrame.WIDTH / 2), 400);
		_editBtn.setSize(btnX, btnY);
		_editBtn.setFont(MainFrame.BUTTON_FONT);
		_editBtn.addActionListener(this);
		add(_editBtn);
		
		_removeBtn = new JButton("<html>Remove</html>");
		_removeBtn.setLocation((MainFrame.WIDTH / 2) + btnX * 2, 400);
		_removeBtn.setSize(btnX, btnY);
		_removeBtn.setFont(MainFrame.BUTTON_FONT);
		_removeBtn.addActionListener(this);
		add(_removeBtn);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 0.0;
		
		c.gridheight = 50;
		c.weighty = 2.0;
		c.gridwidth = 5;
		
		if (_array != null) {
			_list = new JList<Bidder>(_array);
		} else {
			_list = new JList<Bidder>();
		}
		
		_list.setCellRenderer(new MyCellRenderer());
		_scroller = new JScrollPane(_list);
		bag.setConstraints(_scroller, c);
		add(_scroller);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		//c.gridx = 10;
		_text = new JTextArea();
		_text.setEditable(false);
		_text.setLineWrap(true);
		_text.setWrapStyleWord(true);
		_text.setFont(MainFrame.FORM_TF_FONT);
		_text.setText("Please select an option from the list.");
		bag.setConstraints(_text, c);
		add(_text);
		
		_list.addListSelectionListener(new MyListSelectionListener(_list));
		
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
				//popup warning to make a selection
			}
			
		} else if (src == _removeBtn) {
			//popup with confirmation?
			 MainFrame._auction.deleteBidder(_selection);
			 setUp();
		} else {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
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
