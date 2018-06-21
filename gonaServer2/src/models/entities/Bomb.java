package models.entities;

import java.awt.Rectangle;

public class Bomb {
	
	private Rectangle bomb;
	
	public Bomb(Rectangle bomb) {
		super();
		this.bomb = bomb;
	}

	public Rectangle getBomb() {
		return bomb;
	}
	
	public String getValues(){
		return bomb.getX() + "-" + bomb.getY() + "-" + bomb.getWidth() + "-" + bomb.getHeight();
	}
}