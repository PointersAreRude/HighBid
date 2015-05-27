package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Model.Item;

public class StatsPanel extends JPanel implements ActionListener {

	JList<Item> scroller;
	
	JTextArea text;
	
	
	
	public StatsPanel() {
		scroller = new JList<Item>();
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}