package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import constants.ConstatntsUI;
import controller.Actions;
import controller.Controller;
import models.Player;

public class LobbyWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JButton start;
	private DefaultTableModel model;
	private JTable tableMan;
	private JScrollPane scroll;
	private JButton close;
	
	public LobbyWindow(Controller controller) {
		setLayout(new BorderLayout());
		setSize(700, 700);
		setTitle(ConstatntsUI.GAMETITLE);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"PLayers"});
		
		tableMan = new JTable(model);
		tableMan.setRowHeight(25);
		tableMan.setFont(new Font("Arial", Font.BOLD, 15));
		scroll = new JScrollPane(tableMan);

		add(scroll, BorderLayout.CENTER);
		
		JPanel pnlLow = new JPanel();
		pnlLow.setLayout(new GridLayout(1, 2));
		start = new JButton("START");
		start.addActionListener(controller);
		start.setBackground(Color.WHITE);
		start.setForeground(Color.BLUE);
		start.setActionCommand(Actions.START.toString());
		pnlLow.add(start);
		
		close = new JButton("CLOSE");
		close.addActionListener(controller);
		close.setBackground(Color.WHITE);
		close.setForeground(Color.BLUE);
		close.setActionCommand(Actions.CLOSE.toString());
		pnlLow.add(close);
		
		add(pnlLow, BorderLayout.SOUTH);
	}
	
	public void refreshTable(ArrayList<Player> list) {
		for (Player person : list) {
			model.addRow(new Object[] {person.getName()});
			tableMan.setModel(model);
		}
	}
}