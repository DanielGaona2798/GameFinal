package models;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;


public class Game {

	private static ArrayList<Player> playerList;
	private static Rectangle finalPoint;
	private ArrayList<Wall> walls;

	public Game() {
		playerList = new ArrayList<>();
		finalPoint = new Rectangle(1250, 350, 50, 50);
		walls = new ArrayList<>();
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void createMap(){
		for (int i = 40; i < 1250; i+= 120) {
			walls.add(new Wall(new Rectangle(i, 0, 30, 30)));
		}
		for (Iterator<Wall> shoot = walls.iterator(); shoot.hasNext();) {
			Wall s = shoot.next();
			s.start();
		}
	}

	public Rectangle getFinalPoint() {
		return finalPoint;
	}

	public ArrayList<Wall> getRectangleWall() {
		return walls;
	}

	public void addPLayer(Player player){
		playerList.add(player);
	}
	
}
