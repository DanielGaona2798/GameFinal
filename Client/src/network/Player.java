package network;

import java.io.IOException;

import constants.ConstantsNetwork;
import models.Game;
import persistence.JSONFileManager;
import views.MainWindow;

public class Player extends Connection {

	private Game game;
	private MainWindow mainWindow;
	private JSONFileManager fileManager;

	public Player(int sleep, String ip, int port, String name) throws IOException{
		super(ip, port);
		game = new Game(name);
	}

	@Override
	void executeTask() {
		try {
			switch (readResponse()) {
			case ConstantsNetwork.FILE:
				readFile();
				break;
			}
		} catch (IOException e) {
		}
	}

	public String getName() {
		return game.getName();
	}

	private void readFile() {
		try {
			saveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (mainWindow != null && fileManager != null) {
			try {
				mainWindow.initPanel();
				mainWindow.setGame(fileManager.readFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// public void addEnenmy() {
	// game.addEnenmy();
	// }
	//
	// public void validate() {
	// game.validate();
	// }

	// public boolean validateLevel() {
	// return game.validateLevel();
	// }
	//
	// public void manageMovement(int action) {
	// game.manageMovement(action);
	// }
	//
	// public Game getGame() {
	// return game;
	// }
	//
	// public void startGame() {
	// game.start();
	// }

	public void setWindow(MainWindow mainWindow, JSONFileManager fileManager) {
		this.mainWindow = mainWindow;
		this.fileManager = fileManager;
		try {
			mainWindow.initPanel();
			mainWindow.setGame(fileManager.readFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Game getGame() {
		return game;
	}
}