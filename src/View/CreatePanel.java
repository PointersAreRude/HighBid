package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A form panel for creating auction
 * 
 * @author Long Nguyen
 * @version 5/20/2015
 */
public class CreatePanel extends JPanel implements ActionListener {

	/**
	 * Default serial.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * label for create panel's form
	 */
	private JLabel _label;
	
	/**
	 * back button
	 */
	private JButton _backBtn;
	
	public CreatePanel() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLayout(null);
		setComponents();
		addComponents();
	}

	/**
	 * Setting for each component in this panel.
	 */
	private void setComponents() {
		// Set label for create form
		_label = new JLabel("Auction Form");
		_label.setFont(new Font("Tahoma", 0, 50));
		_label.setBounds((int)(MainFrame.WIDTH / 2.7), 20,300,100);
		_label.setForeground(Color.BLUE);
		
		// Set back button
		_backBtn = new JButton("Back");
		_backBtn.setBounds((int)(20), MainFrame.HEIGHT - 100, 150, 60);
		_backBtn.setFont(new Font("Tahoma", 0, 36));
		_backBtn.addActionListener(this);
		
		// TODO: add JLabel and JTextField for the form.
	}

	/**
	 * Add the component to the panel.
	 */
	private void addComponents() {
		this.add(_label);
		this.add(_backBtn);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src == _backBtn) {
			int choice = JOptionPane.showConfirmDialog(null, "Your information in this form "
					+ "will not be saved.  Continue back?", "Warning", JOptionPane.OK_CANCEL_OPTION);
			if (choice == JOptionPane.OK_OPTION) {
				MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StartScreen");
			}
		} else {
			// TODO: add functionality for Save & continue button
		}
	}
	
}
