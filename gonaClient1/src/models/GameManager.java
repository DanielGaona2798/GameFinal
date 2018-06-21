package models;

import java.util.ArrayList;


public class GameManager {
	private ArrayList<Client> gameList;
	
	public GameManager() {
		
		gameList = new ArrayList<>();
	}
	
	public void addGame(Client game) {
		gameList.add(game);
	}
	
	public void deleteGame(Client game) {
		gameList.remove(game);
	}
}