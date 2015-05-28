package View;

import javax.swing.JFrame;

public class OptionFrameTemp extends JFrame {
	
	OptionsPanel _panel = new OptionsPanel();
	
	public OptionFrameTemp() {
		this.setSize(1200,800);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(_panel);
	}
}
