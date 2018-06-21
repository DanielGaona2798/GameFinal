package managers;

import java.awt.Rectangle;
import java.util.ArrayList;

import models.entities.Wall;

public class WallManager {
	
	private ArrayList<Wall> wallList;
	private int x , y;
	private int posx, posy, posyC = 50;

	public WallManager() {
		wallList = new ArrayList<>();
	}

	public void generateRandomWall(int screenHeigth, int screenWidth) {
		for (int i = 0; i < screenWidth-screenHeigth; i++) {
			changeValues(screenHeigth, screenWidth);
			wallList.add(new Wall(new Rectangle(posx, posy, x, 50)));
		}
		generateClosed(screenHeigth, screenWidth);
	}
	
	private void generateClosed(int screenHeigth, int screenWidth) {
		for (int i = 0; i < 25; i++) {
			int increment = (int)(Math.random() * 400) + 20;
			changeValuesClosed(screenHeigth, screenWidth, increment);
			wallList.add(new Wall(new Rectangle(posx, posyC, x, 50)));
		}
	}

	private void changeValuesClosed(int screenHeigth, int screenWidth, int increment) {
		posx += x+increment;
		x = (int)(Math.random() * 50) + 50;
		if(posx>= screenWidth) {
			posyC += y+100;
			posx=0;
			x = 0;
		}
	}

	public ArrayList<Wall> getWallList() {
		return wallList;
	}
	
	private void changeValues(int screenHeigth, int screenWidth) {
		posx += x+50;
		x = (int)(Math.random() * 300) + 50;
		if(posx>= screenWidth) {
			posy += y+100;
			posx=0;
			x = 0;
		}
	}
	
	public void setWallList(String todo){
		wallList.clear();
		String[] rectagulo = todo.split("!");
		for (String string : rectagulo) {
			String[] coordenate = string.split("-");
			wallList.add(new Wall(new Rectangle((int)Double.parseDouble(coordenate[0]), (int)Double.parseDouble(coordenate[1]), (int)Double.parseDouble(coordenate[2]), (int)Double.parseDouble(coordenate[3]))));
		}
	}
	
	public String getAllWall(){
		String a = "";
		for (Wall wall : wallList) {
			a += wall.getValues() + "!";
		}
		return a;
	}
}