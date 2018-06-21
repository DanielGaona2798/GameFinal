package views;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Actions;
import controller.Controller;

public class PnCarlosArregleEstaJoda extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private JButton btnSend;
	
	public PnCarlosArregleEstaJoda(Controller controller) {
		 
		setLayout(new BorderLayout());
		btnSend = new JButton(new ImageIcon(("src/images/fight.png")));
		btnSend.setActionCommand(Actions.SEND.toString());
		btnSend.addActionListener(controller);
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(Color.decode("#3082e5"));
		add(btnSend, BorderLayout.CENTER);
	}
}