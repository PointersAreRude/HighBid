package View;

import java.awt.EventQueue;

public class HighBidMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	new MainFrame().setVisible(true);  
            }
        });
	}

}
