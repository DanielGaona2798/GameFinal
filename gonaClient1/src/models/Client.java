package models;

import java.io.IOException;
import java.util.ArrayList;

import controller.Controller;
import views.ChatWindow;

public class Client extends Connection{

	private int id;
	private String name;
	private int pasword;
	private int state;
	private Controller controller;
	private int lives;
	private int points;
	
	public Client(String name, int pasword, String ip, int port,Controller controller) throws IOException {
		super(ip, port);
		this.name = name;
		this.setPasword(pasword);
		this.controller = controller;
		this.lives = 3;
		this.points = 0;
	}

	@Override
	void executeTask() {
		try {
			String[] path = readResponse().split("#");
			switch (path[0]) {
			case "/registro":
				break;
			case "/segunda":
				break;
			case "/connect":
				if (path[1].equals("1")) {
					controller.showChatWindow();
				} else {
					ChatWindow.impMesagge();
				}
				state = Integer.parseInt(path[1]);
				break;
			case "/tengaLista":
				putListClient(path);
				break;
			case "/send":
				break;
			case "/deseaJugar":
				send("/acceptacion#" + ChatWindow.showConfirmDialog(path[1] + " desea jugar contigo.") + "#" + path[1] + "#" + controller.getheithWindow() + "#" + controller.getWithWindow());
				break;
			case "/paila":
				ChatWindow.showMsg("paila mijo no quieren jugar con tigo");
				break;
			case "/generateWall":
				controller.setWallList(path[1]);
				break;
			case "/generateBomb":
				controller.setBombList(path[1]);
				controller.showPanel();
				break;
			case "/setPointFriend":
				controller.updatePonint(path[1]);
				break;
			case "/updateFiury":
				controller.updateFiury(path[1]);
				controller.updateLantern(path[2]);
				break;
			}
		} catch (IOException e) {
		}
	}

	private void putListClient(String[] path) {
		ArrayList<String> users = new ArrayList<>();
		for (int i = 1; i < path.length; i++) {
			users.add(path[i]);
		}
		controller.putListClient(users);
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public int getPasword() {
		return pasword;
	}


	public void setPasword(int pasword) {
		this.pasword = pasword;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}
	
	
	public int getLives() {
		return lives;
	}


	public int getPoints() {
		return points;
	}

	public void resetDatasLiveAndPoints(){
		this.lives = 3;
		this.points = 0;
	}
}