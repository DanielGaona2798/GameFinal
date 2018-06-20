package views;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import constants.ConstantsUI;
import controllers.Controller;
import models.Game;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private PanelDrawing panelDrawing;
	private Controller controller;

	public MainWindow(Controller controller) {
		this.addKeyListener(controller);
		this.controller = controller;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setSize(ConstantsUI.DIMENSION_DIALOG_INIT);
		
		setVisible(true);
	}

	public void initPanel() {
		panelDrawing = new PanelDrawing(controller);
		add(panelDrawing, BorderLayout.CENTER);
	}
	
	public void setGame(ArrayList<Game> gamelist) {
		panelDrawing.setGame(gamelist);
	}

	public String getNamePlayer() {
		return JOptionPane.showInputDialog("your name");
	}

	public int getPort() {
		return Integer.parseInt(JOptionPane.showInputDialog("port"));
	}

	public String getIp() {
		return JOptionPane.showInputDialog("IP");
	}
	
}
