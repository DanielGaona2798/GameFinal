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

	public void move(int code) {
		switch (code) {
		case KeyEvent.VK_RIGHT:
			rigth();
			break;
		case KeyEvent.VK_LEFT:
			left();
			break;
		case KeyEvent.VK_UP:
			up();
			break;
		case KeyEvent.VK_DOWN:
			down();
			break;
		}
	}

	private void down() {
		rectangle.setLocation(rectangle.x, rectangle.y+20);
	}

	private void up() {
		rectangle.setLocation(rectangle.x, rectangle.y-20);		
	}

	private void left() {
		rectangle.setLocation(rectangle.x-20, rectangle.y);
	}

	private void rigth() {
		rectangle.setLocation(rectangle.x+20, rectangle.y);
	}
	
}
