package View;

import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import Model.Donor;
import Model.Item;

/**
 * Helper for options GUI
 * 
 * @author Long Nguyen
 * @version 6/4/2015
 */
public class FormHelper {

	private JTextField[] _arr;
	public FormHelper() {

	}
	
	public void whichForm(String formName, JTextField[] arr) {
		_arr = arr;
		if(formName.equals("Donor")) {
			donorForm();
		} else {
			itemForm();
		}
	}
	
	public int getRandom() {
		Random rand = new Random();
		int randomNum = rand.nextInt((2 - 0 + 1) + 0);
		return randomNum;
	}
	
	public void donorForm() {
		String[] first = {"John", "Average", "Jane"};
		String[] last = {"Doe", "Doe", "Joe"};
		String[] email = {"JohnDoe@gmail.com", "Average@gmail.com", "jane@gmail.com"};
		String[] address = {"5555 55th Ave S", "1111 11th Ave N", "2222 22th Ave S"};
		String[] phone = {"111 111 1111", "555 555 5555", "777 777 7777"};
		int random = getRandom();
		_arr[0].setText(first[random]);
		_arr[1].setText(last[random]);
		_arr[2].setText(email[random]);
		_arr[3].setText(address[random]);
		_arr[4].setText(phone[random]);
	}
	
	public void itemForm() {
		String[] itemName = {"Apple Iphone 5", "WW2 German Item", "Collectible Eagle Thimble"};
		String[] itemDes = {"A slim and stylish design makes the Apple iPhone 5 lightweight and easy to carry around.",
				"This is an original German Thors Hammer.",
				"Collectible Eagle Thimble From Donohue Mint Condition Metal"};
		String[] startingPrice = {"50", "275", "10"};
		String[] min = {"5", "10", "2"};
		String[] qr = {"123456789", "987654321", "112233445"};
		int ran = getRandom();
		_arr[0].setText(itemName[ran]);
		_arr[1].setText(itemDes[ran]);
		_arr[2].setText(startingPrice[ran]);
		_arr[3].setText(min[ran]);
		_arr[4].setText(qr[ran]);
	}
	
	/**
	 * Check if an item already exists
	 * based on item's qr code.
	 * 
	 * @param item - item object
	 * @return true if item already exists, false otherwise.
	 */
	boolean checkItem(Item item) {
		for(Item i : MainFrame._auction.getItems()) {
			if (i.getQr() == item.getQr()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the current index of a 
	 * certain donor in the list.
	 * 
	 * @param donor a donor object
	 * @return the index of the donor
	 */
	int getDonorIndex(Donor donor) {
		final List<Donor> list = MainFrame._auction.getDonors();
		for(Donor d : list) {
			if(d.equals(donor)) {
				return list.indexOf(donor);
			}
		}
		return -1;
	}

	/**
	 * Get the donor from auction's donor list.
	 * 
	 * @return a donor object
	 */
	public Donor getDonorWithCombo(JComboBox<String> _combo) {
		for(Donor donor: MainFrame._auction.getDonors()) {
			String donorName = donor.getFirstName() + " " + donor.getLastName() + " - " + donor.getEmail();
			if(_combo.getSelectedItem().toString().equals(donorName)) {
				return donor;
			}
		}
		return null;
	}
	
	/*
	 * Chage the return type to Image so Image can be resize.
	 * Mark Ditianquin
	 * 6/3/2015
	 */
	/**
	 * Get an image from filechooser.
	 * 
	 * @return buffered image
	 */
	Image uploadImage() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			Image img = null;
			try {
				img = ImageIO.read(f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return img;
		}
		return null;
	}

	/**
	 * Check if one, two, or all the text fields are empty
	 * 
	 * @param tf An array of text fields to be checked
	 * @return true if there's a field that is empty, false otherwise.
	 */
	boolean checkEmpty(JTextField[] tf) {
		for(int i = 0; i < tf.length; i++) {
			if(tf[i].getText().equals("")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Clear all the text from text fields.
	 * 
	 * @param arr An array of text fields
	 */
	void clearText(JTextField[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i].setText("");
		}
	}

	/**
	 * Check if the donor already exists in the auction's donor list.
	 * 
	 * @param donor A donor to be checked
	 * @return true if already exists, false otherwise.
	 */
	boolean checkDonor(Donor donor) {
		for(Donor d: MainFrame._auction.getDonors()) {
			String donorToAdd = donor.getFirstName() + donor.getLastName() + donor.getEmail();
			String donorInList = d.getFirstName() + d.getLastName() + d.getEmail();
			if(donorToAdd.equals(donorInList)) {
				return true;
			}
		}
		return false;
	}
	
}
