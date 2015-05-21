package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	private JPanel _gbag;
	
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
		_gbag = new JPanel(new GridBagLayout());
		_gbag.setBorder(BorderFactory.createTitledBorder("Auction's Details"));
		_gbag.setBounds(300, 120, 580, 500);
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		
		JLabel facilitatorLabel = new JLabel("Facilitator name: ");
		facilitatorLabel.setFont(new Font("Tahoma", 0, 30));
		_gbag.add(facilitatorLabel, gc);
		
		gc.gridx++;
		JTextField facilitatorTF = new JTextField(20);
		facilitatorTF.setPreferredSize(new Dimension(0,30));
		facilitatorTF.setFont(new Font("Tahoma", 0, 15));
		_gbag.add(facilitatorTF, gc);
		
		gc.gridy++;
		gc.gridx=0;
		
	}

	/**
	 * Add the component to the panel.
	 */
	private void addComponents() {
		this.add(_label);
		this.add(_backBtn);
		this.add(_gbag);
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src == _backBtn) {
			MainFrame.CLAYOUT.show(MainFrame.CONTAINER, "StartScreen");
		} else {
			// TODO: add functionality for Save & continue button
		}
	}
	
}
