package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen extends JPanel implements ActionListener {
	
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
	private JButton _ItemsAndDonorOptionBtn;
	
	/**
	 * open button
	 */
	private JButton _registrationBtn;
	
	private JButton _viewAuctionInfo;
	
	/**
	 * File chooser
	 */
	private JFileChooser fc = new JFileChooser();
	

	public HomeScreen() {
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
		_label = new JLabel("HighBid Home Page");
		_label.setFont(new Font("Tahoma", 0, 70));
		_label.setBounds((int)((MainFrame.WIDTH / 2)-300), 90,700,100);
		_label.setForeground(Color.BLUE);
		
		// Set edit button
		_ItemsAndDonorOptionBtn = new JButton("Items And Donors Option");
		_ItemsAndDonorOptionBtn.setBounds((int)(MainFrame.WIDTH / 2) - 200, 270, 450, 60);
		_ItemsAndDonorOptionBtn.setFont(MainFrame.BUTTON_FONT);
		//_ItemsAndDonorOptionBtn.setContentAreaFilled(false);
		//_ItemsAndDonorOptionBtn.setOpaque(false);
		//_ItemsAndDonorOptionBtn.setBorderPainted(false);
		_ItemsAndDonorOptionBtn.addActionListener(this);
		
		// Set registration button
		_registrationBtn = new JButton("Registration");
		_registrationBtn.setBounds((int)(MainFrame.WIDTH / 2) - 100, 370, 250, 60);
		_registrationBtn.setFont(MainFrame.BUTTON_FONT);
		//_registrationBtn.setContentAreaFilled(false);
		_registrationBtn.addActionListener(this);
		
		//set stats button
		_viewAuctionInfo = new JButton("View Auction Info");
		_viewAuctionInfo.setBounds((int)(MainFrame.WIDTH / 2) - 150, 470, 350, 60);
		_viewAuctionInfo.setFont(MainFrame.BUTTON_FONT);
		//_viewAuctionInfo.setContentAreaFilled(false);
		_viewAuctionInfo.addActionListener(this);
	}

	/**
	 * Add the component to the panel.
	 */
	private void addComponents() {
		this.add(_label);
		this.add(_ItemsAndDonorOptionBtn);
		this.add(_registrationBtn);
		this.add(_viewAuctionInfo);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if(src == _ItemsAndDonorOptionBtn) {
			//TODO: add the Item panel
		} 
		else if(src == _registrationBtn){
			//TODO: add the registration panel
		}
		else {
			// TODO: add the viewAcution panel

		}
	}
	
//	public static void main(String[] args){
//		JFrame frame = new JFrame();
//		HomeScreen te = new HomeScreen();
//		frame.add(te);
//		frame.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
//		
//		frame.setVisible(true);
//	}
}
