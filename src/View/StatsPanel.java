package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.Box;
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

public class StatsPanel<E> extends JPanel {

	private JScrollPane myScroller;
	
	private JList<E> myList;
	
	private JTextArea myText;
	
	private JLabel myLabel;
	
	private JButton myBack;
	
	private E[] myArray;
	
	@SuppressWarnings("unchecked")
	public StatsPanel(ArrayList<E> list) {
		
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
	
	public void setUp() {
		GridBagLayout bag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(bag);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.1;
		c.gridheight = 1;
		c.gridwidth = 5;
		
		myLabel = new JLabel();
		if (myArray[0] != null && myArray[0] instanceof Item) {
			myLabel.setText("Items");
		} else if (myArray[0] != null && myArray[0] instanceof Bidder) {
			myLabel.setText("Bidders");
		} else if (myArray[0] != null && myArray[0] instanceof Donor) {
			myLabel.setText("Donors");
		}
		
		myLabel.setFont(MainFrame.FORM_LABEL_FONT);
		bag.setConstraints(myLabel, c);
		add(myLabel);
		
		//add(Box.createRigidArea(new Dimension(myLabel.getWidth(), myLabel.getHeight())));
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 0.0;
		myBack = new JButton("Back");
		myBack.setFont(MainFrame.FORM_TF_FONT);
		bag.setConstraints(myBack, c);
		add(myBack);
		
		c.gridheight = 50;
		c.weighty = 2.0;
		c.gridwidth = 5;
		myList = new JList<E>(myArray);
		myList.setCellRenderer(new MyCellRenderer());
		myScroller = new JScrollPane(myList);
		bag.setConstraints(myScroller, c);
		add(myScroller);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		//c.gridx = 10;
		myText = new JTextArea();
		myText.setEditable(false);
		myText.setLineWrap(true);
		myText.setWrapStyleWord(true);
		myText.setFont(MainFrame.FORM_TF_FONT);
		myText.setText("Please select an option from the list.");
		bag.setConstraints(myText, c);
		add(myText);
		setSize(1200, 800);
		
		myList.addListSelectionListener(new MyListSelectionListener(myText, myList));
	}
	
//	public void paintComponent(Graphics aGraphics) {
//		super.paintComponent(aGraphics);
//        Graphics2D g2d = (Graphics2D) aGraphics;
//        
//        Shape rectangle = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
//        g2d.setPaint(Color.RED);
//        g2d.setStroke(new BasicStroke(10));
//        g2d.draw(rectangle);
//	}
	
	private class MyListSelectionListener implements ListSelectionListener {
		
		private JTextArea myText;
		
		private JList<E> myList;
		
		public MyListSelectionListener (JTextArea aText, JList<E> aList) {
			myText = aText;
			myList = aList;
		}
		
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
	
	private class MyCellRenderer extends JLabel implements ListCellRenderer<E> {

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