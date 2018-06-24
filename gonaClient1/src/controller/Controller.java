package controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import constants.ConstatntsUI;
import models.Client;
import views.MainWindow;


public class Controller implements ActionListener, KeyListener{

	private Client client;
	private MainWindow mainWindow;
	private Timer timer;
	
	public Controller() {
		try {
			client = new Client(JOptionPane.showInputDialog("Name"), JOptionPane.showInputDialog("IP"),Integer.parseInt(JOptionPane.showInputDialog("Port")), this);
			client.send(ConstatntsUI.INIT_MESSAGE+ client.getName());
		} catch (HeadlessException | NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		mainWindow = new MainWindow(this);
		timer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizeView();
			}
		});
		timer.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public void actualizeView(){
		mainWindow.setView(client.getPlayers(),client.getPoint(),client.getRectangleWall());
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
		
	}
	public static void main(String[] args) {
		new Controller();
	}
}