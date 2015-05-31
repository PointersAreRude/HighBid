package View;

import java.awt.EventQueue;

/**
 * StatsMainTemp class, a TEMPORARY class to run the StatsFrameTemp Frame, not connected to the main HighBid software.
 * 
 * @author Abigail Smith
 * @version 5/30/15
 */
public class StatsMainTemp {

	/**
	 * A main that builds and runs the StatsFrameTemp class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StatsFrameTemp<Object>().setVisible(true);
			}
		});
	}
}
