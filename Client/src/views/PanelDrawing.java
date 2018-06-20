package views;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import controllers.Controller;
import models.Game;

public class PanelDrawing extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Game> gameList;

	public PanelDrawing(Controller controller) {
		this.addKeyListener(controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(gameList != null) {
			for (Game game : gameList) {
				paintPlayer(g, game);
			}
		}
		if(gameList != null) {
			g.drawRect(70, 20, gameList.get(0).getx(), gameList.get(0).gety());
		}
	}

	private void paintPlayer(Graphics g, Game game) {
		g.drawString(game.getName(), (int)game.getPlayer().getX()+10, (int)game.getPlayer().getY()-10);
			g.fillOval((int)game.getPlayer().getX(), (int)game.getPlayer().getY(), (int)game.getPlayer().getWidth(),
					(int)game.getPlayer().getHeight());
			
	}

	public void setGame(ArrayList<Game> gamelist) {
		this.gameList = gamelist;
		revalidate();
	}
}
