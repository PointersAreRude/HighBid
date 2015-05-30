package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Portal for bidder registration.
 * 
 * @author Robbie Nichols
 * @version 5/29/15
 */
public class RegistrationPortal extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private RegisterChooser _chooser;
	
	private JLabel _label;
	private JButton _registerBtn;
	private JButton _editBtn;
	private JButton _backBtn;

	public RegistrationPortal(RegisterChooser theChooser) {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		_chooser = theChooser;
		setLayout(null);
		setComponents();
		addComponents();
	}

	/**
	 * Setting for each component in this panel.
	 */
	private void setComponents() {
		
		_label = new JLabel("Bidder Registration");
		_label.setFont(new Font("Tahoma", 0, 70));
		_label.setBounds((int) ((MainFrame.WIDTH / 2) - 250), 200, 700, 100);
		_label.setForeground(Color.BLUE);

		_registerBtn = new JButton("Add");
		_registerBtn.setBounds(MainFrame.WIDTH / 2 - 175, MainFrame.HEIGHT / 2 -50 , 175, 80);
		_registerBtn.setFont(MainFrame.BUTTON_FONT);
		_registerBtn.addActionListener(this);
		
		_editBtn = new JButton("<html>Edit / Remove</html>");
		_editBtn.setBounds(MainFrame.WIDTH / 2 + 100, MainFrame.HEIGHT / 2 -50 , 175, 80);
		_editBtn.setFont(MainFrame.BUTTON_FONT);
		_editBtn.addActionListener(this);
		
		_backBtn = new JButton("Back");
		_backBtn.setBounds(20, MainFrame.HEIGHT - 100, 175, 60);
		_backBtn.setFont(MainFrame.BUTTON_FONT);
		_backBtn.addActionListener(this);

	}

	/**
	 * Add the component to the panel.
	 */
	private void addComponents() {
		add(_label);
		add(_registerBtn);
		add(_editBtn);
		add(_backBtn);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src == _registerBtn) {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderReg");
		} else if (src == _editBtn) {
			_chooser.createList();;
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderChooser");
		} else {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
		}
	}
}
