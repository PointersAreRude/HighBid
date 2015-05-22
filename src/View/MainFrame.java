package View;

import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main frame
 * 
 * @author Long Nguyen
 * @version 5/19/2015
 */
public class MainFrame extends JFrame {
	
	/**
	 * default serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * window's width
	 */
	protected static final int WIDTH = 1200;
	
	/**
	 * window's height
	 */
	protected static final int HEIGHT = 800;
	
	/**
	 * panel container
	 */
	protected static final JPanel CONTAINER = new JPanel();
	
	/**
	 * card layout
	 */
	protected static final CardLayout CLAYOUT = new CardLayout();

	public MainFrame() {
		setTitle("HighBid");
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setCardLayout();
        
        /*
         * Auction icon for main frame.
         * source: http://www.veryicon.com/icons/system/fresh-addon/auction-1.html
         */
        ImageIcon ImageIcon = new ImageIcon("input/images/Auction.png");
        Image image = ImageIcon.getImage();
        setIconImage(image);
	}
	
	/**
	 * Adding components like JPanel 
	 * here to the main container
	 * that is using CardLayout.
	 */
	private void setCardLayout() {
		// Instantiate new instance for each panel
		final StartScreen start = new StartScreen();
		final CreatePanel create = new CreatePanel();
		
		// Setting up CardLayout
		CONTAINER.setLayout(CLAYOUT);
		CONTAINER.add(start, "StartScreen");
		CONTAINER.add(create,"CreatePanel");
		CLAYOUT.show(CONTAINER, "StartScreen");
		this.add(CONTAINER);
	}

}
