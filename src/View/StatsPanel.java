package View;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeSet;

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
import Model.Donor;
import Model.Item;
import Model.Person;

/**
 * StatsPanel class, displays a list of objects that is sent in, and enables the user to click on the objects and see 
 * the information about each.
 * 
 * @author Abigail Smith
 * @version 5/30/15
 */
public class StatsPanel<E> extends JPanel implements ActionListener {

	/**
	 * JScrollPane to scroll through lists of objects.
	 */
	private JScrollPane myScroller;
	
	/**
	 * JList to represent a list of objects.
	 */
	private JList<E> myList;
	
	/**
	 * JTextArea to display information on selected objects.
	 */
	private JTextArea myText;
	
	/**
	 * JLabel to say what kind of objects are being displayed.
	 */
	private JLabel myLabel;
	
	/**
	 * JButton to go back to previous page.
	 */
	private JButton myBack;
	
	/**
	 * An array to hold a list of objects.
	 */
	private E[] myArray;
	
	/**
	 * GrigBagLayout to place all the components of this panel.
	 */
	private static GridBagLayout myBag;
	
	/**
	 * GridBadConstraints to go along with GridBagLayout.
	 */
	private static GridBagConstraints myC;
	
	/**
	 * Constructor for StatsPanel.
	 */
	@SuppressWarnings("unchecked")
	public StatsPanel() {

	}
	
	/**
	 * Instantiates myArray to hold the objects of the list passed in as a parameter.
	 * 
	 * @param list ArrayList<E> that holds a list of objects (E may become Item, Bidder, or Donor).
	 */
	public void createList(ArrayList<E> list) {

		if (!list.isEmpty() && list.get(0) instanceof Item) {
			myArray = (E[]) new Item[list.size()];
			
		} else if (!list.isEmpty() && list.get(0) instanceof Bidder) {
			myArray = (E[]) new Bidder[list.size()];
			
		} else if (!list.isEmpty() && list.get(0) instanceof Donor) {
			myArray = (E[]) new Donor[list.size()];
		} 
		
		for (int i = 0; i < list.size(); i++) {
			myArray[i] = list.get(i);
		}
		
		setUp();
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		JButton source = (JButton) event.getSource();
		
		if (source == myBack) {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StatsHomePanel");
		}
	}
	
	/**
	 * Sets up the panel.
	 */
	private void setUp() {
		myBag = new GridBagLayout();
		myC = new GridBagConstraints();
		
		setLayout(myBag);
		
		myC.fill = GridBagConstraints.BOTH;
		myC.weightx = 1.0;
		myC.weighty = 0.1;
		myC.gridheight = 1;
		myC.gridwidth = 5;
		
		myLabel = new JLabel();
		if (myArray != null) {
			if (myArray[0] instanceof Item) {
				myLabel.setText("Items");
			} else if (myArray[0] instanceof Bidder) {
				myLabel.setText("Bidders");
			} else if (myArray[0] instanceof Donor) {
				myLabel.setText("Donors");
			}
		} else {
			myLabel.setText("No List Available");
		}
		
		myLabel.setFont(MainFrame.FORM_LABEL_FONT);
		myBag.setConstraints(myLabel, myC);
		add(myLabel);
		
		//add(Box.createRigidArea(new Dimension(myLabel.getWidth(), myLabel.getHeight())));
		
		myC.gridwidth = GridBagConstraints.REMAINDER;
		myC.weighty = 0.0;
		myBack = new JButton("Back");
		myBack.addActionListener(this);
		myBack.setFont(MainFrame.FORM_TF_FONT);
		myBag.setConstraints(myBack, myC);
		add(myBack);
		
		myC.gridheight = 50;
		myC.weighty = 2.0;
		myC.gridwidth = 5;
		if (myArray != null) {
			myList = new JList<E>(myArray);
		} else {
			myList = new JList<E>();
		}
		myList.setCellRenderer(new MyCellRenderer());
		myScroller = new JScrollPane(myList);
		myBag.setConstraints(myScroller, myC);
		add(myScroller);
		
		myC.gridwidth = GridBagConstraints.REMAINDER;
		//c.gridx = 10;
		myText = new JTextArea();
		myText.setEditable(false);
		myText.setLineWrap(true);
		myText.setWrapStyleWord(true);
		myText.setFont(MainFrame.FORM_TF_FONT);
		myText.setText("Please select an option from the list.");
		myBag.setConstraints(myText, myC);
		add(myText);
		setSize(1200, 800);
		
		myList.addListSelectionListener(new MyListSelectionListener(myText, myList));
	}
	
	/**
	 * A listener class that handles the event of an object in the list being selected.
	 * 
	 * @author Abigail
	 * @version 5/30/2015
	 */
	private class MyListSelectionListener implements ListSelectionListener {
		
		/**
		 * JTextArea a text field to be modified.
		 */
		private JTextArea myText;
		
		/**
		 * JList a list that holds the list of objects.
		 */
		private JList<E> myList;
		
		/**
		 * Constructor for this class.
		 * 
		 * @param aText a text field to be modified.
		 * @param aList a list that holds the list of objects.
		 */
		public MyListSelectionListener (JTextArea aText, JList<E> aList) {
			myText = aText;
			myList = aList;
		}
		
		/**
		 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent event) {
			E selected = (E) myList.getSelectedValue();
			
			String text = "";
			if (selected instanceof Item) {
				Item item = (Item) selected;
				text += item.getName() + "\n\nDonor: ";
				
				Donor donor = item.getDonor();
				if (donor != null) {
					text += donor.getFirstName() + " " + donor.getLastName() + "\n\n";
				} else {
					text += "No Donor\n\n";
				}
				
				text += "Description: " + item.getDescription() + "\n\nStarting Price: $" + item.getStartingPrice()
						+ "\nIncrement Amount: $" + item.getMinIncrement() + "\n\nQR code: " + item.getQr()
						+ "\n\nBid History";
				
				ArrayList<Bidder> bidders = (ArrayList<Bidder>) item.getBidderList();
				for (int i = 0; i < bidders.size(); i++) {
					Bidder bddr = bidders.get(i);
					text += "\n        " + (i + 1) + ". " + bddr.getFirstName() + " " + bddr.getLastName() + " : " + bddr.getid();
				}
				
				
				JLabel imageLabel = new JLabel(item.getImage());
				myBag.setConstraints(imageLabel, myC);
				
			} else if (selected instanceof Person) {
				Person person = (Person) selected;
				text += person.getFirstName() + " " + person.getLastName() + "\n\nContact Information\n        Address: "
						+ person.getAddress() + "\n        Phone #: " + person.getPhone() + "\n        Email: " + person.getEmail();
				
				if (person instanceof Bidder) {
					Bidder bidder = (Bidder) person;
					text += "\n\nNickName: " + bidder.getNickName() + "\n\nBidder's ID #: " + bidder.getid()
						+ "\n\nItems Bid on";
				
					TreeSet<Item> itms = (TreeSet<Item>) bidder.getItemsBidOn();
					for (Item item : itms) {
						text += "\n        " + item.getName() + " : " + item.getQr();
					}
						
					text += "\n\nItems Won";
					itms = (TreeSet<Item>) bidder.getItemsWon();
					for (Item item : itms) {
						text += "\n        " + item.getName() + " : " + item.getQr();
					}
				} else if (person instanceof Donor) {
					Donor donor = (Donor) person;
					text += "\n\nItems Donated";
					ArrayList<Item> items = (ArrayList<Item>) donor.getItemList();
					for (Item itm : items) {
						text += "\n        " + itm.getName() + " : " + itm.getQr();
					}
				}
			}
			myText.setText(text);
			
		}
		
	}
	
	/**
	 * Renders the cells inside the main class's JList, defines how the cells in the list will look like.
	 * 
	 * @author Abigail
	 * @version 5/30/2015
	 */
	private class MyCellRenderer extends JLabel implements ListCellRenderer<E> {

		/**
		 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
		 */
		public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, 
														boolean isSelected, boolean cellHasFocus) {
			String toDisplay = "";
			if (value instanceof Item) {
				Item theValue = (Item) value;
				toDisplay = theValue.getName() + " : " + theValue.getQr();
			} else if (value instanceof Bidder) {
				Bidder theValue = (Bidder) value;
				toDisplay = theValue.getFirstName() + " " + theValue.getLastName() + ":" + theValue.getid();
			} else if (value instanceof Donor) {
				Donor theValue = (Donor) value;
				toDisplay = theValue.getFirstName() + " " + theValue.getLastName();
			}
			
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
