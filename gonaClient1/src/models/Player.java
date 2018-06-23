package models;

import java.awt.Rectangle;

public class Player {

	private String name;
	private Rectangle rectangle;
	
	public Player(String name, Rectangle rectangle) {
		this.name = name;
		this.rectangle = rectangle;
	}

	public String getName() {
		return name;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
	
	
}
