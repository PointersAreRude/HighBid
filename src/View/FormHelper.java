package View;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import Model.Donor;
import Model.Item;

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
	
	public void donorForm() {
		_arr[0].setText("John");
		_arr[1].setText("Doe");
		_arr[2].setText("JohnDoe@gmail.com");
		_arr[3].setText("5555 55th Ave S");
		_arr[4].setText("206 222 2222");
	}
	
	public void itemForm() {
		//JTextField[] itemTF = {_itemNameTF, _ItemDescriptionTF, _startPriceTF, _minIncrementTF, _qrTF};
		_arr[0].setText("Apple iphone 5");
		_arr[1].setText("A slim and stylish design makes the Apple iPhone 5 lightweight and easy to carry around.");
		_arr[2].setText("50");
		_arr[3].setText("5");
		_arr[4].setText("123456789");
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
	public Donor getDonor(JComboBox<String> _combo) {
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
