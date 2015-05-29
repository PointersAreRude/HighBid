package View;

import java.awt.EventQueue;

public class HighBidMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//@Override
			/*Removed the @Override tag, it was giving me an error with my compiler.
			  If this is a problem for other people, we should talk about it (we probably have
			  different JDK environments or something)
			  Abigail - 5/20/2015 */
            public void run() {
            	new MainFrame<Object>().setVisible(true);  
            }
        });
	}
}
