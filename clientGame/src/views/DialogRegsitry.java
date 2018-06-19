package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.Actions;
import controller.Controller;

public class DialogRegsitry extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField tfName, txtIP;
	private JButton btnAccept;

	public DialogRegsitry(Controller controller) {
		setLocationRelativeTo(null);
		setSize(250, 300);

		setLayout(new BorderLayout());
	
		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new GridLayout(2, 1));
		add(pnlCenter, BorderLayout.CENTER);
		tfName = new  JTextField();
		tfName.setBorder(new TitledBorder("Input your Username"));
		pnlCenter.add(tfName);

		txtIP = new  JTextField();
		txtIP.setBorder(new TitledBorder("IP"));
		pnlCenter.add(txtIP);

		btnAccept = new JButton("Registry");
		btnAccept.setActionCommand(Actions.ACCEPT.toString());
		btnAccept.addActionListener(controller);
		btnAccept.setForeground(Color.WHITE);
		btnAccept.setBackground(Color.BLUE);
		btnAccept.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
		add(btnAccept, BorderLayout.SOUTH);
	}	
	
	
	public String getName(){
		return tfName.getText();
	}


	public String getIP() {
		return txtIP.getText();
	}
}
