package managers;

import java.util.ArrayList;

import models.entities.User;


public class GameManager {
	private ArrayList<User> gameList;
	
	public GameManager() {
		
		gameList = new ArrayList<>();
	}
	
	public void addGame(User game) {
		gameList.add(game);
	}
	
	public void deleteGame(User game) {
		gameList.remove(game);
	}
}