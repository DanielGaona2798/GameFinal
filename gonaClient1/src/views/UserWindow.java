package views;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.Actions;
import controller.Controller;

public class UserWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel lbIcon;
	private JTextField tfName, tfPassword;
	private JButton btnAccept, btnRegistry, btnClose;
	
	public UserWindow(Controller controller) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(310, 350);
		setExtendedState(MAXIMIZED_BOTH);
		this.getContentPane().setBackground(Color.decode("#ee1b24"));
		GridSystem gridSystem = new GridSystem(this);

		lbIcon = new  JLabel(new ImageIcon(("src/img/MARVEL.png")));
		add(lbIcon, gridSystem.insertComponent(0, 0, 12, 0.01));
		
		tfName = new  JTextField();
		tfName.setBorder(new TitledBorder("Input your Username"));
		add(tfName, gridSystem.insertComponent(2, 0, 12, 0.01));

		tfPassword = new  JTextField();
		tfPassword.setBorder(new TitledBorder("Input your password"));
		add(tfPassword, gridSystem.insertComponent(3, 0, 12, 0.01));

		btnAccept = new JButton("Loggin");
		btnAccept.setActionCommand(Actions.ACCEPT.toString());
		btnAccept.addActionListener(controller);
		btnAccept.setForeground(Color.WHITE);
		btnAccept.setBackground(Color.decode("#ee1b24"));
		btnAccept.setBorder(null);
		btnAccept.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
		add(btnAccept, gridSystem.insertComponent(4, 4, 4, 0.01));
		btnRegistry = new JButton("Do not have an account?, "
				+ "register here");
		btnRegistry.setActionCommand(Actions.REGISTRY.toString());
		btnRegistry.addActionListener(controller);
		btnRegistry.setForeground(Color.WHITE);
		btnRegistry.setBackground(Color.decode("#ee1b24"));
		btnRegistry.setBorder(null);
		btnRegistry.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
		add(btnRegistry, gridSystem.insertComponent(5, 4, 4, 0.01));
		
		btnClose = new JButton("Exit");
		btnClose.setActionCommand(Actions.EXIT.toString());
		btnClose.addActionListener(controller);
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.decode("#ee1b24"));
		btnClose.setFont(new Font("Comic Sans Ms", Font.BOLD, 12));
		btnClose.setBorder(null);
		add(btnClose, gridSystem.insertComponent(6, 10, 4, 0.01));
		setVisible(true);
	}
	
	public String getClient() throws IOException{
		return tfName.getText();
	}
//	public static void main(String[] args) {
//		new UserWindow(null);
//	}
}
