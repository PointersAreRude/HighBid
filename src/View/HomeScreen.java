package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Mark Ditianquin
 * @version 5/28
 * Home screen panel
 */
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
	
	/**
	 * view Acution info
	 */
	private JButton _viewAuctionInfo;
	
	/**
	 * save button
	 */
	private JButton _saveBtn;
	
	/**
	 * File chooser
	 */
	private JFileChooser fc = new JFileChooser();
	
	/**
	 * constractor for the homescreen
	 */
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
		
		//set save button
		_saveBtn = new JButton("Export..");
		//_saveBtn.setBounds((int)(MainFrame.WIDTH / 2) - 150, 470, 350, 60);
		_saveBtn.setLocation(80, 650);
		_saveBtn.setSize(200, 50);
		_saveBtn.setFont(MainFrame.BUTTON_FONT);
		//_viewAuctionInfo.setContentAreaFilled(false);
		_saveBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent theEvent)
            {
                final int result = fc.showSaveDialog(HomeScreen.this);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                	//fc.setCurrentDirectory(new java.io.File("."));
                	fc.setDialogTitle("Save Auction");
                	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                	fc.setAcceptAllFileFilterUsed(false);
                	
                	String file = fc.getSelectedFile().toString();	
                	
                    try
                    {
                    	MainFrame._auction.exportFile(file);
                		
                    }
                    catch (final IOException ex)
                    {
                        JOptionPane.showMessageDialog(null, 
                                                      "File can't be save " + fc.getSelectedFile());
                    }
                }
            }
        });
	}

	/**
	 * Add the component to the panel.
	 */
	private void addComponents() {
		this.add(_label);
		this.add(_ItemsAndDonorOptionBtn);
		this.add(_registrationBtn);
		this.add(_viewAuctionInfo);
		this.add(_saveBtn);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if(src == _ItemsAndDonorOptionBtn) {
			//TODO: add the Item panel
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "OptionsMain");
		} 
		else if(src == _registrationBtn){
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderChooser");
		}
		else if(src == _viewAuctionInfo){
			// TODO: add the viewAcution panel
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StatsHomePanel");
		}
		else{
			//TODO: implement the save button
		}
	}
	
/*	public static void main(String[] args){
		JFrame frame = new JFrame();
		HomeScreen te = new HomeScreen();
		frame.add(te);
		frame.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}*/
}
