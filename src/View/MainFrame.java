package View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Auction;

/**
 * Main frame
 * 
 * This class acts like a backbone for all the panels.
 * Here, We can change frame'size, text field dimension,
 * fonts, etc... These components are used in different
 * panels. This class is also where we add the panel
 * to a CardLayout panel to easily swap between panels.
 * 
 * @author Long Nguyen
 * @version 5/22/2015
 * @param <E>
 */
public class MainFrame<E> extends JFrame {
	/**
	 * the auction object
	 */
	protected static Auction _auction;
	
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
	
	/**
	 * Button's font
	 */
	protected static final Font BUTTON_FONT = new Font("Tahoma", 0, 36);
	
	/**
	 * Text field's dimension
	 */
	protected static final Dimension TF_DIMENSION = new Dimension(0,30);
	
	/**
	 * Label's font
	 */
	protected static final Font FORM_LABEL_FONT = new Font("Tahoma", 0, 30);
	
	/**
	 * Text field's text's font
	 */
	protected static final Font FORM_TF_FONT = new Font("Tahoma", 0, 15);
	

	private StatsPanel<E> statsView = new StatsPanel<E>();

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
		final HomeScreen home = new HomeScreen();
		final OptionsPanel option = new OptionsPanel();
		final StatsHomePanel stats = new StatsHomePanel(this);
		
		// Setting up CardLayout
		CONTAINER.setLayout(CLAYOUT);
		CONTAINER.add(start, "StartScreen");
		CONTAINER.add(create,"CreatePanel");
		CONTAINER.add(home, "HomeScreen");
		CONTAINER.add(option, "OptionsPanel");
		CONTAINER.add(stats, "StatsHomePanel");
		CONTAINER.add(statsView, "StatsView");
		
		CLAYOUT.show(CONTAINER, "StartScreen");
		this.add(CONTAINER);
	}
	
	public void setUpStatsView(ArrayList<E> list) {
		CONTAINER.remove(statsView);
		statsView = new StatsPanel<E>();
		CONTAINER.add(statsView, "StatsView");
		statsView.createList(list);
		
	}

}
