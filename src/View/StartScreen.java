package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	/**
	 * File chooser
	 */
	private JFileChooser fc = new JFileChooser();
	

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
			int returnVal = fc.showOpenDialog(StartScreen.this);
			
			if(returnVal == JFileChooser.APPROVE_OPTION ) {
				File file = fc.getSelectedFile();
			}
		}
	}
}
