package models;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;


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

	

	public void down() {
		rectangle.setLocation(rectangle.x, rectangle.y+20);
	}

	public void up() {
		rectangle.setLocation(rectangle.x, rectangle.y-20);		
	}

	public void left() {
		rectangle.setLocation(rectangle.x-20, rectangle.y);
	}

	public void rigth() {
		rectangle.setLocation(rectangle.x+20, rectangle.y);
	}
	
}
