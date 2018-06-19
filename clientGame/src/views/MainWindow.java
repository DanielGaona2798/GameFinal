package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.Actions;
import controller.Controller;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField txtSend;
	private JTextArea jlblText;
	
	private MenuBar menuBar;
	
	public MainWindow(Controller controller) {
		setTitle("Sockets");
		setLayout(new BorderLayout());
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		menuBar = new MenuBar(controller);
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JPanel pnlLow = new JPanel();
		pnlLow.setLayout(new GridLayout(2, 1));
		add(pnlLow, BorderLayout.SOUTH);
		
		txtSend = new JTextField();
		txtSend.setBorder(new TitledBorder("Your Message"));
		pnlLow.add(txtSend);
		
		jlblText = new JTextArea();
		jlblText.setBorder(new TitledBorder("Messages"));
		add(jlblText, BorderLayout.CENTER);
		
		JButton btnAccept = new JButton("Aceptar");
		btnAccept.addActionListener(controller);
		btnAccept.setActionCommand(Actions.SEND.toString());
		pnlLow.add(btnAccept);
		
	}
	
	public String getNumberwords(){
		return txtSend.getText();
	}
	public void clean(){
		txtSend.setText("");
	}
	public void setTextArea(String txt){
		jlblText.setText(txt);
	}
}
