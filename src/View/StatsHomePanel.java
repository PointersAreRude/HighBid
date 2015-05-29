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

/**
 * @author Mark Ditianquin
 * @version 5/28
 * Home screen panel
 */
public class StatsHomePanel extends JPanel implements ActionListener {
	
	/**
	 * default serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Start screen's label
	 */
	private JLabel _label;
	
	/**
	 * View Donors btn
	 */
	private JButton _viewDonorsBtn;
	
	/**
	 * view items btn
	 */
	private JButton _viewItemsBtn;
	
	/**
	 * view bidders
	 */
	private JButton _viewBiddersBtn;
	
	/**
	 * back bttn
	 */
	private JButton _backBtn;
	
	/**
	 * File chooser
	 */
	private JFileChooser fc = new JFileChooser();
	
	private MainFrame myFrame;
	

	public StatsHomePanel(MainFrame frame) {
		myFrame = frame;
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
		_label = new JLabel("Statistics Screen");
		_label.setFont(new Font("Tahoma", 0, 70));
		_label.setBounds((int)((MainFrame.WIDTH / 2)-250), 200, 700,100);
		_label.setForeground(Color.BLUE);
		
		// Set donor button
		_viewDonorsBtn = new JButton("<html>View<br/>Donors</html>");
		//_ItemsAndDonorOptionBtn.setBounds((int)(MainFrame.WIDTH / 2) - 200, 270, 450, 150);
		_viewDonorsBtn.setLocation((MainFrame.WIDTH / 2) - 250, 400);
		_viewDonorsBtn.setSize(150, 150);
		_viewDonorsBtn.setFont(MainFrame.BUTTON_FONT);
		//_ItemsAndDonorOptionBtn.setContentAreaFilled(false);
		//_ItemsAndDonorOptionBtn.setOpaque(false);
		//_ItemsAndDonorOptionBtn.setBorderPainted(false);
		_viewDonorsBtn.addActionListener(this);
		
		// Set item button
		_viewItemsBtn = new JButton("<html>View<br/>Items</html>");
		//_registrationBtn.setBounds((int)(MainFrame.WIDTH / 2) - 100, 370, 250, 60);
		_viewItemsBtn.setLocation((MainFrame.WIDTH / 2) -75, 400);
		_viewItemsBtn.setSize(150, 150);
		_viewItemsBtn.setFont(MainFrame.BUTTON_FONT);
		//_registrationBtn.setContentAreaFilled(false);
		_viewItemsBtn.addActionListener(this);
		
		//set bidder button
		_viewBiddersBtn = new JButton("<html>View<br/>Bidders</html>");
		//_viewAuctionInfo.setBounds((int)(MainFrame.WIDTH / 2) - 150, 470, 350, 60);
		_viewBiddersBtn.setLocation((MainFrame.WIDTH/2) + 100, 400);
		_viewBiddersBtn.setSize(150, 150);
		_viewBiddersBtn.setFont(MainFrame.BUTTON_FONT);
		//_viewAuctionInfo.setContentAreaFilled(false);
		_viewBiddersBtn.addActionListener(this);
		
		//set back
		_backBtn = new JButton("Back");
		//_viewAuctionInfo.setBounds((int)(MainFrame.WIDTH / 2) - 150, 470, 350, 60);
		_backBtn.setLocation((MainFrame.WIDTH/2) - 550, 620);
		_backBtn.setSize(150, 100);
		_backBtn.setFont(MainFrame.BUTTON_FONT);
		//_viewAuctionInfo.setContentAreaFilled(false);
		_backBtn.addActionListener(this);
	}

	/**
	 * Add the component to the panel.
	 */
	private void addComponents() {
		this.add(_label);
		this.add(_viewDonorsBtn);
		this.add(_viewItemsBtn);
		this.add(_viewBiddersBtn);
		this.add(_backBtn);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if(src == _viewDonorsBtn) {
			myFrame.setUpStatsView(MainFrame._auction.getDonors());
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StatsView");
		} 
		else if(src == _viewItemsBtn){
			myFrame.setUpStatsView(MainFrame._auction.getItems());
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StatsView");
		}
		else if(src == _viewBiddersBtn){
			myFrame.setUpStatsView(MainFrame._auction.getBidders());
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StatsView");
		}
		else{
			//TODO: back button
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
		}
	}
	
//	public static void main(String[] args){
//		JFrame frame = new JFrame();
//		StatsHomePanel te = new StatsHomePanel();
//		frame.add(te);
//		frame.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
}