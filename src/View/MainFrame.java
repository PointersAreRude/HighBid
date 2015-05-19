package View;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	/**
	 * generated serial
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

	public MainFrame() {
		setTitle("HighBid");
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponents();
	}
	
	/**
	 * Adding components like JPanel here.
	 */
	private void addComponents() {
		this.add(new StartScreen());
		
	}
	
	
}
