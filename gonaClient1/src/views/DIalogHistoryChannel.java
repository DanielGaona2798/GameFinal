package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import controller.Actions;
import controller.Controller;

public class DIalogHistoryChannel extends JDialog{

	private static final long serialVersionUID = 1L;
	private JButton btnIn ;
	
	public DIalogHistoryChannel(Controller controller) {
		setUndecorated(true);
		setSize(800,600);
		setLayout(new BorderLayout());
		JTextPane pane = new JTextPane();
		pane.setForeground(Color.BLACK);
		pane.setBackground(Color.decode("#00BFFF"));
		pane.insertIcon(new ImageIcon(getClass().getResource("/img/fondo.jpg")));
		pane.setBorder(null);
		pane.setEditable(false);
		pane.setFont(new Font("Arial", Font.BOLD, 30));
		pane.setText("Nuestros heroes aparecen misticamente por el conjuro de una hada vengativa en un calabozo. Los heroes al ver su situacion resuelven\n"
				+ "que su unica ruta de escape es a traves de un pasillo lleno de obstaculos, además de que el calabozo que fue creado por la hada\n"
				+ "tiene un hechizo que asesina a todo aquel que siga en su interior una vez se cruce la unica puerta de salida."+
				"Link debes llegar a tu objetivo antes que los demas Jugadores\n "+ "Utiliza las Flechas para moverte Y evitar Los Bloques");
		
		add(pane, BorderLayout.CENTER);
		
		btnIn = new JButton("Saltar");
		btnIn.setBackground(Color.WHITE);
		btnIn.setForeground(Color.DARK_GRAY);
		btnIn.addActionListener(controller);
		btnIn.setActionCommand(Actions.INIT.toString());
		add(btnIn, BorderLayout.SOUTH);
	}
	
}
