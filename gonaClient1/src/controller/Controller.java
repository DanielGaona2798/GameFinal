package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import constants.ConstatntsUI;
import models.Client;
import views.DialogRegsitry;
import views.LobbyWindow;
import views.MainWindow;


public class Controller implements ActionListener, KeyListener{

	private Client client;
	private MainWindow mainWindow;
	private Timer timer;
	private DialogRegsitry dialogRegsitry;
	private LobbyWindow lobbyWindow;
	
	public Controller() {
		lobbyWindow = new LobbyWindow(this);
		dialogRegsitry = new DialogRegsitry(this);
		dialogRegsitry.setVisible(true);
		mainWindow = new MainWindow(this);
		timer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizeView();
			}

		});

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void actualizeView(){
		mainWindow.setView(client.getPlayers(),client.getPoint(),client.getRectangleWall());
		lobbyWindow.refreshTable(client.getPlayers());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			client.send(ConstatntsUI.MOVE + String.valueOf(e.getKeyCode())+ "#"+ client.getName());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand()))  {
		case ACCEPT:
			dialogRegsitry.setVisible(false);
			try {
				client = new Client(dialogRegsitry.getName(), dialogRegsitry.getIP(),Integer.parseInt(dialogRegsitry.getPas()), this);
				client.send(ConstatntsUI.INIT_MESSAGE+ client.getName());
			} catch (NumberFormatException | IOException e2) {
				e2.printStackTrace();
			}
			timer.start();
			lobbyWindow.setVisible(true);
			break;
		case START:
			lobbyWindow.setVisible(false);
			mainWindow.setVisible(true);
			break;
		case CLOSE:
			try {
				client.send(ConstatntsUI.CLOSE+ client.getName());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}

	}
	public static void main(String[] args) {
		new Controller();
	}

	public void finish(String string) {
		JOptionPane.showMessageDialog(null,"El ganador es: "+ string);
		mainWindow.setVisible(false);
		lobbyWindow.setVisible(true);
	}
}