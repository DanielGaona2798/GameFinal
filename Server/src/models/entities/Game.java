package models.entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Game extends MyThread{
	private Rectangle player;
	private int x, y;
	private String name;
	private boolean state;

	public Game(int sleep, int x, int y,  String name) {
		super(sleep);
		this.name = name;
		this.x = x;
		this.y = y;
		player = new Rectangle(0, 0, 50, 50);
	}

	@Override
	public void executeTask() {
		validate();
	}

	public void moveleft() {
		player.setLocation((int)player.getX()-20, (int)player.getY());
	}

	public void moveRigth() {
		player.setLocation((int)player.getX()+20, (int)player.getY());
		System.out.println("posisciones del man  derechaaa " + player.getX() + "  " + player.getY());
	}

	public void moveUp() {
		player.setLocation((int)player.getX(), (int)player.getY()-20);
		System.out.println("posisciones del man  arribaaaa " + player.getX() + "  " + player.getY());
	}

	public void moveDown() {
		player.setLocation((int)player.getX(), (int)player.getY()+20);
	}

	public Rectangle getPlayer() {
		return player;
	}

	public void validate() {
		if(player.getX() == x || player.getY() == y || player.getX() == x && player.getY() == y) {
			state = true;
		}else {
			state = false;
		}
	}
	
	public void setDimensions(int width, int height) {
		this.x = width;
		this.y = height;
	}

	public void manageMovement(int action) {
		switch (action) {
		case KeyEvent.VK_W:
			System.out.println(  KeyEvent.VK_UP + "   arribaaaa    " + action);
			moveUp();
			break;
		case KeyEvent.VK_S:
			moveDown();
			break;
		case KeyEvent.VK_A:
			moveleft();
			break;
		case KeyEvent.VK_D:
			moveRigth();
			break;
		}
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public String getName() {
		return name;
	}

	public void setPosition(int x, int y) {
		player.setLocation(x, y);
	}

	public void enqueueActions(int keyCode) {
		System.out.println(keyCode + "codigo");
		manageMovement(keyCode);
	}
	
	public boolean getState() {
		return state;
	}
}
