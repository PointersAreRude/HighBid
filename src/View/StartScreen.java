package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Start screen JPanel
 * 
 * @author Long Nguyen
 * @version 5/19/2015
 */
public class StartScreen extends JPanel{
	
	/**
	 * default serial
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel _label;
	
	private JButton _createBtn;
	
	private JButton _openBtn;

	public StartScreen() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(null);
		setComponents();
		addComponents();
	}

	private void setComponents() {
		// Set HighBid Label
		_label = new JLabel("Welcome to HighBid");
		_label.setFont(new Font("Tahoma", 0, 70));
		_label.setBounds((int)(MainFrame.WIDTH / 4.5), 100,700,100);
		_label.setForeground(Color.BLUE);
		
		// Set create button
		_createBtn = new JButton("Create Auction");
		_createBtn.setBounds((int)(MainFrame.WIDTH / 4.5), 350, 270, 100);
		_createBtn.setFont(new Font("Tahoma", 0, 36));
		
		// Set open button
		_openBtn = new JButton("Open Auction");
		_openBtn.setBounds((int)(MainFrame.WIDTH / 1.9), 350, 270, 100);
		_openBtn.setFont(new Font("Tahoma", 0, 36));
		
	}

	private void addComponents() {
		this.add(_label);
		this.add(_createBtn);
		this.add(_openBtn);
	}
}
