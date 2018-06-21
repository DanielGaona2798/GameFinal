package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.Actions;
import controller.Controller;

public class DialogRegsitry extends JDialog{

	private static final long serialVersionUID = 1L;
	private JLabel lbIcon;
	private JTextField tfName, tfPassword;
	private JButton btnAccept;

	public DialogRegsitry(Controller controller) {
		setLocationRelativeTo(null);
		setSize(250, 300);
		this.getContentPane().setBackground(Color.decode("#e64134"));
		this.setUndecorated(true);

		GridSystem gridSystem = new GridSystem(this);

		lbIcon = new  JLabel(new ImageIcon(("src/images/ironman.png")));
		add(lbIcon, gridSystem.insertComponent(1, 0, 12, 0.01));

		tfName = new  JTextField();
		tfName.setBorder(new TitledBorder("Input your Username"));
		add(tfName, gridSystem.insertComponent(2, 0, 12, 0.01));

		tfPassword = new  JTextField();
		tfPassword.setBorder(new TitledBorder("Input your password"));
		add(tfPassword, gridSystem.insertComponent(3, 0, 12, 0.01));

		btnAccept = new JButton("Registry");
		btnAccept.setActionCommand(Actions.REGISTRY_ACEEPT.toString());
		btnAccept.addActionListener(controller);
		btnAccept.setForeground(Color.WHITE);
		btnAccept.setBackground(Color.decode("#e64134"));
		btnAccept.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
		btnAccept.setBorder(null);
		add(btnAccept, gridSystem.insertComponent(4, 4, 4, 0.01));
	}	


	public String getName(){
		return tfName.getText();
	}
	public String getPasword(){
		return tfPassword.getText();
	}
}
