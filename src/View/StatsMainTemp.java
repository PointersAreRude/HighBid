package View;

import java.awt.EventQueue;

public class StatsMainTemp {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StatsFrameTemp<Object>().setVisible(true);
			}
		});
	}
}
