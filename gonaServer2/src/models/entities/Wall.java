package models.entities;

import java.awt.Rectangle;

public class Wall {
	private Rectangle wall;
	public Wall(Rectangle wall) {
		super();
		this.wall = wall;
	}

	public Rectangle getWall() {
		return wall;
	}

	public void setWall(Rectangle wall) {
		this.wall = wall;
	}
	
	public String getValues(){
		return wall.getX() + "-" + wall.getY() + "-" + wall.getWidth() + "-" + wall.getHeight();
	}
}