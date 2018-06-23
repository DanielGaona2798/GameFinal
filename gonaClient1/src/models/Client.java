package models;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import constants.ConstatntsUI;
import controller.Controller;

public class Client extends Connection{

	private Controller controller;
	private String name;
	private ArrayList<Player> players;
	
	public Client(String name, String ip, int port,Controller controller) throws IOException {
		super(ip, port);
		this.name = name;
		this.controller = controller;
		players = new ArrayList<>();
	}

	@Override
	void executeTask() {
			String[] path;
			try {
				path = readResponse().split("#");
				switch (path[0]) {
				case ConstatntsUI.SEND_ALL:
					getPLayerList(path[1]);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	public void getPLayerList(String a){
		players = new ArrayList<>();
		String[] players2 = a.split(",");
		for (int i = 0; i < players2.length; i++) {
			String string2 = players2[i];
			String[] as = string2.split("!");
			players.add(new Player(as[0], new Rectangle(Integer.parseInt(as[1]),Integer.parseInt(as[2]), 20, 20)));
		}
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	
}