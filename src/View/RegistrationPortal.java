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
	private static final int btnX = 150;
	private static final int btnY = 85;

	private JLabel _label;
	private JButton _registerBtn;
	private JButton _editBtn;
	private JButton _removeBtn;
	private JButton _backBtn;

	public RegistrationPortal() {
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
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
		_registerBtn.setLocation((MainFrame.WIDTH / 2) - btnX * 2, 400);
		_registerBtn.setSize(btnX, btnY);
		_registerBtn.setFont(MainFrame.BUTTON_FONT);
		_registerBtn.addActionListener(this);

		_editBtn = new JButton("<html>Edit<br>Add ID</html>");
		_editBtn.setLocation((MainFrame.WIDTH / 2), 400);
		_editBtn.setSize(btnX, btnY);
		_editBtn.setFont(MainFrame.BUTTON_FONT);
		_editBtn.addActionListener(this);

		_removeBtn = new JButton("<html>Remove</html>");
		_removeBtn.setLocation((MainFrame.WIDTH / 2) + btnX * 2, 400);
		_removeBtn.setSize(btnX, btnY);
		_removeBtn.setFont(MainFrame.BUTTON_FONT);
		_removeBtn.addActionListener(this);

		_backBtn = new JButton("Back");
		_backBtn.setLocation((MainFrame.WIDTH / 2) - 550, 620);
		_backBtn.setSize(btnX, btnY);
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
		add(_removeBtn);
		add(_backBtn);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src == _registerBtn) {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderReg");
		} else if (src == _editBtn) {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "BidderEdit");
		} else if (src == _removeBtn) {
			// MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "removeBidr");
		} else {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "HomeScreen");
		}
	}
}
