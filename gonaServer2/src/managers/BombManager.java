package managers;

import java.awt.Rectangle;
import java.util.ArrayList;

import models.entities.Bomb;
import models.entities.Wall;

public class BombManager {
	
	private ArrayList<Bomb> bombList;
	int x , y;
	int posx, posy, posyC = 50;

	public BombManager() {
		bombList = new ArrayList<>();
	}

	public void generateBomb(int screenHeigth, int screenWidth) {
		for (int i = 0; i < 12; i++) {
			int increment = (int)(Math.random() * 400) + 20;
			changeValuesClosed(screenHeigth, screenWidth, increment);
			bombList.add(new Bomb(new Rectangle(posx, posyC, 20, 20)));
		}
	}

	private void changeValuesClosed(int screenHeigth, int screenWidth, int increment) {
		posx += x+increment;
		x = (int)(Math.random() * 50) + 50;
		if(posx>= screenWidth) {
			posyC += y+200;
			posx=0;
			x = 0;
		}
	}

	public void setBombList(String todo){
		bombList.clear();
		String[] rectagulo = todo.split("!");
		for (String string : rectagulo) {
			String[] coordenate = string.split("-");
			bombList.add(new Bomb(new Rectangle((int)Double.parseDouble(coordenate[0]), (int)Double.parseDouble(coordenate[1]), (int)Double.parseDouble(coordenate[2]), (int)Double.parseDouble(coordenate[3]))));
		}
	}
	
	public ArrayList<Bomb> getBombList() {
		return bombList;
	}
	
	public void deleteBomb() {
		bombList.clear();
	}
	
	public String getAllBombs(){
		String a = "";
		for (Bomb bomb : bombList) {
			a += bomb.getValues() + "!";
		}
		return a;
	}
}
