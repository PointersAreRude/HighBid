package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Auction;

/**
 * Start screen JPanel
 * 
 * @author Long Nguyen
 * @version 5/21/2015
 */
public class StartScreen extends JPanel implements ActionListener {
	
	/**
	 * default serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Start screen's label
	 */
	private JLabel _label;
	
	/**
	 * create button
	 */
	private JButton _createBtn;
	
	/**
	 * open button
	 */
	private JButton _openBtn;
	

	public StartScreen() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(null);
		setComponents();
		addComponents();
	}

	/**
	 * Setting for each component in this panel.
	 */
	private void setComponents() {
		// Set HighBid Label
		_label = new JLabel("Welcome to HighBid");
		_label.setFont(new Font("Tahoma", 0, 70));
		_label.setBounds((int)(MainFrame.WIDTH / 4.5), 100,700,100);
		_label.setForeground(Color.BLUE);
		
		// Set create button
		_createBtn = new JButton("Create Auction");
		_createBtn.setBounds((int)(MainFrame.WIDTH / 4.5), 350, 270, 100);
		_createBtn.setFont(MainFrame.BUTTON_FONT);
		_createBtn.addActionListener(this);
		
		// Set open button
		_openBtn = new JButton("Open Auction");
		_openBtn.setBounds((int)(MainFrame.WIDTH / 1.9), 350, 270, 100);
		_openBtn.setFont(MainFrame.BUTTON_FONT);
		_openBtn.addActionListener(this);
	}

	/**
	 * Add the component to the panel.
	 */
	private void addComponents() {
		this.add(_label);
		this.add(_createBtn);
		this.add(_openBtn);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if(src == _createBtn) {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "CreatePanel");
		} else {
			// TODO: add open auction functionality
//			int returnVal = fc.showOpenDialog(StartScreen.this);
//			
//			if(returnVal == JFileChooser.APPROVE_OPTION ) {
//				File file = fc.getSelectedFile();
//			}
			if (new File("output/AuctionFile.csv").exists()) {
				try {
					MainFrame._auction = new Auction();
					MainFrame._auction.importFile("output/AuctionFile.csv");
				} catch (IOException e1) {
					System.out.println("Exception thrown");
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "File has been loaded. Please click OK to continue...");
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
			} else {
				JOptionPane.showMessageDialog(null, "There is no previous auction file, you will be returned to the previous screen.");
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StartScreen");
			}
			
			// TODO: add import file functionality here
			
		}
	}
}
