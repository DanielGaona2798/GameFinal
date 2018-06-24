package views;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Controller;
import models.Player;

public class PanelDrawing extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Player> players;
	private Rectangle point;
	private ArrayList<Rectangle> rectangleWall;
	
	public PanelDrawing(Controller controller) {
		this.addKeyListener(controller);
		players = new ArrayList<>();
		point = new Rectangle();
		rectangleWall = new ArrayList<>();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Dimension size = getSize();
		ImageIcon background = new ImageIcon(getClass().getResource("/img/fondo.png"));	
		g.drawImage(background.getImage(), 0,0, size.width, size.height, null);
		setOpaque(false);
		for (Player rectangle : players) {
			g.drawString(rectangle.getName(), rectangle.getRectangle().x-10, rectangle.getRectangle().y);
			g.drawImage(new ImageIcon(getClass().getResource("/img/pj.png")).getImage(),rectangle.getRectangle().x, rectangle.getRectangle().y, rectangle.getRectangle().width, rectangle.getRectangle().height, this);
		}
		for (Iterator<Rectangle> shoot = rectangleWall.iterator(); shoot.hasNext();) {
			Rectangle rectangle = shoot.next();
			g.drawImage(new ImageIcon(getClass().getResource("/img/muro.png")).getImage(),rectangle.x, rectangle.y, rectangle.width, rectangle.height, this);
		}
		g.drawImage(new ImageIcon(getClass().getResource("/img/point.png")).getImage(),point.x, point.y, 50, 50,this);
		
	}

	public void aiuda(ArrayList<Player> players2, Rectangle rectangle, ArrayList<Rectangle> arrayList) {
		players = players2;
		point = rectangle;
		rectangleWall = arrayList;
		repaint();
	}
}