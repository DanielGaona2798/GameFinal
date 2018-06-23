package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.Controller;
import models.Player;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private PanelDrawing drawing;
	
	public MainWindow(Controller controller) {
		this.addKeyListener(controller);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		drawing = new PanelDrawing(controller);
		add(drawing, BorderLayout.CENTER);
		setVisible(true);
	
		
	}

	public void setView(ArrayList<Player> players) {
		drawing.aiuda(players);
	}
}
