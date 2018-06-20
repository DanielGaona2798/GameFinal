package models;

import java.awt.Rectangle;

public class Game {
	private Rectangle player;
	private String name;
	private int x, y;

	public Game( String name) {
		super();
		player = new Rectangle(0, 0, 50, 50);
		this.name = name;
	}

	public Rectangle getPlayer() {
		return player;
	}

	public void setPlayer(Rectangle player) {
		this.player = player;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLocation(int x, int y) {
		player.setLocation(x, y);
	}

	public int getx() {
		return x;
	}
	
	public int gety() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
