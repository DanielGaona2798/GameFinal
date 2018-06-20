package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import constants.ConstantsNetwork;
import constants.ConstantsUI;
import network.Player;
import persistence.JSONFileManager;
import views.MainWindow;

public class Controller implements KeyListener{
	private Player player;
	private MainWindow mainWindow;
	private JSONFileManager fileManager;

	public Controller() {
		mainWindow = new MainWindow(this);
		fileManager = new JSONFileManager();
		try {
			player = new Player(200, mainWindow.getIp() , mainWindow.getPort(), mainWindow.getNamePlayer());
			fileManager.writeFile(player.getGame());
			player.send(ConstantsNetwork.GAME);
			player.sendFile(new File(ConstantsUI.PATH_SEND));
			player.setWindow(mainWindow, fileManager);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		try {
			System.out.println("mueveeeee");
			player.send(ConstantsNetwork.MOVEMENT);
			player.send(player.getName());
			player.sendInt(arg0.getKeyCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}