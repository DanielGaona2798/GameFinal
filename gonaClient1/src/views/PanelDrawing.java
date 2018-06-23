package views;


import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.Controller;
import models.Player;

public class PanelDrawing extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Player> players;
	
	public PanelDrawing(Controller controller) {
		this.addKeyListener(controller);
		players = new ArrayList<>();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Player rectangle : players) {
			g.drawString(rectangle.getName(), rectangle.getRectangle().x-10, rectangle.getRectangle().y);
			g.fillOval(rectangle.getRectangle().x, rectangle.getRectangle().y, rectangle.getRectangle().width, rectangle.getRectangle().height);
		}
		
	}

	public void aiuda(ArrayList<Player> players2) {
		players = players2;
		repaint();
	}
}