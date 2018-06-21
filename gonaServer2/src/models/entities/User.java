package models.entities;

import java.io.IOException;
import java.net.Socket;

public class User{

	private String name;
	private int pasword;
	private Socket socket;
	private int points;
	private int lives;

	public User(String name, int pasword, int points, int lives) throws IOException {
		this.name = name;
		this.setPasword(pasword);
		this.points = points;
		this.lives = lives;
	}

	public String getName() {
		return name;
	}
	
	public Socket getSocket() {
		return socket;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

	public int getPasword() {
		return pasword;
	}

	public void setPasword(int pasword) {
		this.pasword = pasword;
	}


	
	public int getPoints() {
		return points;
	}

	public int getLives() {
		return lives;
	}

	public void update(int lives, int points){
		this.lives = 3;
		this.points = 0;
		this.points = points;
		this.lives = lives;
	}
}