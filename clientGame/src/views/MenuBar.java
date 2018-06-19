package views;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JMenuBar;

import controller.Actions;
import controller.Controller;

public class MenuBar extends JMenuBar{

	private static final long serialVersionUID = 1L;

	public MenuBar(Controller controller) {
		JButton btnA = new JButton("Change IP");
		btnA.addActionListener(controller);
		btnA.setActionCommand(Actions.CHANGE_IP.toString());
		btnA.setBackground(Color.WHITE);
		add(btnA);
		
	}
	
}
