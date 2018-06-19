package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import models.ClientConnections;

public class MainWindow extends JFrame{

	private static final Dimension DIMENSION = new Dimension(500, 500);

	private static final long serialVersionUID = 1L;
	
	private JList<String> connectionList;
	private DefaultListModel<String> listModel;
	
	public MainWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(DIMENSION);
	
		listModel = new DefaultListModel<>();
		
		connectionList = new JList<>(listModel);
		add(new JScrollPane(connectionList), BorderLayout.CENTER);
		
		setVisible(true);
	}

	public void refreshList(ArrayList<ClientConnections> list) throws IOException {
		listModel.clear();
		for (ClientConnections clientConnections : list) {
			listModel.addElement(clientConnections.getInput().readUTF());
		}
		repaint();
	}
}
